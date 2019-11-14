package stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
  스냅챗에서 출제된 문제입니다.
  중첩될 수 있는 인터벌들을 갖는 리스트가 있습니다. 중첩되는 인터벌들을 하나로 합친 새로운 리스트를 반환하세요.
  입력 리스트는 정렬되어 있지 않습니다.
  예를 들어, [(1, 3), (5, 8), (4, 10), (20, 25)] 가 주어지면, [(1, 3), (4, 10), (20, 25)] 를 반환해야 합니다.

  아이디어 1) 모든 경우의 수 구하기 O(N^2)

  아이디어 2) 정렬하여 확인하기 O(N)
  - 주어진 범위들을 정렬한다
  - 정렬을 하면 모든 경우를 확인하지 않아도 된다.
    왜냐면, interval[i-1]이 interval[i]와 중첩되지 않는다면, interval[i+1]도 interval[i-1]과 중첩되지 않는다.
    왜냐하면, interval[i+1]은 >= interval[i]이기 때문이다. (정렬되었기 떄문!)
  - 그렇다면, 항상 interval의 중첩 비교는 바로 이전 값과 진행하면 된다.
  - 바로 '이전 값' 이라면 스택을 생각하면 쉽다.
  - 스택은 LAST IN FIRST OUT이기 때문에, 이전 값이 스택 자료구조의 TOP에 위치해있기 때문이다.

  psudo code)
  - public ArrayList<Scope> getInterval(ArrayList<Scope> scopeList) 함수 선언
  - scopeList를 첫번째 값을 기준으로 정렬한다.
  - stack에 첫번째 값을 넣는다
  - scopeList 다음 값을 보면서 TOP과 비교하여 겹치는 부분이 있는지 확인.
    a) 겹친다면, pop하여 새로운 값으로 맵핑하여 push
    b) 안겹친다면, push
  - 반복하여 끝나면, 해당 값을 리스트로 만들어 반환한다.
**/
public class MergeOverrap {

  class Scope implements Comparable<Scope> {

    private final int from;
    private final int to;

    public Scope(int from, int to ){
      this.from = from;
      this.to = to;
    }
    @Override
    public int compareTo(Scope scope) {
      //오름차순 정렬 1 -> 2 -> 3
      if(scope.from < this.from) {
        return 1;
      } else {
        return -1;
      }
    }

  }

  public boolean isOverrap(Scope before, Scope newScope) {
    if(before.from < newScope.from && newScope.from < before.to) {
      return true;
    }
    return false;
  }

  public ArrayList<Scope> removeOverrapInterval(ArrayList<Scope> scopeList) {
    ArrayList<Scope> copyed = (ArrayList<Scope>) scopeList.clone(); //scala에서는 자동! 참조 투명성을 위함!
    Stack<Scope> stack = new Stack<>();

    //step 1) 정렬
    Collections.sort(copyed);

    for(Scope scope: copyed) {
      //step 2-a) 스택이 비었다면, push
      if(stack.isEmpty()) {
        stack.push(scope);
      } else {
        //step 2-b) 스택이 비어있지 않다면, TOP 확인하고 겹치는 부분이 있는가 체크. 겹친다면, pop -> 새로운 Merge 값으로 대체 push
        if(isOverrap(stack.peek(), scope)) {
          Scope top = stack.pop();
          int from = top.from < scope.from ? top.from : scope.from;
          int to = top.to > scope.to ? top.to : scope.to;
          stack.push(new Scope(from, to));
        } else {
          //step 2-c) 겹치는 부분이 없다면, push
          stack.push(scope);
        }
      }
    }
    //step 3) STACK -> ArrayList로 변환 후 반환
    copyed = new ArrayList<Scope>();
    for(Scope scope: stack) {
      copyed.add(scope);
    }
    return copyed;
  }

  public static void main(String[] args) {

    ArrayList<Scope> scopeList = new ArrayList<>();

    Scope scope1 = new MergeOverrap().new Scope(1, 3);
    Scope scope2 = new MergeOverrap().new Scope(5, 8);
    Scope scope3 = new MergeOverrap().new Scope(4, 10);
    Scope scope4 = new MergeOverrap().new Scope(20, 25);

    scopeList.add(scope1);
    scopeList.add(scope2);
    scopeList.add(scope3);
    scopeList.add(scope4);

    ArrayList<Scope> ans = new MergeOverrap().removeOverrapInterval(scopeList);

    for(Scope a: ans) {
      System.out.println(a.from + ", " + a.to);
    }

  }

}
