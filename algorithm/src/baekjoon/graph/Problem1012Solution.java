package baekjoon.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
  문제. 백준 1012 "유기농 배추"

  link) https://www.acmicpc.net/problem/1012

  해결책)

    이 문제는

    - 그래프의 연결 요소를 구하라
    - 인접 배열 그래프 표현에서 상, 하, 좌, 우로 이동하라

   주로 BFS 문제 풀이에서 활용하게 되는 2차원 배열 상,하,좌,우 이동 익숙하지 않은 많은 사람들(나 포함)이 처음엔 if문을 잔뜩 써서 next 이동 방향을 정하기 일쑤다.
   이 상황에서 활용할 수 있는 좋은 방법이 있다.

   1) direction Array 만들기

   2) 반복문을 사용하여 상하좌우 이동하도록 하기
**/
public class Problem1012Solution {

  public class TraverseDFS {

    int row;
    int col;
    int[][] matrix;
    boolean[][] visited;

    public TraverseDFS(int row, int col, int[][] matrix, boolean[][] visited) {
      this.row = row;
      this.col = col;
      this.matrix = matrix;
      this.visited = visited;
    }

    public void dfs(int currX, int currY) {
      visited[currX][currY] = true;

      for(int i=-1; i< 2; i+=2) {
        int nextX = currX + i;
        int nextY = currY + i;

        boolean overflowCheckX = nextX >=0 && nextX < row;
        boolean overflowCheckY = nextY >=0 && nextY < col;

        if(overflowCheckX && !visited[nextX][currY] && matrix[nextX][currY] == 1) {
          dfs(nextX, currY);
        }
        if(overflowCheckY && !visited[currX][nextY] && matrix[currX][nextY] == 1) {
          dfs(currX, nextY);
        }
      }
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Integer testCase = sc.nextInt();

    for(int i=0; i<testCase; i++) {
      Integer row = sc.nextInt();
      Integer col = sc.nextInt();
      Integer edgeCount = sc.nextInt();

      int[][] matrix = new int[row][col];

      for(int k=0; k<row; k++) {
        Arrays.fill(matrix[k], 0);
      }

      for(int j=0; j<edgeCount; j++) {
        Integer x = sc.nextInt();
        Integer y = sc.nextInt();
        matrix[x][y] = 1;
      }

      boolean[][] visited = new boolean[row][col];

      int ansCount = 0;

      //연결 요소의 핵심. 방문하지 않은 곳을 순회한다.
      for(int r=0; r<row; r++) {
        for(int c=0; c<col; c++) {
          if(matrix[r][c] == 1 && !visited[r][c]) {
            new Problem1012Solution().new TraverseDFS(row, col, matrix, visited).dfs(r, c);
            ansCount += 1;
          }
        }
      }
      System.out.println(ansCount);
    }
  }

}
