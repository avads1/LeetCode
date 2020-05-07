package com.longestSubstr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import com.eclipsesource.json.JsonArray;

/*
 * Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 

Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
  
             
 https://www.youtube.com/watch?v=mtHelVTLKRQ
 ==========================================================
 2 pointers
 1) i increments
 2) if char not in the map, put in the map
 3) if char is in the map, then check i-j is less than window or not
 4) 
 
 
 * */

class Solution {
	public int lengthOfLongestSubstring(String s) {
		LinkedHashMap<Character, Integer> strMap = new LinkedHashMap<Character, Integer>();
		int window = 0, i = 0, j = 0;
		while (i < s.length() && j < s.length()) {
			if (!strMap.containsKey(s.charAt(i))) {
				strMap.put(s.charAt(i), 1);
				i++;
				window = Math.max(window, i - j);
			} else {
				strMap.remove(s.charAt(j));
				j++;
			}
		}
		return window;
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

			int ret = new Solution().lengthOfLongestSubstring(s);

			String out = String.valueOf(ret);

			System.out.print(out);
		}
	}
}