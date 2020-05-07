package com.bsquare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.eclipsesource.json.JsonArray;
//"cbbd"
class Solution {
	public String getPaliStr(String s, int i) {
		int j = i;
		if (s.length() % 2 == 0) {
			System.out.println(s);
			StringBuilder sb = new StringBuilder();
			String s1 = s.substring(0, s.length() / 2);
			String s2 = s.substring(s.length() / 2, s.length());
			sb.append(s1).append("$").append(s2);
//            int halfLen = s.length()/2;
//            int len=s.length()
//            for(int k=0;k<s.length()-(s.length()/2);k++){
//                char temp = s.charAt(s.length()/2);
//                s.charAt(halfLen) = s.charAt(s.length());
//                s.charAt(s.length()) = temp;
//            }
			System.out.println("after " + sb.toString());
			s=sb.toString();
		}
		while (i >= 0 && j < s.length()) {
			if (s.charAt(i) == s.charAt(j)) {
				i = i - 1;
				j = j + 1;
			} else {
				break;
			}
		}

		System.out.println(s.substring(i + 1, j));
		return s.substring(i + 1, j);
	}

	public String longestPalindrome(String s) {
		String result = "";
		HashMap<Integer, String> resultMap = new HashMap<Integer, String>();
		for (int i = 1; i < s.length() - 1; i++) {
			result = getPaliStr(s, i);
			if (result.length() > 1) {
				resultMap.put(i, result);
			}
		}
		String maxResult = "";
		for (Map.Entry<Integer, String> entry : resultMap.entrySet()) {
			if (maxResult.length() < entry.getValue().length())
				maxResult = entry.getValue();
		}
		if(maxResult.contains("$")) {
			
		}
		return maxResult;
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
	            
	            String ret = new Solution().longestPalindrome(s);
	            
	            String out = (ret);
	            
	            System.out.print(out);
	        }
	    }
	}
