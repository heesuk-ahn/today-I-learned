package baekjoon.graph;

import java.util.*;

/**
 *
 *  복습) 2019. 08.04 1회
 *  오답) 1회
 *
 *  문제. 백준 2206번 '벽 부수고 이동하기'
 *
 *  link) https://www.acmicpc.net/problem/2206
 *
 *  idea)
 *
 *
 //층을 나누는 것이 중요하다. 2로 나눈 이유는 한번도 안깨고 온 경우, 1번꺠고온 경우이다.
 //층을 나누지 않으면, 문제가 발생할 수 있는데, 1번 깨고 어떤 지점에 미리 도착한 경우, 0번 깨고 온 경우는 무시되어 버리기 떄문이다.
 //즉, 0번깨고 온 최소거리, 1번꺠고 온 최소거리로 나누어 최종적으로 둘중에 하나를 골라줘야한다.
 *
 */
public class Problem2206Solution {

  public class PointXY {

    private final int xPoint;
    private final int yPoint;
    private final int breakerCount;

    public PointXY(int xPoint, int yPoint, int breakerCount) {
      this.xPoint = xPoint;
      this.yPoint = yPoint;
      this.breakerCount = breakerCount;
    }

    public int getXpoint() {
      return this.xPoint;
    }

    public int getYpoint() {
      return this.yPoint;
    }

    public int getBreakerCount() {
      return this.breakerCount;
    }

  }

  final int[] xDirection = {0, 0, 1, -1};
  final int[] yDirection = {1, -1, 0, 0};

  public int bfs(int row, int col, int[][] matrix, int[][][] dist) {
    Queue<PointXY> queue = new LinkedList<>();
    boolean[][][] visited = new boolean[row][col][2];
    queue.add(new PointXY(0, 0, 0));
    visited[0][0][0] = true;
    dist[0][0][0] = 1;

    //플러드 필 => 상 하 좌 우 순회
    while(!queue.isEmpty()) {
      PointXY pointXY = queue.remove();
      int currX = pointXY.getXpoint();
      int currY = pointXY.getYpoint();
      int currBreakerCount = pointXY.getBreakerCount();

      for(int k=0; k<4; k++) {
        int nextX = currX + xDirection[k];
        int nextY = currY + yDirection[k];

        boolean overFlowCheckForX = nextX >= 0 && nextX < row;
        boolean overFlowCheckForY = nextY >= 0 && nextY < col;

        if(overFlowCheckForX && overFlowCheckForY && !visited[nextX][nextY][currBreakerCount] && matrix[nextX][nextY] != 1) {
          visited[nextX][nextY][currBreakerCount] = true;
          dist[nextX][nextY][currBreakerCount] = dist[currX][currY][currBreakerCount] + 1;
          queue.add(new PointXY(nextX, nextY, currBreakerCount));
        }
        if(overFlowCheckForX && overFlowCheckForY && !visited[nextX][nextY][currBreakerCount] && matrix[nextX][nextY] == 1 && currBreakerCount < 1) {
          visited[nextX][nextY][currBreakerCount] = true;
          int nextCurrBreakerCount = currBreakerCount + 1;
          dist[nextX][nextY][nextCurrBreakerCount] = dist[currX][currY][currBreakerCount] + 1;
          queue.add(new PointXY(nextX, nextY, nextCurrBreakerCount));
        }
      }
    }

    int ansForZeroBreaker = dist[row-1][col-1][0] != 0 ? dist[row-1][col-1][0] : Integer.MAX_VALUE;
    int ansForOneBreaker = dist[row-1][col-1][1] != 0 ? dist[row-1][col-1][1] : Integer.MAX_VALUE;

    return Math.min(ansForZeroBreaker, ansForOneBreaker);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int row = sc.nextInt();
    int col = sc.nextInt();
    int[][] matrix = new int[row][col];
    int[][][] distAndBreaker = new int[row][col][2];

    for(int r=0; r<row; r++) {
      String line = sc.next();
      for(int c=0; c<col; c++) {
        matrix[r][c] = line.charAt(c) - '0';
      }
    }

    int getDist = new Problem2206Solution().bfs(row, col, matrix, distAndBreaker);
    int ans = getDist == Integer.MAX_VALUE ? -1 : getDist;

    System.out.println(ans);
  }

}
