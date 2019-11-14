package baekjoon.math;

import java.util.Scanner;

/**
  문제. 10430 백준 - 나머지 연산
  link. https://www.acmicpc.net/problem/10430
**/
public class Problem10430Solution {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Integer a = sc.nextInt();
    Integer b = sc.nextInt();
    Integer c = sc.nextInt();

    System.out.println((a+b)%c);
    System.out.println((a%c + b%c)%c);
    System.out.println((a*b)%c);
    System.out.println((a%c * b%c)%c);
  }

}
