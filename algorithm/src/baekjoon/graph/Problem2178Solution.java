package baekjoon.graph;

import java.util.*;

/**
  복습) 2019. 08.04 1회

  백준 2178. 미로 탐색

  link) https://www.acmicpc.net/problem/2178

  문제해결 :

    BFS 탐색 + 최단 거리 (가중치가 1인 경우) 문제이다. 이미 해당 좌표에서의 최단거리는 가장 먼저 도착하는 루트에서의 길이 채우기 때문에
    dist[next] = dist[now] + 1이 항상 성립한다. 응용이 되는 문제로는 백준 2206번 '벽 부수고 이동하기'가 있다.

    1) "그래프 표현 - 인접 행렬"로 입력 받는다
    2) 인접한 노드가 벽이 아닌 경우 상, 하, 좌, 우 이동하면서 최적 거리를 위한 dist 채워나간다 (dist[next] = dist[now] + 1)
    3) 플러드필이 모두 완료되었을 때, dist의 맨 마지막을 출력한다.

 **/
public class Problem2178Solution {

    public class Point {

      private final Integer xPoint;
      private final Integer yPoint;

      public Point(Integer xPoint, Integer yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
      }

      public Integer getXpoint() {
        return this.xPoint;
      }

      public Integer getYpoint() {
        return this.yPoint;
      }

    }

    private final int[] xDirection = {0, 0, 1, -1};
    private final int[] yDirection = {1, -1, 0, 0};

    public Integer bfs(int[][] matrix, int row, int col) {
      boolean[][] visited = new boolean[row][col];
      int[][] dist = new int[row][col];
      Queue<Point> queue = new LinkedList<>();
      queue.add(new Point(0, 0));
      dist[0][0] = 1;
      visited[0][0] = true;

      while(!queue.isEmpty()) {
        Point pointXY = queue.remove();
        int currX = pointXY.getXpoint();
        int currY = pointXY.getYpoint();

        for(int i=0; i<4; i++) {
          int nextX = currX + xDirection[i];
          int nextY = currY + yDirection[i];

          boolean overFlowCheckForX = nextX >= 0 && nextX < row;
          boolean overFlowCheckForY = nextY >= 0 && nextY < col;

          if(overFlowCheckForX && overFlowCheckForY && !visited[nextX][nextY] && matrix[nextX][nextY] == 1) {
            dist[nextX][nextY] = dist[currX][currY] + 1;
            visited[nextX][nextY] = true;
            queue.add(new Point(nextX, nextY));
          }

        }
      }

      return dist[row-1][col-1];
    }

    public static void main(String[] args) {
      //step 1) "그래프 표현 - 인접행렬"을 통하여 입력을 받는다.
      Scanner sc = new Scanner(System.in);
      int row = sc.nextInt();
      int col = sc.nextInt();
      int[][] matrix = new int[row][col];

      for(int r=0; r<row; r++) {
        String line = sc.next();
        for(int c=0; c<col; c++) {
          matrix[r][c] = line.charAt(c) - '0';
        }
      }

      //step 2) bfs 순회를 한다
      //  step 2-1) 이때 bfs 순회에는 상,하,좌,우 이동이 필요하다.
      //  step 2-2) dist[x][y]를 통해 누적 값을 구해야 한다.
      //  step 2-3) 끝에 도달하면 리턴한다.
      //step 3) dist[x][y]의 값을 출력한다.
      Integer ans = new Problem2178Solution().bfs(matrix, row, col);
      System.out.println(ans);
    }

}
