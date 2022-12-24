package crackingcodeinterview.one.three;

import crackingcodeinterview.one.three.Graph.Node.Status;
import java.util.LinkedList;
import java.util.List;

public class Graph<T> {

  private List<Node<T>> nodes;

  public List<Node<T>> getNodes() {
    return nodes;
  }
  public void setNodes(List<Node<T>> nodes) {
    this.nodes = nodes;
  }

  public static class Node<T> {
    private T value;
    private List<Node<T>> adjacentNodes;
    private Status status;

   public Node(T value, List<Node<T>> adjacentNodes) {
      this.value = value;
      this.adjacentNodes = adjacentNodes;
    }

    public T getValue() {
      return value;
    }

    public void setValue(T v) {
      value = v;
    }
    public List<Node<T>> getAdjacentNodes() {
      return adjacentNodes;
    }

    public void setAdjacentNodes(List<Node<T>> a) {
      adjacentNodes = a;
    }
    public Status getStatus() {
      return status;
    }

    public void setStatus(Status v) {
      status = v;
    }

    public enum Status { UNVISITED, VISITING, VISITED }
  }

  public static void main(String[] args) {
    Graph<String> g = new Graph<>();
    Node<String> n1 = new Node<>("z", null);
    Node<String> n2 = new Node<>("c", null);
    Node<String> n3 = new Node<>("s", List.of(n1, n2));
    Node<String> n4 = new Node<>("f", null);
    Node<String> n5 = new Node<>("e", List.of(n3, n4));
    g.setNodes(List.of(n1, n2, n3, n4, n5));
    System.out.println("routeExists [false]: " + routeExists(g, n1, n4));
    System.out.println("routeExists [true]: " + routeExists(g, n5, n1));
  }



/** Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes. */
  public static <T> boolean routeExists(Graph<T> g, Node<T> a, Node<T> b) {
    if (g == null || g.getNodes() == null || g.getNodes().isEmpty()
        || a == null || b == null) {
      return false;
    }

    g.getNodes().forEach(n -> n.setStatus(Status.UNVISITED));

    LinkedList<Node<T>> queue = new LinkedList<>();
    queue.add(a);

    while(!queue.isEmpty()) {
      Node<T> n = queue.remove();
      if (n == b) {
        return true;
      }

      if (n.getAdjacentNodes() != null && !n.getAdjacentNodes().isEmpty() ) {
        n.getAdjacentNodes().forEach(adjNode -> {
          if(Status.UNVISITED.equals(adjNode.getStatus())) {
            adjNode.setStatus(Status.VISITING);
            queue.add(adjNode);
          }
        });
        n.setStatus(Status.VISITED);
      }
    }

     return false;
  }


}

