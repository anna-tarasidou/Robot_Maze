//TARASIDOU ANNA 
package maze;

public class Cell{
	private int x, y;
	private boolean isObstacle;
	
	public Cell(int x, int y, boolean isObstacle){
		this.x = x;
		this.y = y;
		this.isObstacle = isObstacle;		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public boolean getObstacle(){
		return isObstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.isObstacle = obstacle;
	}
	
	public String toString(){
		return "EIMAI CELL";
	}
}
