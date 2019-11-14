package sorting;

/**
 * <pseudo>
 *  loop from i=1 to i=arr.size-1 :
 *    가정 : arr[0] ~ arr[i-1] sorted
 *    loop from i-1 to 0:
 *      if arr[i-1] > arr[i]:
 *        then
 *          set temp = arr[i-1]
 *          set arr[i-1] = arr[i];
 *          set arr[i] = temp;
 *      else
 *        then break;
 * </pseudo>
 */
public class InsertionSort {

  int[] insertionSort(int[] arr) {
    for(int i=1; i< arr.length; i++) { //1에서 시작한다는 것이 중요 포인트
      for(int k=i-1; k>=0; k--) { //내림차순으로 간다는 것이 포인트.
        if(arr[k] > arr[k+1]) {
          int temp = arr[k+1];
          arr[k+1] = arr[k];
          arr[k] = temp;
        } else {
          break;
        }
      }

    }
    return arr;
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};

    int[] res = new InsertionSort().insertionSort(arr);

    for(int r: res) {
      System.out.println(r);
    }
  }

}
