package cn.chilin.algorithm.stack;

import java.util.Stack;

/**
 * 使用递归反转栈
 *
 */
public class ReverseStackUsingRecursive {
 
	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) return;
		int i = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(i);
	}


	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		reverse(stack);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
	}

}
