import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze {
	// set parameters for the maze object
	private Graph graph;
	private int startNodeID;
	private int endNodeID;
	private int k;
	private List<GraphNode> path;

	public Maze(String inputFile) throws MazeException {
		try {
			// try to read the input file
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			// if able to read pass input to method readInput
			readInput(reader);
		} catch (IOException | GraphException e) {
			// if unable to read file throw MazeException
			throw new MazeException("Error initializing the maze: " + e.getMessage());
		}
	}

	// method to get Graph
	public Graph getGraph() {
		return graph;
	}

	// method to solve the maze
	public Iterator<GraphNode> solve() {
		try {
			// try to set the startNode to the node with startNodeId
			GraphNode startNode = graph.getNode(startNodeID);
			// set path to a new array list
			path = new ArrayList<>();
			// call the DFS function
			return DFS(k, startNode);
		} catch (GraphException e) {
			// if node cannot be found catch the GraphException and print the message
			e.printStackTrace();
			// return null
			return null;
		}
	}

	private Iterator<GraphNode> DFS(int k, GraphNode go) throws GraphException {
		// if the GraphNode passed is the endNodeID
		if (go.getName() == endNodeID) {
			// add the GraphNode to the path list
			path.add(go);
			// return the items in the path list
			return path.iterator();
		}

		// mark current node
		go.mark(true);
		// add current node to path list
		path.add(go);

		// create an iterator called edges for all incident edges of current node
		Iterator<GraphEdge> edges = graph.incidentEdges(go);
		// while edges doesn't equal null and edges has a next in list
		while (edges != null && edges.hasNext()) {
			// set GraphEdge to next GraphEdge in iterator
			GraphEdge edge = edges.next();
			// find next node by seeing if the first endpoint equals the current node return the secondEndpoint else return the firstEndpoint
			GraphNode next = (edge.firstEndpoint().equals(go)) ? edge.secondEndpoint() : edge.firstEndpoint();

			// check if next isn't visited and check the if we have enough coins to go to next node
			if (!next.isMarked() && k >= edge.getType()) {
				// get a list of GraphNodes called result and recursively call the DFS function subtracting the cost to get to next node
				Iterator<GraphNode> result = DFS(k - edge.getType(), next);
				// if result is not equal to null
				if (result != null) {
					return result;
				}
			}
		}

		// backtrack and unmark the current node
		go.mark(false);
		// remove last element in path list
		path.remove(path.size() - 1);
		// return null
		return null;
	}

	private void readInput(BufferedReader inputReader) throws IOException, GraphException {
		// read key values on top of the file and put into respective variables
		int scale = Integer.parseInt(inputReader.readLine().trim()); // S
		int width = Integer.parseInt(inputReader.readLine().trim()); // A
		int length = Integer.parseInt(inputReader.readLine().trim()); // L
		k = Integer.parseInt(inputReader.readLine().trim()); // k

		// create a  new graph with width
		graph = new Graph(width * length);

		// initialize roomLine and wallLine to hold input lines representing rooms and walls
		String roomLine, wallLine = null;

		// loop through each row of the maze
		for (int i = 0; i < length; i++) {
			// read and trim the current line representing rooms
			roomLine = inputReader.readLine().trim();
			// if not the last row
			if (i < length - 1) {
				// read and trim the next line representing walls
				wallLine = inputReader.readLine().trim();
			}

			// loop through each column of the maze
			for (int j = 0; j < width; j++) {
				// get the room character at the current position
				char room = roomLine.charAt(j * 2);
				// calculate the node ID based on the current row and column
				int nodeID = i * width + j;

				// if the room is the start room
				if (room == 's') {
					// set startNodeID
					startNodeID = nodeID;
				// if the room is the end room
				} else if (room == 'x') {
					// set endNodeID
					endNodeID = nodeID;
				}

				// if not the last column, check the right wall
				if (j < width - 1) {
					// get the character representing the right wall
					char rightWall = roomLine.charAt(j * 2 + 1);
					// if the right wall is not a wall ('w')
					if (rightWall != 'w') {
						// determine the coins cost to traverse the edge
						int coins = Character.isDigit(rightWall) ? Character.getNumericValue(rightWall) : 0;
						// insert an edge between the current node and the node to the right
						insertEdge(nodeID, nodeID+1, coins, "horizontal");
					}
				}

				// if not the last row, check the bottom wall
				if (i < length - 1) {
					// get the character representing the bottom wall
					char bottomWall = wallLine.charAt(j * 2);
					if (bottomWall != 'w') {
						// determine the coins cost to traverse the edge
						int coins = Character.isDigit(bottomWall) ? Character.getNumericValue(bottomWall) : 0;
						// insert an edge between the current node and the node below
						insertEdge(nodeID, nodeID + width, coins, "vertical");
					}
				}
			}
		}
	}

	private void insertEdge(int node1, int node2, int linkType, String label) throws GraphException {
		// get the graph nodes corresponding to the given node IDs
		GraphNode nodeU = graph.getNode(node1);
		GraphNode nodeV = graph.getNode(node2);
		// insert an edge between the two nodes with the specified link type and label
		graph.insertEdge(nodeU, nodeV, linkType, label);
	}

}

