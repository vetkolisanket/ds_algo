class Node {

	int val;
	Node next;

	public Node(int val){
		this.val = val;	
	}

}

class Stack {

	Node top;

	public void push(int val){
		Node node = new Node(val);
		if(top == null) {
			top = node;
		} else {
			Node temp = top;
			top = node;
			top.next = temp;
		}
	}

	public int pop() throws Exception {
		if(top != null){
			int val = top.val;
			top = top.next;
			System.out.println(val);
			return val;
		}
		throw new Exception("Stack is empty!");		
	}

	public int top() throws Exception {
		if(top != null){
			System.out.println(top.val);
			return top.val;
		}
		throw new Exception("Stack is empty!");
	}

	public boolean isEmpty(){
		return top == null;
	}

	public void printStack(){
		if(top != null){
			Node node = top;
			while (node != null){
				System.out.print(node.val);
				if(node.next!=null){
					System.out.print("->");
				} else System.out.println();
				node = node.next;
			}
		}
	}

	public static void main(String[] args){
		Stack stack = new Stack();
		try {
			//stack.pop();
			stack.push(1);
			stack.push(2);
			stack.push(3);
			stack.printStack();
			stack.pop();
			stack.top();
			stack.push(4);
			stack.printStack();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
