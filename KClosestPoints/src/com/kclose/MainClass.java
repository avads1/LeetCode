package com.kclose;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

import com.eclipsesource.json.JsonArray;
/* 
[[1,3],[-2,2]]
1

[[3,3],[5,-1],[-2,4]]
2
*/
class Solution {
	public int[][] kClosest(int[][] points, int K) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return (b[0] * b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1]);
			}
		});

		for (int[] point : points) {
			pq.offer(point);
			if (pq.size() > K) {
				pq.poll();
			}
		}

		int[][] res = new int[K][2];
		int index = 0;
		while (pq.size() != 0) {
			res[index++] = pq.poll();
		}

		return res;
	}
}

public class MainClass {
	public static int[] stringToIntegerArray(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return new int[0];
		}

		String[] parts = input.split(",");
		int[] output = new int[parts.length];
		for (int index = 0; index < parts.length; index++) {
			String part = parts[index].trim();
			output[index] = Integer.parseInt(part);
		}
		return output;
	}

	public static int[][] stringToInt2dArray(String input) {
		JsonArray jsonArray = JsonArray.readFrom(input);
		if (jsonArray.size() == 0) {
			return new int[0][0];
		}

		int[][] arr = new int[jsonArray.size()][];
		for (int i = 0; i < arr.length; i++) {
			JsonArray cols = jsonArray.get(i).asArray();
			arr[i] = stringToIntegerArray(cols.toString());
		}
		return arr;
	}

	public static String int2dArrayToString(int[][] array) {
		if (array == null) {
			return "null";
		}
		if (array.length == 0) {
			return "[]";
		}

		StringBuilder sb = new StringBuilder("[");
		for (int[] item : array) {
			sb.append("["+item[0]+","+item[1]+"]");
			sb.append(",");
		}

		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int[][] points = stringToInt2dArray(line);
			line = in.readLine();
			int K = Integer.parseInt(line);

			int[][] ret = new Solution().kClosest(points, K);

			String out = int2dArrayToString(ret);

			System.out.print(out);
		}
	}
}