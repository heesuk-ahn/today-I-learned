package baekjoon.dp;


import java.util.Scanner;

/**
 *  문제. 1로 만들기
 *  링크) https://www.acmicpc.net/problem/1463
 */
public class ProblemSolution1463 {

  public int getMinimumValueForRec(int n, int[] dp, int count) {
    int divValueByTwo = n/2;
    int divValueByThree = n/3;

    //종착지에 도착했다면? 값이 없다면 넣고, 값이 있다면 누가 더 작은지 비교한다. count!
    if((divValueByTwo == 1 || divValueByThree == 1) && dp[1] > count) {
      System.out.println("count : " + count);
      dp[1] = count;
      return dp[1];
    } else {
      //이미 수행했다면 더이상 수행하지 않아도 된다.
      System.out.println("divValueTwo : " + divValueByTwo);
      System.out.println("divValueThree : " + divValueByThree);
      if(dp[divValueByTwo] != 0) {
        return dp[divValueByTwo];
      }
      if(dp[divValueByThree] != 0) {
        return dp[divValueByThree];
      }

      //아직 연산을 수행하지 않았다면, 모든 경우의 수를 수행한다.
      if(n%2 == 0 && dp[divValueByTwo] == 0) {
        //아직 연산을 하지 않았다면?
        //count 횟수를 저장한다.
        dp[divValueByTwo] = count+1;
        return getMinimumValueForRec(divValueByTwo, dp, count+1);
      } else if(n%3 == 0 && dp[divValueByThree] != 0) {
        dp[divValueByThree] = count +1;
        return getMinimumValueForRec(divValueByThree, dp, count+1);
      } else {
        return getMinimumValueForRec(n-1, dp, count+1);
      }
    }

  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Integer n = sc.nextInt();
    int[] dp = new int[1000001];

    int getMinimum = new ProblemSolution1463().getMinimumValueForRec(n, dp, 0);

    System.out.println(getMinimum);


  }

}
