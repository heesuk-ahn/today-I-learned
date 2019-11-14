package baekjoon.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 문제. 조세퍼스 - Baekjoon
 *
 * link) https://www.acmicpc.net/problem/1158
 *
 * sol)
 **  - 순환 하는 것은 Queue 자료구조를 이용하면 편하다
 *   - 앞에서 빼서 뒤로 넣으면 쉽게 구현이 가능하기 때문이다.
 * */
public class Problem1158Solution2 {

  String getJosephusValue2(Queue<Integer> queue, int kill) {
    StringBuilder sb = new StringBuilder();
    int length = queue.size();

    while(length-- > 0) {
      for(int i=1; i<kill; i++) {
        queue.add(queue.remove());
      }
      sb.append(queue.remove());
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int size = sc.nextInt();
    int kill = sc.nextInt();

    Queue<Integer> queue = new LinkedList<>();

    for(int i=0; i<size; i++) {
      queue.add(sc.nextInt());
    }

    System.out.println(new Problem1158Solution2().getJosephusValue2(queue, kill));

  }

}
