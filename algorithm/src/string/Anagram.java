package string;

/*
  아나그램 판별 하는 함수를 작성하라.
  아나그램이란, 순서는 다르지만 같은 문자열 구성으로 되어있는 관게를 의미한다,.

  ex) 문자열 Abc와 bcA는 아나그램이다.
*/
public class Anagram {

  /*
    컴퓨터는 0과 1 숫자 밖에 모르기 때문에 문자도 숫자로 기억합니다.
   이때, 어떤 숫자와 어떤 문자를 대응시키는가에 따라 여러 가지 인코딩 방식이 있는데 통상 아스키 코드 방식을 많이 사용합니다.
   아스키 코드 테이블은 0과 127까지 사용합니다.

   아래 풀이는 아스키코드 테이블을 이용하여 O(N) 시간 복잡도와 O(N) 공간 복잡도를 이용하여 풀었습니다.
   */
  public boolean isAnagram(String strA, String strB) {
    boolean[] asciiTable = new boolean[128];

    for(char a: strA.toCharArray()) {
      asciiTable[a] = true;
    }

    for(char b: strB.toCharArray()) {
      asciiTable[b] = false;
    }

    for(int k=0; k<128; k++) {
      if(asciiTable[k]) return false;
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(new Anagram().isAnagram("Abc", "cbA"));
  }

}
