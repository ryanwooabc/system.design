# Design Newsfeed

## Functional Requirement
- 
- 

## Non-functional Requirement
- low latency: load newsfeed in 2 seconds
- high reliability/availability
- high scalability: 10M DAU
- high consistency: across multiple devices for one user

## Capacity Estimation
- read: 10M DAU * 100 / day = 10K QPS
- write: 100 QPS when w/r = 1/100
- storage: 5MB / post * 10M DAU * 365d * 4y= 50TB * 2k = 100PB
- 
## System API

## Database Design

## High-level Design

## Detail Design

## Notes
