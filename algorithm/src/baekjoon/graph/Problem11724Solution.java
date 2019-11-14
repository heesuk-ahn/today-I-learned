package baekjoon.graph;
import java.util.*;

/**
 * 백준 - 연결 요소 갯수 찾기
 *
 * /**
 *  *
 *  * 문제)
 *  *
 *  * 방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
 *  *
 *  * 입력)
 *  *
 *  * 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다.
 *  * (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
 *
 **/
public class Problem11724Solution {

  public static void dfs(ArrayList<Integer>[] a, boolean[] c, int x) {
    if (c[x]) {
      return;
    }
    c[x] = true; //노드 방문 체크표시하기.
    for (int y : a[x]) { //인접 노드
      if (c[y] == false) {//인접 노드 방문을 안했다면, DFS 순회
        dfs(a, c, y);
      }
    }
  }

  public static void main(String args[]) {

    //step1) 그래프 입력받기 - 인접 리스트
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    int m = sc.nextInt();

    //이 표현은 나도 써야겠다. ArrayList의 어레이 형태..!
    ArrayList<Integer>[] a = (ArrayList<Integer>[]) new ArrayList[n+1];

    for (int i=1; i<=n; i++) {
      a[i] = new ArrayList<Integer>();
    }

    for (int i=0; i<m; i++) {
      int u = sc.nextInt();
      int v = sc.nextInt();
      a[u].add(v);
      a[v].add(u);
    }
    /////////////////////////////////////////////////////////////////////

    boolean[] check = new boolean[n+1];
    int ans = 0;

    //아직 방문 안한 곳에서부터 시작한다. 순차적으로...! 연결요소의 핵심은 아직 방문안한 지점에서부터 DFS로 끝까지 방문하는것.
    //재귀는 하나의 반복문처럼 생각하면 된다. 종료될때까지 가는 것.
    for (int i=1; i<=n; i++) {
      if (check[i] == false) { //특정 정점을 방문하지 않았다면, 순회한다.
        dfs(a, check, i); //더이상 갈 곳이 없을 때까지 순회한다.
        ans += 1; //그 후 카운트를 올린다.
      }
    }

    System.out.println(ans);
  }

}
