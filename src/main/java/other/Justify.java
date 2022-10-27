package other;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Justify {

  /**
   * Problem: We need you to build a function to justify text, this will be your signature:
   *
   * <p>public static String justify(final int len, final String text)
   *
   * <p>- Words must be aligned into lines of size len There can be no split words, if a word
   * doesn't fit in the line move it to the next - Fill the remaining space with blanks for padding
   * Padding must be distributed in decreasing order from left to right No word will be longer than
   * len
   *
   * <p>Example let's say you are given the following call: justify(10, "this is a text with more
   * than 10 characters")
   *
   * <p>your result must look like: _____________________ this is a text with more than 10
   * characters _____________________
   *
   * <p>As you can see for the first line "this is a", we needed 3 spaces so we distributed 2 for
   * the first position and 1 for the last
   */
  public static void main(String[] args) {
    String text = "this is a text with more than 10 characters";
    System.out.println(justify(10, text));
  }

  private static String justify(int expectedLineLength, String text) {
    JustifiedText justifiedText = new JustifiedText(expectedLineLength, text);
    return justifiedText.toString();
  }

  static class JustifiedText {
    private final int expectedLineLength;
    private final StringBuilder result = new StringBuilder();
    private final String text;

    private Line line = new Line();

    JustifiedText(int expectedLineLength, String text) {
      this.expectedLineLength = expectedLineLength;
      this.text = text;
    }

    private class Line {
      List<String> words = new ArrayList<>();
      int noneSpaceCharLength = 0;

      Line() {}

      Line(String word) {
        words.clear();
        noneSpaceCharLength = 0;
        addWord(word);
      }

      int getLength() {
        int spaces = words.size() - 1;
        return noneSpaceCharLength + spaces;
      }

      void addWord(String word) {
        words.add(word);
        noneSpaceCharLength = noneSpaceCharLength + word.length();
      }

      private String justifyLine() {
        int spaceLength = expectedLineLength - noneSpaceCharLength;
        int remainingSpacePlaces = words.size() - 1;

        Iterator<String> iterator = words.iterator();
        StringBuilder line = new StringBuilder();
        line.append(iterator.next());
        while (iterator.hasNext()) {
          int spacesAfterWord =
              (spaceLength / remainingSpacePlaces)
                  + (spaceLength % remainingSpacePlaces > 0 ? 1 : 0);
          for (int i = 0; i < spacesAfterWord; i++) {
            line.append(' ');
          }
          line.append(iterator.next());
          spaceLength = spaceLength - spacesAfterWord;
          remainingSpacePlaces--;
        }

        return line.toString();
      }
    }

    private void addToResult(String line) {
      if (!result.isEmpty()) {
        result.append("\n");
      }
      result.append(line);
    }

    private void addWord(String word) {
      if (word == null || word.isBlank()) {
        return;
      }

      if (line.words.isEmpty() || line.getLength() + word.length() < expectedLineLength) {
        line.addWord(word);
      } else {
        String lineString = line.justifyLine();
        addToResult(lineString);
        line = new Line(word);
      }
    }

    public String toString() {
      if (expectedLineLength < 1 || text == null || text.isBlank()) {
        return "";
      }
      StringBuilder word = new StringBuilder();
      char[] chars = text.toCharArray();
      for (int i = 0; i < chars.length; i++) {
        char c = chars[i];
        if (' ' != c) {
          word.append(c);
        }

        boolean lastChar = (i + 1) == chars.length;
        if (' ' == c || lastChar) {
          addWord(word.toString());
          word = new StringBuilder();
        }
      }

      String lineString = line.justifyLine();
      addToResult(lineString);

      return result.toString();
    }
  }
}
