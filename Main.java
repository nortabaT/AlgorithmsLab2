import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.GraphPathImpl;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.traverse.DepthFirstIterator;

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
		
		WeightedMultigraph<Integer, DefaultWeightedEdge> g = setUpGraph(in, verts, edges);	// set up graph
		System.out.println(g);
		searchGraph(in, g);
		
		outputFile.flush();
		outputFile.close();
	}

	private static void searchGraph(Scanner in, WeightedMultigraph<Integer, DefaultWeightedEdge> g) {
		// TODO Auto-generated method stub
		int start = in.nextInt();
		int end = in.nextInt();
		int timeStart = in.nextInt();
		int timeFin = in.nextInt();
		
		DepthFirstIterator<Integer, DefaultWeightedEdge> i = new DepthFirstIterator<Integer, DefaultWeightedEdge>(g, start);
		while(i.hasNext()){
			System.out.print(i.next() + " ");
		}
		System.out.println("\n" + start + " " + end + " " + timeStart + " " + timeFin);
	}
	
	private static void dfs(WeightedMultigraph<Integer, DefaultWeightedEdge> g, int start, int end, int timeStart, int timeEnd){
		
	}

	private static WeightedMultigraph<Integer, DefaultWeightedEdge> setUpGraph(Scanner in, int v, int e) {
		WeightedMultigraph<Integer, DefaultWeightedEdge> g = new WeightedMultigraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
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
			DefaultWeightedEdge curEdge = g.addEdge(firstV, secondV);	// add edge to graph
			g.setEdgeWeight(curEdge, weight); 							// add weight to this edge
		}
		
		return g;
	}
}
