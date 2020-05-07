package com.deleteDup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
T1 :
[1,2,3,0,0,0]
3
[2,5,6]
3

T2: 
[1]
1
[]
0

T3: 
[]
0
[1]
1

T4:
[2,0]
1
[1]
1

*/

class Solution {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		int size = m + n;
		int[] result = new int[size];
		int i = 0, j = 0, k = 0;
		while (size > 0 && m > 0 && n > 0) {
			if (nums1[i] >= nums2[j] || (nums1[i] == 0 && i != 0) || (nums1[j] == 0 && j != 0)) {
				result[k] = nums2[j];
				j++;
				k++;
			} else {
				result[k] = nums1[i];
				i++;
				k++;
			}
			size--;
		}
		if (m > 0 && n > 0) {
			size = m + n;
			for (int p = 0; p < size; p++) {
				nums1[p] = result[p];
			}
		} else if (m == 0 && n > 0) {
			nums1 = nums2;
//			nums1[]=new int[n];
//			for (int p = 0; p < n; p++) {
//				nums1[p] = nums2[p];
//			}

		}
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

	public static String integerArrayToString(int[] nums, int length) {
		if (length == 0) {
			return "[]";
		}

		String result = "";
		for (int index = 0; index < length; index++) {
			int number = nums[index];
			result += Integer.toString(number) + ", ";
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static String integerArrayToString(int[] nums) {
		return integerArrayToString(nums, nums.length);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int[] nums1 = stringToIntegerArray(line);
			line = in.readLine();
			int m = Integer.parseInt(line);
			line = in.readLine();
			int[] nums2 = stringToIntegerArray(line);
			line = in.readLine();
			int n = Integer.parseInt(line);

			new Solution().merge(nums1, m, nums2, n);
			String out = integerArrayToString(nums1);

			System.out.print(out);
		}
	}
}