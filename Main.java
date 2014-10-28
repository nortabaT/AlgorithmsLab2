import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

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
		
		setUpGraph(in, verts, edges);
		
		outputFile.flush();
		outputFile.close();
	}

	private static void setUpGraph(Scanner in, int v, int e) {
		SimpleWeightedGraph<Integer, DefaultWeightedEdge> g = new SimpleWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		for(int i = 0; i < e; i++){
			int firstV = in.nextInt();	// read in input
			int secondV = in.nextInt();
			int weight = in.nextInt();
			
			if(in.hasNext()){
				in.nextLine();
			}
			
			g.addVertex(firstV);
			g.addVertex(secondV);
			DefaultWeightedEdge curEdge = g.addEdge(firstV, secondV);	// add edge to graph
			g.setEdgeWeight(curEdge, weight); 							// add weight to this edge
		}
		System.out.println(g);
	}
}
