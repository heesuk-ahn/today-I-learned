package etc;

import java.util.HashMap;

public class HashMapTest {


  public static void main(String[] args) {
    HashMap<Integer, String> hashMap = new HashMap<>();
    //만약에 없다면 넣는다.
    hashMap.putIfAbsent(16, "ab");
    hashMap.putIfAbsent(16, "cd");
    System.out.println(hashMap.get(18) != null);
  }

}
