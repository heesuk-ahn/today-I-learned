package datastructure.tree;

import java.util.Optional;

/**
 *
 * 이진 트리를 순회하자.
 * 트리 순회는 재귀와 반복 둘다 사용한다.
 */
public class BinaryTraverse {

  public void inorderForRec(Node currNode) {
    if(currNode.getLeft() != null) {
      inorderForRec(currNode.getLeft());
    }
    System.out.println(currNode.getData());
    if(currNode.getRight() != null) {
      inorderForRec(currNode.getRight());
    }
  }

  public void preOrderForRec(Node currNode) {
    System.out.println(currNode.getData());
    if(currNode.getLeft() != null) {
      preOrderForRec(currNode.getLeft());
    }
    if(currNode.getRight() != null) {
      preOrderForRec(currNode.getRight());
    }
  }

  public void postOrderForRec(Node currNode) {
    if(currNode.getLeft() != null) {
      postOrderForRec(currNode.getLeft());
    }
    if(currNode.getRight() != null) {
      postOrderForRec(currNode.getRight());
    }
    System.out.println(currNode.getData());
  }

  public void postOrderForLoop(Node currNode) {

  }

  public static void main(String[] args) {
   /*
     10
    /  \
   5    5
    \     \
     2    1
          /
        -1

     */
    Node left = new Node(Optional.empty(), Optional.of(new Node(Optional.empty(), Optional.empty(), 2)), 5);
    Node right = new Node(Optional.empty(), Optional.of(new Node(Optional.of(new Node(Optional.empty(), Optional.empty(), -1)), Optional.empty(), 1)), 5);
    Node root = new Node(Optional.of(left), Optional.of(right), 10);


    System.out.println("재귀 중위 순회");
    new BinaryTraverse().inorderForRec(root);
    System.out.println("-----------");

    System.out.println("재귀 전위 순회");
    new BinaryTraverse().preOrderForRec(root);
    System.out.println("-----------");

    System.out.println("재귀 후위 순회");
    new BinaryTraverse().postOrderForRec(root);
    System.out.println("-----------");

  }

}
