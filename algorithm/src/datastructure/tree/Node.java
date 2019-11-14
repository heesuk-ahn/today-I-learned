package datastructure.tree;

import java.util.Optional;

public class Node {

  private Optional<Node> left;
  private Optional<Node> right;
  private Object data;

  public Node(Optional<Node> left, Optional<Node> right, Object data) {
    this.left = left;
    this.right = right;
    this.data = data;
  }

  public Object getData() {
    return this.data;
  }

  public Node getLeft() { return this.left.orElse(null); }

  public Node getRight() { return this.right.orElse(null); }

  public void setLeft(Node leftNode) {
    this.left = Optional.of(leftNode);
  }

  public void setRight(Node rightNode) {
    this.right = Optional.of(rightNode);
  }

  public void setData(Object data) {
    this.data = data;
  }

}