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
  }
}
