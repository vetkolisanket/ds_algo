/*
Find maximum in a stack in O(1) time and O(1) extra space

Given a stack of integers. The task is to design a special stack such that the maximum element can be found in O(1) time and O(1) extra space.
*/

import java.util.*;

class MaxStack {

	private Integer max = null;
	private Stack<Integer> stack;

	public MaxStack() {
		stack = new Stack();
	}

	public int getMax() {
		if (max == null) {
			//throw new Exception("Stack is empty!");
			System.out.println("Stack is empty!");
			return -1;
		}
		return max;
	}

	public void push(int val) {
		if (max == null) {
			max = val;
			stack.push(val);
		} else if (val <= max) {
			stack.push(val);
		} else {
			int newVal = 2*val-max;
			stack.push(newVal);
			max = val;
		}
	}

	public int peek() {
		if (stack.isEmpty()) {
			//throw new Exception("Stack is empty!");
			System.out.println("Stack is empty!");
			return -1;
		}
		if (stack.peek() <= max) {
			return stack.peek();
		}
		return max;
	}

	public int pop() {
		if (stack.isEmpty()) {
			//throw new Exception("Stack is empty!");
			System.out.println("Stack is empty!");
			return -1;
		}
		if (stack.peek() <= max) {
			return stack.pop();
		}
		int popValue = max;
		max = 2*max - stack.pop();
		return popValue;
	}

	public static void main(String[] args) {
		MaxStack stack = new MaxStack();
		stack.push(2);
		System.out.println(stack.getMax());
		stack.push(1);
		System.out.println(stack.getMax());
		stack.push(9);
		System.out.println(stack.getMax());
		stack.push(3);	
		System.out.println(stack.getMax());
		stack.push(123);	
		System.out.println(stack.getMax());
		stack.push(12);
		System.out.println(stack.getMax());
	}

}
