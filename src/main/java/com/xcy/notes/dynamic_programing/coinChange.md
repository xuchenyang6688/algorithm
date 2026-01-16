Related LeetCode Problems:

1. 518. Coin Change 2 (Number of combinations)
    - Count number of ways to make amount (order doesn't matter)

2. 377. Combination Sum IV (Permutations)
    - Count number of combinations (order matters)

3. 279. Perfect Squares
    - Similar to coin change with square numbers as "coins"

4. 983. Minimum Cost For Tickets
    - Variation with time periods instead of coins

5. 416. Partition Equal Subset Sum (0/1 Knapsack)
    - Subset sum problem, related DP technique

6. 494. Target Sum
    - Assign + or - to numbers to reach target

7. 39. Combination Sum
    - Find all combinations that sum to target (backtracking)

8. 40. Combination Sum II
    - Combination sum with duplicates (each used once)

9. 1049. Last Stone Weight II
    - Similar to partition problem

10. 474. Ones and Zeroes
    - Two-dimensional knapsack problem

Common Follow-up Questions:
1. What if we need to count the number of ways, not minimum coins? (Coin Change 2)
2. What if the order matters? (Combination Sum IV)
3. What if each coin can only be used once? (0/1 knapsack)
4. How to reconstruct the actual coin combination, not just count?
5. What if coins have negative values? (Not typical)
6. How to handle very large amounts where DP table is too big?
7. What if we need to minimize coin weight/value instead of count?

Pattern: Coin change is a classic Unbounded Knapsack problem where
items (coins) can be reused infinitely. The DP recurrence is:
dp[i] = min(dp[i], dp[i - coin] + 1) for all coins ≤ i
