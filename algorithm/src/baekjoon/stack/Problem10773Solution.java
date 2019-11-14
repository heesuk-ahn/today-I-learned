package baekjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 *
 * 문제. 백준 제로
 *
 * link) https://www.acmicpc.net/probleam/10773
 *
 * 아이디어)
 *  - 마지막에 들어간 것이 0일 경우 나오는 것이기 때문에 스택이 적합.
 *
 */
public class Problem10773Solution {

  Integer stackSum(ArrayList<Integer> memo) {

    Stack<Integer> stack = new Stack();
    int sum = 0;

    for(int m: memo) {
      if(m == 0) {
        stack.pop();
      } else {
        stack.push(m);
      }
    }

    for(int s: stack) {
      sum += s;
    }

    return sum;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = Integer.parseInt(br.readLine());
    ArrayList<Integer> memo = new ArrayList<>();

    for(int i=0; i<count; i++) {
      memo.add(Integer.parseInt(br.readLine()));
    }

    System.out.println(new Problem10773Solution().stackSum(memo));
  }

}
