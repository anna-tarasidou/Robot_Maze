//TARASIDOU ANNA 
package solver;

import java.util.*;
import maze.Cell;

public class UCS{
	private List<Cell> path;
	private int cost;
	private int extensions;
	
	public UCS(List<Cell> path, int cost, int extensions){
		this.path = path;
		this.cost = cost;
		this.extensions = extensions;
	}
	
	public List<Cell> getPath() {
        return path;
    }
	
	public int getCost() {
        return cost;
    }

    public int getExtensions() {
        return extensions;
    }
}