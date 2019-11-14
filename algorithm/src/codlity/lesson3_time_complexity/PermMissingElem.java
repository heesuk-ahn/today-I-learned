package codlity.lesson3_time_complexity;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 문제)
 *
 * An array A consisting of N different integers is given.
 * The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 *
 * Your goal is to find that missing element.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A, returns the value of the missing element.
 *
 * For example, given array A such that:
 *
 *   A[0] = 2
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 5
 *
 * the function should return 4, as it is the missing element.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 *
 * 문제 이해) 이가 빠졌다. 빠진 이를 찾아라.
 *
 * IDEA 1) 정렬 -> index + 1 보다 크다면 반환, O(nlogn)
 *
 * IDEA 2) 차집합 이용. 결국 연속된 수이기 때문에, N+1까지 빠짐없이 연속된 배열과 차집합을 하여 구분되는 값을 출력
 *
 * IDEA 3)
 *
 *
 */
public class PermMissingElem {

  int getMissingValue(int[] arr) {
    int maxValue = 0;
    int length = arr.length;
    HashSet<Integer> arrSet = new HashSet<>();
    HashSet<Integer> vsArrSet = new HashSet<>();

    for(int i=0; i<length; i++) {
      maxValue = Math.max(arr[i], maxValue);
      arrSet.add(arr[i]);
    }

    for(int i=1; i<=length; i++) {
      vsArrSet.add(i);
    }

    if(vsArrSet.size() == arrSet.size()) {
      return arrSet.size()+1;
    }

    vsArrSet.removeAll(arrSet);

    int ans = 1;
    for(int a: vsArrSet) {
      ans = a;
    }

    return ans;
  }

  public static void main(String[] args) {
    HashMap<Integer, Boolean> map = new HashMap<>();

    int[] arr = {1};

    System.out.println(new PermMissingElem().getMissingValue(arr));
  }

}
