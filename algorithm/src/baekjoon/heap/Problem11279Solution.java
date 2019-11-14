package baekjoon.heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 문제. 백준 - 최대 힙
 * link. https://www.acmicpc.net/problem/11279
 */
public class Problem11279Solution {

  class Heap {

    private int[] heap;
    private int lastPointer = 0;
    private int currPointer = 0;
    private final StringBuilder sb;

    public Heap(int heapSize, StringBuilder sb) {
      this.heap = new int[heapSize];
      this.sb = sb;
    }

    public void insert(int value) {
      lastPointer +=1;
      currPointer +=1;
      heap[currPointer] = value;
      int parentIndex = currPointer/2;

      while(heap[parentIndex] < heap[currPointer] && parentIndex > 0) {
        int temp = heap[parentIndex];
        heap[parentIndex] = heap[currPointer];
        heap[currPointer] = temp;
        // swap [1, 2] => [2, 1]
        currPointer = parentIndex; //currPointer 2 -> 1, lastPointer = 2;
        parentIndex = currPointer/2; //parentIndex 1 -> 0;
      }
    }

    public void pop() {
      //pop logic, max value and remove
      if(!isEmpty()) {
        //SWAP LAST <-> FIRST
        sb.append(heap[1]);
        int temp = heap[1];
        heap[1] = heap[lastPointer];
        heap[lastPointer] = temp;
        lastPointer -=1;

        //HEAPIPY
        int currIndex = 1;
        int leftIndex = currIndex * 2;
        int rightIndex = currIndex * 2 + 1;

        boolean overFlowCheckForLeft = leftIndex <= lastPointer;
        boolean overFlowCheckForRight = rightIndex <= lastPointer;

        while(overFlowCheckForLeft && overFlowCheckForRight && heap[rightIndex] > heap[currIndex] || heap[leftIndex] > heap[currIndex]) {
          if(heap[leftIndex] > heap[rightIndex]) {
            int currTemp = heap[currIndex];
            heap[currIndex] = heap[leftIndex];
            heap[leftIndex] = currTemp;
            currIndex = leftIndex;
          } else {
            int currTemp = heap[currIndex];
            heap[currIndex] = heap[rightIndex];
            heap[rightIndex] = currTemp;
            currIndex = rightIndex;
          }
          leftIndex = currIndex * 2;
          rightIndex = currIndex * 2 + 1;
        }
      } else {
        sb.append(0);
      }
    }

    public boolean isEmpty() {
      if(lastPointer <= 1) {
        return true;
      }
      return false;
    }

    public StringBuilder getSb() {
      return sb;
    }

    public int top() {
      return heap[0];
    }

  }

  public static void main(String[] args) throws IOException  {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    Integer count = Integer.parseInt(br.readLine());
    int[] values = new int[100001];

    for(int i=0; i<count; i++) {
      Integer v = Integer.parseInt(br.readLine());
      values[i] = v;
    }

    Heap heap = new Problem11279Solution().new Heap(100001, new StringBuilder());

    for(int i=0; i<count; i++) {
      if(values[i] == 0) {
        heap.pop();
      } else {
        heap.insert(values[i]);
      }
    }

    System.out.println(heap.getSb().toString());
  }

}
