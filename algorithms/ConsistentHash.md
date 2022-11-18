# Consistent Hash

## Requirement
Too many writes on single DB instance
Solution: sharding by some keys, twitter (userId + createdAt) for balance
4 servers A-D, 12 users 1-12, shard by mod userId, A: 1-3, B: 4-6, C: 7-9, D: 10-12
If A is down,  sharding is changed to B: 1-4, C: 5-8, D: 9 - 12
Too many data will be transferred between servers
Auto Scaling: add/remove servers, have to use CH
Persist Connection for too many reads
Replication: A1/A2, B1/B2, C1/C2, D1/D2, no need CH if server’s down

## API
void init()
void addServer(int serverId)
void deleteServer(int serverId)
int getServer(int key % 360) -> serverId

## Implementation

### init()
PriorityQueue<int[]> pq; 	// [start, end, serverId], max heap of range

### addServer(int serverId)
pq.add(new int[] { 0, 359, 1 });		// ring

### addServer(int newServerId)
int[] top = pq.poll();
Int start = top[0], end = top[1], mid = (start + end) / 2, oldServerId = top[2];
pq.add(new int[] { start, mid, oldServerId };
pq.add(new int[] { mid + 1, end, newServerId };
Update Map<Degree, ServerId>
Update Map<ServerId, int[] range>

### deleteServer(int serverId)
A: [0, 179], B: [180, 269], C: [270, 359]
When B is down, change to A: [0, 179], C: [180, 359]

### getServer(int key % 360)
for (int[] server : pq) {
   if (server[0] <= key && key <= server[1]) {
       return server[2];
   }
}
Or return from Map<Degree, ServerId> cache
TODO: server count more than 360, change M to a big number like 2^31

### TODO-Consistent-Hash-Virtual-Node


