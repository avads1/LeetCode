
public class IsUnique {
	public static void main(String[] args) {
		String s = "abchkdfs";
		System.out.print(checkUnique(s));
	}

	public static boolean checkUnique(String s) {
		//Used to store the seen char
		int mem=0;
		for(int i=0;i<s.length();i++){
			//Gets the value of the char to store it in mem
			int n = s.charAt(i)-'a';
			//To check uniqueness, AND the val of char and the mem
			if((mem&(1<<n))!=0) {
				return false;
			}
			//If it is unique, store it in mem;
			mem=mem|1<<n;			
		}
		return true;
	}
}
