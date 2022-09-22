package stepik.algo.one;

import java.util.Scanner;

/**
 * <a
 * href="https://stepik.org/lesson/13228/step/8?unit=3414">https://stepik.org/lesson/13228/step/8?unit=3414</a>
 */
public class HugeFibonacciModule {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    long n = scanner.nextLong();
    int m = scanner.nextInt();

    long[] fib = new long[2];
    fib[1] = 1L;

    int[] mods = new int[6 * m];
    mods[1] = 1 % m;

    int i = 1;
    do {
      i++;
      long sum = (fib[0] % m) + (fib[1] % m);

      fib[0] = fib[1];
      fib[1] = sum;

      mods[i] = Math.toIntExact(sum % m);
      print(fib, mods, i);
    } while (mods[i - 1] != 0 || mods[i] != 1);

    int period = i - 1;
    i = Math.toIntExact(n % period);

    System.out.println(mods[i]);
  }

  private static void print(long[] fib, int[] mods, int i) {
    System.out.println("fib[" + i + "]: " + fib[1] + " | mods[" + i + "]: " + mods[i]);
  }
}
