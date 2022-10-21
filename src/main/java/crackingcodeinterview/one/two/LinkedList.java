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
  }
}
