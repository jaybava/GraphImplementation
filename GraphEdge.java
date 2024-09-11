
public class GraphEdge {
	// create parameters for the GraphEdge object
	private  final GraphNode origin;
	private final GraphNode destination;
	private int type;
	private String label;

	// construct the GraphEdge object
	public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
		this.origin = u;
		this.destination = v;
		this.type = type;
		this.label = label;
	}
	
	// getters for the GraphEdge object
	public GraphNode firstEndpoint() {
		return this.origin;
	}
	
	public GraphNode secondEndpoint() {
		return this.destination;
	}
	
	public int getType() {
		return this.type;
	}
	
	public String getLabel() {
		return this.label;
	}

	// setters for the GraphEdge object
	public void setType(int type) {
		this.type = type;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
}
