//TARASIDOU ANNA 
package maze;

public class Node implements Comparable<Node> {
    private Cell cell;
    private Node parent;
    private int gCost; 
    private int hCost;
    private int fCost; 

    public Node(Cell cell, Node parent, int gCost, int hCost) {
        this.cell = cell;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
    }

	@Override
    public int compareTo(Node other) {
        if (this.fCost != other.fCost)
            return Integer.compare(this.fCost, other.fCost);
        return Integer.compare(this.hCost, other.hCost); 
    }
	
	public Cell getCell() {
		return cell;
	}
	
	public Node getParent() {
		return parent;
	}

	public int getGCost() {
		return gCost;
	}

	public int getFCost() {
		return gCost + hCost;
	}
}