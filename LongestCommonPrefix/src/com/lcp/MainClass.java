package com.lcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.eclipsesource.json.JsonArray;
// ["flower","flow","flight"]

// ["abab","aba",""]
class Solution {
	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0)
			return "";
		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			int len = prefix.length() > strs[i].length() ? strs[i].length() : prefix.length();
			if (len == 0) {
				return "";
			}
			for (int j = 0; j < len; j++) {
				if (prefix.charAt(j) != strs[i].charAt(j)) {
					prefix = prefix.substring(0, j);
					break;
				} else if (j == len - 1) {
					prefix = prefix.substring(0, j + 1);
					break;
				}
			}
		}
		return prefix;
	}
}

public class MainClass {
	public static String[] stringToStringArray(String line) {
		JsonArray jsonArray = JsonArray.readFrom(line);
		String[] arr = new String[jsonArray.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = jsonArray.get(i).asString();
		}
		return arr;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			String[] strs = stringToStringArray(line);

			String ret = new Solution().longestCommonPrefix(strs);

			String out = (ret);

			System.out.print(out);
		}
	}
}


