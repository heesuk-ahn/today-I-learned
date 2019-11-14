package etc;

/**
 * 페이스북에서 제출된 문제입니다.
 *
 * 0이 대다수를 차지하는 큰 배열이 있습니다.
 *
 * 더 공간 효율적인 자료구조인 SparseArray를 다음과 같은 인터페이스로 구현하세요.
 *
 * init(arr, size): 큰 원본 배열과 사이즈를 인자로 받아 초기화 합니다.
 * set(i, val): 인덱스 i 를 val 값으로 업데이트 합니다.
 * get(i): 인덱스 i 번째 값을 반환합니다.
 *
 * 문제 이해)
 *  - 일단, 초기화 한다는 의미가 무엇인지? 0인 값을 제거하는 것?
 */
public class SparseArray {

  private final int[] arr;
  private final int size;

  public SparseArray(int[] arr, int size) {
    int[] newArr = new int[size];
    this.size = size;
    int originalSize = arr.length;

    for(int i=0; i<size; i++) {
      if(i < originalSize) {
        newArr[i] = arr[i];
      } else {
        newArr[i] = 0;
      }
    }
    this.arr = newArr;
  }

  public void set(int index, int value) {
    if(index >= 0) {
      arr[index] = value;
    } else {
      throw new RuntimeException("index overflow runtime error");
    }
  }

  public int get(int index) {
    if(index >= 0 && index < size){
      return arr[index];
    } else {
      throw new RuntimeException("index outbound error!");
    }
  }

  public static void main(String[] args) {
    int[] arr = {0, 0, 0};
    SparseArray sparseArray = new SparseArray(arr, 5);
    System.out.println(sparseArray.get(0));
    System.out.println(sparseArray.get(10));
  }

}
