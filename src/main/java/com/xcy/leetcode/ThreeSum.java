package com.xcy.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Difficulty: Hard
 */
public class ThreeSum {
    

    public List<List<Integer>> threeSum (int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        // 排序，方便判断重复，以及可以通过递增的顺序，寻找匹配的第二个和第三个数。
        Arrays.sort(nums);

        // first: from 0 to length-1
        for (int first = 0; first<length; first++) {
            //if first' == first, continue;
            if (first!=0 && nums[first] == nums[first-1]){
                continue;
            }
            // 通过第二层循环寻找到所有针对第一个数匹配的结果
            // second移动条件：nums[second] + nums[third]<=-nums[first]
            // third移动条件：nums[sencond] + nums[third]>-nums[first] && second<third, 
            // 为什么third不需要判重，当nums[sencond] + nums[third] == -nums[first], second会继续移动而且去重，不会出现second不变，third‘ == third
            //second: from 0 to length-1
            //third: from length-1
            int third = length - 1;
            int targetSum = -nums[first]; // It won't exceed Integer_MIN VALUE;
            for (int second = first+1; second<length; second++){
                //if second' == second, continue;
                if(second!=first+1 && nums[second] == nums[second-1]){
                    continue;
                }

                // when second + third > targetSum, need to continue to move third pointer until the sum<=targetSum or the thrid<=left
                while(second < third && nums[second] + nums[third] > targetSum){
                    third--;
                }

                if (second == third){
                    break; // although the second pointer doesn't move from first+1 to lenght-1, since we move the third pointer to second, it means the finishment for the given first.
                }

                if (nums[second]+nums[third] == targetSum){
                    List<Integer> tempResult = new ArrayList<>();
                    tempResult.add(nums[first]);
                    tempResult.add(nums[second]);
                    tempResult.add(nums[third]);
                    result.add(tempResult);
                }

            }
        }
        return result;
    }

}
