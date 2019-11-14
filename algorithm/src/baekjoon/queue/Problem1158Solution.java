package baekjoon.queue;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 문제. 조세퍼스 - Baekjoon
 *
 * link) https://www.acmicpc.net/problem/1158
 *
 * sol)
 *
 *  - list와 순환하는 포인터를 이용해서 해결.
 *  - Queue를 이용해서 앞에서 빼고 뒤로 넣는 방식으로도 해결 가능.
 *
 * */
public class Problem1158Solution {

  String getJosephusValue(LinkedList<Integer> list, int pc, int removePosition, StringBuilder sb) {
    sb.append("<");

    while(list.size() != 1) {
      for(int i=1; i<removePosition; i++) {
        pc += 1;
      }
      int r = pc % list.size();
      sb.append(list.get(r) + "," + " ");
      list.remove(r);
      pc = r;
    }

    sb.append(list.remove() + ">");

    return sb.toString();
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int personNumber = sc.nextInt();
    int removePosition = sc.nextInt();
    LinkedList<Integer> queue = new LinkedList<>();

    for(int i=1; i<=personNumber; i++) {
      queue.add(i);
    }

    System.out.println(new Problem1158Solution().getJosephusValue(queue, 0, removePosition, new StringBuilder()));

  }

}
