# Design Uber

## Read
- https://www.educative.io/courses/grokking-modern-system-design-interview-for-engineers-managers/qA6OYXKpOn0

## Functional Requirement
- update driver location
- find nearby drivers
- request a ride
- manage payment
- estimate arrival time
- confirm pickup
- show trip update
- end the trip

## Non-functional Requirement
- high reliability/availability/scalability
- low latency: update location every second
- strong consistency for location, order and payment
- fraud detection

## Capacity Estimatioin
- Totally 500M riders, 5M drivers
- DAU: 20M riders, 3M drivers, 20M rides
- 1H per ride, update location every second
- QPS: 20M / 24 ~= 1M reads/writes
- QPS: 20M * 3600 / 24 / 3600
- Rider Store: 500M * 1K = 500GB
- Location Store: 100Byte * 1M * 24 * 3600 * 365 * 5 = 100Byte * 1M * 10^5 * 2K = 20PB
- Bandwidth: 1M * 100Bps = 800 Mbps
- Server: 1M / 10K = 100

## Infrastructure
- DB, Cache, CDN, LB

## System API

## Database Design
- passengers(id, lastName, firstName, gender, birthDate, phone, email)
- drivers(id, lastName, firstName, gender, birthDate, maker, model, color, plate, phone, email)

## High-level Design

- Passenger ---> LB ---> Aggragator
-            \               |
-             PN         QuadTreeServer  <--- QuadTreeIndex ---> LocationDB
-           /      \         |
- Driver -----> LB --> LocationServer ---> SSD

## Detail Design

### QuadTree Update
- Keep<DriverID, [LastLocation, LatestLocation]> in memory
- reflect driver's current location every 15 seconds
- Memory: (4 bytes + lastLat 8 + lastLon + newLat + newLon) * 1M = 36 MB
- replicates

### Driver's Location
- Push: dedicated notification service, Long Polling or WebSocket
- pub/sub model, subscribe when opening app
- broadcast when DriverLocHashTable changed
- one driver has 5 subscribers
- Memory: DriverID 4 bytes * 500K + PassengerID 8 bytes * 500K * 3 = 21 MB
- Outbound: (DriverID 4 bytes + Lat 8 + Lon 8) * 500K * 5 = 50 MBps
- TODO: Pull when adding new drivers for existing subscribers

### Request Ride
- a passenger sends the request
- a aggragator requests nearby drivers from QuadTree servers
- the aggregator collect all of the result and sort by the rating
- the aggregator will send ride request to top 3 drivers
- one drivers will accept the request and send acknowledgement
- the aggregator will send cancelation to other drivers
- the aggregator will notify the passenger

## Availability
- primary-second: location server, notification server

## Ranking
- drivers(id, ..., trips, total)

## Enhancement
- client is slow or disconnected
- disconnected during ride
- billing handling
- pull vs push

## Terminology
- passenger
- regularly
- propagate,传播，宣传，繁殖
- cushion

## Refs
- https://www.educative.io/courses/grokking-modern-system-design-interview-for-engineers-managers/g7QjDQ566lD
- [Database] How we store and process millions of orders daily:
- https://engineering.grab.com/how-we-store-millions-orders
- [Matching] Using real-world patterns to improve matching in theory and practice
- https://engineering.grab.com/using-real-world-patterns-to-improve-matching
- [Retry] Designing Resilient Systems Beyond Retries (Part 1): Rate-Limiting:
- https://engineering.grab.com/beyond-retries-part-1
- Designing Resilient Systems Beyond Retries (Part 2): Bulkheading, Load Balancing, and Fallbacks
- https://engineering.grab.com/beyond-retries-part-2
- Designing Resilient Systems Beyond Retries (Part 3): Architecture Patterns and Chaos Engineering
- https://engineering.grab.com/beyond-retries-part-3
- [Event processing] Trident - Real-time Event Processing at Scale
- https://engineering.grab.com/trident-real-time-event-processing-at-scale
- [Algorithm]: Using Client-Side Map Data to Improve Real-Time Positioning:
- https://eng.lyft.com/using-client-side-map-data-to-improve-real-time-positioning-a382585ac6e
- How does Uber scale to millions of concurrent requests?
- https://www.youtube.com/watch?v=DY2AR8Wzg3Y