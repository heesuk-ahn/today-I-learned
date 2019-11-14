package baekjoon.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * 문제. 백준 2667. 단지 번호 붙이기
 *
 * 링크 : https://www.acmicpc.net/problem/2667
 *
 *********************************
 *
 * 문제 풀이 아이디어)
 *   먼저 이 문제는 '0'이라는 벽이 있기 때문에, 길이 막히게 된다. 그래프로 생각해보면, 이 문제는 '연결 요소'를 찾는 순회 방법을 사용하면 된다.
 *   왜냐면 0 때문에 그래프가 끊기게 되고, 여러 연결 요소들이 생기게 된다.
 *   이 문제는 그 연결 요소들의 갯수를 찾고, 그 연결 요소들의 길이를 정렬하여 출력하면 된다. (connected component)
 *
 *   connected component를 찾는 방법은 DFS 순회를 이용할 수 있다. 시작 노드에서 시작하여 더이상 갈 길이 없을 때 까지, 간 다음에 순회를
 *   종료한다. 그리고 순회를 종료하면 방문하지 않은 노드를 찾는다. 그리고 방문하지 않은 노드를 다시 시작점으로 하여 순회를 시작한다.
 *   이를 반복하다보면, 결국 방문하지 않은 모든 시작 노드를 통하여 순회를 완료하게 된다.
 *
 *   이 문제는 connected component 방법에서 순회를 할 때, 순회 방법에 대한 제한 조건을 걸어놓은 문제이다.
 *   상, 하, 좌, 우로만 순회할 수 있다는 것이다. 즉, DFS로 순회시에 상 하 좌 우 로만 이동하며, 이동할 수 있는 곳의 조건은
 *
 *   1) 아직 방문을 하지 않았을 것.
 *   2) 0이 아닐 것.
 *
 *   이다. 이 조건을 통하여 DFS를 순회하며, 모든 연결 요소에 대한 길이를 구하면 된다.
 */
public class Problem2667Solution {

  public static void searchCountInZone(int[][] matrix, boolean[][] visited, int currX, int currY, ArrayList<Integer> countNumbers) {
    //현재 지점에 방문 체크
    visited[currX][currY] = true;
    //이동 성공시 카운트를 센다.
    countNumbers.add(1);

    //상, 하, 좌, 우 DFS 깊이 우선으로 이동 순회한다.
    for(int i=-1; i< 2; i+=2) {
      int nextX = currX + i;
      int nextY = currY + i;

      if(nextX >= 0 && nextX < matrix.length && !visited[nextX][currY] && matrix[nextX][currY] != 0) {
        searchCountInZone(matrix, visited, nextX, currY, countNumbers);
      }
      if(nextY >= 0 && nextY < matrix.length && !visited[currX][nextY] && matrix[currX][nextY] != 0) {
        searchCountInZone(matrix, visited, currX, nextY, countNumbers);
      }
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    Integer lineNumber = sc.nextInt();
    int[][] matrix = new int[lineNumber][lineNumber];
    boolean[][] visited = new boolean[lineNumber][lineNumber];
    ArrayList<Integer> ans = new ArrayList<Integer>();

    for(int i=0; i<lineNumber; i++) {
      Arrays.fill(visited[i], false);
    }

    for(int row=0; row<lineNumber; row++) {
      String input =  sc.next();
      for(int col=0; col<lineNumber; col++) {
        matrix[row][col] = input.charAt(col) - '0';
      }
    }

    for(int startNodeForRow=0; startNodeForRow<lineNumber; startNodeForRow++) {
      for(int startNodeForCol=0; startNodeForCol<lineNumber; startNodeForCol++) {
        if(!visited[startNodeForRow][startNodeForCol] && matrix[startNodeForRow][startNodeForCol] != 0) {
          ArrayList<Integer> countNumbers = new ArrayList<>();
          searchCountInZone(matrix, visited, startNodeForRow, startNodeForCol, countNumbers);
          ans.add(countNumbers.size());
        }
      }
    }

    Collections.sort(ans);
    System.out.print(ans.size());
    for(Integer res: ans) {
      System.out.print("\n" + res);
    }
  }

}