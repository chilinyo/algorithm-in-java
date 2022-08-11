package cn.chilin.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author yangzhilin
 * @date 2022/3/7
 */
public class PreSum {

    // 前缀和
    private static int[] preSum;

    public PreSum(int[] nums) {
        // 初始化前缀和
        // 初始化前缀和
        preSum = new int[nums.length + 1];
        // preSum[0] = 0 是为了方便算前缀和
        //    [-2, 0, 3, -5, 2, -1]
        // [0 ,-2, -2, ]
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

    }

    public static void main(String[] args) {
        new PreSum(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(Arrays.stream(preSum).boxed().collect(Collectors.toList()));
    }
}
