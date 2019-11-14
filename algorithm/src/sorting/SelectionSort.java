package sorting;

public class SelectionSort {

  int[] selectionSortAsc(int[] arr) {
    for(int i=0; i<arr.length; i++) {
      //step 1) init max
      int max = Integer.MIN_VALUE;
      int maxPosition = Integer.MIN_VALUE;
      for(int j=0; j<arr.length-i; j++) {
        //step 2) search max
        if(max < arr[j]) {
          max = arr[j];
          maxPosition = j;
        }
      }
      //step 3) fix max to last index
      int temp = arr[arr.length-i-1];
      arr[arr.length-i-1] = max;
      arr[maxPosition] = temp;
    }

    return arr;
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};
    int[] res = new SelectionSort().selectionSortAsc(arr);

    for(int r: res) {
      System.out.println(r);
    }
  }

}
