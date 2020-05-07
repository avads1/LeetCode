package com.reverseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//  1534236469

class Solution {
	public int reverse(int x) {
		int rev = 0;
		boolean flag = false;
		if (x < 0) {
			flag = true;
			x = x * -1;
		}
		while (x > 0) {
			int rem = x % 10;
			x = x / 10;
			if (rev < Integer.MIN_VALUE/10 || rev == Integer.MIN_VALUE && rem < -8) {
				return 0;
			}
			if (rev > Integer.MAX_VALUE/10|| rev == Integer.MAX_VALUE && rem > 7) {
				return 0;
			}
			rev = rev * 10 + rem;
		}
		if (flag)
			return rev * (-1);
		else
			return rev;
	}
}

public class MainClass {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = in.readLine()) != null) {
			int x = Integer.parseInt(line);

			int ret = new Solution().reverse(x);

			String out = String.valueOf(ret);

			System.out.print(out);
		}
	}
}
