package cn.chilin.algorithm;
import java.util.HashMap;
import java.util.Stack;


// 使用单调栈构建MaxTree
public class MaxTree{

	static class Node {
		public Node left;
		public Node right;
		public int value;
		public Node(int value) {
			this.value = value;
		}
	}

	public static Node getMaxTree(int[] arr) {
		Node[] nodes = new Node[arr.length];
		for (int i = 0; i < arr.length; i++) {
			nodes[i] = new Node(arr[i]);
		}

		Stack<Node> stack = new Stack<>();
		// children -> parent
		HashMap<Node, Node> parentsMap = new HashMap<>();

		for(int i = 0; i < nodes.length; i++) {
			while(!stack.isEmpty() && stack.peek().value < nodes[i].value) {
				// 此时弹出的node，并找出它的父节点
				// 单调栈，一个数弹出的时候，栈底是它左边最近的比它大的数，新入栈的是右边最近的比它大的数
				// node -> (l, r), l(栈空时为null),r 中，小的作为node的父节点
				parentsMap.put(stack.pop(), (stack.isEmpty() || (stack.peek().value < nodes[i].value)) ? nodes[i] : stack.peek());
			}
			stack.push(nodes[i]);
		} 
		// 全部入栈以后还需一个弹出操作
		while (!stack.isEmpty()) {
			parentsMap.put(stack.pop(), stack.isEmpty ? null : stack.peek());
		}

		Node head = null;
		Node parent = null;
		for (int i = 0; i < arr.length; i++) {
			parent = parentsMap.get(nodes[i]);
			if (parent == null) {
				head = nodes[i];
			} 
			// 先挂左边，左边用了就挂右边
			else if (parent.left == null) {
				parent.left = nodes[i];
			} else {
				parent.right = nodes[i];
			}
		}
		return head;

	}



}
