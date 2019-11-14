package etc;

import java.util.*;
public class StackP {

  public String solution(String p) {
    Stack<Character> stack = new Stack<Character>();
    int count = 0;
    StringBuilder sb = new StringBuilder();
    int size = p.length();

    for(int i=0; i<size; i++) {
      char getChar = p.charAt(i);

      if(getChar == '(' && count == 0) {
        stack.push('(');
        sb.append('(');
      }
      else if(getChar == '(' && count > 0) {
        count -= 1;
        sb.append(')');
      }
      else if(getChar == ')' && !stack.isEmpty()) {
        stack.pop();
        sb.append(')');
      }
      else if(getChar == ')' && stack.isEmpty()) {
        sb.append('(');
        count += 1;
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) {
    String ans = new StackP().solution("()()))((()()(())))((");

    System.out.println(ans);
  }

}
