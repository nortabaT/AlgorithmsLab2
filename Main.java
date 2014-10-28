import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jgrapht.graph.*;

public class Main {
	
	public static void main(String [] args) throws IOException{
		String inputPath = args[0];															// name of this input file
		String outputPath = args[0].substring(0, args[0].lastIndexOf('.')) + ".out";		// removing the ending file type and replacing it with .out (to signify it's an output of this input file)
		
		FileReader inputFile = new FileReader(new File(inputPath));
		FileWriter outputFile = new FileWriter(new File(outputPath));
		
		Scanner in = new Scanner(inputFile);
		
		int verts = in.nextInt();	// number of n (verts) in file
		int edges = in.nextInt();	// number of m (edges) in file
		
		setUpGraph(in, verts, edges);
		
		outputFile.flush();
		outputFile.close();
	}

	private static void setUpGraph(Scanner in, int v, int e) {
		for(int i = 0; i < v; i++){
			
		}
	}
}
