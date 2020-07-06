package cn.chilin.algorithm.stack;


import java.util.PriorityQueue;
import java.util.Stack;

/**
 *
 * 使用两个栈实现一个队列
 */
public class TwoStackQueue {

    private Stack<Integer> stackPush;

    private Stack<Integer> stackPop;

    public TwoStackQueue() {
        stackPop = new Stack<>();
        stackPush = new Stack<>();
    }

    /**
     *
     * 将push栈里面的元素压到pop栈里面有两个条件：
     * 1. pop栈必须是空的
     * 2. push栈必须一次性将所有数据到压到pop栈
     */
    private void pushToPop(){
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void add(int n) {
        stackPush.push(n);
        pushToPop();
    }

    public int poll() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("your queue is EMPTY");
        }
        pushToPop();
        return stackPop.pop();
    }

    public int peek() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("your queue is EMPTY");
        }
        pushToPop();
        return stackPop.peek();
    }

}
