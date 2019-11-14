package recursion;

public class PrintBinary {

  public String convertToBinary(int decimalNumber) {
    if(decimalNumber == 1) return "1";
    int divisionValue = decimalNumber / 2;
    int remainder = decimalNumber % 2;
    return convertToBinary(divisionValue) + remainder;
  }

  public static void main(String[] args) {
    System.out.println(new PrintBinary().convertToBinary(5));
  }

}
