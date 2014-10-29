import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.jgrapht.graph.WeightedMultigraph;

public class Main {
	
	public static void main(String [] args) throws IOException{
		String inputPath = args[0];															// name of this input file
		String outputPath = args[0].substring(0, args[0].lastIndexOf('.')) + ".out";		// removing the ending file type and replacing it with .out (to signify it's an output of this input file)
		
		FileReader inputFile = new FileReader(new File(inputPath));
		FileWriter outputFile = new FileWriter(new File(outputPath));
		
		Scanner in = new Scanner(inputFile);
		
		int verts = in.nextInt();	// number of n (verts) in file
		int edges = in.nextInt();	// number of m (edges) in file
		in.nextLine();
		
		WeightedMultigraph<Integer, TimeEdge> g = setUpGraph(in, verts, edges);	// set up graph
		searchGraph(in, g);
		
		outputFile.flush();
		outputFile.close();
	}

	private static void searchGraph(Scanner in, WeightedMultigraph<Integer, TimeEdge> g) {
		// TODO Auto-generated method stub
		int start = in.nextInt();
		int end = in.nextInt();
		int timeStart = in.nextInt();
		int timeFin = in.nextInt();
		
		dfs(g, start, end, timeStart, timeFin);
	}
	
	private static void dfs(WeightedMultigraph<Integer, TimeEdge> g, int start, int end, int timeStart, int timeEnd){
		Stack<Integer> stack = new Stack<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<TimeEdge> solution = new Stack<TimeEdge>();
		
		if(g.containsVertex(start)){
			stack.push(start);		// start our DFS
			visited.add(start);		// add this to visited node list
			
			while(!stack.isEmpty()){
				int cur = stack.peek();
				Iterator<TimeEdge> it = g.edgesOf(cur).iterator();
				
				while(it.hasNext()){
					TimeEdge childEdge = it.next();
					// TODO: filter edges by time, and keep track of what time it is
					stack.push(childEdge.getOtherVertex(cur));	// push this edge to our DFS stack
				}
			}
		}
	}

	private static WeightedMultigraph<Integer, TimeEdge> setUpGraph(Scanner in, int v, int e) {
		WeightedMultigraph<Integer, TimeEdge> g = new WeightedMultigraph<Integer, TimeEdge>(TimeEdge.class);
		
		for(int i = 0; i < e; i++){
			int firstV = in.nextInt();	// read input
			int secondV = in.nextInt();
			int weight = in.nextInt();
			
			if(firstV == secondV){
				break; // no loops allowed in this multigraph
			}
			
			if(in.hasNext()){
				in.nextLine();
			}
			
			g.addVertex(firstV);
			g.addVertex(secondV);
			TimeEdge curEdge = g.addEdge(firstV, secondV);	// add edge to graph
			g.setEdgeWeight(curEdge, weight); 							// add weight to this edge
		}
		
		return g;
	}
}
