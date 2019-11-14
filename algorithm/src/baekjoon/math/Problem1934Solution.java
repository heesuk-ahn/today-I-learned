package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제. 백준 1934 - 최소공배수
 *
 * link) https://www.acmicpc.net/problem/1934
 */
public class Problem1934Solution {

  int lcm(int a, int b) {
    int g = gcd(a, b);
    return (a*b)/g; // L = (a*b)/g
  }

  int gcd(int a, int b) {
    if(a%b == 0) return b;
    return gcd(b, a%b);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Integer t = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    for(int i=0; i<t; i++) {
      String[] line = br.readLine().split(" ");
      int a = Integer.parseInt(line[0]);
      int b = Integer.parseInt(line[1]);
      sb.append(new Problem1934Solution().lcm(a, b) + " \n");
    }

    System.out.println(sb.toString());
  }

}
