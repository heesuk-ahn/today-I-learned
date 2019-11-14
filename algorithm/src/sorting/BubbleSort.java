package sorting;

public class BubbleSort {

  int[] bubbleSort(int[] arr) {
    int length = arr.length;
    for(int i=0; i<length; i++) {
      for(int j=0; j<length-i; j++) {
        if((j+1) == (length-i)) {
          break;
        }
        if(arr[j] > arr[j+1]) {
          int temp = arr[j+1];
          arr[j+1] = arr[j];
          arr[j] = temp;
        }
      }
    }

    return arr;
  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};

    int[] res = new BubbleSort().bubbleSort(arr);

    for(int r: res) {
      System.out.println(r);
    }
  }

}
