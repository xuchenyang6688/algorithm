package com.xcy.solutions.two_pointers;

import java.util.List;

public class ThreeSumTest {
    // test empty input

    public static void main(String[] args) {
        ThreeSumTest threeSumTest = new ThreeSumTest();
        threeSumTest.testEmptyNums();
        threeSumTest.testSameNums();
        threeSumTest.testSameAndDuplicateNums();
        threeSumTest.testNoMatchedResult();
        threeSumTest.testMultipleResult();
        threeSumTest.testMaxMinNums();
    }

    private void testEmptyNums () {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[0];
        List<List<Integer>> result =  threeSum.threeSum(nums);
        assert result.isEmpty();
    }

    private void testSameNums () {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[] {0,0,0};
        List<List<Integer>> result = threeSum.threeSum(nums);
        assert result.size()==1 && result.contains(List.of(0,0,0));
    }

    private void testSameAndDuplicateNums () {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[] {0,0,0,0,0,0};
        List<List<Integer>> result = threeSum.threeSum(nums);
        assert result.size()==1 && result.contains(List.of(0,0,0));
    }

    private void testNoMatchedResult() {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[] {1,2,0,-5,-6};
        List<List<Integer>> result = threeSum.threeSum(nums);
        assert result.isEmpty();
    }

    private void testMultipleResult() {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[] {-4,-1,-1,0,1,2};
        List<List<Integer>> result = threeSum.threeSum(nums);
        assert result.size() == 2 && result.contains(List.of(-1,0,1)) && result.contains(List.of(-1,-1,2));
    }

    private void testMaxMinNums(){
        ThreeSum threeSum = new ThreeSum();
        int[] nums = new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE, 1};
        List<List<Integer>> result = threeSum.threeSum(nums);
        assert result.size() == 1 && result.contains(List.of(Integer.MIN_VALUE,1, Integer.MAX_VALUE)) ;
    }

}
