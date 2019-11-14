package codlity.lesson3_time_complexity;

/**
 * A small frog wants to get to the other side of the road. The frog is currently located at position X and wants to get to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.
 *
 * Count the minimal number of jumps that the small frog must perform to reach its target.
 *
 * Write a function:
 *
 * class Solution { public int solution(int X, int Y, int D); }
 *
 * that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.
 *
 * For example, given:
 *
 *   X = 10
 *   Y = 85
 *   D = 30
 * the function should return 3, because the frog will be positioned as follows:
 *
 * after the first jump, at position 10 + 30 = 40
 * after the second jump, at position 10 + 30 + 30 = 70
 * after the third jump, at position 10 + 30 + 30 + 30 = 100
 * Write an efficient algorithm for the following assumptions:
 *
 * X, Y and D are integers within the range [1..1,000,000,000];
 * X ≤ Y.
 */
public class FrogJump {

  /*
    X : 현재위치
    Y : 같거나 좀 더 먼 위치
    D : 고정된 점프할 수 있는 거리

    X에서 몇번의 D를 더하면 Y를 갈 수 있는지,
    D의 횟수를 구하여 반환하라.
  */
  public int solution(int X, int Y, int D) {
    int apartDistance = Y - X;
    double divisionValue = (double) apartDistance / D;

    int n = (int)(Math.ceil(divisionValue));
    return n;
  }

}
