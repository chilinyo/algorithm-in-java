package cn.chilin.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class QuickSort {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 43, 4, 6, 5, 9, 0, 32, 34, 5, 45, 46};
        sort(nums);
        System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
    }

    public static void sort(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int left, int right) {
        if (left >= right) return;
        int p = partition(nums, left, right);
        sort(nums, left, p - 1);
        sort(nums, p + 1, right);
    }

    private static int partition(int[] nums, int left, int right) {
        int i = left;
        int j = right + 1;
        int v = nums[left];
        // 直到i,j相遇跳出循环
        while (true) {
            // 当遇到比v大的数，跳出来
            while (nums[++i] < v) {
                if (i == right) break;
            }
            // 当遇到比v小的数，跳出
            while (nums[--j] > v) {
                if (j == left) break;
            }
            // 先判断边界，在执行交换
            if (i >= j) break;
            exch(nums, i, j);
        }
        // 跳出的时候，因为 i>=j，所以使用j与left交换，将切分值留在nums[j]
        exch(nums, left, j);
        // 此时实现： nums[left..j-1] < nums[j] < nums[j + 1..right]
        return j;
    }

    private static void exch(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
