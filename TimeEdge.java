import org.jgrapht.graph.DefaultWeightedEdge;


@SuppressWarnings("serial")
public class TimeEdge extends DefaultWeightedEdge {
	
	public int getFirst(){
		return (int) this.getSource();
	}
	
	public int getSecond(){
		return (int) this.getTarget();
	}
	
	public int getTime(){
		return (int) this.getWeight();
	}
	
	public int getOtherVertex(int vertex){
		return (vertex == this.getFirst()) ? this.getSecond() : this.getFirst();
	}
	
	@Override
	public String toString(){
		return this.getFirst() + " " + this.getSecond();
	}

}
