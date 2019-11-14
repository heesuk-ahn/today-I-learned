package baekjoon.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제. 백준 - 2진수를 8진수로
 * link. https://www.acmicpc.net/problem/1373
 */
public class Problem1373Solution {

  String convertToOctal(String binaryValue) {
    StringBuilder sb = new StringBuilder();
    Integer length = binaryValue.length();

    for(int i=length-1; i>=0; i-=3) {
      int first = i;
      int second = i-1;
      int third = i-2;
      int sum = 0;

      boolean overflowCheckForSecond = second >= 0 && second < length;
      boolean overflowCheckForThird = third >= 0 && third < length;

      if(overflowCheckForSecond && overflowCheckForThird) {
        sum += (binaryValue.charAt(i) - '0') * 1;
        sum += (binaryValue.charAt(i-1) - '0') * 2;
        sum += (binaryValue.charAt(i-2) - '0') * 4;
      }
      if(overflowCheckForSecond && !overflowCheckForThird) {
        sum += (binaryValue.charAt(i) - '0') * 1;
        sum += (binaryValue.charAt(i-1) - '0') * 2;
      }
      if(!overflowCheckForSecond && !overflowCheckForThird) {
        sum += (binaryValue.charAt(i) - '0') * 1;
      }
      sb.append(sum);
    }

    return sb.reverse().toString();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String binaryValue = br.readLine();
    System.out.println(new Problem1373Solution().convertToOctal(binaryValue));
  }

}
