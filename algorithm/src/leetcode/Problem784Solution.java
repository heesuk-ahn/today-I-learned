package leetcode;

import java.util.*;

public class Problem784Solution {

  /*
    주어진 스트링 S가 있습니다, 우리는 모든 문자를 개별적으로 소문자 또는 대문자로 바꿀 수 있습니다.
    우리가 생성할 수 있는 모든 가능한 새로운 스트링 리스트를 반환하세요.

    Examples:
      Input: S = "a1b2"
      Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

      Input: S = "3z4"
      Output: ["3z4", "3Z4"]

      Input: S = "12345"
      Output: ["12345"]
  */
  public List<String> letterCasePermutation(String S) {
    int length = S.length();
    Queue<String> queue = new LinkedList<>();
    List<String> ans = new LinkedList<>();

    for(int i=0; i<length; i++) {
      int size = queue.size();

      if(i == 0) {
        if(S.charAt(i) <= '9') {
          queue.add(String.valueOf(S.charAt(i)));
        } else {
          queue.add(String.valueOf(S.charAt(i)).toUpperCase());
          queue.add(String.valueOf(S.charAt(i)).toLowerCase());
        }
      } else {
        while(size-- > 0) {
          String popStr = queue.remove();

          if(S.charAt(i) >= '0' && S.charAt(i) <= '9') {
            queue.add(popStr + String.valueOf(S.charAt(i)));
          } else {
            queue.add(popStr + String.valueOf(S.charAt(i)).toUpperCase());
            queue.add(popStr + String.valueOf(S.charAt(i)).toLowerCase());
          }
        }
      }
    }

    for(String s: queue) {
      ans.add(s);
    }

    return ans;
  }

}
