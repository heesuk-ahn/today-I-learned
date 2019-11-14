package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제) 백준 국영수
 *
 * link) https://www.acmicpc.net/problem/10825
 */
public class Problem10825Solution {

  class PersonExamScore implements Comparable<PersonExamScore> {

    private final int korScore;
    private final int engScore;
    private final int mathScore;
    private final String name;

    public PersonExamScore(int korScore, int engScore, int mathScore, String name) {
      this.korScore = korScore;
      this.engScore = engScore;
      this.mathScore = mathScore;
      this.name = name;
    }

    public int getKorScore() {
      return korScore;
    }

    public int getEngScore() {
      return engScore;
    }

    public int getMathScore() {
      return mathScore;
    }

    public String getName() {
      return name;
    }


    /*
      1: 오른쪽에 두기
      -1: 왼쪽에 두기
      0: 냅두기
    */
    @Override
    public int compareTo(PersonExamScore personExamScore) {
      if(this.korScore > personExamScore.getKorScore()) {
        System.out.println("in kor");
        return -1; //내림차순
      } else if(this.korScore == personExamScore.getKorScore()) {
        if(this.engScore < personExamScore.getEngScore()) {
          return -1; //오름차순
        } else if(this.engScore == personExamScore.getEngScore()) {
          if(this.mathScore > personExamScore.getMathScore()) {
            return -1;
          } else if(this.mathScore == personExamScore.getMathScore()) {
            return this.getName().compareTo(personExamScore.getName());
          }
        }
      }
      return 1;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = Integer.parseInt(br.readLine());

    ArrayList<PersonExamScore> scoreList = new ArrayList<PersonExamScore>();

    for(int i=0; i<count; i++) {
      String[] line = br.readLine().split(" ");
      String name = line[0];
      Integer korScore = Integer.parseInt(line[1]);
      Integer engScore = Integer.parseInt(line[2]);
      Integer mathScore = Integer.parseInt(line[3]);

      scoreList.add(new Problem10825Solution().new PersonExamScore(korScore, engScore, mathScore, name));
    }

    Collections.sort(scoreList);

    StringBuilder sb = new StringBuilder();

    for(PersonExamScore p: scoreList) {
      sb.append(p.getName() + "\n");
    }

    System.out.println(sb.toString());
  }

}
