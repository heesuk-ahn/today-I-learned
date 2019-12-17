package leetcode.easy;

import java.util.stream.IntStream;

/**
 * problem) House Robber
 * link) https://leetcode.com/problems/house-robber/
 */
public class Problem198Solution {

  public int rob(int[] nums) {
    int[] getMaxMoney = new int[nums.length];

    if(nums.length == 0) {
      return nums[0];
    }
    if(nums.length == 1) {
      return Math.max(nums[0], nums[1]);
    }

    getMaxMoney[0] = nums[0];
    getMaxMoney[1] = Math.max(nums[0], nums[1]);

    IntStream.rangeClosed(2, nums.length-1).forEach(i -> {
      getMaxMoney[i] = Math.max(getMaxMoney[i-2] + nums[i], getMaxMoney[i-1]);
    });

    return getMaxMoney[nums.length-1];

  }

  public static void  main(String[] args) {
    int[] arr = {2, 1, 1, 2};

    System.out.println(new Problem198Solution().rob(arr));
  }

}
