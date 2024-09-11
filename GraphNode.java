public class GraphNode {

	// give the GraphNode its parameters
	private final int name;
	private boolean marked;
	
	public GraphNode(int name) {
		// construct the GraphNode object
		this.name = name;
		this.marked = false;
	}

	
	//setters and getters
	public void mark(boolean mark) {
		this.marked = mark;
	}
	
	public boolean isMarked() {
		return this.marked;
	}
	
	public int getName() {
		return this.name;
	}
	
}
