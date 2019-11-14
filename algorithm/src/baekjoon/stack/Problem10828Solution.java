package baekjoon.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *  백준 스택 구현 10828번
 *
 *  link)
 */
public class Problem10828Solution {

  int[] stack = new int[10001];
  int pc = -1;

  void push(int x) {
    if(pc > 10000) {
      throw new StackOverflowError("stack over flow!");
    }
    pc+=1;
    stack[pc] = x;
  }

  int pop() {
    if(empty() == 1) {
      return -1;
    } else {
      int value = stack[pc];
      pc -= 1;
      return value;
    }
  }

  int size() {
    return pc+1;
  }

  int empty() {
    if (pc < 0) return 1; else return 0;
  }

  int top() {
    if(empty() == 1) {
      return -1;
    } else {
      return stack[pc];
    }
  }

  String executeStack(ArrayList<String> commands) {

    StringBuilder sb = new StringBuilder();

    for(String command: commands) {
      if(command.equals("top")) {
        sb.append(top() + "\n");
      } else if(command.equals("pop")) {
        sb.append(pop() + "\n");
      } else if(command.equals("size")) {
        sb.append(size() + "\n");
      } else if(command.equals("empty")) {
        sb.append(empty() + "\n");
      } else {
        int value = Integer.parseInt(command.split(" ")[1]);
        push(value);
      }
    }

    return sb.toString();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int commandCount = Integer.parseInt(br.readLine());
    ArrayList<String> commands = new ArrayList<>();

    for(int i=0; i<commandCount; i++) {
      String command = br.readLine();
      commands.add(command);
    }

    System.out.println(new Problem10828Solution().executeStack(commands));
  }

}
