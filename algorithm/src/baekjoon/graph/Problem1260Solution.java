package baekjoon.graph;

import java.util.*;

/**
 * 문제. DFS와 BFS 순회하기
 *
 * link) https://www.acmicpc.net/problem/1260
 *
 */
public class Problem1260Solution {

  public class TraverseDFSAndBFS {

    private final ArrayList<ArrayList<Integer>> graph;
    private final Integer nodeCount;
    private final Integer startNode;

    public TraverseDFSAndBFS(Integer nodeCount, ArrayList<ArrayList<Integer>> graph, Integer startNode) {
      this.nodeCount = nodeCount;
      this.graph = graph;
      this.startNode = startNode;
    }

    public String traverseDFS() {
      boolean visited[] = new boolean[nodeCount+1];
      Arrays.fill(visited, false);
      StringBuilder sb = new StringBuilder();

      return traverseDFSForRecursive(visited, startNode, sb);
    }

    private String traverseDFSForRecursive(boolean[] visited, Integer currNode, StringBuilder sb) {
      visited[currNode] = true;
      sb.append(" " + currNode);

      for(int i=0; i<graph.get(currNode).size(); i++) {
        Integer adjNode = graph.get(currNode).get(i);

        if(!visited[adjNode]) {
          traverseDFSForRecursive(visited, adjNode, sb);
        }
      }

      return sb.toString();
    }

    public String traverseBFS() {
      boolean visited[] = new boolean[nodeCount + 1];
      StringBuilder sb = new StringBuilder();

      return traverseBFSForRecursive(visited, startNode, sb);
    }

    private String traverseBFSForRecursive(boolean[] visited, Integer currNode, StringBuilder sb) {
      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(currNode);
      visited[currNode] = true;

      while(!queue.isEmpty()) {
        Integer nextNode = queue.poll();
        sb.append(" " + nextNode);

        for(int adjNode: graph.get(nextNode)) {
          if(!visited[adjNode]) {
            visited[adjNode] = true;
            queue.add(adjNode);
          }
        }
      }

      return sb.toString();
    }

  }

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int m = sc.nextInt();
    int startNode = sc.nextInt();
    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    for(int i=0; i<=n; i++) {
      graph.add(new ArrayList<>());
    }

    for(int i=0; i<m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();

      graph.get(u).add(v);
      graph.get(v).add(u);
    }

    TraverseDFSAndBFS traverse = new Problem1260Solution().new TraverseDFSAndBFS(n, graph, startNode);

    String resultDFS = traverse.traverseDFS().trim();
    String resultBFS = traverse.traverseBFS().trim();

    System.out.println(resultDFS);
    System.out.println(resultBFS);

  }

}
