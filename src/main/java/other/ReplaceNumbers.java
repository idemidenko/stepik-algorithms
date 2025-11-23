package other;

public class ReplaceNumbers {

  	// hello 123 --> hello _
  	// hello 123 and 456 --> hello _ and _
	// hello 123 456 --> hello _ _
	// do not use regexp
	public static void main(String[] args){

    	System.out.println(replaceNumbers("hello 123"));
    	System.out.println(replaceNumbers("hello 123 and 456"));
    	System.out.println(replaceNumbers("hello 123 456"));
	}

	private static String replaceNumbers(String input) {
		StringBuilder sb = new StringBuilder();
		boolean numberFlag = false;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isDigit(c)) {
				if (!numberFlag) {
					sb.append("_");
					numberFlag = true;
				}
			} else {
				sb.append(c);
				numberFlag = false;
			}
		}

		return sb.toString();
	}
}
