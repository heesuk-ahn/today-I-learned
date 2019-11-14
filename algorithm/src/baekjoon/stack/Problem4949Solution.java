package baekjoon.stack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 문제. 백준 균형잡힌 세상 4949
 *
 * link) https://www.acmicpc.net/problem/4949
 *
 */
public class Problem4949Solution {

  String isBalanced(String line) {
    Stack<Character> bracketStack = new Stack<>();
    char[] charArr = line.toCharArray();

    final char openSmallBracket = '(';
    final char closeSmallBracket = ')';
    final char openBigBracket = '[';
    final char closeBigBracket = ']';
    int notBalancedCount = 0;

    for(char c: charArr) {
      if(c == openSmallBracket || c == openBigBracket) {
        bracketStack.push(c);
      } else if((c == closeSmallBracket || c == closeBigBracket) && !bracketStack.isEmpty()) {
        char popChar = bracketStack.pop();
        char oppositeChar = c == closeBigBracket ? openBigBracket : openSmallBracket;
        if(popChar != oppositeChar) {
          notBalancedCount += 1;
          break;
        }
      } else if((c == closeSmallBracket || c == closeBigBracket) && bracketStack.isEmpty()) {
          notBalancedCount += 1;
          break;
      }
    }

    String ans = notBalancedCount == 0 && bracketStack.isEmpty() ? "yes" : "no";

    return ans;
  }

  public static void main(String[] args) throws IOException  {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while(true) {
      String line = br.readLine();

      if(line.length() == 1 && line.equals(".")) {
        break;
      }

      System.out.println(new Problem4949Solution().isBalanced(line));
    }
  }

}
