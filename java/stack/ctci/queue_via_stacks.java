/*
Queue via Stacks: Implement a MyQueue class which implements a queue using two stacks.
*/
//In this question it will be impressive to ask the interviewer if he would want push to be constant time operation or pop

//Push constant time operation
class MyQueue<T> {

	Stack<T> stackA;
	Stack<T> stackB;

	public MyQueue() {
		stackA = new Stack();
		stackB = new Stack();
	}

	public void add(T item) {
		stackA.push(item);
	}

	public T remove() throws EmptyQueueException  {
		if (stackA.isEmpty()) throw new EmptyQueueException();
		while (!stackA.isEmpty()) {
			stackB.push(stackA.pop());
		}
		T item = (T) stackB.pop();
		while (!stackB.isEmpty()) {
			stackA.push(stackB.pop());
		}
		return item;
	}

	public T peek() throws EmptyQueueException {
		if (stackA.isEmpty()) throw new EmptyQueueException();
		while (!stackA.isEmpty()) {
			stackB.push(stackA.pop());
		}
		T item = stackB.peek();
		while (!stackB.isEmpty()) {
			stackA.push(stackB.pop());
		}
	}

	public boolean isEmpty() {
		return stackA.isEmpty();
	}

	public int size() {
		return stackA.size();
	}

}

//Pop and peek constant time operation
class MyQueue<T> {

	//Most stuff same as above only add, remove and peek will change a bit

	public void add(T item) {
		while (!stackA.isEmpty()) {
			stackB.push(stackA.pop());
		}
		stackA.push(item);
		while (!stackB.isEmpty()) {
			stackA.push(stackB.pop());
		}
	}

	public T remove() throws EmptyQueueException {
		if (stackA.isEmpty()) throw new EmptyQueueException();
		return stackA.pop();
	}

	public T peek() throws EmptyQueueException {
		if (stackA.isEmpty()) throw new EmptyQueueException();
		return stackA.peek();
	}

}
