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

    private static void sort(int[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int p = partition(nums, lo, hi);
        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }

    private static int partition(int[] nums, int lo, int hi) {
        int i = lo;
        int j = hi + 1; // 因为右侧扫描是先自减，这里+1处理一下
        int v = nums[lo];
        // 直到i,j相遇跳出循环
        while (true) {
            // 当遇到比v大的数，跳出来
            while (nums[++i] < v) {
                if (i == hi) break;
            }
            // 当遇到比v小的数，跳出
            while (nums[--j] > v) {
                if (j == lo) break;
            }
            // 先判断边界，在执行交换
            if (i >= j) break;
            exch(nums, i, j);
        }
        // 跳出的时候，因为 i>=j，所以使用j与lo交换，将切分值留在nums[j]
        exch(nums, lo, j);
        // 此时实现： nums[lo..j-1] < nums[j] < nums[j + 1..hi]
        return j;
    }

    private static void exch(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
