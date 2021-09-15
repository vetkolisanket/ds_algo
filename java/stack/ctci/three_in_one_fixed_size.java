/*
Three in One: Describe how you could use a single array to implement three stacks.
*/

//Fixed array size implementation
class FixedMultiStack {
	private int numberOfStacks = 3;
	private int stackSize;
	private int[] values;
	private int[] sizes;

	public FixedMultiStack(int stackSize){
		this.stackSize = stackSize;
		values = new int[numberOfStacks*stackSize];
		sizes = new int[numberOfStacks];
	}

	public void push(int stackNum, int data) throws FullStackException  {
		if (isFull(stackNum)) throw new FullStackException();
		sizes[stackNum]++;
		int top = indexOfTop(stackNum);
		values[top] = data;
	}

	public int pop(int stackNum) throws EmptyStackException  {
		if (isEmpty(stackNum)) throw new EmptyStackException();
		int top = indexOfTop(stackNum);
		int data = values[top];
		values[top] = 0;
		sizes[stackNum]--;
		return data;
	}

	public int peek(int stackNum) throws EmptyStackException(){
		if (isEmpty(stackNum)) throw new EmptyStackException();
		return values[indexOfTop(stackNum)];
	}

	public boolean isEmpty(int stackNum){
		return sizes[stackNum] == 0;
	}

	public boolean isFull(int stackNum){
		return sizes[stackNum] == stackSize;
	}

	public int indexOfTop(int stackNum){
		int offset = stackNum*stackSize;
		int size = sizes[stackNum];
		return offset+size-1;
	}
}
