package baekjoon.math;

  import java.util.Scanner;

/**
 * 문제. 백준 2745
 * link. https://www.acmicpc.net/problem/2745
 */
public class Problem2745Solution {

  Integer convertToDecimal(String str, Integer b) {
    int ans = 0;
    int length = str.length();

    for(int i=0; i<length; i++) {
      if('0' <= str.charAt(i) && str.charAt(i) <= '9') {
        ans = ans * b + (str.charAt(i) - '0');
      } else {
        ans = ans * b + (str.charAt(i) - 'A' + 10);
      }
    }

    return ans;
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String[] line = sc.nextLine().split(" ");

    String characters = line[0];
    Integer n = Integer.parseInt(line[1]);

    System.out.println(new Problem2745Solution().convertToDecimal(characters, n));
  }

}
