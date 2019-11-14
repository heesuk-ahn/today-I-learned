package search;

/**
 * 이분 탐색을 통해 값을 찾는다.
 *
 * 이분 탐색은 정렬이 되어있을 떄, logN 시간에 찾을 수 있는 탐색 기법이다.
 *
 * ㅇ
 */
public class BinarySearch {

  int findValue(int[] arr, int expectedValue) {
    int ans = -1;
    int firstIndex = 0;
    int lastIndex = arr.length - 1;
    int middle = (firstIndex + lastIndex) / 2;

    while(firstIndex <= lastIndex) {
      if(arr[middle] == expectedValue) {
        ans = middle;
        break;
      }
      if(arr[middle] > expectedValue) {
        middle -= 1;
        lastIndex = middle;
        middle = (firstIndex + lastIndex) / 2;
      } else {
        middle += 1;
        firstIndex = middle;
        middle = (firstIndex + lastIndex) / 2;
      }
    }

    return ans;
  }

  public static void main(String[] args) {

    int[] arr = {1, 2, 3, 4, 5};

    int ans = new BinarySearch().findValue(arr, 0);

    System.out.println(ans);
  }

}
