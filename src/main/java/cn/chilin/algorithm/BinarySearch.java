package cn.chilin.algorithm;

public class BinarySearch {


    public static void main(String[] args) {
//        int index = binarySearch(new int[]{1, 2, 3, 5, 6, 7, 8}, 100);
//        System.out.println(index);
        System.out.println(searchLeftBound(new int[]{2, 3, 5, 7, 8, 8, 8, 9}, 8));
    }

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    static int binarySearch(int[] nums, int target) {
        // 设置为闭区间 [left, right]
        int left = 0, right = nums.length - 1;
        // 因为是闭区间，所以跳出条件是 left > right，这也是下面right = mid -1 & left = mid + 1的原因
        while (left <= right) {
            // 1.算出中间索引，且要避免溢出
            int mid = left + (right - left) / 2;
            // 2.比较target和中间索引的值
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 此时target在左边，缩右界
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 此时target在右边，缩左界
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 二分查找左界
     * @param nums
     * @param target
     * @return
     */
    static int searchLeftBound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        // 这里依然是使用闭区间来查找
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                // 当找到target的时候，先缩界不返回
                right = mid - 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        // 最后得检查left的越界情况
        if (left >= nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

}
