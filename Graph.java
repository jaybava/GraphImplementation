import java.util.*;

public class Graph implements GraphADT {
	
	// create a hashmap with the key being an integer and value being a GraphNode and call it nodes
	private final Map<Integer, GraphNode> nodes;
	// create a hashmap with the key being a GraphNode and the values being a list of GraphEdges
	private final Map<GraphNode,List<GraphEdge>> adjacencyList;
	
	public Graph(int n) {
		// constructor for the Graph
		nodes = new HashMap<>();
		adjacencyList = new HashMap<>();
		for (int i = 0; i < n; i++){
			// create new node with key i
			GraphNode node = new GraphNode(i);
			// put this new node in the nodes HashMap
			nodes.put(i, node);
			// put this new node in adjacencyList HashMap and an empty list of GraphEdges
			adjacencyList.put(node, new ArrayList<>());
		}
	}

	// method to insert edges
	@Override
	public void insertEdge(GraphNode nodeU, GraphNode nodeV, int type, String label) throws GraphException {
		// check if nodes exists in HashMap
		if (!nodes.containsValue(nodeU) || !nodes.containsValue(nodeV)) {
			throw new GraphException("One or both nodes do not exist in the graph.");
		}
		// check if an edge already exists between given nodes
		if (areAdjacent(nodeU, nodeV)) {
			throw new GraphException("An edge already exists between the given nodes.");
		}
		// create the edge between two nodes with correct parameters
		GraphEdge edge = new GraphEdge(nodeU, nodeV, type, label);
		// insert into adjacency list of both nodes
		adjacencyList.get(nodeU).add(edge);
		adjacencyList.get(nodeV).add(edge);
	}

	// method to get a node from the graph
	@Override
	public GraphNode getNode(int u) throws GraphException {
		GraphNode node = nodes.get(u);
		if(node == null){
			throw new GraphException("Node with specified name does not exist");
		}
		return node;
	}
	// method to get incident edges of a node
	@Override
	public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
		// check if node exists in graph
		if (!nodes.containsValue(u)) {
			throw new GraphException("The node does not exist in the graph.");
		}
		// create a list of GraphEdges and use the get function of the adjacency list
		List<GraphEdge> edges = adjacencyList.get(u);
		// check if list returned is empty
		if (edges.isEmpty()) {
			return null;
		}
		// return the list GraphEdges
		return edges.iterator();
	}

	// method to return the edge connecting two nodes
	@Override
	public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
//		check if those nodes exist, then check if they have edges, then who has the least number of edges.
//		find the appropriate edge and return it, if no such edge exists remember to return null 
//		there are faster ways too ;)
		// check if given nodes exist in HashMap
		if (!nodes.containsValue(u) || !nodes.containsValue(v)) {
			throw new GraphException("One or both nodes do not exist in the graph.");
		}
		// loop for each edge in the list of edges
		for (GraphEdge edge : adjacencyList.get(u)) {
			// check if the firstEndpoint is u and the secondEndpoint is v or vice versa
			if ((edge.firstEndpoint().equals(u) && edge.secondEndpoint().equals(v)) ||
					(edge.firstEndpoint().equals(v) && edge.secondEndpoint().equals(u))) {
				// if condition is met return the edge
				return edge;
			}
		}
		// else throw a GraphException
		throw new GraphException("There is no edge between the given nodes.");
	}

	// method to find whether two nodes are adjacent
	@Override
	public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
//		maybe you could use a previously written method to solve this one quickly...
		// filter if either nodes don't exist in graph
		if (!nodes.containsValue(u) || !nodes.containsValue(v)) {
			throw new GraphException("One or both nodes do not exist in the graph.");
		}
		try{
			// call on getEdge function
			getEdge(u, v);
			// if edge is found return true
			return true;
		} catch (GraphException e){
			// if exception is triggered return false
			return false;
		}
	}

}
