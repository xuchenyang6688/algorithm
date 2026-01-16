Related LeetCode Problems:

1. 509. Fibonacci Number
    - Direct Fibonacci sequence calculation

2. 746. Min Cost Climbing Stairs
    - Variation: each step has a cost, find minimum cost

3. 1137. N-th Tribonacci Number
    - Extension: steps of 1, 2, or 3 at a time

4. 198. House Robber
    - Similar DP pattern: rob[i] = max(rob[i-1], rob[i-2] + nums[i])

5. 213. House Robber II
    - House Robber with circular arrangement

6. 91. Decode Ways
    - Similar DP: ways[i] = ways[i-1] + ways[i-2] with conditions

7. 62. Unique Paths
    - 2D version: number of ways to reach bottom-right corner

8. 63. Unique Paths II
    - Unique Paths with obstacles

9. 64. Minimum Path Sum
    - Similar grid DP with minimization

10. 120. Triangle
    - Triangular array, find minimum path sum

Common Follow-up Questions:
1. What if you can climb 1, 2, or 3 steps at a time? (Tribonacci)
2. What if each step has a different cost? (Min Cost Climbing Stairs)
3. What if some steps are broken and cannot be stepped on?
4. How to reconstruct the actual sequences, not just count them?
5. What if steps have negative values (going down)?
6. How to handle very large n (e.g., n = 10^9)? (Use matrix exponentiation)
7. What if the number of steps you can take varies (e.g., [1, 3, 5])? (Coin change pattern)

Pattern: This is the classic Fibonacci DP problem. The recurrence
ways(n) = ways(n-1) + ways(n-2) appears in many variations:
- Fibonacci numbers (shifted by 1)
- Decoding ways for strings
- Unique paths in grids
- Many combinatorial counting problems
