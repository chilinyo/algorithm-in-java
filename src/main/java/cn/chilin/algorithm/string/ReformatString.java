package cn.chilin.algorithm.string;

import java.util.Arrays;

/**
 * @author yangzhilin
 * @date 2022/8/11
 *
 * 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 *
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 *
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 *
 * 输入：s = "a0b1c2"
 * 输出："0a1b2c"
 * 解释："0a1b2c" 中任意两个相邻字符的类型都不同。 "a0b1c2", "0a1b2c", "0c2a1b" 也是满足题目要求的答案。
 *
 */
public class ReformatString {
    public static void main(String[] args) {

        System.out.println(reformat("abcd11111"));

    }

    static String reformat(String s) {
        // 1.数字和字母的个数相差不能大于1个
        // 2.将个数多的放在偶数下标，将个少的放在奇数下标，这个规格可以改，保证下标奇偶分开即可
        char[] array = s.toCharArray();
        int digitSum = 0;
        for (char c : array) {
            if (isDigit(c)) digitSum ++;
        }
        int alphaSum = s.length() - digitSum;
        if (Math.abs(alphaSum - digitSum) > 1) return "";

        boolean flag = digitSum > alphaSum;
        // i坐标上要放个数多的类型，j上面要放个数少的类型
        for (int i = 0, j = 1; i < s.length(); i = i + 2) {
            // 如果是数字，而且flag不满足，即 数字  <= 字母
            // 如果是字母，而且flag满足，即 数字 > 字母
            // 两种走进if的条件都是 i位置上的放的不是个数多的类型，所以要交换，要和 j位置上个数多的类型交换
            if (isDigit(array[i]) != flag) {
                // 找到j位置上个数多的类型，就退出循环，条件其实跟上面一样的
                while (isDigit(array[j]) != flag) {
                    j = j + 2;
                }
                exch(array, i, j);
            }
        }
        return String.valueOf(array);
    }

    static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static void exch(char[] array, int i, int j) {
        char tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }




}
