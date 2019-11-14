package etc;

/**
 * 주어진 배열 arr에 n개의 정수 타입 숫자들이 정렬 되어 들어가 있다.
 * 여기서 임의의 숫자 target이 그 배열의 몇 번째 있는지 반환하는 함수를 구현하라
 *
 * 예외 경우) arr에 값이 없는 경우.
 */
public class SearchArray {

  private final int NOT_FOUND = -1;


  class NotFound extends Throwable {

    private final String message;

    public NotFound(String message) {
      this.message = message;
    }

    @Override
    public String getMessage() {
      return this.message;
    }

  }

  public int searchNForRec(int[] arr, int target, int front, int mid, int last) throws NotFound {
   /*
    - Base Case 설정
      - front == last, 마지막 인자 비교 후, 맞다면 인덱스 리턴. 아니라면 에러 반환.
    - mid와 target이 일치하면 index 리턴
    - mid가 target보다 크다면, 왼쪽으로 윈도우 이동
      - last = mid-1
      - mid = (front + last) / 2
      - 재귀 호출
    - mid가 target보다 작다면, 오른쪽으로 윈도우 이동
      - front = mid+1;
        mid = (front + last) / 2
      - 재귀호출
   */
    if(front == last) {
      if(arr[mid] == target) {
        return mid;
      } else {
        throw new NotFound("does not exist error");
      }
    } else if(arr[mid] == target) {
      return mid;
    } else if (arr[mid] > target) {
      last = mid-1;
      mid = (front + last) / 2;
      return searchNForRec(arr, target, front, mid, last);
    } else {
      front = mid+1;
      mid = (front + last) / 2;
      return searchNForRec(arr, target, front, mid, last);
    }
  }

  public int binarySearch(int[] arr, int expectedValue) throws NotFound {
    int front = 0;
    int last = arr.length-1; //4
    int mid = (front+last)/2; //2
    int ans = NOT_FOUND;

    if(arr.length == 0) {
      throw new NotFound("NOT FOUND VALUE");
    }
    //{1, 2, 9, 78, 124};
    while(front <= last) {
      if(arr[mid] == expectedValue) { //arr[2] = 9, 2
        ans = mid;
        break;
      }
      if(arr[mid] > expectedValue) {
        //왼쪽으로 이동!
        last = mid-1; //1
        mid = (front + last) / 2; // 0+1 /2 = 0
      } else {
        //오른쪽으로 이동
        front = mid+1; //1 + 1 /2 = 1
        mid = (front + last) / 2;
      }
    }

    return ans;
  }

  public static void main(String[] args) throws NotFound {
    int[] arr = {1, 2, 3, 4, 5};
    int target = 2;

    System.out.println(new SearchArray().binarySearch(arr, target));
  }

}
