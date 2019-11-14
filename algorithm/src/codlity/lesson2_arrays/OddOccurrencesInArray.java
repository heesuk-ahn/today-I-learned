package codlity.lesson2_arrays;

/**
 * 문제.
 *
 * A non-empty array A consisting of N integers is given. The array contains an odd number of elements,
 * and each element of the array can be paired with another element that has the same value, except for one element that is left unpaired.
 *
 * For example, in array A such that:
 *
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 *
 * the elements at indexes 0 and 2 have value 9,
 * the elements at indexes 1 and 3 have value 3,
 * the elements at indexes 4 and 6 have value 9,
 * the element at index 5 has value 7 and is unpaired.
 *
 * Write a function:
 *
 *   class Solution { public int solution(int[] A); }
 *
 * that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.
 *
 * For example, given array A such that:
 *
 *   A[0] = 9  A[1] = 3  A[2] = 9
 *   A[3] = 3  A[4] = 9  A[5] = 7
 *   A[6] = 9
 *
 * the function should return 7, as explained in the example above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an odd integer within the range [1..1,000,000];
 * each element of array A is an integer within the range [1..1,000,000,000];
 * all but one of the values in A occur an even number of times.
 *
 * IDEA 1) 정렬을 하고, index를 2씩 증가시키면서 fair 체크를 한다. O(nlogn)
 *
 * - 예제 인풋을 정렬을 하면 아래와 같이 될 것이다
 * - 33799
 * - 위와 같은 배열을 2칸씩 비교해서 fair인지 unfair인지 비교하는 함수를 만든다.
 * - 0부터 시작하여 2씩 증가한다면, (0, 1) 비교, (2, 3) 비교...
 * - 0과 1을 비교하면 33으로 fair, 2과 3을 비교하면 79이므로 unfair하다.
 * - 이때, 앞에자리가 unfair이다, 왜냐면 i-2와 i-1은 이미 fair이므로, i는 이전 값과 비교하면 unfair다.
 * - 위와 같은 알고리즘을 사용하게 된다면, 정렬에는 nlogn이 들고, 비교는 O(N)이 된다.
 *
 * IDEA 2) boolean 해시 테이블을 사용한다 O(N) , 공간 복잡도 O(N)
 * - 주어진 배열을 순회하며 boolean 해시 테이블의 플래그를 true로 바꾼다, 만약 true라면 false로 바꾼다.
 * - 순회가 종료되면, 해시테이블을 다시 N번 순회하며, 플래그가 true인 index 값을 찾는다.
 *
 * IDEA 3) XOR을 사용해서 개선이 가능하다. O(N), 공간 복잡도 O(1)
 *
 * - XOR의 특성은 같은 값일 경우에는 0이 된다는 것이다. 마치 9-9는 0인 것과 같다.
 * - 각각의 value들을 ^을 이용하여 XOR 연산을 한다.
 * - 순회가 종료되고 남은 수는 XOR 연산으로 제거되지 못한 이진수의 값으로 결과값을 알아낼 수 있다.
 */
public class OddOccurrencesInArray {

  /*
    IDEA 3) XOR을 사용해서 개선이 가능하다. O(N), 공간 복잡도 O(1)
      - XOR의 특성은 같은 값일 경우에는 0이 된다는 것이다. 마치 9-9는 0인 것과 같다.
      - 각각의 value들을 ^을 이용하여 XOR 연산을 한다.
      - 순회가 종료되고 남은 수는 XOR 연산으로 제거되지 못한 이진수의 값으로 결과값을 알아낼 수 있다.
  */
  int getOddValueByXOR(int[] arr) {
    int length = arr.length;
    int res = 0;
    for(int i=0; i<length; i++) {
      res ^=arr[i];
    }
    return res;
  }


  /*
    IDEA 2) boolean 해시 테이블을 사용한다 O(N) , 공간 복잡도 O(N)
      - 주어진 배열을 순회하며 boolean 해시 테이블의 플래그를 true로 바꾼다, 만약 true라면 false로 바꾼다.
      - 순회가 종료되면, 해시테이블을 다시 N번 순회하며, 플래그가 true인 index 값을 찾는다.
  */
  int getOddValueByHash(int[] arr) {
    boolean[] hashTable = new boolean[1000001];
    int legnth = arr.length;
    int ansIndex = 0;

    for(int i=0; i<legnth; i++) {
      if(hashTable[arr[i]]) {
        hashTable[arr[i]] = false;
      } else {
        hashTable[arr[i]] = true;
      }
    }

    for(int i=0; i<legnth; i++) {
      if(hashTable[arr[i]]) {
        ansIndex = arr[i];
        break;
      }
    }

    return ansIndex;
  }

  public static void main(String[] args) {
    int[] arr = {9, 7, 9, 3, 2, 3, 2};

    System.out.println(new OddOccurrencesInArray().getOddValueByXOR(arr));
    System.out.println(new OddOccurrencesInArray().getOddValueByHash(arr));
  }

}
