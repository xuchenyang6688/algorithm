package com.xcy.solutions.sorting;

import java.util.Arrays;

public class QuickSorting {

    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);

    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivotIndex = start;
        int left = start;
        int right = end;
        while (left <= right) {
            while (left <= right && nums[left] <= nums[pivotIndex]) {
                left++;
            }
            while (left <= right && nums[right] > nums[pivotIndex]) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        if (pivotIndex != right) {
            int temp = nums[pivotIndex];
            nums[pivotIndex] = nums[right];
            nums[right] = temp;
            pivotIndex = right;
        }

        quickSort(nums, start, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, end);
    }


    public static void main(String[] args) {
        QuickSorting solution = new QuickSorting();
        int[] input = new int[]{3, 4, 1, 5, 2};
        solution.quickSort(input);
        assert Arrays.equals(new int[]{1, 2, 3, 4, 5}, input);

    }

}
