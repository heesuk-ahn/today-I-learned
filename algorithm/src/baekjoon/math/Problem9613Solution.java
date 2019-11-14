package baekjoon.math;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 문제. GCD 합
 *
 * lin) https://www.acmicpc.net/problem/9613
 *
 */
public class Problem9613Solution {

  int gcd(int a, int b) {
    if(a%b == 0) return b;
    return gcd(b, a%b);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int testCase = sc.nextInt();

    for(int i=0; i<testCase; i++) {
      int numberCount = sc.nextInt();
      int sum = 0;
      ArrayList<Integer> arrayList = new ArrayList<>();

      for(int k=0; k<numberCount; k++) {
        int input = sc.nextInt();
        arrayList.add(input);
      }
      for(int j=0; j<arrayList.size()-1; j++) {
        for(int k=j+1; k<arrayList.size(); k++) {
          int getGcd = new Problem9613Solution().gcd(arrayList.get(j), arrayList.get(k));
          sum += getGcd;
        }
      }
      System.out.println(sum);
    }

  }

}
