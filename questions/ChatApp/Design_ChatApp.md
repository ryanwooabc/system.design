# Design ChatApp
- https://systeminterview.com/design-a-chat-system.php
- https://www.educative.io/courses/grokking-the-system-design-interview/B8R22v0wqJo
- https://www.algoexpert.io/systems/workspace/design-slack
- Weak Hire: service, database, storage, tables
- Hire: web socket, sharding, push notification
- Strong Hire: group chat, online status update

## Functional Requirement
- 1-on-1 chat, and group chat, text message
- user online status, lastSeen time
- message status: sent, delivered & read
- message sequence and persistence
- notification
- TODO: send image, voice and video
- TODO: ID-Generator, GraphDB
- TODO: Security

## Non-functional Requirement
- high reliability, message not lost
- high availability, 99.999%, 3600*24*365*0.001% = 5 min
- high scalability, 100M DAU
- low latency, real-time experience

## Capacity Estimation
- 100M DAU * 100 messages * 100 bytes = 1TB / day
- write: 100M * 100 / 10^5 = 100K QPS
- storage: 1TB * 2000 = 2PB / 5 years
- bandwidth: 100K QPS * 100 bytes = 10MBps
- memory: 1M concurrent * 10K / connection = 10GB
- connection: 100M / 500K = 200 chat servers

## Database Design
- users(id, name, password)
- groups(id, name, createUserId)
- friends(fromId, toType, toId, lastChatTime)
- messages(id, fromId, toType, toId, content, timestamp)

## System API
- boolean sendMessage(fromId, toType, toId, content)
- List<Entity> getFriends(fromId)
- List<Message> getMessages(fromId, lastMessageId)

## High-level Design
![This is an image](Design_ChatApp_Ryan_v3.png)

## Detail Design
### Data Flow
- 00 user logins app
- 10 user client finds an available web socket server S1 with discovery service
- 11 users send heartbeat to web socket server, and status service stores status
- 21 user1 send a message to user2
- 22 web socket server S1 will return "message sent" to user1
- 23 web socket server S1 checks status DB and forwards the message to S2 if user2 is online
- 24 user2 returns "message delivered" to S2, S1 and user1
- 25 user2 returns "message read" to S2, S1, and user1 if the thread is open
- 26 web socket server S1 always sends message to MQ
- 30 message service will persist message to message DB
- 31 notification service service will check user2's status and notify user2 if user2 is offline
- 40 user2 can get friend list from GraphDB
- 41 user2 can read all of messages with message service if back online
  
### Scalability
- sharding web socket server by user ID
  
  
