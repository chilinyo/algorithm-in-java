package cn.chilin.algorithm.stack;


import java.util.Stack;

/**
 *
 * 设计一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作
 * 要求 getMin() 的时间负责度是 O(1)\
 *
 * 思路：两个栈实现，一个存放数据，一个存放最小值，应保持stackMin从栈底到栈顶是从大到小，这样getMin()只需stackMin的peek()操作即可。
 */
public class GetMinStack {

    private Stack<Integer> stackData;

    private Stack<Integer> stackMin;

    public GetMinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int num) {
        // 最次压栈跟最小值栈的栈顶比较，小的就压入栈顶
        if (stackMin.isEmpty()) {
            stackMin.push(num);
        } else if (num <= getMin()) {
           stackMin.push(num);
        }
        stackData.push(num);
    }

    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException("your stack is EMPTY");
        }
        int v =  stackData.pop();
        // 由压栈规则可以知道，v只能>=stackMin的值，所以当v==stackMin栈顶元素时，同步弹出
        if (v == getMin()) {
            stackMin.pop();
        }
        return v;

    }


    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("your stack is EMPTY");
        }
        return stackMin.peek();
    }

}
