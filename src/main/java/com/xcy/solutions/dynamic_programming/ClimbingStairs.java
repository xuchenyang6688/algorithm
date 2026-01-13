package com.xcy.solutions.dynamic_programming;

/**
 * 70. Climbing Stairs
 * You are climbing a staircase. It takes n steps to reach the top.
 *
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 *
 * Input: n = 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 */
public class ClimbingStairs {
    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * @param n total of n steps of the stair
     * @return count of ways to climb to the top of the stair
     */
    public int countOfWaysClimeStairs(int n){

        if (n == 1 || n==2){
            return n;
        }
        int pre1 = 1;
        int pre2 = 2;
        for (int i=3; i<=n; i++){
            int current = pre1 + pre2;
            pre1 = pre2;
            pre2 = current;
        }
        return pre2;
    }

    public static void main(String[] args) {
        ClimbingStairs solution = new ClimbingStairs();
        assert 8 == solution.countOfWaysClimeStairs(5);
    }

}
