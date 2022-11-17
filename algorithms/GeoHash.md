# Geo Algorithm

## Requirement
Store geo location for driver, restaurant, passenger
Search
Used by system

## Scenario
Uber, Yelp, Uber Eats
Google Map, Home Insurance
Redfin, Zillow, Hotel.com
Meetup, TikTok

## Database Schema
places(long id,  string name, double lat, double lon)

## API Design
List<Integer> findNearby(double lat, double lon, double radius, int extraFilters);

## SQL
Input: (x, y, d)
select * from places where abs(lat - x) <= d && abs (lon - y) <= d;
select * from places where lat >= x - d && lat <= x + d && lon >= y - d && lon >= y + d;
Pros: simple algorithm, easy to implement
Cons: scan the whole table, difficult to index lon/lat, two 1M-size-sets join
TODO-Rayn: check why no index

## Update
Shard by placeId#createdTime, shard 2M drivers to 2000 servers
TODO-DB-sharding

## Grid
- divide map to grids
- associate grids to cities
- select gridId from city_grids where cityId = 1;
- select * from places where abs(lat - x) <= d and abs (lon - y) <= d and gridId in $GridIds;
- Pros: 1K-size-set join, fast DB query
- Cons: LA, LV, different TODO, waste disk

## Quadtree / Dynamic Grid
- structure: location tree
- build: from root node, split if more than 500 locations
- search: from root node, and repeat if not found
- neighbor: parent pointer
- Pros: save disk/memory
- Cons: Shard, different root node for different servers

## GeoHash
- geographic location encoding, (lat, lon) -> string
- (38.123456, 72,543211) -> w50zy71zu
-                                             w50zy71zv
- base32, [0-9a-z] except ailo
- len(base32)=8, precision=19m, len(base32)=9, precision=2m
- sqrt(32)
- 0   2
-  | /  |
- 1   3
- Pros: fast query with string index
- Cons: complicated to implement
- TODO: update location, with cache, sync to DB, audit logs
- TODO-query: load DB for large map range

TODO-Ryan: special handle when 8 is far from 7 than k
https://upload.wikimedia.org/wikipedia/commons/thumb/c/c2/Geohash-OddEvenDigits.png/680px-Geohash-OddEvenDigits.png


## Refs
- https://en.wikipedia.org/wiki/Geohash
- https://blog.51cto.com/bigdata/2871320
- https://www.educative.io/courses/grokking-the-system-design-interview/B8rpM8E16LQ
- https://en.wikipedia.org/wiki/Quadtree
- https://halfrost.com/go_spatial_search/



