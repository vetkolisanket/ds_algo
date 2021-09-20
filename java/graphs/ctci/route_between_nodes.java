/*
Route Between Nodes: Given a directed graph, design an algorithm to find out whether there is a route between two nodes.
*/
//CTCI implementation
enum State { Unvisited, Visited, Visiting;  }

boolean search(Graph g, Node start, Node end) {
	if (start == end) return true;
	
	LinkedList<Node> q = new LinkedList<Node>();

	for (Node u : g.getNodes()) u.state = State.Unvisited;
	start.state = State.Visiting;
	q.add(start);
	Node u;
	while (!q.isEmpty()) {
		u = q.removeFirst();
		if (u != null) {
			for (Node v: u.getAdjacent()) {
				if (v.state == State.Unvisited) {
					if (v == end) {
						return true;
					} else {
						v.state = State.Visiting;
						q.add(v);
					}
				}
			}
			u.state = State.Visited;
		}
	}
	return false;
}

//My implementation
boolean routeExists(Node a, Node b) {
	if (a == b) return true;
	Queue<Node> q = new Queue<Node>();
	q.enqueue(a);
	a.visited = true;
	
	while(!q.isEmpty()) {
		Node t = q.dequeue();
		for (Node n : t.adjacent) {
			if (n == b) return true;
			if (!n.visited) {
				q.enqueue(n);
			}
		}
	}
	return false;
}
