class LinkedList {
	private Node root;

	public void add(int val){
		Node newNode = new Node(val);
		if(root == null) {
			root = newNode;
		} else {
			Node node = root;
			while(node.next != null){
				node = node.next;
			}
			node.next = newNode;
		}
	}

	private void printSelf() {
		if (root == null) {
			System.out.println("null");
		} else {
			Node node = root;
			while (node != null){
				System.out.print(node.val);
				if (node.next != null) System.out.print("->");
				node = node.next;
			}
		}
	}

	public static void main(String[] args){
		LinkedList list = new LinkedList();
		int length = args.length;
		for(int i=0;i<length;i++){
			list.add(Integer.parseInt(args[i]));
		}
		list.printSelf();
	}

}
