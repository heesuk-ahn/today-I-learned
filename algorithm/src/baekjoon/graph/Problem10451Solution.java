package baekjoon.graph;


import java.util.Scanner;

/**
 * 순열 사이클
 *
 * - 순열이 주어졌을 때, 순열 사이클의 개수를 찾는 문제
 * - DFS를 이용해서 이미 방문했던, 수를 방문하면 return 하는 방식으로 풀 수 있다.
 *
 */
public class Problem10451Solution {

  public static void dfs(int[] sequenceArr, boolean[] visited, int currNode) {
    visited[currNode] = true;

    // 순열이라 무조건 1:1 대응이기 때문에, 반복문으로 순회할 필요가 없다.
    if(!visited[sequenceArr[currNode]]) {
      dfs(sequenceArr, visited, sequenceArr[currNode]);
    }
  }

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();
    Scanner sc = new Scanner(System.in);

    int testCase = sc.nextInt();

    while(testCase-- > 0) {
      int sequenceSize = sc.nextInt();
      int[] sequenceArr = new int[sequenceSize+1];
      boolean[] visited = new boolean[sequenceSize+1];
      int cycle = 0;

      for(int i=1; i <= sequenceSize; i++) {
        sequenceArr[i] = sc.nextInt();
      }

      for(int j=1; j <= sequenceSize; j++) {
        if(!visited[j]) {
          dfs(sequenceArr, visited, j);
          cycle ++;
        }
      }
      sb.append("\n" + cycle);
    }

    System.out.println(sb.toString().trim());
  }

}
