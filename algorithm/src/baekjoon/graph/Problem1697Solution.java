package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * 복습) 2019. 08.04 1회
 *
 * 백준 1697. 숨바꼭질
 *
 * link) https://www.acmicpc.net/problem/1697
 *
 * 해결)
 *  - 1차원 좌표지만, 해당 좌표에서 앞으로 (+1), 뒤로(-1), 2배 앞으로(*2) 로 왔다갔다 할 수 있고, 최단 거리를 구하는 것이므로
 *    BFS로 모든 경우의 수를 구할 수 있다.
 *  - 단, 이미 방문한 곳은 또 방문을 할 수 없도록 방문 테이블을 선언해서 동일한 연산이 반복되지 않도록 한다.
 **/
public class Problem1697Solution {

  public class MyPositionHistory {

    private final int currX;
    private final int commandCount;

    public MyPositionHistory(int currX, int commandCount) {
      this.currX = currX;
      this.commandCount = commandCount;
    }

    public int getCurrX() {
      return this.currX;
    }

    public int getCommandCount() {
      return this.commandCount;
    }

  }

  public TreeSet<Integer> getMinimumCount(TreeSet<Integer> ans, int myPosition, int targetPosition) {
    //step 2) bfs를 통해 현재 위치와 이동할 수 있는 커맨드 3개를 입력한다
    int maximumInputSize = 100001;
    boolean[] visited = new boolean[maximumInputSize]; //들어올 수 있는 가장 큰 값.

    //step 2-1) 현재 위치 정보와 수행한 커맨드 수를 기억하는 자료구조를 이용한다.
    Queue<MyPositionHistory> queue = new LinkedList<>();
    //step 2-2) 현재 시작점 위치 정보를 queue에 넣는다.
    queue.add(new MyPositionHistory(myPosition, 0));
    visited[myPosition] = true;

    //step 2-3) queue가 비지 않을때 까지 반복한다
    while (!queue.isEmpty()) {
      MyPositionHistory currPositionHistory = queue.remove();
      int currX = currPositionHistory.getCurrX();
      int currCommandCount = currPositionHistory.getCommandCount();
      int beforeX = currX - 1;
      int nextXplus = currX + 1;
      int nextMultiply = currX * 2;

      boolean overflowCheckForMinus = beforeX >= 0 && beforeX < maximumInputSize;
      boolean overFlowCheckForPlus = nextXplus >= 0 && nextXplus < maximumInputSize;
      boolean overFlowCheckForMultiply = nextMultiply >=0 && nextMultiply < maximumInputSize;

      //동생 위치에 도달했는가 체크
      if(currX == targetPosition) {
        ans.add(currCommandCount);
        break;
      }

      //step 2-4) 현재 위치 -1을 한다.
      if(overflowCheckForMinus && !visited[beforeX]) {
        visited[beforeX] = true;
        queue.add(new MyPositionHistory(beforeX, currCommandCount + 1));
      }
      //step 2-5) 현재 위치 *2를 실행한다.
      if(overFlowCheckForMultiply && !visited[nextMultiply]) {
        visited[nextMultiply] = true;
        queue.add(new MyPositionHistory(nextMultiply, currCommandCount + 1));
      }
      //step 2-6) 현재 위치 + 1을 실행한다.
      if(overFlowCheckForPlus && !visited[nextXplus]) {
        visited[nextXplus] = true;
        queue.add(new MyPositionHistory(nextXplus, currCommandCount + 1));
      }
    }

    return ans;
  }


  public static void main(String[] args) {

    //step 1) 수빈이의 위치와 동생의 위치를 입력받는다
    Scanner sc = new Scanner(System.in);

    int myPosition = sc.nextInt();
    int targetPosition = sc.nextInt();
    TreeSet<Integer> ans = new TreeSet<>();

    //step 3) 위에서 작업이 다 끝나고 나면, 현재 커맨드 카운트 정보를 반환한다.
    System.out.println(new Problem1697Solution().getMinimumCount(ans, myPosition, targetPosition).first());
  }

}
