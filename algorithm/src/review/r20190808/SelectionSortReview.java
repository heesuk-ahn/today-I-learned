package review.r20190808;

public class SelectionSortReview {

  int[] getSortedArray(int[] arr) {
    for(int i=0; i<arr.length; i++) {
      int max = Integer.MIN_VALUE;
      int maxPosition = 0;
      for(int k=0; k<arr.length-i; k++) {
        if(max < arr[k]) {
          max = arr[k];
          maxPosition = k;
        }
      }
      arr[maxPosition] = arr[arr.length-i-1];
      arr[arr.length-i-1] = max;
    }

    return arr;
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};

    int[] res = new SelectionSortReview().getSortedArray(arr);

    for(int r: res) {
      System.out.println(r);
    }
  }

}
