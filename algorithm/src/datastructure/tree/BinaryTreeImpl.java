package datastructure.tree;

import java.util.Optional;

/**
 * implement Binary Tree and traverse inoder, preOrder, postOrder
 */
public class BinaryTreeImpl {

  public class BinaryTree {

    private Node root;

    public BinaryTree(Node root) {
      this.root = root;
    }

    public void inorderSearch(Node node) {
      if(node != null) {
        inorderSearch(node.getLeft());
        System.out.println(node.getData());
        inorderSearch(node.getLeft());
      }
    }

    public void preOrderSearch(Node node) {
      if(node != null) {
        System.out.println(node.getData());
        preOrderSearch(node.getLeft());
        preOrderSearch(node.getRight());
      }
    }

    public void postOrderSearch(Node node) {
      if(node != null) {
        postOrderSearch(node.getLeft());
        postOrderSearch(node.getRight());
        System.out.println(node.getData());
      }
    }

  }

  public static void main(String[] args) {

    Node rootNode = new Node(Optional.empty(), Optional.empty(), 0);
    Node leftNode = new Node(Optional.empty(), Optional.empty(), 1);
    Node rightNode = new Node(Optional.empty(), Optional.empty(), 2);

    rootNode.setLeft(leftNode);
    rootNode.setRight(rightNode);

    Node leftOfleftNode = new Node(Optional.empty(), Optional.empty(), 3);
    Node rightOfRightNode = new Node(Optional.empty(), Optional.empty(), 4);

    leftNode.setLeft(leftOfleftNode);
    leftNode.setRight(rightOfRightNode);

    System.out.println("---중위 순회---");
    new BinaryTreeImpl().new BinaryTree(rootNode).inorderSearch(rootNode);

    System.out.println("---전위 순회---");
    new BinaryTreeImpl().new BinaryTree(rootNode).preOrderSearch(rootNode);

    System.out.println("---후위 순회---");
    new BinaryTreeImpl().new BinaryTree(rootNode).postOrderSearch(rootNode);

  }

}
