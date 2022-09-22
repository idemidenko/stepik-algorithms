package stepik.algo.two;

import java.util.Scanner;

public class GreatestCommonDivisor {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int a = scanner.nextInt();
    int b = scanner.nextInt();

    if (b > a) {
      int temp = a;
      a = b;
      b = temp;
    }

    while (b != 0) {
      int mod = a % b;
      a = b;
      b = mod;
    }

    System.out.println(a);
  }
}
