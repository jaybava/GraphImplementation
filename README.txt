Maze Solver Project
This project implements a Maze Solver in Java using graph-based representations. The program reads maze definitions from text files, builds corresponding graphs, and attempts to solve the maze using Depth-First Search (DFS). It also includes visualization support using Java Swing.

Project Structure
.
├── Board.java           - Custom Swing component for drawing
├── DrawMaze.java        - Main class for visualizing the maze
├── Graph.java           - Graph implementation using an adjacency list
├── GraphADT.java        - Graph Abstract Data Type interface
├── GraphEdge.java       - Class representing edges in the graph
├── GraphException.java  - Custom exception for graph operations
├── GraphNode.java       - Class representing nodes in the graph
├── Maze.java            - Class for parsing and solving the maze
├── maze0.txt            - Sample maze definition file 0
└── maze1.txt            - Sample maze definition file 1

Files Overview
Board.java
A custom Swing component used for drawing parts of the maze.

DrawMaze.java
Provides a graphical interface for visualizing the maze.
Draws maze elements such as walls, doors, corridors, the start, and the exit points.
Reads maze definitions and supports graphical rendering of the solution path.

Graph.java
Implements the graph data structure using an adjacency list.
Provides methods to:
Insert edges between nodes.
Check adjacency between nodes.
Retrieve nodes and edges.

GraphADT.java
Defines the abstract methods for the graph operations:
insertEdge
getNode
incidentEdges
getEdge
areAdjacent

GraphEdge.java
Represents an edge between two nodes.
Stores edge information such as:
Endpoints (GraphNode).
Edge type.
Label.

GraphNode.java
Represents a node in the graph.
Contains:
Node ID (name).
mark field for traversal state (used in DFS).

Maze.java
Reads maze input files and constructs the corresponding graph.
Implements Depth-First Search (DFS) to find a path from the start to the exit.
Handles coins required to pass through certain doors.

GraphException.java
Custom exception class for handling errors related to graph operations.

Sample Maze Files
maze0.txt:

70
4
3
4
s1owo1o
cwcwcwc
o2o3oco
ww4wcwc
ococx3o

maze1.txt:

80
5
3
0
scococo8o
cwcw1w2w3
oco4ococo
5wcwcwcw6
o9oco7x0o

How to Run the Project
Compile the Java files:

javac *.java

Run the Maze Solver:

java Solve maze0.txt

Replace maze0.txt with any other maze definition file.

How the Program Works
Maze Parsing:
The maze is read from a .txt file containing:
Room size, width, and length of the maze.
Characters representing different elements (e.g., walls, corridors, start, exit).

Graph Construction:
The maze is represented as a graph where:
Rooms are nodes.
Passages and doors are edges.

Depth-First Search (DFS):
The DFS algorithm explores paths from the start node to the exit node, avoiding cycles and considering coin requirements.

Visualization:
The maze and the solution path are displayed using Java Swing.

Maze Elements
s: Start
x: Exit
w: Wall
c: Corridor
Numbers (e.g., 1, 2): Doors requiring specific coins
