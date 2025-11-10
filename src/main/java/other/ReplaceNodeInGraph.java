package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReplaceNodeInGraph {

	public static void main(String[] args) {

		// Assume adjacencyList maps node IDs to lists of neighbor IDs
		Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

		// Build initial adjacency list
		adjacencyList.put(1, new ArrayList<>(List.of(2, 3)));
		adjacencyList.put(2, new ArrayList<>(List.of(1, 3)));
		adjacencyList.put(3, new ArrayList<>(List.of(1, 2)));

		Graph graph = new Graph(adjacencyList);

		System.out.println("Before replacement: \n" + adjacencyList);

		// Replace node 2 with node 4
		graph.replaceNode(2, 4);

    System.out.println("After replacement: \n" + adjacencyList);
	}

	static class Graph {

		Map<Integer, List<Integer>> adjacencyList;

		Graph(Map<Integer, List<Integer>> adjacencyList) {
			this.adjacencyList = adjacencyList;
		}

		void replaceNode(int oldId, int newId) {
			// Step 1: Get neighbors of old node
			List<Integer> neighbors = adjacencyList.remove(oldId);

			// Step 2: Add new node with same neighbors
			adjacencyList.put(newId, neighbors);

			// Step 3: Update all lists to replace oldId with newId
			for (List<Integer> adj : adjacencyList.values()) {
				for (int i = 0; i < adj.size(); i++) {
					if (adj.get(i) == oldId) {
						adj.set(i, newId);
					}
				}
			}
		}
	}
}
