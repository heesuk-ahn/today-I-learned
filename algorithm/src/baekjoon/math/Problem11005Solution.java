package baekjoon.math;

import java.util.Scanner;

/**
 * 문제. 백준 - 진법변환
 * link) https://www.acmicpc.net/problem/11005
 */
public class Problem11005Solution {

  String convertToBase(int number, int division) {
    StringBuilder sb = new StringBuilder();

    while(number >= division) {
      int remainder = number % division >= 10 ? (number % division) + 55 : number % division;
      char print = remainder >= 10 ? (char) remainder : Character.forDigit(remainder, 10);
      number = number / division;
      sb.insert(0, print);
    }

    if(number >= 10) {
      char numberChar = (char) (number + 55);
      sb.insert(0, numberChar);
    } else {
      sb.insert(0, Character.forDigit(number, 10));
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int number = sc.nextInt();
    int division = sc.nextInt();

    System.out.println(new Problem11005Solution().convertToBase(number, division));
  }

}
