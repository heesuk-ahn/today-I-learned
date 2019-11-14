package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *  문제. 백준 수 정렬하기 2
 *
 *  link) https://www.acmicpc.net/problem/2751
 *
 *  참고: https://www.geeksforgeeks.org/quick-sort/
 */
public class Problem2751Solution  {

  int partition(int[] arr, int start, int end) {
    //이 부분이 정렬된 경우를 피하기 위한 부분이다. 중간 값을 선택한다.
    int midean = (start + end) / 2;
    int pivot = arr[midean];

    arr[midean] = arr[end];
    arr[end] = pivot;

    int smallerPointer = start-1;
    int biggerPointer = start;

    for(int i=biggerPointer; i<end; i++) {
      if(arr[i] <= pivot) {
        // swap arr[i] and arr[j]
        smallerPointer += 1;
        int biggerTemp = arr[smallerPointer];
        arr[smallerPointer] = arr[i];
        arr[i] = biggerTemp;
      }
    }

    // swap arr[i+1] and arr[high] (or pivot)
    int temp = arr[smallerPointer+1];
    arr[smallerPointer+1] = arr[end];
    arr[end] = temp;
    pivot = smallerPointer+1;

    return pivot;
  }

  void quickSort(int[] arr, int start, int end) {

    if(start<end) {
      int pivot = partition(arr, start, end);
      quickSort(arr, start, pivot-1 );
      quickSort(arr, pivot+1, end);
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int lineCount = Integer.parseInt(br.readLine());
    int[] arr = new int[lineCount];
    StringBuilder sb = new StringBuilder();

    for(int i=0; i<lineCount; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    new Problem2751Solution().quickSort(arr, 0, lineCount-1);

    for(int res: arr) {
      sb.append(res + "\n");
    }

    System.out.println(sb.toString());
  }

}
