# Cache

## cache penetration
- 穿透, Always hit DB as not in Cache
- Solution: empty object in Cache

## cache breakdown 
- 击穿, Hit DB for invalid hot key
- Solution: with mutex

## cache avalanche
- 雪崩, Hit DB for all keys
- Solution: different TTL
