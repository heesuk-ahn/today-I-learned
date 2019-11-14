package baekjoon.graph;

import java.util.ArrayList;
import java.util.Scanner;

/*

문제) 백준 2606. 웜바이러스

link) https://www.acmicpc.net/problem/2606

sol)
  이 문제는 대표적으로 DFS를 한번 순회해서 순회하는 count를 세는 문제이다. 기본적으로 DFS 순회를 할 수 있으면 풀리는 문제이고,
그래프에서 연결 요소를 푸는 문제는 아니다.
 */
public class Problem2606Solution {

  public class TraverseDFS {

    ArrayList<ArrayList<Integer>> graph;
    Integer startNode;
    Integer nodeCount;

    public TraverseDFS(ArrayList<ArrayList<Integer>> graph, Integer startNode, Integer nodeCount) {
      this.graph = graph;
      this.startNode = startNode;
      this.nodeCount = nodeCount;
    }

    public Integer getInfectionCount() {
      boolean[] visited = new boolean[nodeCount + 1];
      Integer count = 0;
      getInfectionCountForRec(visited, startNode, count);

      for(boolean b: visited) {
        if (b) {
          count += 1;
        }
      }

      return count;
    }

    private void getInfectionCountForRec(boolean[] visited, Integer currNode, Integer count) {
      visited[currNode] = true;

      for(int i=0; i < graph.get(currNode).size(); i++) {
        Integer adjNode = graph.get(currNode).get(i);

        if(!visited[adjNode]) {
          count = count + 1;
          getInfectionCountForRec(visited, adjNode, count);
        }
      }

    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    //step 1) graph 표현 - 인접리스트
    int computerCount = sc.nextInt();
    int edgeCount = sc.nextInt();
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    for(int i=0; i<=computerCount; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i=0; i<edgeCount; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();

      graph.get(u).add(v);
      graph.get(v).add(u);
    }

/*
7
6
1 2
2 3
1 5
5 2
5 6
4 7
*/

    //step 2) DFS를 통해 순회를 하면서 start node를 제외한 count를 센다
    Integer resCount = new Problem2606Solution().new TraverseDFS(graph, 1, computerCount).getInfectionCount();

    System.out.println(resCount-1);
  }

}
