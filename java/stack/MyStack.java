class MyStack<T>{

	class StackNode<T> {
		T data;
		StackNode<T> next;

		public StackNode(T data) {
			this.data = data;
		}
	}

	StackNode<T> top;

	public void push(T data){
		StackNode node = new StackNode(data);
		node.next = top;
		top = node;
	}

	public T pop(){
		if (top == null) return null;
		T item = top.data;
		top = top.next;
		return item;
	}

	public T peek(){
		if (top == null) return null;
		return top.data;
	}

	public boolean isEmpty(){
		return top == null;
	}

	public void printStack(){
		StackNode node = top;
		while (node != null){
			System.out.print(node.data + "->");
			node = node.next;
		}
		System.out.println("null");
	}

	public static void main(String[] args){
		MyStack<Integer> stack = new MyStack<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.pop();
		stack.push(5);
		stack.push(6);
		stack.pop();
		stack.printStack();
	}

}
