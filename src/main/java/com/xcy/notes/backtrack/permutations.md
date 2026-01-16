Related LeetCode Problems:

1. 47. Permutations II
    - Contains duplicates, need to avoid duplicate permutations

2. 31. Next Permutation
    - Find lexicographically next permutation of array

3. 60. Permutation Sequence
    - Return k-th permutation sequence

4. 784. Letter Case Permutation
    - Permutations of letter case (lower/upper) for strings

5. 77. Combinations
    - All possible combinations of k numbers from 1..n

6. 78. Subsets
    - All possible subsets (power set)

7. 90. Subsets II
    - Subsets with duplicates

8. 39. Combination Sum
    - Combinations that sum to target (reuse allowed)

9. 40. Combination Sum II
    - Combination sum with duplicates (no reuse)

10. 267. Palindrome Permutation II
    - Generate all palindromic permutations

Common Follow-up Questions:
1. What if the array contains duplicates? (Permutations II)
2. How to generate permutations in lexicographic order?
3. How to generate next permutation without generating all? (Next Permutation)
4. How to handle very large n where n! is too big to store?
5. How to generate random permutation uniformly?
6. How to generate permutations iteratively without recursion?

Pattern: Permutation problems are fundamental backtracking exercises.
The swapping approach is efficient for distinct elements, while the
visited array approach generalizes better to problems with duplicates.
*/