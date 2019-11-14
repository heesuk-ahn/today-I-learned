package baekjoon.math;

import java.util.Scanner;

/**
 * 문제. 팩토리얼의 0의 개수
 *
 * 링크. https://www.acmicpc.net/problem/1676
 */
public class Problem1676Solution {

  int getFactorialValueForRec(int n) {
    if(n<=1) return 1;
    return n * getFactorialValueForRec(n-1);
  }

  /*
    끝자리가 0인 개수를 구하려면, 10^n에서 n의 개수를 구해야한다.
    10의 지수를 구하려면
    2*5 = 10인데, 이때 5의 n보다는 2의 n승이 훨씬 더 많기때문에 결과적으로는
    5^n승의 n만 구하면 10의 n을 알 수 있다.

    그렇다면 주어진 N에서 5로 나누어떨어지는 갯수를 구한다.

    100을 가정해보자. 100의 경우 100/5 = 20개이다.
    하지만 20개 중에서 5를 더 가지고 있는 경우가 있다. 5를 두개가지고 있는 경우는

    25, 50, 75, 100 이렇게 4개이다.

    100/25 = 4개

    5^3은 125이기때문에 없다.
    즉 20 + 4개 = 24개가 0의 개수이다.

    7 => 7
    8 => 5
   */
  int getZeroCount(int n, int exponential, int sum) {
    if(Math.pow(5, exponential) > n) {
      return sum;
    } else {
      double div = n / Math.pow(5, exponential);
      double remainder = n % Math.pow(5, exponential);

      if(remainder == 0) {
        sum+=div;
        return getZeroCount(n, exponential + 1, sum);
      } else {
        return getZeroCount(n, exponential + 1, sum);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Integer n = sc.nextInt();

    System.out.println(new Problem1676Solution().getZeroCount(n, 1, 0));
  }

}
