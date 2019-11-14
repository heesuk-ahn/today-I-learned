package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 문제) 백준 - 좌표 정렬하기
 *
 * link) https://www.acmicpc.net/problem/11650
 *
 * idea)
 *   - 제한 시간 1초에 N의 크기가 100만 이므로 최소 n * log N 시간 복잡도로 해결해야한다.
 *   - n logN 정렬 알고리즘은 병합정렬, 퀵정렬 등이 있다.
 *   - 둘 중에 퀵 정렬을 구현하여 해결해보자.
 */
public class Problem11650Solution {

  class PointXY {

    private final int x;
    private final int y;

    public PointXY(int x, int y) {
      this.x = x;
      this.y = y;
    }

    public int getX() {
      return x;
    }

    public int getY() {
      return y;
    }

  }

  int partition(ArrayList<PointXY> points, int start, int end) {
    int median = (start + end) / 2;

    PointXY pivotPoint = points.get(median);
    PointXY endPoint = points.get(end);

    points.set(median, endPoint);
    points.set(end, pivotPoint);

    int lowwerPoint = start-1;
    int biggerPoint = start;

    for(int i=biggerPoint; i < end; i++) {
      if(points.get(i).getX() < pivotPoint.getX()) {
        //swap
        lowwerPoint += 1;
        PointXY temp = points.get(lowwerPoint);
        points.set(lowwerPoint, points.get(i));
        points.set(i, temp);
      }
      if(points.get(i).getX() == pivotPoint.getX() && points.get(i).getY() < pivotPoint.getY()) {
        //swap
        lowwerPoint += 1;
        PointXY temp = points.get(lowwerPoint);
        points.set(lowwerPoint, points.get(i));
        points.set(i, temp);
      }
    }

    //final swap end <-> lowwerPoint + 1
    int exchangePoint = lowwerPoint + 1;

    PointXY temp = points.get(exchangePoint);
    points.set(exchangePoint, points.get(end));
    points.set(end, temp);
    return exchangePoint;
  }

  public void quickSortByPointXY(ArrayList<PointXY> points, int start, int end) {

    if(start < end) {
      int pivot = partition(points, start, end);
      quickSortByPointXY(points, start, pivot-1);
      quickSortByPointXY(points, pivot+1, end);
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = Integer.parseInt(br.readLine());
    ArrayList<PointXY> points = new ArrayList<>();

    for(int i=0; i< count; i++) {
      String inputLine = br.readLine();
      String[] splitLine = inputLine.split(" ");

      int x = Integer.parseInt(splitLine[0]);
      int y = Integer.parseInt(splitLine[1]);

      points.add(new Problem11650Solution().new PointXY(x, y));
    }

    //sorted
    new Problem11650Solution().quickSortByPointXY(points, 0, count-1);

    StringBuilder sb = new StringBuilder();

    for(PointXY p: points) {
      sb.append(p.getX() + " " + p.getY() + "\n");
    }

    System.out.println(sb.toString());
  }

}
