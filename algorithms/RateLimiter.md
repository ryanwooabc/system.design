# Rate Limiter Algorithms

## Token Bucket
- refill token by fix rate
- overflow bucket size
- pros: not waste quota
- cons: handle burst

## Leaking Bucket
- handle by fix rate
- overflow bucket size
- pros: for stable outflow use
- cons: not handle burst 

## Fixed Windows Counter
- process N request every second
- cons: spike traffic at window boundry

## Sliding Window Log
- track request time
- keep fixed window in queue
- pros: accurate
- cons: consume memory

## Sliding Window Counter
- PrevWindow * 0.7 + CurrentWindow
- pros: avoid burst

# Refs
- Alex Xu, System Design Interview, Vol 1
