package cn.chilin.algorithm.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author yangzhilin
 * @date 2022/8/11
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 *
 */
public class Brackets {

    public static void main(String[] args) {
        System.out.println(isValid("(("));
    }

    static boolean isValid(String s) {
        if (s == null || s.equals("") || s.length() % 2 != 0) return false;
        // 先用一个map去存括号对
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');
        LinkedList<Character> stack = new LinkedList<>();
        for(char c : s.toCharArray()) {
            // 是右括号的话，先判断map里面有没有
            if (pairs.containsKey(c)) {
                // 1. 如果栈是空的，说明一上来就是右括号
                // 2. 如果栈顶不是同类型的话，就证明不匹配，如 [{]}
                if (stack.isEmpty() || stack.peek() != pairs.get(c)) {
                    return false;
                }
                // 一对的括号归约掉
                stack.pop();
            } else {
                // 左括号放到栈里面
                stack.push(c);
            }
        }
        return stack.isEmpty();

    }

}
