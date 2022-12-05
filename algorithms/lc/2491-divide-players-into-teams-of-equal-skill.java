// 2491-divide-players-into-teams-of-equal-skill
// Q: https://leetcode.com/problems/divide-players-into-teams-of-equal-skill/

class Solution {

    /*
        # Questions: 
         - 将数组分成若干对，没对和相同，求对乘积之和

        # Input: 
         - 技能数组

        # Output:
         - 乘积和
         - 不能分配返回-1

        # Constraints:
         - 技能数组长度长度为偶数, [2, 10^5]
         - 技能，[1, 1000]

        # Test Case:
         - skill = [3,2,5,1,3,4], return 22
         - skill = [1,1,2,3], return -1

        # Solution1: 计数 + 乘法
         - 统计每个skill值的个数，与总和
         - 计算每队和p，不如非整数，返回-1
         - 从1到p/2-1遍历数对里小的数
         - 如果两数相等，个数是奇数返回-1，否则该数平方*一半个数
         - 否则，大小数的个数必须相等，大数乘小数，乘个数
         - Time: O(N)
         - Space: O(V)

        # Solution2: 排序 + 双指针
         - 对数组排序，每对和为首尾和
         - 前后指针向中间移动，如果和不为固定数，则返回-1
         - 否则两数乘积加到结果上
         - Time: O(N*LogN)
         - Space: O(1)
    */
    public long dividePlayers11(int[] skill) {
        int[] count = new int[2001];
        long n = skill.length, m = n / 2, sum = 0;
        for (int v : skill) {
            sum += v;
            count[v]++;
        }
        long p = sum / m, ans = 0;
        if (p * m != sum) {
            return -1;
        }
        for (int i = 1; i <= p / 2; i++) {
            int j = (int) (p - i);
            if (i == j) {
                if (count[i] % 2 == 1) {
                    return -1;
                }
                ans += 1L * i * j * count[i] / 2;
            } else {
                if (count[i] != count[j]) {
                    return -1;
                }
                ans += 1L * i * j * count[i];
            }
        }
        return ans;
    }

    public long dividePlayers(int[] skills) {
        Arrays.sort(skills);
        int n = skills.length;
        long ans = 0, sum = skills[0] + skills[n - 1];
        for (int i = 0; i < n / 2; i++) {
            if (skills[i] + skills[n - 1 - i] != sum) {
                return -1;
            }
            ans += (skills[i] * skills[n - 1 - i]);
        }
        return ans;
    }
}
/*
[3,2,5,1,3,4]
[3,4]
[1,1,2,3]

java.lang.ArrayIndexOutOfBoundsException: Index 1999 out of bounds for length 1001
  at line 29, Solution.dividePlayers
  at line 54, __DriverSolution__.__helper__
  at line 84, __Driver__.main
*/
