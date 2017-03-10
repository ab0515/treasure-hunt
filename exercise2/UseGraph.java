package exercise2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.io.BufferedReader;

public class UseGraph {

	/**
	 * @param args
	 * @throws VertexExistsException
	 * @throws GraphIsFullException
	 */

	// Please complete the code for the following method.
	// A correctly working method gets up to 5 marks
	// A quality implementation gets up to 3 marks
	static Graph<String> loadGraph(String pathName)
			throws FileNotFoundException, GraphIsFullException,
			VertexExistsException
	// Loads a String graph from a text file formatted as follows:
	// N: some number -> max number of vertices
	// V: vertexname -> at most N lines for this format
	// E: vertex1, vertex2 -> each line gives an edge from vertex1 to vertex2
	// (vertex1, vertex2 must be strings)
	// Example:
	// N: 5
	// V: A
	// V: B
	// V: C
	// V: D
	// E: A,B
	// E: A,C
	// E: C,D
	// The loadGraph must create the following graph:
	//
	// A --- B
	// |
	// |
	// C --- D
	//
	// If the text file is empty or contains at least the N: ... line, the graph
	// must be empty (not null!)
	//
	{
		// Your code goes here
		String line = null;
		Graph<String> g1 = new Graph<String>();
		BufferedReader getData = null;
		try{
			getData = new BufferedReader(new FileReader(pathName));
			File file = new File(pathName);
			
			while ((line = getData.readLine()) != null) {
				line = line.replaceAll("\\s", ""); 
				if (file.length() <= 1) {
					return g1;
				}
				else {
					String[] lineData = line.split(":");
					if (lineData[0].equals("N"))
						g1 = new Graph<String>(Integer.parseInt(lineData[1]));
					else if (lineData[0].equals("V")) 
						g1.addVertex(lineData[1]);
					else if (lineData[0].equals("E")) {
						if (lineData[1].length() == 3) {
							String[] edges = lineData[1].split(",");
							g1.addEdge(edges[0], edges[1]);
						}
					}	
				}
			}
			
		} // try
		catch (FileNotFoundException e) {
			System.out.println("Given file is not Found! Please check your path name");
		}
		catch (GraphIsFullException e){
			System.out.println("Graph is full. No longer possible to add a vertex");
		}
		catch (VertexExistsException e){
			System.out.println("Given vertex is already in Graph!");
		}
		catch (IOException e){
			System.out.println(e);
		}
		finally {
			try {
			getData.close();
			}
			catch (IOException e){
				System.out.println(e);
			}
		}
		return g1;
	}

	public static void main(String[] args) throws FileNotFoundException,
			GraphIsFullException, VertexExistsException {
		// TODO Auto-generated method stub
		String pathname = args[0];
		Graph<String> g1 = loadGraph(pathname);
		System.out.println(g1.connectedComponents());
	}

}
