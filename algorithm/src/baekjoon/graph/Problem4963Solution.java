package baekjoon.graph;

import java.util.*;

/**
 *
 *  복습) 2019.8.5 1회
 *  문제) 백준 4963. 섬의 개수 구하기
 *
 *  link) https://www.acmicpc.net/problem/4963
 *
 * core concept :
 *   - 그래프에서 8방향에 대해 순회하는 방법에 대해서 알아야 한다.
 *   - 그래프에서 4방향에 대해 순회하는 방법에 대해서 알야아 한다.
 *   - 그래프에서 DFS로 순회하기
 *   - 그래프에서 BFS로 순회하기
 *   - 그래프에서 연결 요소 구하는 법을 알아야 한다.
 */
public class Problem4963Solution {

  final int[] xDirection = {0, 0, 1, -1, -1, 1, -1, 1};
  final int[] yDirection = {1, -1, 0, 0, 1, 1, -1, -1};

  void dfs(int[][] matrix, int row, int col, boolean[][] visitied, int currX, int currY) {

    visitied[currX][currY] = true;

    for(int k=0; k<8; k++) {
      int nextX = currX + xDirection[k];
      int nextY = currY + yDirection[k];

      boolean overFlowCheckForX = nextX >= 0 && nextX < row;
      boolean overFlowCheckForY = nextY >= 0 && nextY < col;

      if(overFlowCheckForX && overFlowCheckForY && !visitied[nextX][nextY] && matrix[nextX][nextY] != 0) {
        dfs(matrix, row, col, visitied, nextX, nextY);
      }
    }

  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    while(true) {
      int col = sc.nextInt();
      int row = sc.nextInt();

      if(row == 0 && col == 0) {
        break;
      }

      int[][] matrix = new int[row][col];
      boolean[][] visited = new boolean[row][col];
      int ans = 0;

      for(int r=0; r<row; r++) {
        for(int c=0; c<col; c++) {
          matrix[r][c] = sc.nextInt();
        }
      }

      for(int r=0; r<row; r++) {
        for(int c=0; c<col; c++) {
          if(!visited[r][c] && matrix[r][c] != 0) {
            new Problem4963Solution().dfs(matrix, row, col, visited, r, c);
            ans ++;
          }
        }
      }

      System.out.println(ans);
    }

  }

}
