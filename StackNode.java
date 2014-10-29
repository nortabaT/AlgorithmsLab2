public class StackNode{
	public int val;
	public int depth;
	public int time;
	
	public StackNode(int val, int depth){
		this.val = val;
		this.depth = depth;
		this.time = 0;
	}
	
	public void setTime(int time){
		this.time = time;
	}
}