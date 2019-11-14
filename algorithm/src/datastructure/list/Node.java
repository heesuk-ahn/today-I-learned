package datastructure.list;

public class Node {

  private final Object data;
  private Node next;

  public Node(Object data) {
    this.data = data;
  }

  public void setNext(Node nextNode) {
    this.next = nextNode;
  }

  public Object getData() {
    return this.data;
  }

  public Node getNext() {
    return next;
  }

}
