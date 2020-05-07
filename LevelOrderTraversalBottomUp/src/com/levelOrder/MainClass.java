package com.levelOrder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode
 * left; TreeNode right; TreeNode(int x) { val = x; } }
 */

//  [3,9,20,null,null,15,7]

//  []

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

class Solution {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> currentRow = new ArrayList<List<Integer>>();
		if (root == null) {
			List<Integer> currentLevel = new ArrayList<Integer>();
			currentRow.add(0, currentLevel);
			return currentRow;
		}
		queue.add(root);
		while (!queue.isEmpty()) {
			List<Integer> currentLevel = new ArrayList<Integer>();

			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode temp = queue.poll();
				if (temp.left != null)
					queue.add(temp.left);
				if (temp.right != null)
					queue.add(temp.right);
				currentLevel.add(temp.val);
			}
			currentRow.add(0, currentLevel);
		}
		return currentRow;
	}
}

public class MainClass {
	public static TreeNode stringToTreeNode(String input) {
		input = input.trim();
		input = input.substring(1, input.length() - 1);
		if (input.length() == 0) {
			return null;
		}

		String[] parts = input.split(",");
		String item = parts[0];
		TreeNode root = new TreeNode(Integer.parseInt(item));
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);

		int index = 1;
		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int leftNumber = Integer.parseInt(item);
				node.left = new TreeNode(leftNumber);
				nodeQueue.add(node.left);
			}

			if (index == parts.length) {
				break;
			}

			item = parts[index++];
			item = item.trim();
			if (!item.equals("null")) {
				int rightNumber = Integer.parseInt(item);
				node.right = new TreeNode(rightNumber);
				nodeQueue.add(node.right);
			}
		}
		return root;
	}

	public static String integerArrayListToString(List<Integer> nums, int length) {
		if (length == 0) {
			return "[]";
		}

		String result = "";
		for (int index = 0; index < length; index++) {
			Integer number = nums.get(index);
			result += Integer.toString(number) + ", ";
		}
		return "[" + result.substring(0, result.length() - 2) + "]";
	}

	public static String integerArrayListToString(List<Integer> nums) {
		return integerArrayListToString(nums, nums.size());
	}

	public static String int2dListToString(List<List<Integer>> nums) {
		StringBuilder sb = new StringBuilder("[");
		for (List<Integer> list : nums) {
			sb.append(integerArrayListToString(list));
			sb.append(",");
		}

		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			TreeNode root = stringToTreeNode(line);

			List<List<Integer>> ret = new Solution().levelOrderBottom(root);

			String out = int2dListToString(ret);

			System.out.print(out);
		}
	}
}