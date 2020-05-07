
public class URLify {
	public static void main(String[] args) {
		String s = "Mr John Smith    ";
		System.out.print(encodeURL(s,13));
	}

	public static String encodeURL(String s,int len) {
		char[] arr = s.toCharArray();
		int k=arr.length-1;
		int i=len-1;
		while(i>=0){
			if(arr[i]==' '){
				arr[k]='0';
				arr[k-1]='2';
				arr[k-2]='%';
				k=k-3;
				i--;
			}else{
				arr[k--]=arr[i--];
			}
			System.out.println(arr);
		}
		return new String(arr);
	}
}
