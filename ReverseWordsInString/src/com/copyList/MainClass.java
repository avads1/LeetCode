package com.copyList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.eclipsesource.json.JsonArray;

/*
"the sky is blue"

"a good   example"
 * */
class Solution {
	public String reverseWords(String s) {
		List<String> actualList = new ArrayList<String>();
		s = s.trim();
		s = s + " ";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != ' ') {
				sb.append(s.charAt(i));
			} else {
				actualList.add(sb.toString().trim());
				sb.delete(0, i);
			}
		}
		for (int i = actualList.size() - 1; i >= 0; i--) {
			if (!actualList.get(i).isEmpty()) {
				sb.append(actualList.get(i));
				sb.append(" ");
			}
		}
		return sb.toString().trim();
	}
}

public class MainClass {
	public static String stringToString(String input) {
		return JsonArray.readFrom("[" + input + "]").get(0).asString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			String s = stringToString(line);

			String ret = new Solution().reverseWords(s);

			String out = (ret);

			System.out.print(out);
		}
	}
}