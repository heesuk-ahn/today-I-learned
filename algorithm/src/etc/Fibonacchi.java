package etc;

/**

 피보나치 함수를 구현해보세요.

 문제 이해)
 0 1 1 2 3 5 . . .

 F(N) = F(N-1) + F(N-2)

 IDEA 1) 재귀로 풀어보자.

 1) BASE CASE를 설정하자. N <=1 일 경우 return n을 돌려준다.
 TOP-DOWN이기 때문에 함수의 종료시점을 명확하게 해주지 않으면 무한 루프에 빠지게 된다.
 2) Recursive CASE를 만들자.
 F(N)은 이전항과 전전항의 합이므로, return f(n-1) + f(n-2);를 하게되면, 맨 아래까지 내려가서
 백트랙하면서 값을 더하게 된다.
 3) 시간 복잡도: O(2^n)이 된다. 재귀 호출이 2번씩 매번 일어나기 때문이다.

 IDEA 2) 반복문으로 풀자.
 1) 호출스택을 사용하지 않기 위해서 반복문을 사용할 수 있다.
 2) 이 방법은 아래에서 위로 올라가는 bottom up 방식이다.
 **/
public class Fibonacchi {

  public int getFibonacciForRec(int N) {
    if(N<=1) {
      return N;
    }
    return getFibonacciForRec(N-1) + getFibonacciForRec(N-2);
  }

  public int getFibonacciForLoop(int N) {
    int firstValue = 0;
    int secondValue = 1;
    int nextValue = 0;

    if(N <=1) {
      return N;
    }

    //첫번째 케이스는 이미 했기때문에 1부터 시작.
    for(int i=1; i<N; i++) {
      nextValue = firstValue + secondValue;
      firstValue = secondValue;
      secondValue = nextValue;
    }
    return nextValue;
  }

  public static void main(String[] args) {
    int ans1 = new Fibonacchi().getFibonacciForRec(4);
    int ans2 = new Fibonacchi().getFibonacciForLoop(4);

    /*
      0 1 1 2 3 5
      ans1 sholudBe 3
      ans2 shouldBe 3
    */
    System.out.println(ans1);
    System.out.println(ans2);
  }

}
