import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Assert;

public class QuickSort {

	public static int partition(int[] nums, int low, int high) {
		int pivot = nums[high];
		int i = low - 1;
		for (int j = low; j <= high - 1; j++) {
			if (nums[j] <= pivot) {
				i++;
				int temp = nums[i];
				nums[i] = nums[j];
				nums[j] = temp;
			}
		}
		int temp = nums[i + 1];
		nums[i + 1] = nums[high];
		nums[high] = temp;
		return i + 1;

	}

	public static void quicksort(int[] nums, int low, int high) {
		if (low < high) {
			int p = partition(nums, low, high);
			/*System.out.println(low + " to " + high + " with pivot index " + p);
			for (int n : nums) {
				System.out.print(n + " ");
			}
			System.out.println();*/
			quicksort(nums, low, p - 1);
			quicksort(nums, p + 1, high);
		}
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
		quicksort(unsort, 0, nums.length - 1);
		for(int i=0;i<nums.length;i++){
			assertEquals(nums[i],unsort[i]);
		}
		System.out.println("output ");
		for (int n : unsort) {
			System.out.print(n + " ");
		}
	}
}
