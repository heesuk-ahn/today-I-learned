package datastructure.list;

public class ReverseList extends SimpleLinkedList {

  //return 타입이 있는 재귀
  public Node reverseList(Node parentNode, Node currNode) {

    //단순 action 재귀를 통해 끝까지 위치를 옮긴다.
    if(currNode.getNext() != null) {
      System.out.println("go to next node : " + currNode.getData());
      reverseList(currNode, currNode.getNext());
    }

    if(currNode == getHead()) {
      Node head = getHead();
      Node tail = getTail();

      currNode.setNext(null);
      Node temp = head;
      Node newHead = setHead(tail);
      setTail(temp);

      return newHead;
    } else {
      currNode.setNext(parentNode);
      return currNode;
    }

  }


  public static void main(String[] args) {

    Node firstNode = new Node(1);
    Node secondNode = new Node(2);

    Node newNode = new Node(3);

    SimpleLinkedList simpleLinkedList = new ReverseList();

    simpleLinkedList.addNewNode(firstNode);
    simpleLinkedList.addNewNode(secondNode);
    simpleLinkedList.addNewNode(newNode);

    Node currNode = simpleLinkedList.getHead();

    while(currNode != null) {
      System.out.println(currNode.getData());
      currNode = currNode.getNext();
    }

    /*
      expected LinkedList

      head -> 1 -> 2 -> 3 -> null

    */

    Node reverseHead = ((ReverseList) simpleLinkedList).reverseList(firstNode, firstNode);

    System.out.println(reverseHead.getData());
    System.out.println(reverseHead.getNext().getData());
    System.out.println(reverseHead.getNext().getNext().getData());

    System.out.println(simpleLinkedList.getHead().getData());
    System.out.println(simpleLinkedList.getTail().getData());

     /*
      expected reverse linked list

      head -> 3 -> 2 -> 1 -> null

    */
  }

}
