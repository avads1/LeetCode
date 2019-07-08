package com.validIPAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.eclipsesource.json.JsonArray;

/*
 * Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.
IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
 each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
 The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 
 is a valid one. Also, we could omit some leading zeros among four hexadecimal digits and some low-case 
 characters in the address to upper-case ones, so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid 
 IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using 
two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is an 
invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 
02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"

Output: "IPv4"

Explanation: This is a valid IPv4 address, return "IPv4".
Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"

Output: "IPv6"

Explanation: This is a valid IPv6 address, return "IPv6".
Example 3:
Input: "256.256.256.256"

Output: "Neither"

Explanation: This is neither a IPv4 address nor a IPv6 address.

============================================================
Test Cases
"02001:0db8:85a3:0000:0000:8a2e:0370:7334"

"2001:0db8:85a3:0000:0000:8a2e:0370:7334"

"1e1.4.5.6"

"2001:0db8:85a3:0:0:8A2E:0370:7334:"

"256.256.256.256"

"1.1.1.1."

"12.12.12.12.12"

"2001:0db8:85a3:00000:0:8A2E:0370:7334"

"20EE:FGb8:85a3:0:0:8A2E:0370:7334" -invalid

"2001:0db8:85a3:0:0:8A2E:0370:7334" - valid

"20EE:Fb8:85a3:0:0:8A2E:0370:7334" - valid

"2001:db8:85a3:0::8a2E:0370:7334" - Invalid

"1081:db8:85a3:01:-0:8A2E:0370:7334" - Invalid

"15.16.-0.1" - Invalid

"192.0.0.1" - Valid

 * */
class Solution {
	public String validIPAddress(String IP) {
		boolean isValid = false;
		if (IP.contains(".")) {
			isValid = validateIP4(IP);
			if (isValid) {
				return "IPv4";
			}
		} else if (IP.contains(":")) {
			isValid = validateIP6(IP);
			if (isValid) {
				return "IPv6";
			}
		}
		return "Neither";
	}

	private boolean validateIP6(String IP) {
		if (IP.startsWith(":") || IP.endsWith(":") || IP.contains("-")) {
			return false;
		} else {
			String[] ip6 = IP.split(":");
			if (ip6.length == 8) {
				for (int i = 0; i < 8; i++) {
					if (ip6[i].length() > 4 || hasNonHexChar(ip6[i]) || ip6[i].equals(""))
						return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	private boolean hasNonHexChar(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			if (str.matches(".*[g-zG-Z].*")) {
				return true;
			}
		}
		return false;
	}

	private boolean validateIP4(String IP) {
		if (IP.startsWith(".") || IP.endsWith(".") || IP.contains("-")) {
			return false;
		} else {
			String[] ip4 = IP.split("\\.");
			int ip4Num = 0;
			if (ip4.length == 4) {
				for (int i = 0; i < 4; i++) {
					if (!ip4[i].startsWith("0") || (ip4[i].length() == 1 && ip4[i].contains("0"))) {
						try {
							ip4Num = Integer.parseInt(ip4[i]);
						} catch (Exception e) {
							return false;
						}
						if (ip4Num > 255 || ip4Num < 0) {
							return false;
						}
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return true;
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
			String IP = stringToString(line);

			String ret = new Solution().validIPAddress(IP);

			String out = (ret);

			System.out.print(out);
		}
	}
}
