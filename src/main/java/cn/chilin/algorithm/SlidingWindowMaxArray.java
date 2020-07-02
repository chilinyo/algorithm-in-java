package cn.chilin.algorithm

import java.util.LinkedList;


/**
 * 滑动窗口大小是3
 * [4 34 34] 5 4 6 67 99 88  --> 34 
 * 4 [34 34 5] 4 6 67 99 88  --> 34 
 * 4 34 [34 5 4] 6 67 99 88  --> 34 
 * 4 34 34 [5 4 6] 67 99 88  --> 6 
 * 4 34 34 5 [4 6 67] 99 88  --> 67 
 * 4 34 34 5 4 [6 67 99] 88  --> 99 
 * 4 34 34 5 4 6 [67 99 88]  --> 99
 * output: 34 34 34 6 67 99 99 
 */
public class SlidingWindowMaxArray{

	public static int[] getMaxWindow(int[] arr, int n) {
		if (arr == null || n < 1 || arr.length < n) return null;
		LinkedList<Integer> qmax = new LinkedList<>();
		int[] res = new int[arr.length - n + 1];
		int index = 0;
		for (int i = 0; i < arr.length; i++) {
			// 滑动窗口add逻辑
			while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
				qmax.pollLast();
			}
			qmax.addLast(i);
			// 滑动窗口remove逻辑
			if (qmax.peekFirst() == i - n) {
				qmax.pollFirst();
			}
			// 规定大小的窗口成型以后，才收集结果
			if (i >= n - i) {
				res[index++] = arr[qmax.peekFirst()];
			}
 		}
 		return res;
	}

	public static void printArray(int[] arr) {
		for (int i  = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = new int[]{4,34,34,5,4,6,67,99,88};
		int[] res = getMaxWindow(arr, 3);
		printArray(res);
	}

}
