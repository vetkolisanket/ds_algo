class MyQueue<T> {
	class QueueNode<T> {
		T data;
		QueueNode<T> next;

		public QueueNode(T data){
			this.data = data;
		}
	}

	QueueNode<T> first;
	QueueNode<T> last;

	public void add(T data) {
		QueueNode node = new QueueNode(data);
		if (first == null) first = node;
		else last.next = node;
		last = node; 
	}

	public T remove(){
		if (first == null) return null;
		T data = first.data;
		first = first.next;
		if (first == null) last = null;
		return data;
	}

	public T peek(){
		if (first == null) return null;
		return first.data;
	}

	public boolean isEmpty(){
		return first == null;
	}

	public void printQueue(){
		QueueNode node = first;
		while(node != null){
			System.out.print(node.data+"->");
			node = node.next;
		}
		System.out.println("null");
	}

	public static void main(String[] args){
		MyQueue<Integer> queue = new MyQueue<Integer>();
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		queue.remove();
		queue.peek();
		queue.add(6);
		queue.printQueue();
	}
}
