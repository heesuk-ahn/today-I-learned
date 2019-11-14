package leetcode;

public class Problem162Solution {


  /**
   * 이 문제는 선형 검색을 하는 문제인데, 관건은 리니어하게 찾는 것이 아니라,
   * 바이너리 서치등을 활용해서 속도를 개선해야 한다는 것이다
   */
  public int findPeakElement(int[] nums) {

    int ans = 0;

    //아래 코드는 반복문을 순회하면서 O(N)이 된다. N보다 빠른 검색으로 속도를 개선하자.
    for(int i=0; i< nums.length; i++) {
      boolean checkSafetyBoundary = i-1>0 && i+1 < nums.length;
      boolean startPoint = i==0;
      boolean endPoint = i==nums.length - 1;

      if(startPoint && nums[i] > nums[i+1]) {
        ans = i;
      }
      if(!startPoint && checkSafetyBoundary && nums[i-1] < nums[i] && nums[i+1] < nums[i]) {
        ans = i;
      }
      if(endPoint && nums[i] > nums[i-1]) {
        ans =  i;
      }
    }

    return ans;
  }

  public int findPeakElementForBinarySearch(int[] nums) {


    return 1;
  }

  public static void main(String[] args) {
    int[] nums = {1,2,3,1};

    Integer res = new Problem162Solution().findPeakElement(nums);
    System.out.println(res);

  }

}
