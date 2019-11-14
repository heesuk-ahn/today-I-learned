package baekjoon.graph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * 복습) 2019. 08.04 1회
 *
 * 문제) 백준 2331번 '반복수열'
 *
 * link) https://www.acmicpc.net/problem/2331
 *
 * Idea)
 *
 *  반복되는 수열은 즉 사이클을 의미한다. 사이클의 시작점을 찾고, 해당 사이클 시작점의 (인덱스 - 1)의 값을 하여 사이클에 빠지지 않은 인덱스 값을 구한다.
 *
 *   - 사이클의 시작점 => DFS 로 순회 하다가 이미 방문한 곳을 발견했을 경우.
 */
public class Problem2331Solution {


  public static Integer removeCycle(ArrayList<Integer> sequenceNode, Integer a, Integer p) {
    Integer sum = 0;

    for(int i=0; i < a.toString().length(); i++) {
      Integer v = a.toString().charAt(i) - '0';
      sum += (int) Math.pow(v, p);
    }

    if(sequenceNode.contains(sum)) {
      return sequenceNode.indexOf(sum);
    } else {
      sequenceNode.add(sum);
      return removeCycle(sequenceNode, sum, p);
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Integer a = sc.nextInt();
    Integer p = sc.nextInt();
    ArrayList<Integer> sequenceNode = new ArrayList<Integer>();
    sequenceNode.add(a);
    Integer ans = removeCycle(sequenceNode, a, p);
    System.out.println(ans);
  }

}
