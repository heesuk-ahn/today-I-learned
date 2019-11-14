package baekjoon.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * 문제) 백준 괄호
 *
 * link) https://www.acmicpc.net/problem/9012
 *
 * 문제 이해)
 *  - 단순히 '(', ')' 갯수를 세어 비교하는 걸로는 정확히 열고 닫는 쌍이 맞는지 알 수 없다. ex.반례 "())(()"
 *
 *  해답 )
 *    - count 방식 : https://gist.github.com/Baekjoon/c5052cc44c9ccc15733b
 *
 */
public class Problem9012Solution {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int testCase = sc.nextInt();

    for(int t=0; t<testCase; t++) {
      String ps = sc.next();
      Stack<Character> stack = new Stack();
      int ans = 0;

      for(int p=0; p< ps.length(); p++) {
        if(ps.charAt(p) == '(') {
          stack.push(ps.charAt(p));
        } else {
          if(!stack.isEmpty()) {
            stack.pop();
          } else {
            ans += 1;
          }
        }
      }

      if(ans == 0 && stack.isEmpty()) {
        System.out.println("YES");
      } else {
        System.out.println("NO");
      }

    }

  }

}
