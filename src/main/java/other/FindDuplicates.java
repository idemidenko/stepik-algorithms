package other;

import java.util.HashMap;
import java.util.Map;


public class FindDuplicates {

	// input - array of integers, find duplicates, output - {integer} - {number of duplicates}
	public static void main(String[] args){
		int[] input = new int[]{1, 2, 1, 4, 5, 1, 7, 8, 9, -10, 0, 0};

		Map<Integer, Integer> duplicatesMap = countDuplicates(input);
		for (Map.Entry<Integer, Integer> entry : duplicatesMap.entrySet()) {
			if (entry.getValue() > 1) {
				System.out.println(entry.getKey() + " - " + entry.getValue());
			}
		}
	}

	private static Map<Integer, Integer> countDuplicates(int[] input) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i : input) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		return map;
	}
}
