# Questions: 
 - given a unsorted list array and target T
 - return two index,  array[i] + array[j] = T
 - exactly one solution

# Input: 
 - array of integer [a1, a2, ... an]

# Output:
 - [i, j]

# Constraints:
 - 1 ≤ array.length < 10^6
 - 0 ≤ array[i] < 10^8

# Test Case:
 - array = [2,7,11,15], T = 9 return [0, 1]
 - array = [3, 3], T = 6 return [0, 1]

# Solution1: 
 - Idea: Two for loop to check all possible answer 
 - Time: O(N^2)
 - Space: O(1)

# Solution2:
 - Idea: Use Hashmap to record element seen before
 - Time: O(N)
 - Space: O(N)