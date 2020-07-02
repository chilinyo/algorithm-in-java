package cn.chilin.algorithm;
import java.util.Stack;

/** 
 *  1 1 1 0
 *  1 1 1 0
 *  0 0 0 1
 *  得到最大的矩形的面积是 6
 *	---------------------------
 *  思路：
 *  1. 转成直方图(每行累计1，遇0清零)：
 *	1 1 1 0
 *	2 2 2 0 
 *	0 0 0 1
 *	2. 每个数像两边扩充，直到遇到比自己小的数，即是单调栈，找出两边最近的比自己小的数
 *  3. 求单调栈的时候，遇到相同的数也弹出，此时最后一个数依然会计算正确，比如 1 2 4 5 6 7 4 5 3，第一个4->(2,4) 第二个4->(2, 3)，此时仍能算出最大值
 */

public class MaximalRectangle {

	public static int maxRecSize(int[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) return 0;
		int maxArea = 0;
		// 代表每行累计数（高度）
		int[] height = new int[map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
			}
			maxArea = Math.max(maxArea, maxRecFromBottom(height));
		}
		return maxArea;
	}

	// 通过单调栈算面积
	public static int maxRecFromBottom(int[] height) {
		if (height == null || height.length == 0) return 0;
		int maxArea = 0;

		// 注意stack 里面存的是下标
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < height.length; i ++ ) {
			// 新入栈的数小于等于栈顶的数时弹出
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				int j = stack.pop();
				// 如果是空，则从起始坐标0的上一位 -1 开始算
				int k = stack.isEmpty() ? -1 : stack.peek();
				// 这里i,k 分别是最近的比height[j]小的数的下标
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		// 栈中还有数据，证明都是单调递增的如  1,2,3,4,5 需弹出并计算面积，跟上面差不多
		while (!stack.isEmpty()) {
			int i = stack.pop();
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[i];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}

	public static void main(String []args) {
		int[][] map = new int[][]{{1,1,1,0}, {1,1,1,0}, {0,0,0,1}};
		int maxArea = maxRecSize(map);
		System.out.println(maxArea);
	}

}













