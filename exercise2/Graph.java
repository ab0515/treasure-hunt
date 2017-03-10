package exercise2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import stack.*;

public class Graph<T> implements GraphInterface<T> {
	public static final int DEF_CAPACITY = 10;
	public static final int NULL_EDGE = 0;
	public static final int DEFAULT_WEIGHT = 1;
	private int numVertices;
	private int maxVertices;
	private T[] vertices;
	private int[][] edges;
	private boolean[] marks; // marks[i] is mark for vertices[i]

	public Graph() {
		numVertices = 0;
		maxVertices = DEF_CAPACITY;
		vertices = (T[]) new Object[DEF_CAPACITY];
		marks = new boolean[DEF_CAPACITY];
		edges = new int[DEF_CAPACITY][DEF_CAPACITY];
	}
	
	public Graph(int maxV)
	// Instantiates a graph with capacity maxV.
	{
		numVertices = 0;
		maxVertices = maxV;
		vertices = (T[]) new Object[maxV];
		marks = new boolean[maxV];
		edges = new int[maxV][maxV];
	}

	public boolean isEmpty()
	// Returns true if this graph is empty; otherwise, returns false.
	{
		return (numVertices == 0);
	}

	public boolean isFull()
	// Returns true if this graph is full; otherwise, returns false.
	{
		return (numVertices == maxVertices);
	}

	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks
	// A quality implementation gets up to 2 marks
	public void addVertex(T vertex) throws GraphIsFullException,
			VertexExistsException {
		// If graph is full, it throws GraphIsFullException
		// If vertex is already in this graph, it throws VertexExistsException
		// Otherwise adds vertex to this graph.
		//
		// Your code goes here
		if (this.isFull()) 
			throw new GraphIsFullException("Graph is Full!");
		
		else {
			if (vertex != null) {
				if (checkVertex(vertex))  
					throw new VertexExistsException("Given vertex already exists in Graph");
				else {
					int index = -1;
					for (int i=0; i<vertices.length; i++) 
						if (vertices[i] != null)
							index = i;	
					vertices[index+1] = vertex;	// put into next empty slot
				}
			}
		}
	}
	
	// helper method
	public boolean checkVertex(T vert) {
		//Check whether a vertex is already in graph 
		if (vert == null) 
			return false;
		else {
			for (int i = 0; i < vertices.length; i++) 
				if (vertices[i] != null && vertices[i].equals(vert)) // vertex exists in Graph
					return true;	
		return false;
		}
	}
	
	// helper method
	public int findVIndex(T vert) {
		// Return an index of a given vertex
		int index = -5;
		if (vert != null) 
			for (int i = 0; i < vertices.length; i++) 
				if (vertices[i] != null && vertices[i].equals(vert)) // vertex exists in Graph
					index = i;
		return index;
	}
	
	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks
	// A quality implementation gets up to 2 marks
	public void addEdge(T fromVertex, T toVertex)
	// Adds an edge with the specified weight from fromVertex to toVertex.
	{
         // Your code goes here
		if (checkVertex(fromVertex) && checkVertex(toVertex)) {
			int fromV = findVIndex(fromVertex);
			int toV = findVIndex(toVertex);
			if ((fromV+toV) >= 0 && fromV != toV) {
				edges[fromV][toV] = 1;
				edges[toV][fromV] = 1;
			}
		} 
	} 

	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks
	public Queue<T> getToVertices(T vertex)
	// Returns a queue of the vertices that are adjacent from vertex.
	{
		// Your code goes here
		Queue<T> result = new LinkedList<T>();
		if (vertex != null) {
			int index = findVIndex(vertex);
			for (int i = vertices.length - 1; i >= 0; i--) 
				if (edges[index][i] == 1) // two vertices connected by an edge
					result.add(vertices[i]);
		}
		return result;
	}

	public void clearMarks()
	// Sets marks for all vertices to false.
	{
		for (int i = 0; i < numVertices; i++)
			marks[i] = false;
	}

	// Please complete the code for the following method.
	// A correctly working method gets up to 1 mark

	public void markVertex(T vertex)
	// Sets mark for vertex to true.
	{
		// Your code goes here
		if (vertex != null) {
			int index = findVIndex(vertex);
			marks[index] = true;
		}
	}

	// Please complete the code for the following method.
	// A correctly working method gets up to 1 mark

	public boolean isMarked(T vertex)
	// Returns true if vertex is marked; otherwise, returns false.
	{
		// Your code goes here
		if (vertex != null) {
			int i = findVIndex(vertex);
			if (marks[i])
				return true;
		}
		return false;
	}

	// Please complete the code for the following method.
	// A correctly working method gets up to 5 marks
	// A quality implementation gets up to 3 marks
	private Set<T> DFSVisit(T startVertex)
	// Uses depth-first search algorithm to visit as much vertices as
	// possible
	// starting from startVertex.
	// In the process, keeps track of all vertices reachable from
	// startVertex
	// by adding them to a Set<T> variable.
	// Once done, returns the Set<T> that we build up.
	//
	{
		// Your code goes here
		Set<T> result = new HashSet<T>();
		LinkedStack<T> track = new LinkedStack<T>();
		if (startVertex != null) {
			track.push(startVertex);
			while (!track.isEmpty()) {
				T cur = track.top();
				track.pop();
				if (!isMarked(cur)) {
					markVertex(cur);
					result.add(cur);
					Queue<T> neighbors = getToVertices(cur);
					while (!neighbors.isEmpty())
						track.push(neighbors.remove());
				}		
			}
		}
		return result;
	}

	// Please complete the code for the following method.
	// A correctly working method gets up to 2 marks
	// A quality implementation gets up to 2 marks
	public ArrayList<Set<T>> connectedComponents()
	// Returns a list of connected components of the graph
	// For each vertex that does not belong to the connected components
	// already on the list,
	// call DFSVisit to obtain a set of vertices connected to the current
	// vertex
	// Add the set to the list
	{
		// Your code goes here
		ArrayList<Set<T>> result = new ArrayList<Set<T>>();
		for (int i = 0; i < vertices.length; i++) 
			if (vertices[i] != null && !isMarked(vertices[i]))
				result.add(DFSVisit(vertices[i]));
			
		return result;
	}
}