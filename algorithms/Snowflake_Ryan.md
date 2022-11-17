# Twitter Snowflake Approach
- https://en.wikipedia.org/wiki/Snowflake_ID
- https://www.cnblogs.com/52fhy/p/11743413.html
- https://blog.twitter.com/engineering/en_us/a/2010/announcing-snowflake
- sign 1 bit, not used, always 0 as positive number
- timestamp 41 bits, 2^41/1000/3600/24/365 = 69.7 years
- datacenter 5 bits: 2^5 = 32
- machine 5 bits: 2^5 = 32
- seq 12 bits: 2^12 = 4096
