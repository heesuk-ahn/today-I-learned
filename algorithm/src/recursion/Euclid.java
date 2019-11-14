package recursion;

/**
 * Euclid 호제법
 *
 * 최대 공약수를 구하는 알고리즘으로, 주어진 두 수 a b에서 큰 수를 작은 수로 나누었을 때, 나머지가 0일 경우, 작은 수가 최대 공약수가 된다.
 * 만약, 0이 아닐 경우, 작은 수였던 값과 나머지를 다시 나누어서 반복적으로 구한다.
 */
public class Euclid {

  //TOP-DOWN
  public int getMaxCommonDenominator(int a, int b) {
    int remainder = a % b;
    if(remainder == 0) return b;
    else {
      return getMaxCommonDenominator(b, remainder);
    }
  }

  //BOTTOM-UP
  public int getMaxCommonDenominatorForLoop(int a, int b) {
    System.out.println(a);
    System.out.println(b);
    System.out.println(a%b);
    while(a%b != 0) {
      b = a%b;
      a = b;
    }
    return b;
  }

  public static void main(String[] args) {
    int maxCommonDenominator = new Euclid().getMaxCommonDenominator(18, 12);
    int maxCommonDenominatorForLoop = new Euclid().getMaxCommonDenominatorForLoop(18, 12);

    System.out.println(maxCommonDenominator);
    System.out.println(maxCommonDenominatorForLoop);
  }

}
