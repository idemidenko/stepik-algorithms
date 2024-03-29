package crackingcodeinterview.one.one;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArraysAndStrings {

  public static void main(String[] args) {
    String str = "string11";
    System.out.println(str + " is unique: " + isUniqueChars(str));

    String s2 = "1string1";
    System.out.println(str + " is permutation of " + s2 + ": " + isPermutation(str, s2));

    String urlifyInput = " a  b      ";
    System.out.println(
        "urlify: input - [" + urlifyInput + "], result - [" + urlify(urlifyInput) + "]");

    String palindrome = "aba";
    System.out.println(
        "String: ["
            + palindrome
            + "] isPalindromePermutation: ["
            + isPalindromePermutation(palindrome)
            + "]");
    String palindromePermutation = "Tact Coa";
    System.out.println(
        "String: ["
            + palindromePermutation
            + "] isPalindromePermutation: ["
            + isPalindromePermutation(palindromePermutation)
            + "]");

    printIsOneEditAway("pale", "ple");
    printIsOneEditAway("pales", "pale");
    printIsOneEditAway("pale", "bale");
    printIsOneEditAway("pale", "bake");
    printIsOneEditAway("sale", "sale");
    printIsOneEditAway("", "");

    printCompress("aabcccccaaa");
    printCompress("");
    printCompress("aabcca");

    printRotateMatrix(
        new int[][] {
          {0, 1, 2, 3, 4},
          {5, 6, 7, 8, 9},
          {10, 11, 12, 13, 14},
          {15, 16, 17, 18, 19},
          {20, 21, 22, 23, 24}
        });
    printRotateMatrix(
        new int[][] {
          {0, 1, 2, 3},
          {4, 5, 6, 7},
          {8, 9, 0, 1},
          {2, 3, 4, 5}
        });

    printZeroMatrix(
        new int[][] {
          {1, 2, 3, 4, 5},
          {6, 5, 3, 0, 5},
          {1, 1, 2, 0, 2},
          {1, 1, 2, 1, 2},
          {0, 1, 2, 1, 0},
          {3, 1, 2, 1, 2}
        });
  }

  // nlogn
  /**
   * Is Unique: Implement an algorithm to determine if a string has all unique characters. What if
   * you cannot use additional data structures?
   */
  private static boolean isUniqueChars(String str) { // string11
    if (str == null) {
      throw new IllegalArgumentException("Incoming string should not be null");
    }
    char[] chars = str.toCharArray(); // ['s' 0, 't' 1, 'r' 2, 'i' 3, 'n' 4, 'g' 5, '1' 6, '1' 7]
    for (int i = 0; i < chars.length; i++) { // 0 | 1 | 2 | 3
      char c = chars[i]; // 's' | t | r | i
      for (int j = i + 1; j < chars.length; j++) { // 4 | 5 | 6 | 7
        if (c == chars[j]) { // i == n | g | 1 | 1
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Check Permutation: Given two strings,write a method to decide if one is a permutation of the
   * other.
   */
  private static boolean isPermutation(String s1, String s2) {
    if (s1 == null || s2 == null) {
      throw new IllegalArgumentException("Params should not be null. s1 = " + s1 + ", s2 = " + s2);
    }

    char[] s1Array = s1.toCharArray();
    Arrays.sort(s1Array);
    char[] s2Array = s2.toCharArray();
    Arrays.sort(s2Array);
    return Arrays.equals(s1Array, s2Array);
  }

  /**
   * URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the
   * string has sufficient space at the end to hold the additional characters,and that you are given
   * the "true" length of the string. (Note: If implementing in Java,please use a character array so
   * that you can perform this operation in place.) EXAMPLE Input: "Mr John Smith ", 13 Output:
   * "Mr%20John%20Smith"
   */
  private static String urlify(String s) {
    char[] chars = s.toCharArray();
    boolean skipSpace = true;

    for (int i = chars.length - 1, j = chars.length - 1;
        i >= 0;
        i--) { // 10 10 | 9 10 | ... | 4 10 | 3 9 | 2 6 | 1 3 | 0 2
      char c = chars[i]; // b | ' ' | ' ' | a |

      if (c == ' ') {
        if (skipSpace) {
          continue;
        }
        // replace space
        chars[j--] = '0';
        chars[j--] = '2';
        chars[j--] = '%';
      } else {
        skipSpace = false;
        chars[j--] = c; // b | a
      }
    }
    return new String(chars);
  }

  /**
   * Palindrome Permutation: Given a string, write a function to check if it is a permutation of a
   * palindrome. A palindrome is a word or phrase that is the same forwards and backwards. A
   * permutation is a rearrangement of letters. The palindrome does not need to be limited to just
   * dictionary words. EXAMPLE Input: Tact Coa Output: True (permutations: "taco cat", "atco cta",
   * etc.)
   *
   * <p>Input: T actC oa Output: True (permutations: "t acoc at", "a tcoc ta", etc.)
   */
  private static boolean isPalindromePermutation(String s) {
    if (s == null || s.length() < 3) {
      return false;
    }

    s = s.replaceAll("\\s", "").toLowerCase();

    if (isPalindrome(s)) {
      return false;
    }
    char[] chars = s.toCharArray();
    Map<Character, Integer> charCountMap = new HashMap<>();
    for (char c : chars) {
      int count = charCountMap.getOrDefault(c, 0);
      charCountMap.put(c, ++count);
    }

    boolean oddCountFound = false;
    for (int i : charCountMap.values()) {
      if (i % 2 != 0) {
        if (oddCountFound) {
          return false;
        } else {
          oddCountFound = true;
        }
      }
    }
    return true;
  }

  private static boolean isPalindrome(String s) {
    char[] chars = s.toCharArray();
    for (int i = 0, j = chars.length - 1; i < j; i++) {
      if (chars[i] != chars[j--]) {
        return false;
      }
    }
    return true;
  }

  /*
  One Away: There are three types of edits that can be performed on strings: insert a character,
  remove a character, or replace a character. Given two strings, write a function to check if
  they are one edit (or zero edits) away.

  EXAMPLE
  pale, ple -> true
  pales, pale -> true
  pale, bale -> true
  pale, bake -> false
  */
  public static boolean isOneEditAway(String s1, String s2) {
    if (s1 == null || s2 == null) {
      return false;
    }

    char[] chars1 = s1.toCharArray();
    char[] chars2 = s2.toCharArray();

    int lengthDiff = chars1.length - chars2.length;
    boolean removeOperation = false;
    if (lengthDiff == 1 || lengthDiff == -1) {
      removeOperation = true;
    } else if (lengthDiff != 0) {
      return false;
    }

    int numberOfEdits = 0;
    int maxNumberOfEdits = 1;
    for (int i = 0, j = 0; i < chars1.length && j < chars2.length; i++, j++) {
      char c1 = chars1[i];
      char c2 = chars2[j];

      if (c1 != c2) {
        if (++numberOfEdits > maxNumberOfEdits) {
          return false;
        }

        if (removeOperation) {
          if (chars1.length > chars2.length) {
            c1 = chars1[++i];
          } else {
            c2 = chars2[++j];
          }
          if (c1 != c2) {
            return false;
          }
        }
      }
    }

    return true;
  }

  private static void printIsOneEditAway(String s1, String s2) {
    System.out.printf(
        "isOneEditAway: s1 = [%s], s2 = [%s], result = [%s]%n", s1, s1, isOneEditAway(s1, s2));
  }

  /*
  String Compression: Implement a method to perform basic string compression using the counts of
  repeated characters. For example, the string aabcccccaaa would become a2blc5a3.
  If the "compressed" string would not become smaller than the original string, your method should
  return the original string. You can assume the string has only uppercase and lowercase
  letters (a - z).
   */
  public static String compress(String s) {
    if (s == null || s.length() < 3) {
      return s;
    }

    char[] chars = s.toCharArray();
    int repeatedCharsCounter = 1;
    StringBuilder sb = new StringBuilder();

    for (int i = 0, j = 1; j < chars.length; i++, j++) {
      char current = chars[i];
      char next = chars[j];

      if (current == next) {
        repeatedCharsCounter++;
      } else {
        sb.append(current).append(repeatedCharsCounter);
        repeatedCharsCounter = 1;
      }
    }

    sb.append(chars[chars.length - 1]).append(repeatedCharsCounter);
    return sb.length() < s.length() ? sb.toString() : s;
  }

  public static void printCompress(String s) {
    System.out.printf("compress: s=[%s], result=[%s]%n", s, compress(s));
  }

  /*
     Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is
     4 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
  */
  public static int[][] rotateMatrix(int[][] matrix) {
    if (matrix == null) {
      return null;
    }

    int n = matrix.length;

    for (int square = 0; square < n / 2; square++) { // 0
      int j = square; // 0

      for (int x = 0; x < n - 2 * square - 1; x++, j++) { // 0
        int i = square; // 0 | 0
        int currentValue = matrix[i][j]; // 2 | 8
        for (int y = 0; y < 4; y++) { // 0 | 1 | 2 | 3 | 0
          int nextValue = matrix[j][n - i - 1]; // 0 | 3 | 5 | 2 | 1
          matrix[j][n - i - 1] = currentValue; // 2 | 0 | 3 | 5 | 8
          currentValue = nextValue; // 0 | 3 | 5 | 2 | 1
          int tempi = i; // 0 | 0 | 3 | 3
          i = j; // 0 | 3 | 3 | 0
          j = n - tempi - 1; // 3 | 3 | 0 | 0
        }
      }
    }
    return matrix;
  }

  public static void printRotateMatrix(int[][] matrix) {
    System.out.printf(
        "rotateMatrix: matrix=[%s], result=[%s]%n",
        Arrays.deepToString(matrix), Arrays.deepToString(rotateMatrix(matrix)));
  }

  /*
    Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0,
    its entire row and column are set to 0.
  */
  public static void zeroMatrix(int[][] matrix) {
    if (matrix == null) {
      return;
    }

    int[][] zeroElementsMatrix = new int[matrix.length][matrix[0].length];

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          zeroElementsMatrix[i][j] = 1;
          /*
          {0, 0, 0, 0, 0},
          {0, 0, 0, 1, 0},
          {0, 0, 0, 1, 0},
          {0, 0, 0, 0, 0},
          {1, 0, 0, 0, 1},
          {0, 0, 0, 0, 0}
          */
        }
      }
    }

    for (int i = 0; i < zeroElementsMatrix.length; i++) {
      for (int j = 0; j < zeroElementsMatrix[0].length; j++) {
        if (zeroElementsMatrix[i][j] == 1) {
          for (int x = 0; x < matrix.length; x++) {
            matrix[x][j] = 0;
          }
          for (int x = 0; x < matrix[0].length; x++) {
            matrix[i][x] = 0;
          }
          /*
          {0, 2, 3, 0, 0},
          {0, 0, 0, 0, 0},
          {0, 0, 0, 0, 0},
          {0, 1, 2, 0, 0},
          {0, 0, 0, 0, 0},
          {0, 1, 2, 0, 0}
          */
        }
      }
    }
  }

  private static void printZeroMatrix(int[][] matrix) {
    System.out.println("zeroMatrix: input:");
    for (int[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }
    zeroMatrix(matrix);
    System.out.println("zeroMatrix: result:");
    for (int[] row : matrix) {
      System.out.println(Arrays.toString(row));
    }
  }
}
