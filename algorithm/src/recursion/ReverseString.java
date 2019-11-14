package recursion;

public class ReverseString {

  String reverseStringForRec(String str) {
    if(str.equals("")) {
      return "";
    }
    return reverseStringForRec(str.substring(1)) + str.charAt(0);
  }

  String reverseStringForIter(String str) {
    int length = str.length();
    String reverseStr = "";

    while(length-- > 0) {
      System.out.println("length : " + length);
      reverseStr += str.charAt(length);
    }
    return reverseStr;
  }

  public static void main(String[] args) {
    String ans = new ReverseString().reverseStringForRec("abc");
    String ans2 = new ReverseString().reverseStringForIter("abc");

    System.out.println(ans);
    System.out.println(ans2);
  }

}
