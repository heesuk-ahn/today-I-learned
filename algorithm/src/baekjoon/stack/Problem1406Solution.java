package baekjoon.stack;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 *  문제. 백준 에디터
 *
 *  link) https://www.acmicpc.net/problem/1406
 *
 */
public class Problem1406Solution {

  String executeCommand(Stack<Character> stack, ArrayList<String> commands) {
    Stack<Character> tempStack = new Stack<>();

    for(String command: commands) {
      if (command.charAt(0) == 'L' && !stack.isEmpty()) {
        //커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
        tempStack.push(stack.pop());
      } else if (command.charAt(0) == 'D' && !tempStack.isEmpty()) {
        //커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
        stack.push(tempStack.pop());
      } else if (command.charAt(0) == 'B' && !stack.isEmpty()) {
        //커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
        //삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 실제로 커서의 오른쪽에 있던 문자는 그대로임
        stack.pop();
      } else if (command.charAt(0) == 'P'){
        //$라는 문자를 커서 왼쪽에 추가함
        Character newChar = command.charAt(2);
        stack.push(newChar);
      }
    }

    StringBuilder sb = new StringBuilder();

    for(char c: stack) {
      sb.append(c);
    }

    //stack에서 pop을 해서 넣었으니까 뺄때는 역으루 pop해서 빼야 순서가 맞음..!
    while(!tempStack.isEmpty()) {
      sb.append(tempStack.pop());
    }

    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
    //입력이 많을 때는 scanner 쓰면 시간초과가 날 수 있음.
    //scanner => BufferedReader
    //System.out.println => StringBuilder
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Stack<Character> textStack = new Stack<>();
    ArrayList<String> commands = new ArrayList<>();

    String textFromSystem = br.readLine();

    int textLength = textFromSystem.toCharArray().length;
    int commandCount = Integer.parseInt(br.readLine());

    for(int i=0; i<commandCount; i++) {
      String command = br.readLine();
      commands.add(command);
    }

    for(int i=0; i<textLength; i++) {
      textStack.add(textFromSystem.charAt(i));
    }


    System.out.println(new Problem1406Solution().executeCommand(textStack, commands));

  }

}
