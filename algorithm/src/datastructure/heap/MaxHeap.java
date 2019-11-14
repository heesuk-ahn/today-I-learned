package datastructure.heap;

public class MaxHeap {

  //부모 노드 k에서 heapify를 수행 O(N). 역레벨 오더 방식
  public void heapifyForRec(int[] arr, int size, int k) {
    int leftNode = (2 * k) + 1;
    int rightNode = (2 * k) + 2;

    int largest = arr[k];
    int largestIndex = k;

    if(leftNode < size && largest < arr[leftNode]) {
      largest = arr[leftNode];
      largestIndex = leftNode;
    }
    if(rightNode < size && largest < arr[rightNode]) {
      largest = arr[rightNode];
      largestIndex = rightNode;
    }

    //swap
    if(largestIndex != k) {
      int temp = arr[k];
      arr[k] = arr[largestIndex];
      arr[largestIndex] = temp;
      heapifyForRec(arr, size, largestIndex);
    }

    //traverse
    if(largestIndex == k && largestIndex > 0) {
      largestIndex = k-1;
      heapifyForRec(arr, size, largestIndex);
    }
  }

  public void heapifyForLoop(int[] arr) {

    int size = arr.length-1;
    int k = (size / 2) -1;

    while(k > 0) { //재귀를 반복문으로 바꿀 때, 베이스 케이즈가 들어가야한다. (변화하는 인덱스)
      int leftNode = (2*k) + 1;
      int rightNode = (2*k) + 2;
      int largestIndex = k;
      int largestValue = arr[k];

      if(leftNode < size && largestValue < arr[leftNode]) {
        System.out.println("largestValue left! : " + leftNode);
        largestIndex = leftNode;
        largestValue = arr[leftNode];
      }
      if(rightNode < size && largestValue < arr[rightNode]) {
        System.out.println("largestValue right! : " + rightNode);
        largestIndex = rightNode;
        largestValue = arr[rightNode];
      }

      //변화가 발생했다면, swap
      if(largestIndex != k) {
        int temp = arr[k];
        arr[k] = arr[largestIndex];
        arr[largestIndex] = temp;
        k = largestIndex;
      } else {
        //변화가 발생하지 않았다면? 역레벨 오더 방식 순회
        k = k-1;
      }
    }

  }

  public void heapSort(int[] arr, int end) {

  }

  public static void main(String[] args) {
    int[] array = {2, 8, 6, 1, 10, 15, 3, 12, 11};

    new MaxHeap().heapifyForLoop(array);

    for(int r: array) {
      System.out.println(r);
    }

  }

}
