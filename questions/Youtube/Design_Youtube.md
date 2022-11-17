# Design Youtube

## Functional Requirements
- upload videos
- search videos with title
- view and share videos
- video views, likes and dislikes
- add and view comments

## Non-functional Requirements
- high reliability, no data lost
- high scalability, handle 100M DAU
- high avalability, 99%
- low latency, better real time

## Capacity Estimation
- 1B DAU * 10 video / day
- read: 10^9*10/10^5 = 100K QPS
- read / write = 100:1
- write: 1K QPS, upload 1K / s
- storage: 100MB * 1K * 10^5 = 10PB / day
- bandwidth: 100K * 100MB = 10TB / s

## Database Design
- users(id, name, email, password)
- videos(id, title, url, views, likes, dislikes)
- comments(id, user_id, video_id, content, timestamp)

## System API
- boolean upload(token, title, content)
- List<Video> search(token, title)
- Data watch(token, video_id, offset, codec)

## Storages
- RDBMS: UserDB, MetadataDB
- Blob: Video, Thumbnail
- CDN: hot videos, geographically distributed

## Video Processing
- breakdown into chunks
- decode and encode
- create thumbnail and preview
- video understand with ML
- all parallelized
  
## TODO
- sharding

