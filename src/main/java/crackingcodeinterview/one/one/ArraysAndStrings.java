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
}
