package baekjoon.graph;


import leetcode.Problem162Solution;

import java.util.*;

/**
 * 복습 2019. 08.04 1회
 *
 * 2146. 다리만들기
 *
 * link) https://www.acmicpc.net/problem/2146
 *
 */
public class Problem2146Solution {

  //x 좌표, y 좌표를 위한 객체이다.
  public class Pointer {

    private final int xPoint;
    private final int yPoint;

    public Pointer(int xPoint, int yPoint) {
      this.xPoint = xPoint;
      this.yPoint = yPoint;
    }

    public int getxPoint() {
      return xPoint;
    }

    public int getyPoint() {
      return yPoint;
    }

  }

  //방향을 나타낸다.
  final int[] xDirection = {0, 0, 1, -1};
  final int[] yDirection = {1, -1, 0, 0};

  //step 2) 각 섬에 DFS를 이용하여 라벨링을 한다. 연결 요소를 사용하여 모든 단절된 그래프를 라벨링 할 수 있다.
  public void dfsForLabel(int matrixSize, int[][] matrix, int labelNumber, boolean[][] visited, Pointer point) {

    int currX = point.getxPoint();
    int currY = point.getyPoint();

    visited[currX][currY] = true;
    matrix[currX][currY] = labelNumber;

    //플러드 필 : 함수 하나의 호출 당 4번씩 call을 하게 된다.
    for(int k=0; k<4; k++) {
      int nextX = currX + xDirection[k];
      int nextY = currY + yDirection[k];

      boolean overFlowCheckForX = nextX >= 0 && nextX < matrixSize;
      boolean overFlowCheckForY = nextY >= 0 && nextY < matrixSize;

      //4번의 콜중에서 다음으로 갈 수 있는 것은 아래와 같이 방문하지 않았고, 갈 수 있는 길인 경우 뿐이다.
      if(overFlowCheckForX && overFlowCheckForY && !visited[nextX][nextY] && matrix[nextX][nextY] != 0) {
        dfsForLabel(matrixSize, matrix, labelNumber, visited, new Pointer(nextX, nextY));
      }
    }

  }

  //step 3) BFS로 영역을 확장해가며, DIST에 저장한다. 더이상 이동할 곳이 없을 때까지 진행된다.
  public void bfsForExtend(int matrixSize, int[][] matrix, int[][] dist) {
    //step 3-1) BFS로 확장을 위해 방문 테이블을 생성한다.
    boolean[][] visited = new boolean[matrixSize][matrixSize];
    //step 3-2) BFS를 순차적으로 실행하기 위한 Queue<Pointer>를 생성한다
    Queue<Pointer> queue = new LinkedList<>();
    //step 3-3) 모든 섬의 좌표를 queue에 넣는다.
    for(int r=0; r<matrixSize; r++) {
      for(int c=0; c<matrixSize; c++) {
        if(matrix[r][c] != 0) {
          queue.add(new Pointer(r, c));
          dist[r][c] = 0;
          visited[r][c] = true;
        }
      }
    }
    //step 3-4) 모든 섬의 좌표를 하나씩 꺼내면서 상, 하, 좌, 우 이동을하며 dist를 채운다.
    while(!queue.isEmpty()) {
      Pointer pointer = queue.remove();
      int currX = pointer.getxPoint();
      int currY = pointer.getyPoint();

      for(int k=0; k<4; k++) {
        int nextX = currX + xDirection[k];
        int nextY = currY + yDirection[k];

        boolean overFlowCheckForX = nextX >= 0 && nextX < matrixSize;
        boolean overFlowCheckForY = nextY >=0 && nextY < matrixSize;

        //플러드 필이기 때문에, 한 함수당 상,하,좌,우 방문을 하지만, 입장할 수 있는 것은 아래 조건문을 통과하는 경우만 이다.
        //바다인 경우에만 갈 수 있는데, 이유는 다른 섬까지 구하는게 아니라, 섬과 섬 사이의 거리만 구하기 때문이다.
        if(overFlowCheckForX && overFlowCheckForY && !visited[nextX][nextY] && matrix[nextX][nextY] == 0) {
          visited[nextX][nextY] = true;
          dist[nextX][nextY] = dist[currX][currY] + 1;
          matrix[nextX][nextY] = matrix[currX][currY]; //간척 사업의 포인트. matrix 자체도 수정한다.
          queue.add(new Pointer(nextX, nextY));
        }
      }
    }
  }

  public static void main(String[] args) {
    //step 1) "그래프 표현 - 인접 행렬" 을 이용하여 그래프로 입력받는다.
    Scanner sc = new Scanner(System.in);
    int matrixSize = sc.nextInt();
    int[][] matrix = new int[matrixSize][matrixSize];
    boolean[][] visited = new boolean[matrixSize][matrixSize];
    int labelNumber = 1;
    //자동 오름차순 정렬이 된다.
    TreeSet<Integer> ans = new TreeSet<>();

    for(int r=0; r<matrixSize; r++) {
      for(int c=0; c<matrixSize; c++) {
        matrix[r][c] = sc.nextInt();
      }
    }

    //step 2) 각 섬에 DFS를 이용하여 라벨링을 한다. 연결 요소를 사용하여 모든 단절된 그래프를 라벨링 할 수 있다.
    for(int r=0; r<matrixSize; r++) {
      for(int c=0; c<matrixSize; c++) {
        if(matrix[r][c] != 0 && !visited[r][c]) {
          new Problem2146Solution().dfsForLabel(matrixSize, matrix, labelNumber++, visited, new Problem2146Solution().new Pointer(r, c));
        }
      }
    }

    //step 3) BFS로 영역을 확장해가며, DIST에 저장한다. 더이상 이동할 곳이 없을 때까지 진행된다.
    int[][] dist = new int[matrixSize][matrixSize];
    new Problem2146Solution().bfsForExtend(matrixSize, matrix, dist);
    //위의 BFS를 통해 플러드필을 하게 되면, dist에는 섬과 섬 사이의 거리들이 저장되어있다.

    //step 4) 모든 matrix를 방문하며, 상,하,좌,우 충돌지점을 찾는다. 충돌지점을 찾는다면, 충돌 지점의 값을 ans list에 담는다.
    for(int r=0; r<matrixSize; r++) {
      for(int c=0; c<matrixSize; c++) {
        for(int k=0; k<4; k++) {
          int nextX = r +  new Problem2146Solution().xDirection[k];
          int nextY = c + new Problem2146Solution().yDirection[k];
          boolean overFlowCheckForX = nextX >= 0 && nextX < matrixSize;
          boolean overFlowCheckForY = nextY >= 0 && nextY < matrixSize;
          //step 4-1) 현재 매트릭스가 다음에 갈 매트릭스 값과 다를 경우는 충돌이다. 이 경우 충돌나는 두 지점의 dist 값을 더한다.
          if(overFlowCheckForX && overFlowCheckForY && matrix[r][c] != matrix[nextX][nextY]) {
            int sumDist = dist[r][c] + dist[nextX][nextY];
            ans.add(sumDist);
          }
        }
      }
    }

    //step 5) ans list를 sort하여 가장 작은 값을 출력한다.
    System.out.println(ans.first());
  }

}
