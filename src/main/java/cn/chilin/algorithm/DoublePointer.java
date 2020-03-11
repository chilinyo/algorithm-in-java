package cn.chilin.algorithm;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DoublePointer {

    static class ListNode {
        ListNode next;

        Object data;

        ListNode(Object data) {
            this.data = data;
        }
    }

    /**
     * 双指针用法一：快慢指针判断链表是否有环
     *
     * @param head
     * @return
     */
    static boolean hasCycle(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 找到闭环的起始节点
     *
     * @param head
     * @return
     */
    static ListNode detectNode(ListNode head) {
        ListNode fast, slow;
        fast = slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                // 相遇点跳出循环
                break;
            }
        }
        // 此时将任一指针指向head，再次相遇的位置就是环的起始节点
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 左右指针，two sum 问题， 跟二分查找很像
     *
     * @param nums   一个升序数组, 如: [2, 7, 11, 15]
     * @param target 两数之和的目标值, 如: 9
     * @return 返回符合条件的两个数的下标值, 如返回: [1, 2], 否则返回: [-1, -1]
     */
    static int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 按题意是index + 1
            } else if (sum > target) {
                right--;                               // sum大了点，右指针往左移一位
            } else if (sum < target) {
                left++;                                // sum小了，左指针往右移一位
            }
        }
        return new int[]{-1, -1};


    }

    public static void main(String[] args) {
//        ListNode node1 = new ListNode("1");
//        ListNode node2 = new ListNode("2");
//        ListNode node3 = new ListNode("3");
//        ListNode node4 = new ListNode("4");
//        ListNode node5 = new ListNode("5");
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node2;
//        System.out.println(detectNode(node1).data);

        int []res = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.stream(res).boxed().collect(Collectors.toList()));
    }


}
