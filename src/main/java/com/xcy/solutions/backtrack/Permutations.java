package com.xcy.solutions.backtrack;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 46. Permutations
 *
 * Given an array nums of distinct integers, return all the possible
 * permutations. You can return the answer in any order.
 *
 * Example:
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Constraints:
 * - 1 <= nums.length <= 6
 * - -10 <= nums[i] <= 10
 * - All integers in nums are unique
 */
public class Permutations {

    /**
     * Generates all permutations of the input array using backtracking.
     *
     * Approach: Backtracking with Swapping
     * 1. Start with the original array as first candidate
     * 2. At each position index, try swapping with all subsequent positions
     * 3. Recursively generate permutations for remaining positions
     * 4. Backtrack by swapping back to restore original state
     *
     * Key Insight: By swapping elements in-place, we generate permutations
     * without extra space for tracking used elements. Each swap creates a
     * new ordering for the recursive call to explore.
     *
     * Time Complexity: O(n * n!) where n = nums.length
     *   - There are n! permutations total
     *   - Each permutation takes O(n) to copy to result list
     *
     * Space Complexity: O(n!) for storing all permutations
     *   - O(n) additional space for recursion stack depth
     *   - In-place swapping means no extra space for tracking used elements
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> permutation = new ArrayList<>(nums.length);

        // Convert array to list for easy swapping
        for (int num : nums) {
            permutation.add(num);
        }
        backtrack(0, permutation, result);
        return result;
    }

    /**
     * Recursive backtracking function to generate permutations.
     *
     * @param index current position being fixed in the permutation
     * @param permutation the current ordering of elements
     * @param result list to collect all permutations
     */
    private void backtrack(int index, List<Integer> permutation, List<List<Integer>> result) {
        // Base case: complete permutation found
        if (index == permutation.size()) {
            result.add(new ArrayList<>(permutation)); // Make a copy
            return;
        }

        // Generate permutations by swapping current index with all positions >= index
        for (int i = index; i < permutation.size(); i++) {
            // Place element at position i into position index
            Collections.swap(permutation, index, i);
            // Recursively generate permutations for remaining positions
            backtrack(index + 1, permutation, result);
            // Backtrack: restore original ordering
            Collections.swap(permutation, index, i);
        }
    }

    /**
     * Alternative implementation using visited array.
     * More intuitive but uses extra O(n) space for tracking used elements.
     */
    public List<List<Integer>> permuteWithVisited(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        backtrackWithVisited(nums, visited, new ArrayList<>(nums.length), result);
        return result;
    }

    private void backtrackWithVisited(int[] nums, boolean[] visited, List<Integer> permutation,
                                      List<List<Integer>> result) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                permutation.add(nums[i]);
                visited[i] = true;
                backtrackWithVisited(nums, visited, permutation, result);
                permutation.remove(permutation.size() - 1); // Do not use permutation.remove(nums[i]), it's ambiguous, remove(int index) or remove(Object obj)
                visited[i] = false;
            }
        }
    }


    // ==================== TESTING UTILITIES ====================
    /**
     * Helper to sort permutations for consistent comparison.
     */
    private static List<List<Integer>> getSortedPermutations(List<List<Integer>> permutations) {
        if (permutations == null) return null;
        // Sort the list of permutations lexicographically
        List<List<Integer>> sortedPermutations = new ArrayList<>(permutations.size());
        // Deep copy
        for(List<Integer> permutation : permutations){
            sortedPermutations.add(new ArrayList<>(permutation));
        }
        sortedPermutations.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) return cmp;
            }
            return Integer.compare(a.size(), b.size());
        });
        return sortedPermutations;
    }

    /**
     * Compare permutations
     */
    private static boolean comparePermutationsIgnoreOrder(
            List<List<Integer>> list1,
            List<List<Integer>> list2) {

        if (list1 == null && list2 == null) return true;
        if (list1 == null || list2 == null) return false;
        if (list1.size() != list2.size()) return false;

        // Sort both lists (creating copies)
        List<List<Integer>> sorted1 = getSortedPermutations(list1);
        List<List<Integer>> sorted2 = getSortedPermutations(list2);

        // Compare element by element
        for (int i = 0; i < sorted1.size(); i++) {
            List<Integer> perm1 = sorted1.get(i);
            List<Integer> perm2 = sorted2.get(i);

            if (perm1.size() != perm2.size()) return false;
            for (int j = 0; j < perm1.size(); j++) {
                if (!perm1.get(j).equals(perm2.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    // ==================== TEST CASES ====================
    public static void main(String[] args) {
        Permutations solution = new Permutations();
        List<List<Integer>> result = solution.permute(new int[]{1, 2, 3});
        List<List<Integer>> expected = List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1)
                , List.of(3, 1, 2), List.of(3, 2, 1));
        assert comparePermutationsIgnoreOrder(expected, result);

        // Test single element
        result = solution.permute(new int[]{3});
        expected = List.of(List.of(3));
        assert comparePermutationsIgnoreOrder(expected, result);
    }
}
