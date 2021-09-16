/*
Stack Min: How would you design a stack which, in addition to push and pop, has a function min which returns the minimum element? Push, pop and min should all operate in 0(1) time.
*/
class MyStack {

	class StackNode {
		int data;
		int min;
		StackNode next;
	
		public StackNode(int data) {
			this.data = data;
		}
	}

	StackNode top;

	public void push(int data) {
		StackNode node = new StackNode(data);
		if (top == null) {
			node.min = data;
		} else {
			node.min = data < top.min ? data : top.min;
		}
		node.next = top;
		top = node;
	}

	public int pop() throws EmptyStackException  {
		if (top == null) throw new EmptyStackException();
		int val = top.data;
		top = top.next;
		return val;
	}

	public int peek() throws EmptyStackException {
		if (top == null) throw new EmptyStackException();
		return top.data;
	}

	public boolean isEmpty() {
		return top == null;
	}

	public int min() throws EmptyStackException {
		if (top == null) throw new EmptyStackException();
		return top.min;
	}

}
