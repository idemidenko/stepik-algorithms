package crackingcodeinterview.one.two;

public class LinkedList {

  Node head;

  private static class Node {
    Node next;
    int data;

    Node(int data) {
      this.data = data;
    }
  }

  void appendToTail(int data) {
    Node end = new Node(data);
    if (head == null) {
      head = end;
      return;
    }

    Node n = head;
    while (n.next != null) {
      n = n.next;
    }
    n.next = end;
  }

  void removeDups() {
    Node pointer1 = head;
    while (pointer1 != null) {
      Node pointer2 = pointer1;
      while (pointer2.next != null) {
        if (pointer1.data == pointer2.next.data) {
          pointer2.next = pointer2.next.next;
        } else {
          pointer2 = pointer2.next;
        }
      }
      pointer1 = pointer1.next;
    }
  }

  public Node kThToLast(int k) {
    Node pt1 = head;
    Node pt2 = head;

    for (int i = 0; i < k; i++) {
      if (pt1 == null) {
        return null;
      }
      pt1 = pt1.next;
    }

    while (pt1.next != null) {
      pt1 = pt1.next;
      pt2 = pt2.next;
    }

    return pt2;
  }

  static void printKThToLast() {
    LinkedList list = new LinkedList();
    list.appendToTail(1);
    list.appendToTail(2);
    list.appendToTail(1);
    list.appendToTail(3);
    list.appendToTail(2);
    list.appendToTail(4);

    int k = 3;
    System.out.printf("kThToLast: input = [%s], k = [%d]%n", list, k);
    System.out.printf("kThToLast: result = [%d]%n", list.kThToLast(k).data);
  }

  public void deleteMiddleNode(Node n) {
    if (n == null || n.next == null) {
      return;
    }

    Node next = n.next;
    n.data = next.data;
    n.next = next.next;
  }

  static void printDeleteMiddleNode() {
    LinkedList list = new LinkedList();
    list.appendToTail(1);
    list.appendToTail(2);
    list.appendToTail(1);
    list.appendToTail(3);
    list.appendToTail(2);
    list.appendToTail(4);

    Node node = list.head.next.next.next;
    System.out.printf("deleteMiddleNode: input = [%s], node=[%d]%n", list, node.data);
    list.deleteMiddleNode(node);
    System.out.printf("deleteMiddleNode: result = [%s]%n", list);
  }

  /*
  Partition: Write code to partition a linked list around a value x, such that all nodes less than x come before all nodes greater than or equal to x. If x is contained within the list, the values of x only need to be after the elements less than x (see below). The partition element x can appear anywhere in the "right partition"; it does not need to appear between the left and right partitions.
  EXAMPLE
  Input: 3 -> 5 -> 8 -> 5 -> 10 -> 2 -> 1[partition=5]
  Output: 3 -> 1 -> 2 -> 10 -> 5 -> 5 -> 8
  	*/
  public void partition(int value) {
    if (head == null || head.next == null) {
      return;
    }

    Node previous = head;
    Node current = head.next;
    while (current != null) {
      Node next = current.next;
      if (current.data < value) {
        current.next = head;
        head = current;
        previous.next = next;
        current = next;
      } else {
        previous = current;
        current = next;
      }
    }
  }

  static void printPartition() {
    LinkedList list = new LinkedList();
    list.appendToTail(1);
    list.appendToTail(2);
    list.appendToTail(7);
    list.appendToTail(3);
    list.appendToTail(2);
    list.appendToTail(4);

    int value = 3;
    System.out.printf("partition: input = [%s], value=[%d]%n", list, value);
    list.partition(value);
    System.out.printf("partition: result = [%s]%n", list);
  }

  /*
  Sum Lists: You have two numbers represented by a linked list, where each node contains a single digit.The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a function that adds the two numbers and returns the sum as a linked list.
  EXAMPLE
  Input:(7-> 1 -> 6) + (5 -> 9 -> 2). That is,617 + 295.
  Output:2 -> 1 -> 9. That is,912.
  */
  public LinkedList sumReverse(LinkedList anotherList) {
    if (anotherList == null || anotherList.head == null) {
      return null;
    }

    Node pt1 = head;
    Node pt2 = anotherList.head;

    int transfer = 0;
    LinkedList sum = new LinkedList();
    Node sumTail = null;
    while ((pt1 != null && pt2 != null) || transfer == 1) {
      int data1, data2;

      data1 = pt1 != null ? pt1.data : 0;
      data2 = pt2 != null ? pt2.data : 0;
      int sumNumber = data1 + data2 + transfer;

      if (sumNumber > 9) {
        sumNumber = sumNumber % 10;
        transfer = 1;
      } else {
        transfer = 0;
      }

      Node sumNode = new Node(sumNumber);
      if (sumTail == null) {
        sumTail = sumNode;
        sum.head = sumNode;
      } else {
        sumTail.next = sumNode;
        sumTail = sumNode;
      }

      if (pt1 != null) {
        pt1 = pt1.next;
      }
      if (pt2 != null) {
        pt2 = pt2.next;
      }
    }

    return sum;
  }

  public static void printSumReverse() {
    LinkedList list1 = new LinkedList();
    list1.appendToTail(7);
    list1.appendToTail(1);
    list1.appendToTail(6);

    LinkedList list2 = new LinkedList();
    list2.appendToTail(9);
    list2.appendToTail(8);
    list2.appendToTail(7);
    list2.appendToTail(3);

    System.out.printf("sumReverse: list1 = [%s], list2=[%s]%n", list1, list2);
    System.out.printf("sumReverse: result = [%s]%n", list1.sumReverse(list2));
  }

  public static LinkedList sumReverseRecursion(LinkedList l1, LinkedList l2) {
    if (l1 == null || l1.head == null || l2 == null || l2.head == null) {
      return null;
    }

    Node n1 = l1.head;
    Node n2 = l2.head;
    int transfer = 0;

    Node n = sumReverseRecursion(n1, n2, transfer);
    LinkedList sum = new LinkedList();
    sum.head = n;

    return sum;
  }

  private static Node sumReverseRecursion(Node n1, Node n2, int transfer) {
    if (n1 == null && n2 == null && transfer == 0) {
      return null;
    }

    int v1 = n1 == null ? 0 : n1.data;
    int v2 = n2 == null ? 0 : n2.data;
    int sumValue = v1 + v2 + transfer;

    if (sumValue > 9) {
      sumValue = sumValue % 10;
      transfer = 1;
    } else {
      transfer = 0;
    }

    Node sum = new Node(sumValue);
    if (n1 != null) {
      n1 = n1.next;
    }
    if (n2 != null) {
      n2 = n2.next;
    }
    sum.next = sumReverseRecursion(n1, n2, transfer);

    return sum;
  }

  public static void printSumReverseRecursion() {
    LinkedList list1 = new LinkedList();
    list1.appendToTail(7);
    list1.appendToTail(1);
    list1.appendToTail(6);

    LinkedList list2 = new LinkedList();
    list2.appendToTail(9);
    list2.appendToTail(8);
    list2.appendToTail(7);
    list2.appendToTail(3);

    System.out.printf("sumReverseRecursion: list1 = [%s], list2=[%s]%n", list1, list2);
    System.out.printf("sumReverseRecursion: result = [%s]%n", sumReverseRecursion(list1, list2));
  }

  /*
  Palindrome: Implement a function to check if a linked list is a palindrome.
   */
  public boolean isPalindrome() {
    Node n = head;
    if (n == null) {
      return false;
    }

    return isPalindrome(n).equals;
  }

  private PalindromeComparisonResult isPalindrome(Node n) {
    if (n.next == null) {
      PalindromeComparisonResult comparisonResult = new PalindromeComparisonResult();
      comparisonResult.left = head;
      comparisonResult.right = n;
      comparisonResult.equals = comparisonResult.left.data == comparisonResult.right.data;
      return comparisonResult;
    }

    PalindromeComparisonResult comparisonResult = isPalindrome(n.next);
    comparisonResult.left = comparisonResult.left.next;
    comparisonResult.right = n;
    if (comparisonResult.equals) {
      comparisonResult.equals = comparisonResult.left.data == comparisonResult.right.data;
    }

    return comparisonResult;
  }

  private static class PalindromeComparisonResult {
    Node left;
    Node right;
    boolean equals;
  }

  public static void printIsPalindrome() {
    LinkedList list1 = new LinkedList();
    list1.appendToTail(1);
    list1.appendToTail(2);
    list1.appendToTail(1);
    list1.appendToTail(3);
    list1.appendToTail(2);
    list1.appendToTail(1);

    System.out.printf("isPalindrome: input = [%s], result=[%s]%n", list1, list1.isPalindrome());

    LinkedList list2 = new LinkedList();
    list2.appendToTail(1);
    list2.appendToTail(2);
    list2.appendToTail(3);
    list2.appendToTail(3);
    list2.appendToTail(2);
    list2.appendToTail(1);

    System.out.printf("isPalindrome: input = [%s], result=[%s]%n", list2, list2.isPalindrome());
  }

  /**
   * Intersection: Given two (singly) linked lists, determine if the two lists intersect. Return the
   * intersecting node. Note that the intersection is defined based on reference, not value.That is,
   * if the kth node of the first linked list is the exact same node (by reference) as the jth node
   * of the second linked list, then they are intersecting.
   *
   * <p>Example:
   *
   * <p>S:1 => A:1 => B:9 => C:3 => D:4
   *
   * <p>F:2 => C:3 => D:4
   *
   * <p>return: C:3 node
   */
  private static Node findIntersection(LinkedList l1, LinkedList l2) {
    if (l1 == null || l2 == null || l1.head == null || l2.head == null) {
      return null;
    }

    SizeAndTail sizeAndTail1 = findSizeAndTail(l1);
    SizeAndTail sizeAndTail2 = findSizeAndTail(l2);

    if (sizeAndTail1.tail != sizeAndTail2.tail) {
      return null;
    }

    int size1 = sizeAndTail1.size;
    int size2 = sizeAndTail2.size;

    int diff = Math.abs(size1 - size2);
    Node longer = size1 > size2 ? l1.head : l2.head;
    Node shorter = size1 > size2 ? l2.head : l1.head;

    longer = getKthNode(longer, diff);
    if (longer == null) {
      return null;
    }

    while (longer != shorter) {
      longer = longer.next;
      shorter = shorter.next;
    }

    return longer;
  }

  private static Node getKthNode(Node n, int k) {
    for (int i = 0; i < k; i++) {
      if (n == null) {
        return null;
      }

      n = n.next;
    }

    return n;
  }

  private static SizeAndTail findSizeAndTail(LinkedList l) {
    if (l == null) {
      return null;
    }
    if (l.head == null) {
      return new SizeAndTail(null, 0);
    }

    Node node = l.head;
    int size = 1;
    while (node.next != null) {
      node = node.next;
      size++;
    }

    return new SizeAndTail(node, size);
  }

  private static class SizeAndTail {
    final Node tail;
    final int size;

    private SizeAndTail(Node tail, int size) {
      this.tail = tail;
      this.size = size;
    }
  }

  static void printFindIntersection() {
    LinkedList l1 = new LinkedList();
    l1.appendToTail(1);
    l1.appendToTail(2);
    l1.appendToTail(3);
    l1.appendToTail(4);
    l1.appendToTail(5);
    l1.appendToTail(6);
    System.out.printf("l1 = [%s] ", l1);

    LinkedList l2 = new LinkedList();
    l2.appendToTail(11);
    l2.appendToTail(12);
    l2.head.next.next = l1.head.next.next.next;
    System.out.printf("l2 = [%s] ", l2);

    Node intersection = findIntersection(l1, l2);
    System.out.printf("intersection = [%s] ", intersection.data);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("[");
    if (head != null) {
      stringBuilder.append(head.data);

      Node n = head.next;
      while (n != null) {
        stringBuilder.append(", ").append(n.data);
        n = n.next;
      }
    }
    stringBuilder.append("]");
    return stringBuilder.toString();
  }

  public static void main(String[] args) {
    LinkedList removeDupsList = new LinkedList();
    removeDupsList.appendToTail(1);
    removeDupsList.appendToTail(2);
    removeDupsList.appendToTail(1);
    removeDupsList.appendToTail(3);
    removeDupsList.appendToTail(2);
    removeDupsList.appendToTail(4);

    System.out.printf("removeDups: input = [%s]%n", removeDupsList);
    removeDupsList.removeDups();
    System.out.printf("removeDups: result = [%s]%n", removeDupsList);

    printKThToLast();
    printDeleteMiddleNode();
    printPartition();
    printSumReverse();
    printSumReverseRecursion();
    printIsPalindrome();
    printFindIntersection();
  }
}
