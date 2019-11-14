package datastructure.tree;

import java.util.Optional;

/**
 * implement Binary search Tree
 *
 * rule)
 *  - extend binary tree (maximum node is two)
 *  - subtree left < root < subtree right
 */
public class MutableBinarySearchTreeImpl {

  private Node root;

  public MutableBinarySearchTreeImpl(Node root) {
    this.root = root;
  }

  //부모 노드를 알아야 하게 구현
  public void insert(Node parentNode, Node currNode, Node newNode) {
    if(root == null) root = newNode;
    if(currNode != null && (Integer) currNode.getData() > (Integer) newNode.getData()) {
      parentNode = currNode;
      insert(parentNode, currNode.getLeft(), newNode);
    }
    if(currNode != null && (Integer) currNode.getData() < (Integer) newNode.getData()) {
      parentNode = currNode;
      insert(parentNode, currNode.getRight(), newNode);
    }
    if(currNode == null && (Integer) parentNode.getData() > (Integer) newNode.getData()) {
      parentNode.setLeft(newNode);
    }
    if(currNode == null && (Integer) parentNode.getData() < (Integer) newNode.getData()) {
      parentNode.setRight(newNode);
    }
  }

  //부모 노드를 몰라도 되게 구현
  public void insert(Node currNode, Node newNode) {
    if((Integer) currNode.getData() > (Integer) newNode.getData()) {
      if(currNode.getLeft() != null) {
        insert(currNode.getLeft(), newNode);
      } else {
        currNode.setLeft(newNode);
      }
    }
    if((Integer) currNode.getData() < (Integer) newNode.getData()) {
      if(currNode.getRight() != null) {
        insert(currNode.getRight(), newNode);
      } else {
        currNode.setRight(newNode);
      }
    }
  }

  //이진 검색 트리에서 값을 찾을 때는 값의 크기를 비교해가며 재귀적으로 순회해야한다.
  public boolean search(Node currNode, Integer value) {
    //step 0) 현재 노드가 null이라면, 못찾은 것이므로 false를 return 한다.
    if(currNode == null) {
      return false;
    }
    //step 1) 찾고자 하는 값보다 크다면 왼쪽으로 이동
    if((Integer) currNode.getData() > value) {
      currNode = currNode.getLeft();
      return search(currNode, value);
    }
    //step 2) 찾고자 하는 값보다 작다면 오른쪽으로 이동,
    else if((Integer) currNode.getData() < value) {
      currNode = currNode.getRight();
      return search(currNode, value);
    }
    //step 3) 일치하는 값을 찾았다면 true
    else {
      return true;
    }
  }

  //subtree에서 제일 왼쪽이 가장 작은 값이다.
  public Integer searchMinimumValueInTree(Node node) {
    if(node.getLeft() != null) {
      return searchMinimumValueInTree(node.getLeft());
    } else {
      return (Integer) node.getData();
    }
  }

  //subtree에서 제일 오른쪽이 가장 큰 값이다.
  public Integer searchMaxValueInTree(Node node) {
    if(node.getRight() != null) {
      return searchMaxValueInTree(node.getRight());
    } else {
      return (Integer) node.getData();
    }
  }

  public Optional<Integer> findLeftSuccessor(Node parentNode, Node childrenNode) {
    if(childrenNode.getRight() != null) {
      parentNode = childrenNode;
      findLeftSuccessor(parentNode, childrenNode.getRight());
    }
    if(parentNode.getLeft() == childrenNode && parentNode != root) {
      return Optional.of((Integer) parentNode.getData());
    } else {
      return Optional.empty();
    }
  }

  //targetValue의 successor를 찾는다. successor란 바로 다음으로 큰 수이다.
  public Optional<Integer> findSuccessor(Node targetNode) {
    //step 1). right node가 있는가
    if(targetNode.getRight() != null) {
      //step 2-1) right node가 있다면, right node에서 가장 작은 수를 찾아온다
      return Optional.of(searchMinimumValueInTree(targetNode.getRight()));
    } else {
      //step 2-2) right node가 없다면,
      //step 3-1) left node는 있는가
      if(targetNode.getLeft() == null) {
        return Optional.empty();
      } else {
        //step 3-3) 있다면, 왼쪽의 가장 오른쪽 리프 노드를 찾는다.
        Node currNode = targetNode.getLeft();
        Node parentNode = targetNode;
        //step 3-4) 해당 리프 노드에서 부터 거슬러 올라오면서, 부모 노드의 왼쪽 자식 노드가 되는 순간에 그 부모 노드가 successor이다.
        return findLeftSuccessor(parentNode, currNode);
      }

    }
  }

  public static void main(String[] args) {
    //make node
    Node root = new Node(Optional.empty(), Optional.empty(), 5);
    Node node1 = new Node(Optional.empty(), Optional.empty(), 3);
    Node node2 = new Node(Optional.empty(), Optional.empty(), 7);
    Node node3 = new Node(Optional.empty(), Optional.empty(), 2);
    Node node4 = new Node(Optional.empty(), Optional.empty(), 9);
    Node node5 = new Node(Optional.empty(), Optional.empty(), 6);
    Node node6 = new Node(Optional.empty(), Optional.empty(), 4);

    MutableBinarySearchTreeImpl bst = new MutableBinarySearchTreeImpl(root);

    //insert 시에는 기존의 구조는 손상시키지 않고, 말단 노드에 연결한다.
    bst.insert(root, node1);
    bst.insert(root, node2);
    bst.insert(root, node3);
    bst.insert(root, node4);
    bst.insert(root, node5);
    bst.insert(root, node6);

    /*
      예상되는 트리 모양

            5
          /   \
         3     7
        / \   / \
       2   4 6   9

     */
    boolean isExist = bst.search(root, 5);
    Integer minimumValue = bst.searchMinimumValueInTree(root);
    Integer maxValue = bst.searchMaxValueInTree(root);
    Optional<Integer> findSuccess = bst.findSuccessor(root);

    System.out.println("search value exist? : " + isExist);
    System.out.println("minimum value : " + minimumValue);
    System.out.println("max value : " + maxValue);
    System.out.println("find successor : " + findSuccess.orElse(0));
  }

}
