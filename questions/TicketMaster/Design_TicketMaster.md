# Design TicketMaster

# 0000-system-ticket-master

## Functional Requirement
- list cities -> movies -> theaters -> shows
- the system shows seating arrangement
- a user can select multiple seats
- seat status: avaiable, hold, booked
- a user can hold seats for 5 minutes before payment
- a user can wait for seat becoming hold to available
- waiting users can get released seats with FIFO

## Non-functional Requirement
- high reliablity/availability/sacalibity
- low latency
- high consitency and security
- TODO: auth, limit 10 tickets for each IP
- TODO: seat number

## Capacity Estimatioin
- 3B page views / month, 10M tickets / month
- 500 cities * 10 theaters * 10 rooms * 200 seats * 5 shows * 100 bytes = 5GB / day
- Storage 5GB * 365 * 5 = 10 TB

## System API
- JSON search(token, query, city, lat, lon, radius, start, end, count, pageToken)
	- token: auth, rate limiting
	- return [{showId, title, start, end, cityId, movieId, theaterId, seats: [{id, type, status, price}]}
- boolean book(token, showId, seatIDs)
	
## Database Design
- cities(id, name, state, lat, lon)
- theaters(id, name, cityId)
- rooms(id, name, theaterId)
- seats(id, no, type, roomId)
- movies(id, title, desc, duration, lang, released, country, genre)
- shows(id, movieId, roomId, date, start, end)
- users(id, lastName, firstName, email, phone, pass)
- bookings(id, userId, showId, time, seats)
- show_seats(id, showId, seatId, price, status, bookingID)
- payments(id, amount, transactionId, timestamp, status)

## High-level Design
- Client ---> LB ---> WebServer ---> AppServer -+-> Database
-                                               +-> Cache

- WebServer: auth and session
- AppSvr: store and read data

## Detail Design

### Booking Workflow
- select city, search and choose movie
- list theaters, select a show, list seats
- select seats, make a bookingId
- back to seat page if booking failed
- wait at seat page if some seats hold
- pay in 5 minutes if booking successful

### ActiveBookingService
- Keep <ShowID, LinkedHashMap<BookingID, Timestamp>> in memory
- drop bookings if expired or payed

### WaitingUserService
- Keep <ShowID, LinkedHashMap<UserID, Timestamp>> in memory

## Concurrency
- begin transaction
- select seatId from show_seats where showId = 1 and seatId in (2,3,4) and status = 0;
- update show_seats;
- update bookings;
- commit transation;

## Fault Tolerance
- primary-second: ActiveBookingService, WaitingUserService, database

## Sharding
- by MovieID, Cons: hot movies
- by ShowID
- ConsistentHash: ActiveBookingService, WaitingUserService

## Teminology
- affiliate
- traffic spike
- keep up with the surge in traffic
- genre
- imdeponent

