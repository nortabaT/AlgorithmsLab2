import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import org.jgrapht.graph.WeightedMultigraph;

public class Main {
	
	public static int start;
	public static int end;
	public static int timeStart;
	public static int timeEnd;
	
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
		searchGraph(in, outputFile, g);
		
		outputFile.flush();
		outputFile.close();
	}

	private static void searchGraph(Scanner in, FileWriter outputFile, WeightedMultigraph<Integer, TimeEdge> g) throws IOException {
		// TODO Auto-generated method stub
		start = in.nextInt();
		end = in.nextInt();
		timeStart = in.nextInt();
		timeEnd = in.nextInt();
		
		outputFile.write(dfs(g));
	}
	
	private static String dfs(WeightedMultigraph<Integer, TimeEdge> g){
		Stack<Integer> stack = new Stack<Integer>();
		HashSet<Integer> visited = new HashSet<Integer>();
		Stack<Integer> timeStack = new Stack<Integer>();
		String solution = "";
		
		if(g.containsVertex(start)){
			stack.push(start);	// start our DFS
			visited.add(start);	// add this to visited node list
			timeStack.push(0);	// add start time 0
			System.out.println(start);
			solution += start + "\n";
			int time = timeStart;
			
			while(!stack.isEmpty()){
				int cur = stack.peek();
				Iterator<TimeEdge> it = g.edgesOf(cur).iterator();
				TimeEdge child = null;
				int otherVertex = 0;
				
				while(it.hasNext()){ 	// find next unvisited child
					child = it.next();
					otherVertex = child.getOtherVertex(cur);
					if(child.getTime() > timeEnd || visited.contains(otherVertex) || child.getTime() < time){ // check if adding this edge time is within our bounds
						child = null;
						continue;
					}else{
						break;	// valid node
					}
				}
					
				if(child != null){	
					visited.add(otherVertex);	// add this vertex to visited set
					stack.push(otherVertex);	// push this vertex to our DFS stack
					time = child.getTime();	// push current solution's time to stack
					System.out.println(child + " " + child.getTime());
					solution += child + " " + child.getTime() + "\n";
					
					if(otherVertex == end){
						System.out.println("SOLUTION: \n\n"+solution);
						return solution;
					}
				}else{
					stack.pop(); // backtrack
				}
			}
		}
		System.out.println("Could not find solution, exiting and writing 0 to file.");
		return "0";
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
			g.setEdgeWeight(curEdge, weight); // add weight to this edge
		}
		
		return g;
	}
}
