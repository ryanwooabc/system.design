// 2492-minimum-score-of-a-path-between-two-cities
// Q: https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities

class Solution {
    
    /*
        # Questions: 
         - 求图中点1到点n的最小路值

        # Input: 
         - 点数n和路的数组(两点加权重)

        # Output:
         - 最小路值

        # Constraints:
         - 2 <= n <= 105
         - 1 <= roads.length <= 105
         - roads[i].length == 3
         - 1 <= ai, bi <= n
         - ai != bi
         - 1 <= distancei <= 104
         - There are no repeated edges.
         - There is at least one path between 1 and n.

        # Test Case:
         - n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]], return 5
         - n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]], return 2

        # Solution1: BFS
         - 讲边转成带权重的无向图，创建访问标记
         - 创建队列，放入节点0
         - BFS访问所有点和边，更新最小边
         - Time: O(V + E)
         - Space: O(V + E)

        # Solution2: UionFind
         - 创建并初始化root数组
         - 遍历所有边，合并每对顶点
         - 遍历所有边，如果和节点0同根，则更新最小的结果
         - Time: O(V + E)
         - Space: O(V)
    */
    public int minScore11(int n, int[][] roads) {
        Map<Integer, Integer>[] graph = new Map[n];
        for (int i = 0; i < n; i ++) {
            graph[i] = new HashMap<>();
        }
        for (int[] r : roads) {
            int a = r[0] - 1, b = r[1] - 1, c = r[2];
            graph[a].put(b, c);
            graph[b].put(a, c);
        }
        
        int ans = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n];
        Queue<Integer> bfs = new ArrayDeque<>() {{ add(0); }};
        for (visited[0] = true; !bfs.isEmpty(); ) {
            int i = bfs.poll();
            for (int j : graph[i].keySet()) {
                if (!visited[j]) {
                    bfs.add(j);
                    visited[j] = true;
                }
                ans = Math.min(ans, graph[i].get(j));
            }
        }
        return ans;
    }
    
    public int minScore(int n, int[][] roads) {
        int[] root = new int[n];
        for (int i = 0; i < n; i ++) {
            root[i] = i;
        }
        for (int[] r : roads) {
            int a = find(root, r[0] - 1), b = find(root, r[1] - 1);
            if (a != b) {
                root[a] = root[b];
            }
        }        
        int t = find(root, 0), ans = Integer.MAX_VALUE;
        for (int[] r : roads) {
            if (find(root, r[0] - 1) == t) {
                ans = Math.min(ans, r[2]);
            }
        }
        return ans;
    }
    
    int find(int[] root, int i) {
        return root[i] == i ? i : (root[i] = find(root, root[i]));
    }
    
    public int minScore1F(int n, int[][] roads) {
        int m = roads.length;
        
        int min = roads[0][2];
        for(int i=0;i<m;i++){
            min = Math.min(min, roads[i][2]);
        }
        
        return min;
    }    
}