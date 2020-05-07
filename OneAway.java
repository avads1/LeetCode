
public class OneAway {
	// Similar to LC Buddy strings
	public static void main(String[] args) {
		String[] s = { "pale", "ple", "pale","pale" };
		String[] t = { "bale", "pale", "ple","pke" };
		for (int i = 0; i < s.length; i++) {
			System.out.println(edit(s[i], t[i]));
		}
	}

	public static boolean editReplace(String s, String t) {
		boolean diff=false;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)!=t.charAt(i)) {
				if(diff) {
					return false;
				}
				diff=true;
			}
		}
		return true;
	}
	/*
	 *  "ple"
	 *  "pale"
	 */
	public static boolean editInsert(String s, String t) {
		int i=0;
		int j=0;
		int count=0;
		while(i<s.length() && j<t.length()) {
			if(s.charAt(i)==t.charAt(j)) {
				i++;
			}else {
				count++;
				if(count>1) {
					return false;
				}
			}
			j++;
		}
		return true;
	}
	

	public static boolean edit(String s, String t) {
		if(Math.abs(s.length()-t.length())>1) {
			return false;
		}
		if(s.length()==t.length()) {
			return editReplace(s, t);
		}else {
			String a=s.length()<t.length()?s:t;
			String b=s.length()<t.length()?t:s;
			return editInsert(a,b);
		}

	}

}
