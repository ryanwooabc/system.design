# Pagination in Firestore

## Pagination
- myQuery = restaurantRef.whereField("city", isEqualTo: "Tokyo")
-                        .whereField("category", isEqualTo: "tempura")
-                        .order(by: "rating", descending: true)
-                        .limit(to: 3)

## Next Page
- myQuery = myQuery.start(after: prevDocId)

## Problem
- [1, 2, 3, 4, 5, 6].page(3, 1) = [1, 2, 3]
- [1, 2, 4, 3, 5, 6].page(3, 2) = [3, 5, 6]
- No. 3 changed order, and retrieved twice, but No. 4 missing

## Solution #1
- always get the real-time data
- pros: better for stable data
- cons: messy data

## Solution #2
- disable listener for old data
- activate listener for new data
- pros: better for chat app
- cons: old data not real time
- example: wechat newsfeed?

## Solution #3
- activate listener for all data
- pros: all data real time
- cons: heavy

## Solution #4
- re-run query with increasing limit
- pros: easiest
- cons: expensive to request all old data

## Refs
- How Do I Paginate My Data? | Get to know Cloud Firestore #7
- https://www.youtube.com/watch?v=poqTHxtDXwU
- https://firebase.google.com/docs/firestore/query-data/query-cursors