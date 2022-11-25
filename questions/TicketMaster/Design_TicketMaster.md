# Design TicketMaster

## Functional Requirement
- list cities / movies / cinemas / shows / rooms / seats
- select seats, hold and pay
- release seats if not paid
- FIFO for released seats

## Non-functional Requirement
- high reliability / availability / scalability
- low latency
- high consistency, ACID

## Capacity Estimation
- 100 Cities * 100 Cinemas * 20 Room * 10 Shows * 100 Seats = 200M DAU
- QPS: 200M / 10^5 = 2K writes, 80K reads / s
- Storage: 200M * 2K * 100bytes = 20TB / 5y

## System API
- List<Movie> searchMovie(apiKey, keyword, city, lat, lon, radius, startTime, endTime, pageToken, pageSize)
- List<Room>
- 

## Database Design

## High-level Design

## Detail Design