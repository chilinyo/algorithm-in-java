package cn.chilin.algorithm;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class KMPMatch {

    public static void main(String[] args) {
        System.out.println(
                search("abcbadslfkjghabcdaldkjghabcdalsdkgh;labcddkjf", "abcd")
        );
    }

    static List<Integer> search(String text, String pattern) {
        // 获得局部匹配表
        int[] pmt = getPMT(pattern);

        List<Integer> result = new ArrayList<>();
        // 统计当前的最大匹配个数
        int maxLen = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == pattern.charAt(maxLen)) {

                // 如果配对，当前最大匹配长度增加一
                maxLen ++;
            } else if (maxLen > 0 && text.charAt(i) != pattern.charAt(maxLen)) {
                // 当匹配个数大于零而且失配时，返回到次大前缀后缀匹配长度
                maxLen = pmt[maxLen - 1];
            }
            if (maxLen == pattern.length()) {
                // 获取模式串在字符串里面的起始index
                result.add(i - maxLen + 1);
                // 匹配完成，配对个数清零
                maxLen = 0;
            }

        }

        return result;

    }

    static int[] getPMT(String pattern) {
        int[] pmt = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (maxLength > 0 && pattern.charAt(i) != pattern.charAt(maxLength)) {
                maxLength = pmt[maxLength - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(maxLength)) {
                maxLength ++;
            }
            pmt[i] = maxLength;
        }

        return pmt;
    }
}
