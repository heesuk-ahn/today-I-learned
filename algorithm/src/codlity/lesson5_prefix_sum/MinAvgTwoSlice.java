package codlity.lesson5_prefix_sum;

/**
 *
 * A non-empty array A consisting of N integers is given. A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
 *
 * For example, array A such that:
 *
 *     A[0] = 4
 *     A[1] = 2
 *     A[2] = 2
 *     A[3] = 5
 *     A[4] = 1
 *     A[5] = 5
 *     A[6] = 8
 * contains the following example slices:
 *
 * slice (1, 2), whose average is (2 + 2) / 2 = 2;
 * slice (3, 4), whose average is (5 + 1) / 2 = 3;
 * slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
 * The goal is to find the starting position of a slice whose average is minimal.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a non-empty array A consisting of N integers, returns the starting position of the slice with the minimal average. If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
 *
 * For example, given array A such that:
 *
 *     A[0] = 4
 *     A[1] = 2
 *     A[2] = 2
 *     A[3] = 5
 *     A[4] = 1
 *     A[5] = 5
 *     A[6] = 8
 * the function should return 1, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−10,000..10,000].
 *
 * IDEA 1) 모든 경우 구하기 O(N^2)
 *
 * IDEA 2) 재귀로 완전 탐색 O(NM)
 *
 * IDEA 3) 수학 성질 이용하기. 2가지 전제가 있다 O(N)
 *
 * - N이 주어졌을 떄, N의 평균과 N의 부분집합의 평균들의 평균은 N의 평균과 같다.
 * - 즉, 4개의 원소로 이루어진 값의 평균은 결국 2개씩 이루어진 부분집합의 평균보다는 크거나 작다. (중간 값)
 * - 최소 값을 찾는 경우에는 결국 부분집합 2개만 고려하면 된다.
 * - 하지만, 홀수인 경우에는? 3인 경우의 부분집합 값도 고려해야한다.
 * - 5개라고 할 경우 2개의 평균 부분집합, 3개의 평균부분집합 둘 중 하나가 최저 값이기 때문이다.
 */
public class MinAvgTwoSlice {

  //2개와 3개의 부분집합의 평균 중 더 적은것을 min과 비교하여 넣는다
  public int solution(int[] A) {
    int length = A.length;
    int min = Integer.MAX_VALUE;
    int minIndex = 0;

    for(int i=2; i<length; i++) {
      int minCheckIndex = 0;
      int minCheck = 0;
      int subThreeValueAvg = (A[i-2] + A[i-1] + A[i])/3;
      int subTwoValueAvg = (A[i-1] + A[i])/2;

      if(subTwoValueAvg > subThreeValueAvg) {
        minCheckIndex = i-2;
        minCheck = subThreeValueAvg;
      } else {
        minCheckIndex = i-1;
        minCheck = subTwoValueAvg;
      }

      if(min > minCheck) {
        min = minCheck;
        minIndex = minCheckIndex;
      }
    }

    return minIndex;
  }

}
