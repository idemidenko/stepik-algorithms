package other;

public class ReplaceInString {

  public static void main(String[] args) {
    String str = "Hello World";
    String sub = "World";
    String replace = "Java";

    System.out.println(str.replace(sub, replace));
    System.out.println(replace(str, sub, replace));
  }

  private static String replace(String str, String sub, String replace) {
    StringBuilder sb = new StringBuilder(str);
    int indexOfSub = str.indexOf(sub);
    sb.replace(indexOfSub, indexOfSub + sub.length(), replace);
    return sb.toString();
  }
}
