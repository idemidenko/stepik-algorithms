package other;

import java.util.HashSet;
import java.util.Set;

// Given a string s, find the length of the longest substring without repeating characters.
public class LongestSubstring {

  public static void main(String[] args) {
    String str = "abcabbcbb";

    int longestSubstringSize = findLongestSubstringSize(str);
    System.out.println(longestSubstringSize);
  }

  private static int findLongestSubstringSize(String str) {
    if (str == null || str.isEmpty()) {
      return 0;
    }

    Set<Character> seenChars = new HashSet<>();
    int start = 0;
    int maxLength = 0;

    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      while (seenChars.contains(c)) {
        seenChars.remove(str.charAt(start++));
      }

      seenChars.add(c);

      maxLength = Math.max(maxLength, i - start + 1);
    }

    return maxLength;
  }
}
