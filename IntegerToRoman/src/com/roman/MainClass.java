package com.roman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/*
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: 3
Output: "III"
Example 2:

Input: 4
Output: "IV"
Example 3:

Input: 9
Output: "IX"
Example 4:

Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
Example 5:

Input: 1994
Output: "MCMXCIV" 
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 * 
 * */
class Solution {
	StringBuilder sb = new StringBuilder();

	public String intToRoman(int num) {
		int inputNum = num;
		int size = 0;
		while (inputNum != 0) {
			inputNum = inputNum / 10;
			size = size + 1;
		}

		resolveToRomanNum(num, size);
		return sb.toString();

	}

	private void resolveToRomanNum(int num, int size) {
		int number = 0;
		for (int i = size; i > 0; i--) {
			number = splitNumber(num, i);
			number = number / (int) Math.pow(10, i - 1);
			convertIntToRoman(number, (int) Math.pow(10, i - 1));
		}
	}

	private void convertIntToRoman(int number, int placeVal) {
		if (number == 1) {
			sb.append(getChar(placeVal));
		} else if (number == 2) {
			sb.append(getChar(placeVal)).append(getChar(placeVal));
		} else if (number == 3) {
			sb.append(getChar(placeVal)).append(getChar(placeVal)).append(getChar(placeVal));
		} else if (number == 4) {
			sb.append(getChar(placeVal)).append(getChar(5 * placeVal));
		} else if (number == 5) {
			sb.append(getChar(5 * placeVal));
		} else if (number == 6) {
			sb.append(getChar(5 * placeVal)).append(getChar(placeVal));
		} else if (number == 7) {
			sb.append(getChar(5 * placeVal)).append(getChar(placeVal)).append(getChar(placeVal));
		} else if (number == 8) {
			sb.append(getChar(5 * placeVal)).append(getChar(placeVal)).append(getChar(placeVal))
					.append(getChar(placeVal));
		} else if (number == 9) {
			sb.append(getChar(placeVal)).append(getChar(10 * placeVal));
		}
	}

	private int splitNumber(int num, int placeValue) {
		int tenthPower = (int) Math.pow(10, placeValue);
		int number = num / tenthPower;
		number = num - (number * tenthPower);
		return number;
	}

	public String getChar(int num) {
		Map<Integer, String> romanMap = new HashMap<Integer, String>();
		romanMap.put(1, "I");
		romanMap.put(5, "V");
		romanMap.put(10, "X");
		romanMap.put(50, "L");
		romanMap.put(100, "C");
		romanMap.put(500, "D");
		romanMap.put(1000, "M");
		return romanMap.get(num);
	}
};

public class MainClass {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int num = Integer.parseInt(line);

			String ret = new Solution().intToRoman(num);

			String out = (ret);

			System.out.print(out);
		}
	}
}