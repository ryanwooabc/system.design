# Design Distributed ID Generator
- https://courses.systeminterview.com/courses/system-design-interview-an-insider-s-guide

## Functional Requirement
- sequencial ID on local machine
- unique ID in global

## Non-functional Requirement
- high reliability 
- high availability
- high scalability
- low latency

## Capacity Estimation

## System API

## Database Design

## High-level Design

## Detail Design
- Twitter Snowflake Approach
- sign 1 bit + timestamp 41 bits + datacenter 5 bits + machine 5 bits + seq 12 bits
