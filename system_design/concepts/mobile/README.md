# Mobile System Design Concepts

## Communication between client and server

### Stateless communication b/w client and server (REST)
Stateless communication - Every communication between the client and the server is like a new one. No information (state) is held from a previous 
communication or to a follow-up communication in future

### Bidirectional communication using web sockets
Both client and server can get data from each other. Needs persistent connection. More complicated to implement than stateless communication

### Unidirectional communication using Server-Sent Events (SSE)
Allows the client to stream events over an HTTP/1.1 connection without polling. Communication is carried out from server to client, client cannot send data
to server
#### Pros:
* real-time traffic using a single connection.
#### Cons:
* keeps a persistent connection.

#### Why use SSE over Web sockets?
SSEs are sent over traditional HTTP. That means they do not require a special protocol or server implementation to get working. WebSockets on the other hand, require full-duplex connections and new Web Socket servers to handle the protocol. In addition, Server-Sent Events have a variety of features that WebSockets lack by design such as automatic reconnection, event IDs, and the ability to send arbitrary events.

#### Resources to read more about SSE
* [Article on how to implement SSE server client communication in android](https://proandroiddev.com/unidirectional-server-client-communication-using-sse-in-android-79b825aa0670)
