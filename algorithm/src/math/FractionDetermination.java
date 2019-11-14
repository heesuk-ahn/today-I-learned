package math;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *  소수 판별법
 */
public class FractionDetermination {

  /**
   *  O(N)시간 소수 판별
   */
  public boolean fractionDeterminationForN(int a) {
    for(int i=2; i<a; i++) {
      if(a%i == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * [에라토스테네스의 접근]
   *
   * O(N^1/2) 루트 시간 복잡도 시간 판별
   */
  public boolean fractionDeterminationForRoot(int a) {
    for(int i=2; i*i<a; i++) {
      if(a%i == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * [에로토스테네스의 체]
   *
   * n까지의 모든 소수를 구하라.
   *
   */
  public boolean[] fractionDeterminationForAll(int n) {
    boolean[] fractionTable = new boolean[n+1]; //1, 2, 3, ..... n
    Arrays.fill(fractionTable, true);
    fractionTable[0] = false;
    fractionTable[1] = false;

    for(int i=2; i*i<n; i++) {
      int multiply = 2;
      while(i*multiply <= n) {
        int value = i*multiply;
        fractionTable[value] = false;
        multiply += 1;
      }
    }
    return fractionTable;
  }

  public static void main(String[] args) {

    int validFraction = 7;
    int invalidFraction = 16;

    // O(N) 시간으로 소수를 판별하라.
    boolean ans1 = new FractionDetermination().fractionDeterminationForN(validFraction);
    boolean ans2 = new FractionDetermination().fractionDeterminationForN(invalidFraction);

    // O(루트 N) 시간으로 소수를 판별하라
    boolean ans3 = new FractionDetermination().fractionDeterminationForRoot(validFraction);
    boolean ans4 = new FractionDetermination().fractionDeterminationForRoot(invalidFraction);

    //10까지의 소수를 구하라.
    int n = 10;
    boolean[] ans5 = new FractionDetermination().fractionDeterminationForAll(n);

    if(ans1 == true && !ans2) {
      System.out.println("PASS!");
    }
    if(ans3 == true && !ans4) {
      System.out.println("PASS!");
    }

    for(int i=2; i<=n; i++) {
      if(ans5[i]) {
        System.out.println("소수 : " + i);
      }
    }

  }

}
