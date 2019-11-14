package etc;


/**
 *
 * arr = [2, 7, 4, 0, 9, 5, 1, 3]
 * sum = 20
 *
 * 정렬되어있지 않은 arr에서 4개의 수를 골라서 그 합이 20인 갯수를 구하라.
 *
 *
 * IDEA 1) 모든 경우의 수를 구하기 O(N^4)
 *  - 주어진 array에서 4개의 수를 고른다.
 *  - 4개의 수의 합이 sum과 같다면 count를 올린다.
 *
 * IDEA 2) 해시맵을 사용한다.
 *
 * - 4개의 수를 모두 찾으려면 O(N^4)가 걸린다.
 * - 4개의 수를 모두 순회하지말고 2개씩 쌍으로 순회한다.
 * - 각 쌍의 합을 key로 한다.
 * - 각 쌍의 합이 20이 되면 그 경우가 Quadruplet이다.
 * - 그러기 위해서는 sum - two value를 더한것을 빼고, 남은 것과 일치하는 key를 맵에서 찾는다.
 * - 여기서도 핵심은 결국 해시맵은 key를 통해 value를 찾아오는 것이다.
 * - 해시맵을 사용해야한다는 생각이 들기가 어려웠다.
 *
 ** solution link) https://www.techiedelight.com/4-sum-problem/
 */
public class ArrayQuadruplet {


  int getCountForExpectedSum(int[] arr, int sum) {

    return 1;
  }

  public static void main(String[] args) {

  }

}
