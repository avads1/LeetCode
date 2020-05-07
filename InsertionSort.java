import static org.junit.Assert.assertEquals;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		int[] nums = { 9, 3, 2, 4 };
		int[] unsort = Arrays.copyOf(nums, nums.length);
		Arrays.sort(nums);
		System.out.println("Input ");
		for (int n : unsort) {
			System.out.print(n + " ");
		}
		System.out.println();
		insertionSort(unsort);
		for(int i=0;i<nums.length;i++){
			assertEquals(nums[i],unsort[i]);
		}
		System.out.println("output ");
		for (int n : unsort) {
			System.out.print(n + " ");
		}
	}

	private static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int x = arr[i];
			int j = i - 1;
			while (j>=0 && arr[j] > x) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j+1] = x;
//			System.out.println();
//			for (int n : arr) {
//				System.out.print(n + " ");
//			}
//			System.out.println();
		}

	}
}
