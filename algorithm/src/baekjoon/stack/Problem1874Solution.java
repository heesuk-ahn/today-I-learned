package baekjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 문제. 백준 스택 수열
 *
 * link) https://www.acmicpc.net/problem/1874
 */
public class Problem1874Solution {

  private final Character push = '+';
  private final Character pop = '-';

  void isStackSequence(LinkedList<Integer> sortedValue, LinkedList<Integer> expectedValue, Stack<Integer> stack, StringBuilder sb) {
    if(!stack.isEmpty() && stack.peek().equals(expectedValue.getFirst())) {
      expectedValue.remove();
      stack.pop();
      sb.append(pop + "\n");
      isStackSequence(sortedValue, expectedValue, stack, sb);
    } else if(!sortedValue.isEmpty()){
      stack.push(sortedValue.remove());
      sb.append(push + "\n");
      isStackSequence(sortedValue, expectedValue, stack, sb);

    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = Integer.parseInt(br.readLine());
    LinkedList<Integer> arr = new LinkedList<Integer>();
    Stack<Integer> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();

    for(int i=0; i<count; i++) {
      arr.add(Integer.parseInt(br.readLine()));
    }

    LinkedList<Integer> expectedArr = (LinkedList<Integer>) arr.clone();

    Collections.sort(arr);


    new Problem1874Solution().isStackSequence(arr, expectedArr, stack, sb);

    if(stack.isEmpty()) {
      System.out.println(sb.toString());
    } else {
      System.out.println("NO");
    }

  }

}
