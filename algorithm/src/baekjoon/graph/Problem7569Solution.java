package baekjoon.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 복습) 2019.8.5 1회
 * 오답) 2회
 *
 * 문제) 백준 7569. 토마토 3D
 *
 * link) https://www.acmicpc.net/problem/7569
 *
 */
public class Problem7569Solution {

  public class PointXYZ {

    private final int x;
    private final int y;
    private final int z;

    public PointXYZ(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

    public int getZ() {
      return z;
    }

  }

  final int[] xDirection = {0, 0, 1, -1};
  final int[] yDirection = {1, -1, 0, 0};
  final int[] zDirection = {0, 1, -1};

  void bfs(int[][][] matrix, boolean[][][] visited, Queue<PointXYZ> queue, int row, int col, int height, int[][][] dist) {

    while(!queue.isEmpty()) {
      PointXYZ pointXYZ = queue.remove();
      int currX = pointXYZ.getX();
      int currY = pointXYZ.getY();
      int currZ = pointXYZ.getZ();

      for(int k=0; k<4; k++) {
        int nextX = currX + xDirection[k];
        int nextY = currY + yDirection[k];

        boolean overFlowCheckForX = nextX >=0 && nextX < row;
        boolean overFlowCheckForY = nextY >=0 && nextY < col;

        for(int z=0; z<3; z++) {
          int nextZ = currZ + zDirection[z];
          boolean overFlowCheckForZ = nextZ >= 0 && nextZ < height;
          if(overFlowCheckForX && overFlowCheckForY && overFlowCheckForZ && !visited[nextX][nextY][nextZ] && matrix[nextX][nextY][nextZ] != -1) {
            visited[nextX][nextY][nextZ] = true;
            matrix[nextX][nextY][nextZ] = 1;
            dist[nextX][nextY][nextZ] = dist[currX][currY][currZ] + 1;
            queue.add(new PointXYZ(nextX, nextY, nextZ));
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int col = sc.nextInt();
    int row = sc.nextInt();
    int height = sc.nextInt();
    int[][][] matrix = new int[row][col][height];
    boolean[][][] visited = new boolean[row][col][height];
    int[][][] dist = new int[row][col][height];

    Queue<PointXYZ> queue = new LinkedList<>();

    for(int h=0; h<height; h++) {
      for(int r=0; r<row; r++) {
        for(int c=0; c<col; c++) {
          matrix[r][c][h] = sc.nextInt();
        }
      }
    }

    for(int h=0; h<height; h++) {
      for(int r=0; r<row; r++) {
        for(int c=0; c<col; c++) {
          if(matrix[r][c][h] == 1) {
            queue.add(new Problem7569Solution().new PointXYZ(r, c, h));
            visited[r][c][h] = true;
            dist[r][c][h] = 1;
          }
        }
      }
    }

    new Problem7569Solution().bfs(matrix, visited, queue, row, col, height, dist);

    int getAns = -1;

    for(int r=0; r<row; r++) {
      for(int c=0; c<col; c++) {
        for(int h=0; h<height; h++) {
          if(visited[r][c][h] && matrix[r][c][h] == 1) {
            getAns = Math.max(dist[r][c][h], getAns);
          }
        }
      }
    }

    for(int r=0; r<row; r++) {
      for(int c=0; c<col; c++) {
        for(int h=0; h<height; h++) {
          if(!visited[r][c][h] && matrix[r][c][h] == 0) {
            getAns = -1;
          }
        }
      }
    }

    System.out.println(getAns);

  }

}
