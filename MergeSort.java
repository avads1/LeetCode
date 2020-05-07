import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class MergeSort {
	/*
	 * 9,		2, 		4,		 5, 	6, 		7, 		8, 		1, 		2
	 * 
	 * 
	 * 
	 * */
	public static void merge(int[] nums,int low,int mid,int high) {
		int l1 = mid-low+1;
		int l2 = high-mid;
		int[] left = new int[l1];
		int[] right = new int[l2];
		System.out.println("***********Merge**********");
		for(int i=0;i<l1;i++) {
			left[i]=nums[low+i];
		}
		for(int i=0;i<l1;i++) {
			System.out.print(left[i]+" ");
		}
		System.out.println();
		for(int j=0;j<l2;j++){
			right[j]=nums[mid+1+j];
		}
		for(int j=0;j<l2;j++){
			System.out.print(right[j]+" ");
		}
		System.out.println();
		int i=0;
		int j=0;
		int k=low;
		while(i<l1&& j<l2) {
			if(left[i]<right[j]) {
				nums[k++]=left[i++];
			}else{
				nums[k++]=right[j++];
			}
		}
		while(i<l1) {
			nums[k++]=left[i++];
		}
		while(j<l2){
			nums[k++]=right[j++];
		}		
	}
	static int call=1;
	public static void mergesort(int[] nums,int low,int high){
		if(low>=high) {
			return;
		}
		int mid=(low+high)/2;
		System.out.println("********************");
		System.out.println("Call : "+(call++)+" [ low = "+low+" , high = "+high+" mid = "+mid+" ]");
		mergesort(nums,low,mid);
		mergesort(nums,mid+1,high);
		merge(nums,low,mid,high);
	}
	
	public static void main(String[] args) {
		int[] nums = { 9, 2, 4, 5, 6, 7, 8, 1, 2 };
		int[] unsort = Arrays.copyOf(nums, nums.length);
		Arrays.sort(nums);
		System.out.println("Input ");
		for (int n : unsort) {
			System.out.print(n + " ");
		}
		System.out.println();
		mergesort(unsort, 0, nums.length - 1);
		for(int i=0;i<nums.length;i++){
			assertEquals(nums[i],unsort[i]);
		}
		System.out.println("output ");
		for (int n : unsort) {
			System.out.print(n + " ");
		}
	}
}
