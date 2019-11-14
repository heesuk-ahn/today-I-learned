package sorting;

public class MergeSort {

  void mergeSort(int[] arr, int start, int end) {
    //start랑 end가 같아지면 종료
    if(start < end) {
      int middle = (start + end)/2;
      mergeSort(arr, start, middle); //왼쪽먼저.
      mergeSort(arr, middle+1, end); //그다음 오른쪽
      merge(arr, start, middle, end); //왼쪽 오른쪽 정렬 끝나면 머지.
    }
  }

  void merge(int[] arr, int start, int middle, int end) {
    int i = start;
    int j = middle+1;
    int k = start;
    int[] temp = new int[arr.length];

    while(i <= middle && j <= end) {
      if(arr[i] >= arr[j]) {
        temp[k++] = arr[j++];
      } else {
        temp[k++] = arr[i++];
      }
    }
    while(i <= middle) {
      temp[k++] = arr[i++];
    }
    while(j <= end) {
      temp[k++] = arr[j++];
    }

    for(int z=start; z<=end; z++) {
      arr[z] = temp[z];
    }

  }

  public static void main(String[] args) {
    int[] arr = {5, 4, 3, 2, 1};

    int start = 0;
    int end = arr.length-1;

    new MergeSort().mergeSort(arr, start, end);

    for(int r: arr) {
      System.out.println(r);
    }
  }

}
