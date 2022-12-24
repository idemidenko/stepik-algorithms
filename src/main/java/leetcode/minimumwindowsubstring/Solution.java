package leetcode.minimumwindowsubstring;

/*
Given two strings s and t of lengths m and n respectively, return the minimum window
substring
 of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.


Constraints:
m == s.length
n == t.length
1 <= m, n <= 105
s and t consist of uppercase and lowercase English letters.

Follow up: Could you find an algorithm that runs in O(m + n) time?

*/

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

class Solution {

  public static void main(String[] args) {
    Solution solution = new Solution();
    System.out.println(solution.minWindow("ab", "b"));
  }

  public String minWindow(String s, String t) {
    MinWindow minWindow = new MinWindow(s, t);
    return minWindow.get();
  }

  /*
     Window
          s1: ADOBEACAODEBANC
          s2: AAABC

          pointer 3

          chars:
          minWindowPosition 0 6
          charToPositionsMap:
          - A
          count 3
          positions [5 7 12]
          - B
          count 1
          positions [11]
          - C
          count 1
          positions [14]
          positionToChar:
          - 5: A
          - 7: A
          - 11: B
          - 12: A
          - 14: C

          startEndPosition 0 null
  */

  private static class MinWindow {

    private String s1;
    private String s2;

    private Chars chars;

    private String minWindow;

    public MinWindow(String s1, String s2) {
      if (s1 == null || s1.length() == 0) {
        throw new IllegalArgumentException("s1 should not be empty");
      }
      if (s2 == null || s2.length() == 0) {
        throw new IllegalArgumentException("s2 should not be empty");
      }

      this.s1 = s1;
      this.s2 = s2;

      chars = new Chars(s2);
      minWindow = calculateMinWindow();
    }

    private String calculateMinWindow() {
      char[] inputChars = s1.toCharArray();
      for (int i = 0; i < inputChars.length; i++) {
        char inputChar = inputChars[i];
        chars.evalChar(inputChar, i);
      }

      MinWindowPosition minWindowPosition = chars.getMinWindowPosition();
      return minWindowPosition == null
          ? ""
          : s1.substring(minWindowPosition.getStart(), minWindowPosition.getEnd() + 1);
    }

    public String get() {
      return minWindow;
    }

    private class Chars {

      Map<Character, CharPositions> charToPositionsMap = new HashMap<>();
      TreeMap<Integer, Character> positionToChar = new TreeMap<>();
      private MinWindowPosition minWindowPosition;

      public Chars(String s) {
        if (s == null || s.length() == 0) {
          throw new IllegalArgumentException("s should not be empty");
        }

        // fill counters
        char[] charArray = s.toCharArray();
        for (char c : charArray) {

          charToPositionsMap.merge(
              c, new CharPositions(), (oldValue, newValue) -> oldValue.incrementCount());
        }
      }

      public MinWindowPosition getMinWindowPosition() {
        return minWindowPosition;
      }

      public void evalChar(char c, int index) {
        CharPositions charPositions = charToPositionsMap.get(c);
        if (charPositions == null) {
          return;
        }

        Integer removedPosition = charPositions.addPosition(index);
        positionToChar.put(index, c);
        if (removedPosition != null) {
          positionToChar.remove(removedPosition);
        }

        if (full()) {
          if (minWindowPosition == null || minWindowPosition.size() > getCurrentWindowSize()) {
            minWindowPosition =
                new MinWindowPosition(positionToChar.firstKey(), positionToChar.lastKey());
          }
        }
      }

      public boolean full() {
        for (CharPositions charPositions : charToPositionsMap.values()) {
          if (!charPositions.full()) {
            return false;
          }
        }

        return true;
      }

      int getCurrentWindowSize() {
        return positionToChar.lastKey() - positionToChar.firstKey() + 1;
      }

      private class CharPositions {
        private int count = 1;
        private LinkedList<Integer> positions = new LinkedList<>();

        public CharPositions incrementCount() {
          count++;
          return this;
        }

        /** Returns removed position or null. */
        public Integer addPosition(int index) {
          Integer removedPosition = null;
          if (full()) {
            removedPosition = positions.removeFirst();
          }

          positions.add(index);

          return removedPosition;
        }

        public boolean full() {
          return count == positions.size();
        }
      }
    }

    private static class MinWindowPosition {

      int start;
      int end;

      public MinWindowPosition(int start, int end) {
        this.start = start;
        this.end = end;
      }

      public int getStart() {
        return start;
      }

      public int getEnd() {
        return end;
      }

      public int size() {
        return end - start + 1;
      }
    }
  }
}
