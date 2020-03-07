package cn.chilin.algorithm;

public class BinarySearch {


    public static void main(String[] args) {
        int index = binarySearch(new int[]{1, 2, 3, 5, 6, 7, 8}, 100);
        System.out.println(index);
    }

    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    static int binarySearch(int[] nums, int target) {
        // 设置为闭区间 [left, right]
        int left = 0, right = nums.length - 1;
        // 因为是闭区间，所以跳出条件是 left == right
        while (left <= right) {
            // 1.算出中间索引，且要避免溢出
            int mid = left + (right - left) / 2;
            // 2.比较target和中间索引的值
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 此时target在左边，将右界改成mid - 1
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 此时target在右边，将左界改成mid + 1
                left = mid + 1;
            }
        }
        return -1;
    }
}
