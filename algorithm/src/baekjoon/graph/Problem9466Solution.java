package baekjoon.graph;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  문제)
 *
 * 이번 가을학기에 '문제 해결' 강의를 신청한 학생들은 텀 프로젝트를 수행해야 한다. 프로젝트 팀원 수에는 제한이 없다.
 * 심지어 모든 학생들이 동일한 팀의 팀원인 경우와 같이 한 팀만 있을 수도 있다.
 * 프로젝트 팀을 구성하기 위해, 모든 학생들은 프로젝트를 함께하고 싶은 학생을 선택해야 한다. (단, 단 한 명만 선택할 수 있다.)
 * 혼자 하고 싶어하는 학생은 자기 자신을 선택하는 것도 가능하다.
 *
 * 학생들이(s1, s2, ..., sr)이라 할 때, r=1이고 s1이 s1을 선택하는 경우나, s1이 s2를 선택하고, s2가 s3를 선택하고,...,
 * 택하고, sr이 s1을 선택하는 경우에만 한 팀이 될 수 있다.
 *
 * 예를 들어, 한 반에 7명의 학생이 있다고 하자. 학생들을 1번부터 7번으로 표현할 때, 선택의 결과는 다음과 같다.
 *
 * 1	2	3	4	5	6	7
 * 3	1	3	7	3	4	6
 * 위의 결과를 통해 (3)과 (4, 7, 6)이 팀을 이룰 수 있다. 1, 2, 5는 어느 팀에도 속하지 않는다.
 *
 * 주어진 선택의 결과를 보고 어느 프로젝트 팀에도 속하지 않는 학생들의 수를 계산하는 프로그램을 작성하라.
 *
 * idea)
 *  - 이 문제는 정점과 그리고 관계가 나오기 때문에 그래프로 풀 가능성이 높다.
 *  - 문제의 핵심은 사이클을 찾고 그 사이클을 출력하는 것이다.
 *  - n (2 ≤ n ≤ 100,000)
 *  - n이 최대 100,000 이기 때문에, n^2알고리즘으로는 3초의 시간제한을 넘는다.
 *  - 상수 알고리즘 이거나, 또는 n * logN 으로 풀릴 수 있다.
 */
public class Problem9466Solution {

  public static class Cycle {

    private ArrayList<Integer> cycleBuffer;
    private Integer start;
    private Integer end;

    public Cycle(ArrayList<Integer> cycleBuffer, Integer start, Integer end) {
      this.start = start;
      this.end = end;
      this.cycleBuffer = cycleBuffer;
    }

    public void setStart(Integer start) {
      this.start = start;
    }

    public void setEnd(Integer end) {
      this.end = end;
    }

  }

  public static Cycle dfs(ArrayList<Integer> personChoice, Integer currPerson, Cycle cycle, boolean[] visited) {
    visited[currPerson] = true;
    Integer nextPoint = personChoice.get(currPerson);

    if(cycle.cycleBuffer.contains(nextPoint)) {
      cycle.setStart(cycle.cycleBuffer.indexOf(nextPoint));
      cycle.setEnd(cycle.cycleBuffer.indexOf(currPerson));
      return cycle;
    } else {
      cycle.cycleBuffer.add(nextPoint);
      return dfs(personChoice, nextPoint, cycle, visited);
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Integer testCase = sc.nextInt();
    StringBuilder sb = new StringBuilder();

    while(testCase-- > 0) {
      //step1) 학생수를 입력받는다
      Integer personNumber = sc.nextInt();

      //step2) 학생들을 입력받는다. 어떤 자료구조에? 학생들은 여러명을 선택하는 것이 아니라, 하나의 선택만 할 수 있다.
      ArrayList<Integer> personChoice = new ArrayList<>();
      personChoice.add(0);


      boolean[] visited = new boolean[personNumber + 1];
      ArrayList<Cycle> cycles = new ArrayList<>();

      //step3) i -> selectedPerson을 나타내도록 한다.
      for(int i = 1; i <= personNumber; i++) {
        Integer selectedPerson = sc.nextInt();
        personChoice.add(selectedPerson);
      }

      for(int j = 1; j <= personNumber; j++) {
        if(!visited[j]) {
          Cycle cycle = dfs(personChoice, j, new Cycle(new ArrayList<>(), 0, 0), visited);
          cycles.add(cycle);
        }
      }

      Integer sumSize = 0;

      for(Cycle c: cycles) {
        sumSize += c.cycleBuffer.size() - c.cycleBuffer.indexOf(c.start);
      }

      System.out.println(sumSize);

    }
  }

}
