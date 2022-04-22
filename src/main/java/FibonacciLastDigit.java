import java.util.Scanner;

public class FibonacciLastDigit {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();

    int[] fibLastDigit = new int[2];
    fibLastDigit[1] = 1;

    for (int i = 2; i <= n; i++) {
      int lastDigitSum = (fibLastDigit[0] + fibLastDigit[1]) % 10;
      fibLastDigit[0] = fibLastDigit[1];
      fibLastDigit[1] = lastDigitSum;
    }

    System.out.println(fibLastDigit[1]);
  }
}
