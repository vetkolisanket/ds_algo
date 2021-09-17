/*
Stack of Plates: Imagine a (literal) stack of plates. If the stack gets too high, it might topple. Therefore, in real life, we would likely start a new stack when the previous stack exceeds some threshold. Implement a data structure SetOfStacks that mimics this. SetOfStacks should be composed of several stacks and should create a new stack once the previous one exceeds capacity. SetOfStacks. push () and SetOfStacks. pop () should behave identically to a single stack (that is, pop ( ) should return the same values as it would if there were just a single stack).
FOLLOW UP
Implement a function popAt(int index) which performs a pop operation on a specific sub-stack.
*/

//Approach where stacks are rebalanced to fully occupy threshold
public class SetOfStacks {

	List<Stack> stacks = new ArrayList<Stack>();
	public in capacity;

	public SetOfStacks(int capacity) {
		this.capacity = capacity;
	}

	public Stack getLastStack() {
		if (stacks.size() == 0) return null;
		return stacks.get(stacks.size() - 1);
	}

	public void push(int v) {
		Stack last = getLastStack();
		if (last != null && !last.isFull()) {
			last.push(v);
		} else {
			Stack stack = new Stack(capacity);
			stack.push(v);
			stacks.add(stack);
		}
	}

	public in pop() {
		Stack last = getLastStack();
		if (last == null) throw new EmptyStackException();
		int v = last.pop();
		if (last.size == 0) stacks.remove(stacks.size() - 1);
		return v;
	}

	public boolean isEmpty() {
		Stack last = getLastStack();
		return last == null || last.isEmpty();
	}

	public int popAt(int index) {
		return leftShift(index, true);
	}

	public int leftShift(int index, boolean removeTop) {
		Stack stack = stacks.get(index);
		int removed_item;
		if (removeTop) removed_item = stack.pop();
		else removed_item = stack.removeBottom();
		if (stack.isEmpty()) {
			stacks.remove(index);
		} else if (stacks.size() > index + 1) {
			int v = leftShift(index + 1, false);
			stack.push(v);
		}
		return removed_item;
	}
}

public class Stack {
	private int capacity;
	public Node top, bottom;
	public int size = 0;

	public Stack(int capacity) {
		this.capacity = capacity;
	}

	public boolean isFull() {
		return capacity == size;
	}

	public void join(Node above, Node below) {
		if (below != null) below.above = above;
		if (above != null) above.below = below;
	}

	public boolean push(int v) {
		if (size >= capacity) return false;
		size++;
		Node n = new Node(v);
		if (size == 1) bottom = n;
		join(n, top);
		top = n;
		return true;
	}

	public int pop() {
		Node t = top;
		top = top.below;
		size--;
		return t.value;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int removeBottom() {
		Node b = bottom;
		bottom = bottom.above;
		if (bottom != null) bottom.below = null;
		size--;
		return b.value;
	}
}

//Approach where the stacks in between might not be fully occupied to threshold
class SetOfStacks {

	int threshold;
	List<Stack<Integer>> stacks = new ArrayList();

	public SetOfStacks(int threshold) {
		this.threshold = threshold;
	}


	public void push(int val) {
		Stack stack = getStack();
		stack.push(val);
	}

	private Stack getStack() {
		int size = stacks.size;
		if (size == 0 || stacks.get(size - 1).size() == threshold) {
			addNewStackToList();
		}
		return stacks.get(stacks.size() - 1);
	}

	private void addNewStackToList() {	
		Stack stack = new Stack<Integer>();
		stacks.put(stack);
	}

	private int pop() throws EmptyStackException {
		if (stacks.isEmpty()) throw new EmptyStackException();
		Stack lastStack = stacks.get(stacks.size() - 1);
		int val = lastStack.pop();
		if (lastStack.isEmpty()) {
			stacks.remove(stacks.size() - 1);
		}
		return val;
	}

	private int popAtIndex(int index) throws IndexOutOfBoundException() {
		if (index >= stacks.size()) throw new IndexOutOfBoundException();
		return stacks.get(index).pop();
	}

}
