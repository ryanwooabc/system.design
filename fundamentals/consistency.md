# CH5 Replica

## Requirements
- Latency, TODO-Georgraphical: Local
- Availability
- Scalability

## Single Leader
- Leader: Active, Write/Read
- Follower: Passive, Read
- Replicatioin Log, Change Stream, Sequenced WAL
- Follower: Hot(Read)/Warm(Backup)/Cold(Archive)
- Writer->Leader->Follower<-Reader

## Sync vs ASync
- Sync
- Writer-1->Leader-2->FollowerN
- Writer<-4-Leader<-3-/
- High Consitentency, High Write Latency 
- Semi-synchronous: One sync replica + other aync

- ASync
- Writer-1->Leader-3->FollowN
- Writer<-2-/
- TODO-Complete-ASync, not durable

## Add Follower
- Snapshot + Log Sequence Number / Binlog Coordinnates
- Catchup Recovery
- Failover, Manual or Consensus
- L1: a=3, a=5, F2: a=3
- Solution1:    L2: a=3, not durable
- TODO-CH8-Split-Brain

## Log Replication
- 1, statement-based
- not support now()/rand()
- not support multiple concurrent transactions with auto increment
- TODO-trigger-store-procedure
- 2, WAL, PostgreSQL/Oracle
- Leader/Follower write WAL before replication
- TODO-disk-byte-dependency-upgrade
- 3, Line-based Logic Log Replication, MySQL
- add/update with all columns, delete with primary key
- decouple with store engine, uggrad, backward compatibility, application friendly
- 4. Trigger-based replication
- subset, logic conflict, application
- trigger and call application, high cost, not stable but flexible
- Databus for Oracle, Bucardo for PostgreSQL
- 5. Database log
- Oracle Golden Gate

## TODO: https://vonng.github.io/ddia/#/ch5?id=%e5%a4%8d%e5%88%b6%e5%bb%b6%e8%bf%9f%e9%97%ae%e9%a2%98

## Replication Lag
- availability, scalability & low latency
- write to one leader, and read from multiple followers, partial sync
- eventually consistency: all leader and followers will be the same after no write for a while
- RDBMS async follower also have it

## Read after Write Consistency
- Issue: found data missing after write
- Cause: write to leader and read from follower before async replication
- Solution: read-after-write = read-your-writes consistency
- Option 1: read modified-possible content from leader, own profile in social network
- Option 2.1: update last modified time in Redis, read from leader if last modification happened in last minute
- Option 2.2: monitor replication lag, TODO
- Option 3: read last modified time, and compare with follower replication time, forward to another follower if newer
- Option 4: route leader service to the local datacenter
- Option 5: route all requests of a user to the same datacenter, record last modified time in this datacenter
