package datastructure.list;

public class SimpleLinkedList {

  private Node head;
  private Node tail;

  public void addNewNode(Node newNode) {
    if(head == null) {
      head = newNode;
      tail = newNode;
    } else {
      tail.setNext(newNode);
      tail = newNode;
    }
  }

  public Node getHead() {
    return this.head;
  }

  public Node getTail() {
    return this.tail;
  }

  protected Node setHead(Node newHead) {
    this.head = newHead;
    return this.head;
  }

  protected Node setTail(Node newTail) {
    this.tail = newTail;
    return this.tail;
  }
}
