package etc;

import java.util.Stack;

/**
 * 애플에서 출제된 문제입니다.
 *
 * 주어진 이진 트리에서, 루트 (최상위 노드) 에서 리프 (자식이 없는 최하위 노드) 까지의 경로를 모두 더하였을 떄 가장 작은 값을 갖는 경로를 찾고, 그 합을 반환하세요.
 * 예를 들어, 이 트리에서 최소 값을 갖는 경로는 [10, 5, 1, -1] 이며, 그 합인 15를 반환해야 합니다.
 *
 * 아이디어 1) 이 문제는 이진 트리를 순회하는 방법에 대해서 알면 되는 문제다.
 */
public class BinaryTreeSum {

  int min = Integer.MAX_VALUE;

  static class Node {

    Node left;
    Node right;
    int data;

    public Node(int data) {
      this.data = data;
    }

    Node getLeft() {
      return this.left;
    }

    Node getRight() {
      return this.right;
    }

    void setLeft(Node newNode) {
      this.left = newNode;
    }

    void setRight(Node newNode) {
      this.right = newNode;
    }

    int getData() {
      return this.data;
    }
  }

  public int getMinValueFromBinaryTreeForRec(Node currNode, int pathSum, int min) {
    if(currNode == null) {
      return Math.min(min, pathSum);
    }
    int leftSum = getMinValueFromBinaryTreeForRec(currNode.left, pathSum + currNode.data, min);
    int rightSum = getMinValueFromBinaryTreeForRec(currNode.right, pathSum + currNode.data, min);
    int minValue = Math.min(leftSum, rightSum);
    min = Math.min(minValue, min);
    return min;
  }

  /*
    반복문으로 구현
   */
  class NodeWithCounter {
    Node node;
    int pathCount;

    public NodeWithCounter(Node node, int pathCount) {
      this.node = node;
      this.pathCount = pathCount;
    }
  }

  public int getMinValueFromBinaryTreeForLoop(NodeWithCounter currNodeWithCounter) {
    NodeWithCounter currNode = currNodeWithCounter;
    int min = Integer.MAX_VALUE;
    Stack<NodeWithCounter> stack = new Stack<>();

    while(true) {
      while(currNode.node != null) {
        currNode.pathCount += currNode.node.data;
        stack.push(currNode);
        currNode = new NodeWithCounter(currNode.node.getLeft(), currNode.pathCount);
      }

      if(!stack.isEmpty()) {
        NodeWithCounter node = stack.pop();

        //leaf node
        if(node.node.getRight() == null) {
          min = Math.min(min, node.pathCount);
        } else {
          currNode = new NodeWithCounter(node.node.getRight(), node.pathCount);
        }
      } else {
        break;
      }
    }

    return min;
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
    Node root = new Node(10);
    root.setLeft(new Node(5));
    root.setRight(new Node(5));

    root.getLeft().setRight(new Node(2));

    root.getRight().setRight(new Node(1));
    root.getRight().getRight().setLeft(new Node(-1));

    System.out.println(new BinaryTreeSum().getMinValueFromBinaryTreeForRec(root, 0, Integer.MAX_VALUE));
    System.out.println(new BinaryTreeSum().getMinValueFromBinaryTreeForLoop(new BinaryTreeSum().new NodeWithCounter(root, 0)));
  }

}
