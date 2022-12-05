# Count-Min Sketch Algorithm

# Option 1: Single HashMap + Head
- HashMap<String, Long> count;
- PriorityQueue<String> pq;
- if (count.get(word) > count.get(pq.peek()) { pq.poll(); pq.add(word); };
- 10M * 1K = 10GB memory
  
# Option 2: Sharding HashMap + Heap
- ip = hash(word) % N
- Top K on N servers
- Aggregate at last
  
# Option 3: 

# Refs
- https://soulmachine.gitbooks.io/system-design/content/cn/bigdata/heavy-hitters.html
