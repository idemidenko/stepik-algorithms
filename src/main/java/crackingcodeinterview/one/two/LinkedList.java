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
  }
}
