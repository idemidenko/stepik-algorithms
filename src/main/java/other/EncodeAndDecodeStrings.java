package other;

import java.util.ArrayList;
import java.util.List;

/*
 	Design an algorithm to encode a list of strings to a single string. The encoded string is then decoded back to the original list of strings.
	Please implement encode and decode
	Example 1:
	Input: ["neet","code","love","you"]
	Output:["neet","code","love","you"]
*/
public class EncodeAndDecodeStrings {

  public static void main(String[] args) {
    print(List.of("neet", "code", "love", "you"));
    print(List.of("we", "say", ":", "yes"));
  }

  static void print(List<String> strs) {
    String encoded = encode(strs);
    System.out.println("encoded: " + encoded);
    List<String> decoded = decode(encoded);
    System.out.println("decoded: " + decoded);
  }

  // 4#neet4#code4#love3#you
  public static String encode(List<String> strs) {
    StringBuilder res = new StringBuilder();
    for (String s : strs) {
      res.append(s.length()).append('#').append(s);
    }
    return res.toString();
  }

  public static List<String> decode(String str) {
    List<String> res = new ArrayList<>();
    int i = 0;
    while (i < str.length()) {
      int j = i;
      while (str.charAt(j) != '#') {
        j++;
      }
      int length = Integer.parseInt(str.substring(i, j));
      i = j + 1;
      j = i + length;
      res.add(str.substring(i, j));
      i = j;
    }
    return res;
  }
}
