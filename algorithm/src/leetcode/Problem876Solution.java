package leetcode;

/*
  876. Middle of the Linked List (EASY)

  단일 연결 리스트가 주어졌을 때, 리스트의 중간을 split하여 반환하는 함수를 만들어라.

  1) 문제해결 아이디어

  단방향 리스트이기 때문에, 일단 전체 길이를 알아야 한다. 전체 길이를 알기 위해서는 리스트의 끝까지 순회를 하면서
  list의 사이즈를 구해야 한다. O(n)

  그 후, 구한 list의 사이즈를 가지고 2를 나누어서 중간 길이를 구한 뒤,

  해당 길이만큼만 순회한다.
*/

public class Problem876Solution {

  public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
  }

  public ListNode middleNode(ListNode head) {

    //step1) 전체 사이즈를 구한다.
    int listSize = 0;
    ListNode headCopy = head;

    while(headCopy.next != null) {
      ListNode currNode = headCopy.next;
      headCopy = currNode;
      listSize += 1;
    }

    //step2) 전체 사이즈를 2로 나눈 값에 +1을 하여 중간지점을 찾는다
    int middleSize = (listSize % 2 == 0) ? (listSize / 2) : (listSize / 2) + 1;

    //step3) next로 이동하는데 중간만큼만 이동한다.
    while(middleSize-- != 0) {
      head = head.next;
    }

    return head;
  }

}
