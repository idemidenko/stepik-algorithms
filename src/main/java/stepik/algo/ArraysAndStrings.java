package stepik.algo;

public class ArraysAndStrings {

  public static void main(String[] args) {
    String str = "string11";
    System.out.println(str + " is unique: " + isUniqueChars(str));
  }

  // nlogn
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
}
