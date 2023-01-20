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
