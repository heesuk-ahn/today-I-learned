package baekjoon.greedy;

import java.util.Scanner;

/**
 * 백준 11047번 그리디 알고리즘. 동전 조합 최솟값 구하기
 *
 * link: https://www.acmicpc.net/problem/11047
 */
public class Problem11047Solution {

  public int getMinimumCombinationForRec(int[] coins, int expectedValue) {
    int count = 0;

    for(int i=coins.length-1; i>=0; i--) {
      //key point : 잔돈은 나누고, 나머지를 돌려주는 것이다.
      int divisionCount = expectedValue / coins[i];
      int remainderValue = expectedValue % coins[i];
      expectedValue = remainderValue;
      count += divisionCount;
      divisionCount = 0;
    }

    return count;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int coinTypeNumber = sc.nextInt();
    int expectedValue = sc.nextInt();
    int[] coins = new int[coinTypeNumber];

    for(int i=0; i<coinTypeNumber; i++) {
      coins[i] = sc.nextInt();
    }

    int ans = new Problem11047Solution().getMinimumCombinationForRec(coins, expectedValue);

    System.out.println(ans);
  }

}
