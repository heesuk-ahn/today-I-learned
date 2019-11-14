package etc;

import java.util.*;

/*
  IDEA) O(N*M) 백트래킹 방법

  ['code', 'doce', 'ecod', 'framer', 'frame']

  위의 배열에서 아나그램을 제거한 인덱스들을 안다면?

  code == 아나그램인 경우는 제거
  code != 아나그램이 아니라면, queue에 인덱스 추가 후, next 어레이로 이동.
  주어진 인덱스에서 넥스트 인덱스 확인.
 */
public class PlayAnagram {

  public class CompareAnagram {

    private int currPosition;
    private int nextPosition;

    public CompareAnagram(int currPosition, int nextPosition) {
      this.currPosition = currPosition;
      this.nextPosition = nextPosition;
    }

  }

  public boolean isAnagram(String a, String b) {
    char[] convertToCharFromA = a.toCharArray();
    Arrays.sort(convertToCharFromA);

    char[] convertToCharFromB = b.toCharArray();
    Arrays.sort(convertToCharFromB);

    return new String(convertToCharFromA).equals(new String(convertToCharFromB));
  }

  public String[] funWithAnagrams(String[] anagrams) {
    //아나그램 중복체크를 통과한 것들만 저장되는 스택
    Queue<CompareAnagram> removedAnagramsIndex = new LinkedList<>();
    ArrayList<Integer> ansIndexNumber = new ArrayList();
    int length = anagrams.length;

    //퀵 소트 nLogN으로 정렬
    Arrays.sort(anagrams);

    //anagrams 길이 가드코드 넣기

    //첫번째 아나그램을 넣는다,
    removedAnagramsIndex.add(new CompareAnagram(0, 1));
    ansIndexNumber.add(0);

    while(!removedAnagramsIndex.isEmpty()) {
      CompareAnagram compareAnagram = removedAnagramsIndex.remove();
      boolean overFlowCheckNext = compareAnagram.nextPosition >=0 && compareAnagram.nextPosition < length;

      if (overFlowCheckNext && isAnagram(anagrams[compareAnagram.currPosition], anagrams[compareAnagram.nextPosition])) {
        //아나그램인 경우는 Next 값으로 이동
        removedAnagramsIndex.add(new CompareAnagram(compareAnagram.currPosition, compareAnagram.nextPosition + 1));
      }
      if(overFlowCheckNext && !isAnagram(anagrams[compareAnagram.currPosition], anagrams[compareAnagram.nextPosition])) {
        //아나그램이 아닌경우는, removedAnagramsIndex 새로운 출발점으로 등록.
        removedAnagramsIndex.add(new CompareAnagram(compareAnagram.nextPosition, compareAnagram.nextPosition + 1));
        ansIndexNumber.add(compareAnagram.nextPosition);
      }

    }

    String[] ans = new String[ansIndexNumber.size()];
    int index = 0;

    for(int i: ansIndexNumber) {
      ans[index++] = anagrams[i];
    }

    return ans;
  }

  public static void main(String[] args) {
    //'code', 'doce', 'ecod', 'framer', 'frame'
    String[] arr = new String[5];
    arr[0] = "code";
    arr[1] = "doce";
    arr[2] = "ecod";
    arr[3] = "fremer";
    arr[4] = "frame";

    String[] ans = new PlayAnagram().funWithAnagrams(arr);

    for(String s: ans) {
      System.out.println(s);
    }
  }

}
