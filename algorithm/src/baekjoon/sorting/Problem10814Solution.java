package baekjoon.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 문제. 백준 10814 나이순 정렬
 *
 * link) https://www.acmicpc.net/problem/10814
 *
 */
public class Problem10814Solution {

  class Person {

    private final int old;
    private final String name;

    public Person(int old, String name) {
      this.old = old;
      this.name = name;
    }

    public int getOld() {
      return old;
    }

    public String getName() {
      return name;
    }
  }

  void merge(ArrayList<Person> people, int start, int middle, int end) {
    ArrayList<Person> temp = (ArrayList<Person>) people.clone();

    int leftStart = start;
    int leftEnd = middle;
    int rightStart = middle + 1;
    int rightEnd = end;
    int tempCounter = start;

    while(leftStart <= leftEnd && rightStart <= rightEnd) {
      if(people.get(leftStart).getOld() <= people.get(rightStart).getOld()) {
        temp.add(tempCounter++, people.get(leftStart++));
      } else {
        temp.add(tempCounter++, people.get(rightStart++));
      }
    }
    while(leftStart <= leftEnd) {
      temp.add(tempCounter++, people.get(leftStart++));
    }
    while(rightStart <= rightEnd) {
      temp.add(tempCounter++, people.get(rightStart++));
    }

    for(int i=start; i<=end; i++) {
      people.set(i, temp.get(i));
    }
  }

  void mergeSort(ArrayList<Person> people, int start, int end) {
    if(start < end) {
      int middle = (start + end) / 2;

      mergeSort(people, start, middle);
      mergeSort(people, middle+1, end);
      merge(people, start, middle, end);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int count = Integer.parseInt(br.readLine());
    ArrayList<Person> people = new ArrayList<>();

    for(int i=0; i<count; i++) {
      String[] line = br.readLine().split(" ");
      int old = Integer.parseInt(line[0]);
      String name = line[1];
      people.add(new Problem10814Solution().new Person(old, name));
    }

    //merge sort
    new Problem10814Solution().mergeSort(people, 0, count-1);

    StringBuilder sb = new StringBuilder();

    for(Person p: people) {
      sb.append(p.getOld() + " " + p.getName() + "\n");
    }

    System.out.println(sb.toString());
  }

}
