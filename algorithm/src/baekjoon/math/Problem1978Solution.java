package baekjoon.math;

import java.util.Scanner;

/**
 * 문제. 백준 - 1978 소수찾기
 *
 * link) https://www.acmicpc.net/problem/1978
 */
public class Problem1978Solution {

  boolean isPrime(int number) {
    for(int i=2; i*i<number; i++) {
      if(number%i == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String[] args) {
//    Scanner sc = new Scanner(System.in);
//
//    int count = sc.nextInt();
//    int[] values = new int[count];
//
//    for(int i=0; i<count; i++) {
//      values[i] = sc.nextInt();
//    }
//
//    int ans = 0;
//
//    for(int i=0; i<count; i++) {
//      if(new Problem1978Solution().isPrime(values[i])) {
//        ans += 1;
//      }
//    }

    System.out.println('a' - 'a');
  }

}
