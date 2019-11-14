package baekjoon.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 *  문제) 백준 쇠막대기 10799번
 *
 *  link) https://www.acmicpc.net/problem/10799
 *
 *  IDEA)
 *  방법 1)
 *    - '쌍'을 맞추는 문제기 때문에 STACK으로 풀 수 있다.
 *    - '('인 경우에는 STACK에 PUSH한다. 이때, '('를 넣을 때, +1을 stick count에 더해준다. 막대기가 새로 열린 것이기 때문이다.
 *    - '(' 다음에 ')'가 바로 온다면, 이것은 레이저 이므로, STACK SIZE 만큼을 stick count 결과 값에 더해준다. 단, 이때 STACK SIZE는
 *      이전에 늘어났으므로, pop을 통해 줄여줘야 하고, stick count도 -1 줄여줘야 한다.
 *  방법2)
 *    - 입력받는 문자의 index를 stack에 저장한다.
 *    - 이때, 인덱스의 차이가 1이라면, 레이저, 아니라면 막대의 끝을 의미한다.
 */
public class Problem10799Solution {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String line = sc.next();
    int length = line.toCharArray().length;
    Stack<Character> stack = new Stack<>();
    int stickCount = 0;
    char beforeChar = '(';

    for(int c=0; c< length; c++) {
      if(line.charAt(c) == '(') { // 막대가 열리는 경우
        stack.push(line.charAt(c));
        stickCount += 1;
        beforeChar = line.charAt(c);
      } else if(beforeChar == '(' && line.charAt(c) == ')'){ //레이저인 경우
        stack.pop();
        stickCount -= 1;
        stickCount += stack.size();
        beforeChar = line.charAt(c);
      } else if(beforeChar == ')' && line.charAt(c) == ')') { //레이저가 아니라, 막대가 닫히는 경우
        stack.pop();
      }
    }
    System.out.println(stickCount);
  }

}
