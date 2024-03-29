# Chat Application
[Reference](https://github.com/weeeBox/mobile-system-design/blob/master/exercises/chat-app.md)

## Requirement Gathering

### Functional Requirements
- User should be able to see list of his recent chats
- User should be able to open 1-on-1 chat and send and receive messages
- Image attachments to messages
- User should be able to see the status of the messages (sending/sent/failed) and read receipts

### Non-functional requirements
- Offline support (reading messages)
- Secure message storage
- Real-time notifications

### Out of scope
- Video and voice messages
- Sign-up and log-in
- Delete/Edit message
- Group chats

## HLD
![chat-app flow](../images/chat-app.svg)

## Components

### Server-side components
- **Backend** - Represents the whole backend infrastructure, responsible for responding to request from clients.
- **Push-provider** - Represents the mobile push infrastructure, receives push payload from backend and delivers it to the clients.
- **CDN** - Responsible for delivering static content to clients

### Client-side components
- **API Service** - Abstracts the client-server communication from the rest of the system
- **Persistence** - The single source of truth, the data received by the system gets persisted on the disk and propagated to other components.
- **Repo** - A mediator between API service and persistence. Receives requests from other components to get/post data, requests the data from persistence and provides to other components or requests/posts the data from/to API service, saves the data receieved from API service to persistence and then propagates it to other components
- **Chat Lobby** - Represents a set of components responsible for displaying a list of recent chats
- **Chat Room** - Represents a set of components responsible for displaying a single chat
- **DI** - Responsible for providing dependencies to components
- **Image Loader** - Responsible for loading images from web/disk into UI elements
- **Attachment Provider** - Responsible for loading attachments from gallery and camera into memory
- **Navigation** - Responsible for coordinating flow logic between chat lobby and chat room. Helps decouple components of the system from each other
- **App Module** - Responsible for gluing the system together 

## Deep Dive

### API Service
- Abstraction of newtwork communication layer. Idea is to isolate low-level transport primitives from the rest of the app to promote modularity and testability.
- The communication between the client and the server can be split up into 3 major categories

#### Bi-directional communication layer
- This is required for real-time communication between two clients. Each client will establish bi-directional connection with the server for sending/receiving messages and status.
- We need to decide between TCP (connection-based) and UDP (connectionless) protocols
    - TCP-based clients establish virtual connection and guarantee order delivery of messages by retransmitting lost packets. Because of this they are more expensive in terms of battery life (especially on flaky networks where connection has to be established multiple times). Another disadvantage is the 64k limit on connection count for host ports and bigger packet header size as compared to UDP. Some examples of TCP-based protocols are Web Sockets (Slack), XMPP (WhatsApp, Zoom, Google Talk) and MQTT (IoT, Smart Home)
    - UDP-based clients are lightweight and don't require any handshakes, but they don't guarantee ordered delivery of messages and has no error checking beyond simple checksums. Some examples of UDP-based protocols are WebRTC (Discord, Google Hangouts, Facebook Messenger)
- Given I don't have much knowledge of these options, best choice would be to do a quick PoCs around a few of them and pick whichever seems most appropriate. For now we can think of going ahead with WebSockets, the disadvantage of this would be the protocol is schemeless and does not provide automatic reconnection as well as some security flaws. Alternatively we can try HTTP-polling but it would generate an excessive amount of backend traffic. There is also gRPC (bi-directional streaming) or graphQL (subscriptions) as an option but I don't have much experience on these yet
- We will need web-socket to send and receive real-time chat events, for everything else we can use HTTP-based protocol. A typical event can look something like this
```
{
    connectionId: String,
    eventType: "HELLO|MSG_IN|MSG_OUT|MSG_READ|BYE"
    payload: {...}
}
```
Params:
- connectionId - For server to differentiate between clients
- eventType - To identify the event and process the payload. Different event types are:
    - **HELLO** - To establish a connection (bi-directional)
    - **MSG_IN** - Incoming message
    - **MSG_OUT** - Outgoing message
    - **MSG_READ** - Read receipt (bi-directional)
    - **BYE** - To close a connection
- Will contain the message and/or its payload


- HELLO and MSG_READ messages will be bi-directional implying they can be sent by both the client and server. HELLO will be sent by client to establish connection and server can reply back with a hello as an acknoledgement. MSG_READ a client will send as an acknowledgement for message read and server will forward this to the other client part of the chat room.
- When the app goes in background we don't need to keep the socket connection alive, we can use push notifications to let the client know of new messages and bring the app back in foreground

#### HTTP-based layer
- Request-response layer for getting paginated list of chats or message history
- REST protocol could be a good choice since we don't need request customisation to think of GraphQL which will also increase backend complexity
- Potential endpoints
    - ``GET /login`` - initiates a new client session and returns a JSON Web Token which will be used to authorizing further requests
    - ``GET /chats?before_id=<X>&limit=<Y>`` - receives a paginated list of chats
    - ``GET /chats/<chat_id>/messages?before_id=<X>&limit=<Y>`` - receives a paginated list of messages from a specific chat
- JWT token is needed for authentication. Every request after login should include Authorization header: ``Authorization: Bearer <token>``
- ``before_id`` used while getting chats or messages is for cursor based pagination, it contains the id of the chat/message before which ``limit`` no. of chat/messages are to be fetched. We can also used keyset based pagination based on timestamp of the last message, but it might not be reliable as we cannot trust a clients device clock.
- Some of the HTTP response codes we can discuss are:
    - ``401 Unauthorized`` - the client auth token is missing or expired or incorrect. A login request must be made to get a new auth token to proceed further
    - ``Unprocessable Entity`` - the client request data in malformed and should not be retried
    - ``429 Too Many Requests`` - the client reached the rate-limiting threshold
    - ``500 Internal Server Error`` - the client should use exponential back-off to retry the failed request

#### Cloud messaging layer
- A typical push payload will look something like
```
{
    user_id: String,
    messages: [
        {
            user_name: String,
            text: String,
            created_at: long
        },
        ....
    ]
}
```

- We need both user_id and user_name because user_id will be used to verify the receiver is the same user who is intended to recieve the message, there is a chance that a new user has logged in on the same device, we won't want to show him the messages of the previous user, user_name will be used with text which will contain the first 100-120 characters of the message to display in the notification in system tray
    
#### API Service Diagram
![API Service Diagram](../images/chat-app-api-service-diagram.png)

- Having separate interfaces (protocols) between network layer and business logic layer: ChatLobbyService (receive a list of chats, create/delete chats) and ChatRoomService (receive message history, send/receive messages, download/upload attachments) allows us to decouple different modules of the app, for e.g. the Chat Lobby flow can recieve the ChatLobbyService via DI and use the exposed APIs via interface without having to know the underlying network module implementation. It also simplifies development/testing and developers can swap real implementation with fake/mock service and provide data from disk or memory
- We can also add a model convertor layer to transform network layer data models into business layer model objects, this helps us keep the business layer not be affected by changes in network layer model contracts
e.g. consider the api sends you response in following format


```
ChatMessageData:
+ id: String
+ user_id: String
+ text: String
+ status: String
+ created_at: Long
+ attachments: String // comma-separated list
```

You can transform into business logic layer which will be more contextual


```
ChatMessage
+ id: String
+ userId: String
+ text: String
+ status: ChatMessageStatus
+ createdAt: Date
+ attachments: [Attachments]
```

### Data Model
- We would use relational database (ORM) to store chats, messages, users and attachment

```
Chat
+ id: String (Primary Key)
+ last_message_user_id: String (Foreign Key)
+ last_message_id: String (Foreign Key)
```

- The chat table consists of chat id, id of the last message in the chat and the id of the user who sent the last message in the chat

```
User
+ id: String (Primary Key)
+ name: String
+ profile_pic_url: String
```

- The user table consists of user's id, his name to be displayed and url of his profile pic

```
Message
+ id: String (Primary Key)
+ chat_id: String (Foreign Key)
+ user_id: String (Foreign Key)
+ created_at: Long
+ text: String
+ status: String
+ attachment: String
```

- Message table consists of messages from all chats. We would store message id, id of the chat of which the message is part of, id of the user who created the message, time at which the message was created, message text, status of the message can be `PENDING`, `SENT`, `READ`, `FAILED` and comma-separated list of attachment ids

```
Attachment
+ id: String
+ url: String
+ thumb_url: String
+ path: String
+ thumb_path: String
+ status: String
+ total_size: Long
+ progress_size: Long
```

- The attachment table will consist of all the attachments of all the messages from all the chats. We would store the id of the attachment, its urls (original and thumb), path where the attachment is downloaded and stored (path and thumb_path), status can be `UPLOADING`, `DOWNLOADING`, `FAILED`, `READY`, total file size and progress file size (uploaded/downloaded bytes)
- We can join the chat table with message table on `last_message_id` and User table on `last_user_id` to get a list of recent chats. Each result could be represented by the following model class

```
ChatInfo
+ chatId: String
+ lastUserName: String
+ lastUserId: String
+ lastMessageText: String
+ lastMessageTimestamp: Long
```

- We can get a list of messages for a specific chat by selecting on `chat_id` column
- We can use client-generated 128-bit UUIDs as ids for messages and attachments. Outgoing messages can be identified by `user_id` and outgoing attachments by empty urls. Once an attachments is uploaded it is indistinguishable from a remote attachment
- The advantage of such an approach is its simplicity and idempotency, the disadvantage is given the ids are generated on client side they are less reliable and less secure compared to backend generation
- Alternatively we can maintain server and local ids. All local operations will be done using local id and backend operations using server id. We would also need to build a bijection (one-to-one mapping) between the two

- For attachments we can keep a single table for uploads and downloads, this will simplify the design since we will only have to join a single table and all information related to attachments will be in one place. We can differentiate between uploads and downloads by the value of the url column, the rows which have a url but no local path are to be downloaded and the ones which have local path but no url are to be uploaded, once an attachment is downloaded/uploaded they will be indistinguishable from each other and if we do want to distinguish them we can do so on the basis of user id who created the attachment by making a join on message, user and attachment table
- We can have 3-4 statuses for attachment table namely `READY`, `UPLOAD`, `DOWNLOAD`, `FAILURE`. A `READY` status indicates the attachment has been processed (uploaded/downloaded)
- We are storing the total size of the attachment and the progress size of the attachment in order to support resumable downloads (todo. read about this)
- The Api Service via HTTP client will be responsible for downloading/uploading attachments. We would need a task dispatcher to limit the no. of concurrent operations
- We can automatically download attachments on WiFi and ask for user input on cellular network. We can also provide option in settings to change this configuration for better user experience

- For timestamp we will use UTC timestamps (epoch) and convert them to local timestamps on client side.
- For outgoing messages we will use local timestamp and for incoming messages we will use server timestamp. The local timestamp for outgoing messages will make sense since that is the time when the client created the message and server timestamp for incoming messages since that will be time when the message was actually sent to the server (might be delayed due to client being offline, network issue, or the client having wrong time)

### Security and Privacy
- We will be storing the messages on users device for offline browsing. Additionally we can also store the messages on server but it can make the user feel less secured. Even if we were to implement end-to-end encryption user might still feel insecure as the developer will still have access to the encryption key and an attacker who has access to server can still compromise user privacy. The **user perception** of privacy is as important as privacy implementation itself. 
- So instead what we can do is hold the messages on server in a queue while they are yet to be delivered to the correct users and once delivered we can delete them from server
- We can additionally give user the option to backup his chats on google drive or somewhere he trusts and can use as backup if needed in future

### Conclusion
- There are a lot of topics that can be covered in this problem statement. Don't try to go deep into detail of something unless asked explicitly to
- Cover as much ground as possible
- Listen to the interviewer and keep track of time
