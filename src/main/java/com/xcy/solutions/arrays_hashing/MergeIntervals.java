package com.xcy.solutions.arrays_hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. Merge Intervals
 *
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the
 * non-overlapping intervals that cover all the intervals in the input.
 *
 * Example:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * Constraints:
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 */
public class MergeIntervals {

    /**
     * Merges overlapping intervals using sorting and greedy merging.
     *
     * Approach: Sort & Merge
     * 1. Sort intervals by start time (ascending)
     * 2. Initialize result with first interval
     * 3. Iterate through sorted intervals:
     *    - If current interval doesn't overlap with last merged interval, add it
     *    - If they overlap, merge by extending end time of last merged interval
     *
     * Key Insight: After sorting by start time, an interval can only overlap
     * with the immediately previous merged interval in our result list.
     *
     * Time Complexity: O(N log N) where N = intervals.length
     *   - Sorting takes O(N log N)
     *   - Merging pass takes O(N)
     *
     * Space Complexity: O(N) for storing the result (or O(log N) for sorting space)
     *
     * @param intervals array of [start, end] intervals
     * @return merged non-overlapping intervals
     */
    public int[][] merge(int[][] intervals) {
        // Edge cases: empty or single interval
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Step 1: Sort intervals by start time
        // Important: Sort by start, then by end to handle equal start times
        // Arrays.sort(int[] array) only supports int[] instead of int[][], we need to use Arrays.sort(int[][] a,
        // Comparator)
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // Step 2: Merge overlapping intervals
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = merged.get(merged.size() - 1);
            // Check if current interval overlaps with last merged interval
            // Overlap occurs when: current.start <= last.end

            if (current[0] <= last[1]) {
                // Merge: extend last interval's end if needed. In the case [1,9], [2,5], no need to extend end when iterate [2,5]
                last[1] = Math.max(last[1], current[1]);
            } else {
                // No overlap: add current interval as new merged interval
                merged.add(current);
            }
        }

        // Step 3: Convert List to array
        return merged.toArray(new int[merged.size()][2]);
    }

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();
        // Test Case: Overlapping + No Overlapping + Unsorted
        int[][] input = new int[][]{{1,3}, {2,5}, {1,9}, {11,14}};
        int[][] expected = new int[][]{{1,9}, {11,14}};
        assert Arrays.deepEquals(expected, solution.merge(input));

        // Test Case: Edge case - single interval
        input = new int[][]{{1,3}};
        expected = new int[][]{{1,3}};
        assert Arrays.deepEquals(expected, solution.merge(input));

        // Test Case: Completely no overlapping
        input = new int[][]{{1,9}, {15,17}};
        expected = new int[][]{{1,9},{15,17}};
        assert Arrays.deepEquals(expected, solution.merge(input));
    }
}
