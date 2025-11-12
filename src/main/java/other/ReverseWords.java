package other;

import java.util.Arrays;
import java.util.regex.Pattern;


public class ReverseWords {

  private static final Pattern PATTERN = Pattern.compile("\\s+");

	public static void main(String[] args){
		String string = "  the sky  is blue ";
		String reversedWords = reverseWords(string);
		System.out.println(reversedWords);
	}

	private static String reverseWords(String string) {
		if (string == null || string.isEmpty()) {
			return string;
		}

		String[] words = PATTERN.split(string.trim());
		StringBuilder sb = new StringBuilder();
		for (int i = words.length - 1; i >=0; i--) {
			sb.append(words[i]);
			if (i > 0) {
				sb.append(" ");
			}
		}

		return sb.toString();
	}
}
