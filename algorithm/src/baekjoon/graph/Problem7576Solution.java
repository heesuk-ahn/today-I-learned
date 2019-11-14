package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 *
 * 문제: 철수의 토마토 농장
 *
 * link) https://www.acmicpc.net/problem/7576
 *
 * 해결 포인트:
 *
 *  1) bfs에서 시작 지점이 여러 곳이 될 수 있는 경우이다. 이 경우에는 start point를 여러개를 주어서 queue에 방문할 곳으로 채워넣는다.
 *     방문하는 곳은 queue에서 차례대로 진행되기 때문에 A지점 -> B지점 -> A지점..순으로 차례대로 점점 확산될 갈 것이다.
 *
 *  2) bfs에서 특정 지점에서의 누적치가 아니라, 가장 큰 누적치를 찾아야 한다. 특정 지점의 목표가 있는 문제가 아니라, 모두가 익었을 때, 최소 기간
 *  이기때문에, 여러 지점에서 출발하여서 가운데 쯤에서 더이상 갈 곳이 없을 경우다. 이때 누적치는 가장 큰 값이 최소 기간이 된다.
 *
 *  3) 예외 경우, -1. 만약 방문도 못했는데 0인 경우가 있으면, 그 경우는 -1로 막혀서 방문하지 못한 것이다. 이 경우는 -1을 반환한다.
 *
 */

public class Problem7576Solution {

  public class Point {

    private final int xPoint;
    private final int yPoint;

    public Point(int xPoint, int yPoint) {
      this.xPoint = xPoint;
      this.yPoint = yPoint;
    }

    public int getXpoint() {
      return this.xPoint;
    }

    public int getYpoint() {
      return this.yPoint;
    }

  }

  private final int[] xDirection = {0, 0, 1, -1};
  private final int[] yDirection = {1, -1, 0, 0};

  public Integer findMaxValue(int row, int col, int[][] values) {
    int maxValue = 0;

    for(int r=0; r<row; r++) {
      for(int c=0; c<col; c++) {
        if(maxValue < values[r][c]) {
          maxValue = values[r][c];
        }
      }
    }
    return maxValue;
  }

  public Integer bfs(int row, int col, int[][] matrix) {
    boolean[][] visited = new boolean[row][col];
    int[][] dist = new int[row][col];
    Queue<Point> queue = new LinkedList<>();

    for(int r=0; r<row; r++) {
      for(int c=0; c<col; c++) {
        if(matrix[r][c] == 1) {
          queue.add(new Point(r, c));
          visited[r][c] = true;
          dist[r][c] = 0;
        }
      }
    }

    while(!queue.isEmpty()) {
      Point currPoint = queue.remove();
      int currX = currPoint.getXpoint();
      int currY = currPoint.getYpoint();

      //플러드 필 알고리즘 현재 위치의 상, 하, 좌, 우 모두 확인 후 이동할 루트로 넣어놓기.
      for(int k=0; k<4; k++) {
        int nextX = currX + xDirection[k];
        int nextY = currY + yDirection[k];

        boolean overFlowCheckForX = nextX >= 0 && nextX < row;
        boolean overFlowCheckForY = nextY >= 0 && nextY < col;
        int block = -1;

        if(overFlowCheckForX && overFlowCheckForY && !visited[nextX][nextY] && matrix[nextX][nextY] != block) {
          visited[nextX][nextY] = true;
          dist[nextX][nextY] = dist[currX][currY] + 1;
          queue.add(new Point(nextX, nextY));
        }
      }

    }

    int ans = findMaxValue(row, col, dist);

    for(int r=0; r<row; r++) {
      for(int c=0; c<col; c++) {
        if(!visited[r][c] && matrix[r][c] == 0) {
          ans = -1;
        }
      }
    }

    return ans;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int col = sc.nextInt();
    int row = sc.nextInt();
    int[][] matrix = new int[row][col];

    for(int r=0; r<row; r++) {
      for(int c=0; c<col; c++) {
        matrix[r][c] = sc.nextInt();
      }
    }

    Integer ans = new Problem7576Solution().bfs(row, col, matrix);
    System.out.println(ans);
  }

}
