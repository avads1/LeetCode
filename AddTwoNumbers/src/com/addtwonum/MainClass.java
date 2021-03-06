package com.addtwonum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}

/*
 * [2,4,3]
  [5,6,4]
 * 
 * [1,8]
  [0]
 * 
 * [5] 
 [5]
 */
class Solution {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode tempList = new ListNode(0);
		ListNode current = tempList;
		while (l1 != null || l2 != null) {
			int x = 0, y = 0;
			if (l1 == null) {
				x = 0;
			} else {
				x = l1.val;
			}
			if (l2 == null) {
				y = 0;
			} else {
				y = l2.val;
			}
			int sum = x + y + carry;
			carry=0;
			if (sum >= 10) {
				carry = sum / 10;
				sum = sum % 10;
			}
			current.next = new ListNode(sum);
			current = current.next;
			System.out.println("tempList : " + current.val);
			if (l1 != null)
				l1 = l1.next;
			if (l2 != null)
				l2 = l2.next;
		}
		if (carry > 0) {
			current.next = new ListNode(carry);
		}
		return tempList.next;

	}
};

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

	public static ListNode stringToListNode(String input) {
		// Generate array from the input
		int[] nodeValues = stringToIntegerArray(input);

		// Now convert that list into linked list
		ListNode dummyRoot = new ListNode(0);
		ListNode ptr = dummyRoot;
		for (int item : nodeValues) {
			ptr.next = new ListNode(item);
			ptr = ptr.next;
		}
		return dummyRoot.next;
	}

	public static String listNodeToString(ListNode node) {
		if (node == null) {
			return "[]";
		}

		String result = "";
		while (node != null) {
			result += Integer.toString(node.val) + ", ";
			node = node.next;
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			ListNode l1 = stringToListNode(line);
			line = in.readLine();
			ListNode l2 = stringToListNode(line);

			ListNode ret = new Solution().addTwoNumbers(l1, l2);

			String out = listNodeToString(ret);

			System.out.print(out);
		}
	}
}
