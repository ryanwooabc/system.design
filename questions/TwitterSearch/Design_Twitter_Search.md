# 0000-system-twitter-search.md


## Functional Requirement
- update/search social status
- search words with AND/OR

## Non-functional Requirement

## Capacity Estimatioin
- 1.5B users, 800M DAU, 400M new tweet / day
- search 500M times / day
- Write: 400M * 280 bytes / 24 / 3600 = 1.2 MB / s
- Storage: 400M * 280 bytes * 365 * 5 = 120GB * 2K = 240 TB
- Machines: 240 TB * 2 = 4TB * 120 servers

## System API
- String search(token, query, count, sort, pageToken)
	- token: auth and rate limiting
	- TODO: pageToken
	- return JSON, [{tweetId, text, creator, timestamp, likes}]
	
## Database Design

## High-level Design
-                              +-> UserDB
- Client ---> LB ---> AppSvrN ---> AggregatorN ---> TweetDBN
-                                     |               |
-                                IndexServerN <--- IndexBuilderN
                      
## Detail Design

### TweetID
- 400M * 365 * 5 = 800 B = 2 ^ (31 +9) -> 5 bytes
- Consistent Hash, Sharding

### Indexing
- 300K words, 200K nouns
- 300 B tweets for the last 2 years
- TweetID: 300B * 5 bytes = 1.5 TB
- Distributed KV Store: <Word, List<TweetID>>
- 40 words but only 15 except "the", "an", "and" etc
- Memory: 1.5T * 15 = 22 TB
- Machines: 22 TB = 128 GB * 172 servers

## Sharding
- by words, consistent hash, but hot word not handled
- by tweet id/object, data/load distributed evenly

## Fault Tolerance / Replicates
- Recover IndexServer with <IndexServerID, Set<TweetID>>

## Cache
- between aggreator and TweetDB
- LRU

## LoadBalancer
- between client and application servers
- between application and aggregator
- Round Robin to intellgent LB

## Ranking
- by social graph distance, popularity, relevance


## Terminology
- popularity