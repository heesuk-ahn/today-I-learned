package baekjoon.math;

/**
 * 문제. 백준 - 2609번
 *
 * link. https://www.acmicpc.net/problem/2609
 */
public class Problem2609Solution {

  //latest common multiply
  //최소 공배수는 최대 공약수를 활용하여 구할 수 있다.
  //주의할 점은 최소 공배수는 두수보다 커지기 때문에, 자료형이 overflow 날 수 있음을 인지하고 올바른 자료형을 사용해야 한다.
  int lcm(int a, int b) {
    int getGcdValue = gcd(a, b);
    return getGcdValue * (a/getGcdValue) * (b/getGcdValue); // L = A*B / G
  }

  //greatest common divisor
  //유클리드 호제법을 이용하면 간단하게 구할 수 있다.
  int gcd(int a, int b) {
    if(a%b == 0) {
      return b;
    }
    return gcd(b,a%b);
  }

  public static void main(String[] args) {
    System.out.println(new Problem2609Solution().gcd(16, 8));
    System.out.println(new Problem2609Solution().lcm(16, 8));
  }

}


