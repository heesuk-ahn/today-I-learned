package etc;

import java.util.*;

public class Test {

  public List<List<Integer>> permuteUnique(int[] nums) {
    int[] originArray = nums.clone();
    int length = nums.length;
    int[] copyArray = nums.clone();
    int copyIndex = 0;
    List<List<Integer>> ansList = new ArrayList<>();

    //같은 값이 나올 떄까지 순열을 돌린다.
    while(true) {
      //순열을 돌린다.
      int[] tempArray = copyArray.clone();

      for(int i=0; i<length; i++) {
        copyArray[(i+1)%length] = tempArray[i];
      }
      //원본 배열과 비교한다.
      for(int i=0; i<length; i++) {
        if(originArray[i] == copyArray[copyIndex]) {
          copyIndex += 1;
        } else {
          copyIndex = 0;
          break;
        }
      }

      if(copyIndex == length) {
        break;
      } else {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<length; i++) {
          list.add(copyArray[i]);
        }
        ansList.add(list);
      }
    }
    return ansList;
  }

  public static void main(String[] args) {
    int[] nums = {1, 1, 2};

    List<List<Integer>> list = new Test().permuteUnique(nums);

    System.out.println(list.size());

  }


}
