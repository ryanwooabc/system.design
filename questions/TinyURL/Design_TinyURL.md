# Design_TinyURL
- https://www.educative.io/courses/grokking-the-system-design-interview/m2ygV4E81AR
- https://systeminterview.com/unlock.php
- https://www.youtube.com/watch?v=He-V_RuHwek

## Steps
- functional/non-functional requirements, 5 min
- capacity estimation, 5 min
- high level and data flow, 5 min
- database design, 5 min
- System API, 5 min
- detail and trade off, 10 min
- optimization and monitor, 3 min
- summary, 2 min
- ref: https://jishuin.proginn.com/p/763bfbd6a6d3

## Estimate
- 1, K, M, G, T, P
- 1, 3, 6, 9, 12, 15
- 1 day = 3600 * 24 ~= 10^5 seconds
- 5 years = 365 * 4 ~= 2*10^3 days

## Functional Requirement
- twitter message has a length limit
- input a URL and get a short URL
- visit the short URL and redirect to the original URL
- short URL must be random
- short URL must be unique
- short URL must be human-readable
- all URLs will expire if inactive
- stored for 5 years 
- recaptcha to avoid heavy use

## Non-functional Requirement
- high reliability, no data lost/incorrect
- high availability, 99.99% 
- high scalability, 100M visits / day
- low latency, reponse time <= 100ms

## Capacity Estimation
- 100M URLs / day
- Write QPS: 100M / 24 / 3600 = 1K
- Write/Write=1/10, Read 10K QPS
- Capacity: 100M * 365 days * 5 years = 200B
- Storage: 100 bytes * 200B ~= 20TB

## System API
- JSON shortURL(String originalURL); 
- return { success: true, longURL: xxx, error: xxx }
- JSON restoreURL(String shortURL);
- return { success: true, shortURL: xxx, error: xxx }

## Database Design
- urls(id, originalURL, shortURL)

## High-level Design
![ImageURL](https://github.com/classoversea/system.design/blob/main/questions/TinyURL/Design_TinyURL_Ryan.png)

## Detail Design

# redirection
- 301: moved permenently, for speed
- 302: moved temporarily, for analysis 

### short URL - Hash with Collision
- [A-Za-z0-9], 62^7 > 200B
- MD5 16 bytes, SHA-1 20 bytes, first 7 chars of MD5 encoding
- keep appending pre-defined string and hash until no collision
- expensive to check collision in DB
- space efficient with bloom filter

### short URL - ID to Base62
- generate a unique ID, e.g. 11157
- encode, 11157 = 2*62^2 + 55^62 + 59 = "2TX"
- URL length not fixed
- TODO-ID-Gen-Snowflake

