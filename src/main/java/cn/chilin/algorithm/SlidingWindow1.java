package cn.chilin.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口1
 * 腾讯一面题
 * <p>
 * 小明玩射击游戏，总共有 n 种( 1 - n )号码的气球，小明射了 m 枪，求最少连续射击几枪能把 n 种气球都击满。
 * 给出一个数组，代表 m 枪分别击中的气球号码，0 代表没有命中
 * 例如：
 * m = 12, n = 5
 * nums =  {2, 5, 3, 1, 3, 2, 4, 1, 0, 5, 4, 3}
 * >> res = 6
 *
 * @author yangzhilin
 * @date 4/7/21
 */
public class SlidingWindow1 {

    public static void main(String[] args) {
        int m = 12;
        int n = 5;
        int[] nums = {2, 5, 3, 1, 3, 2, 4, 1, 0, 5, 4, 3};
        int i = slidingWindow(m, n, nums);
        System.out.println(i);
    }


    static int slidingWindow(int m, int n, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0, right = 0, valid = 0;
        Map<Integer, Integer> needMap = new HashMap<>();
        Map<Integer, Integer> windowMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            needMap.put(i, 1);
        }

        while (right < m) {
            int in = nums[right];
            right++;
            if (needMap.containsKey(in)) {
                // Java map 操作有点麻烦
                if (windowMap.containsKey(in)) {
                    windowMap.put(in, windowMap.get(in) + 1);
                } else {
                    windowMap.put(in, 1);
                }
                if (windowMap.get(in).equals(needMap.get(in))) {
                    valid++;
                }
            }

            // 当要命中个数符合条件时，缩小窗口
            while (valid == needMap.size()) {

                // 在这里更新最小子串
                if (right - left < res) {
                    res = right - left;
                }
                int out = nums[left];
                left++;

                // 将数据丢出窗口的操作，跟加入时刚好相反，先判断减少 valid
                if (needMap.containsKey(out)) {

                    if (windowMap.get(out).equals(needMap.get(out))) {
                        valid--;
                    }

                    windowMap.put(out, windowMap.get(out) - 1);


                }

            }

        }


        return res == Integer.MAX_VALUE ? -1 : res;
    }

}
