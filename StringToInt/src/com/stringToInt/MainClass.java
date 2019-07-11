package com.stringToInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import com.eclipsesource.json.JsonArray;

/*
 * REGEX link
 * http://tutorials.jenkov.com/java-regex/index.html#regular-expressions
 * https://howtodoinjava.com/regex/word-boundary-starts-ends-with/
 * 
 * 
 * Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. 
Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
 and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect
 on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists
 because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit 
signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or
 INT_MIN (−231) is returned.
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
 * */
class Solution {
	public int myAtoi(String str) {
		int num = 0;
		str = str.trim();
		String[] strArr = str.split(" ");
		for (int i = 0; i < strArr.length; i++) {
			if (!strArr[i].matches("[a-zA-Z]+\\.?")) {
				if (strArr[i].contains(".")) {
					int end = strArr[i].indexOf(".");
					strArr[i] = strArr[i].substring(0, end);
				}
				if (strArr[i].contains("-") && strArr[i].contains("+") && distanceBetweenMinusAndPlus(strArr[i]) < 2
						|| (strArr[i].length() == 1 && (strArr[i].startsWith("-") || strArr[i].startsWith("+")))
						|| strArr[i].length() == 0 || strArr[i].matches("[A-Za-z-+]+")
						|| strArr[i].matches("-{2}[0-9A-Za-z]+") || strArr[i].matches("[+]{2}[0-9A-Za-z]+")
						|| startsWithPlusMinusAndAlphabet(strArr[i])) {
					return 0;
				}

				// "-13+8"
				if (distanceBetweenMinusAndPlus(strArr[i]) > 1) {
					strArr[i] = strArr[i].substring(0, strArr[i].length() - 1);
				}

				// "-5-"
				if (strArr[i].endsWith("-") || strArr[i].endsWith("+")) {
					strArr[i] = strArr[i].substring(0, strArr[i].length() - 1);
				}

				// "010"
				while (strArr[i].startsWith("0") && !strArr[i].contains("-")) {
					int start = strArr[i].indexOf("0");
					strArr[i] = strArr[i].substring(start + 1, strArr[i].length());
				}

				// "0-1"
				if (!strArr[i].startsWith("-") && !strArr[i].endsWith("-") && strArr[i].contains("-")
						&& strArr[i].matches("[0-9-]+")) {
					int end = strArr[i].indexOf("-");
					strArr[i] = strArr[i].substring(0, end);
				} else if (!strArr[i].startsWith("+") && !strArr[i].endsWith("+") && strArr[i].contains("+")
						&& strArr[i].matches("[0-9+]+")) {
					int end = strArr[i].indexOf("+");
					strArr[i] = strArr[i].substring(0, end);
				}

				// " -0012a42"
				char strChar[] = strArr[i].toCharArray();
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < strChar.length; j++) {
					if (strChar[j] >= 48 && strChar[j] <= 57 || strChar[j] == 43 || strChar[j] == 45) {
						// if (strChar[j] == 48 && j == 0) {
						sb.append(strChar[j]);
						// }
					} else {
						break;
					}
				}
				if (sb.length() > 0) {
					try {
						num = Integer.parseInt(sb.toString());
					} catch (Exception e) {
						if (strArr[i].matches("[-]+[0-9A-Za-z]+")) {
							num = -2147483648;
						} else {
							num = 2147483647;
						}
						return num;
					}
					return num;
				} else {
					return 0;
				}
			} else {
				break;
			}
		}
		return num;
	}

	private boolean startsWithPlusMinusAndAlphabet(String s) {
		return Pattern.compile("^[+-][A-Za-z]").matcher(s).find();
	}

	private int distanceBetweenMinusAndPlus(String string) {
		int minus = string.indexOf("-");
		int plus = string.indexOf("+");
		int dist = 0;
		if (minus > plus) {
			dist = minus - plus;
		} else {
			dist = plus - minus;
		}
		return dist;
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
			String str = stringToString(line);

			int ret = new Solution().myAtoi(str);

			String out = String.valueOf(ret);

			System.out.print(out);
		}
	}
}
