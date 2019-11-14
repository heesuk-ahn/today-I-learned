package baekjoon.graph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 복습) 2019. 08.04 1회
 * 틀린 횟수) 2회
 *
 * 백준 - 이분 그래프 찾기
 *
 * link - https://www.acmicpc.net/problem/1707
 *
 */
public class Problem1707Solution {

  private final static Integer BLUE_COLOR = 1;
  private final static Integer RED_COLOR = 2;

  public static class Color {

    private Integer selfColor;
    private Integer oppositeColor;

    public Color(Integer selfColor, Integer oppositeColor) {
      this.selfColor = selfColor;
      this.oppositeColor = oppositeColor;
    }

  }

  public static void dfs(ArrayList<Integer>[] graph, int[] visited, Integer currNode, Color color) {
    visited[currNode] = color.selfColor;

    //모두다 방문을 한다.
    for(int i=0; i < graph[currNode].size(); i++) {
      if(visited[graph[currNode].get(i)] == 0) { //인접 노드 방문은 현재 색과 다르게 한다.
        dfs(graph, visited, graph[currNode].get(i), new Color(color.oppositeColor, color.selfColor));
      }
    }

  }

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    Scanner sc = new Scanner(System.in);
    int testCase = sc.nextInt();

    for(int i=0; i<testCase; i++) {
      int nodeNumber = sc.nextInt();
      int edgeNumber = sc.nextInt();
      ArrayList<Integer>[] graph = new ArrayList[nodeNumber+1];

      for(int k=1; k<= nodeNumber; k++) {
        graph[k] = new ArrayList<>();
      }

      for(int j=0; j<edgeNumber; j++) {
        int u = sc.nextInt();
        int v = sc.nextInt();

        graph[u].add(v);
        graph[v].add(u);
      }

      int[] visited = new int[nodeNumber + 1];

      for(int s: visited) {
        visited[s] = 0;
      }

      /**
       * 연결 그래프와 마찬가지로 그래프가 중간에 끊길 수 있으므로, 시작 정점을 항상 순회해서 찾아서 모두 방문해주어야 한다.
       */
      for (int a=1; a<=nodeNumber; a++) {
        if (visited[a] == 0) {
          dfs(graph, visited, a, new Color(BLUE_COLOR, RED_COLOR));
        }
      }

      String ans = "YES";

      /**
       * 이분 그래프에서 판단법의 핵심은 "인접 노드가 나와 색이 다른가?"를 검증하는 것이다.
       * 양 끝점의 색이 같을 경우, 이분 그래프가 아니다. 각 노드의 인접은 항상 다른 컬러여야 한다.
       */
      for(int s=1; s < graph.length; s++) {
        for(int z=0; z < graph[s].size(); z++) { //모든 인접 노드를 순회
         if (visited[s] == visited[graph[s].get(z)]) { //인접 노드의 색이 현재 노드의 색과 같다면
           ans = "NO"; //NO
           //이렇게 될 수 있는 이유는 방문을 하면서 색을 이미 칠했다면 방문을 하지 않기때문.
         }
        }
      }
      sb.append("\n" + ans);
    }

    System.out.print(sb.toString().trim());
  }

}
