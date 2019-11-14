package recursion;

public class Fibonacci {

  //재귀를 이용하여 탑-다운 방식
  public int getFibonacciValueForRec(int n) {
    if (n != 0 && n <=2) return 1;
    if (n == 0) return 0;
    return getFibonacciValueForRec(n-2) + getFibonacciValueForRec(n-1);
  }

  public int getFibonacciValueForLoop(int n) {
    int count = 2;
    int firstValue = 0;
    int secondValue = 1;
    int nextValue = 1;

    while(count < n) {
      firstValue = secondValue;
      secondValue = nextValue;
      nextValue = firstValue + secondValue;
      ++count;
    }

    return nextValue;
  }

  public static void main(String[] args) {
    int n = 3;

    int resForLoop = new Fibonacci().getFibonacciValueForLoop(n);
    int resForRec = new Fibonacci().getFibonacciValueForRec(n);
    System.out.println(resForLoop);
    System.out.println(resForRec);



  }

}
