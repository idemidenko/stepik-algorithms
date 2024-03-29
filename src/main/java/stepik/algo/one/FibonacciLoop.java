package stepik.algo.one;

import java.util.Scanner;

public class FibonacciLoop {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();

    int[] fib = new int[n + 1];
    fib[0] = 0;
    fib[1] = 1;

    for (int i = 2; i <= n; i++) {
      fib[i] = fib[i - 1] + fib[i - 2];
    }

    System.out.println(fib[n]);
  }
}
