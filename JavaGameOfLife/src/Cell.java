
public class Cell {
	
	private boolean alive;		// the state of the cell in the current generation
	private boolean aliveNext;	// the state of the cell in the next generation


	// constructor
	public Cell () {
		alive = false;
	}
			
	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public boolean isAliveNext() {
		return aliveNext;
	}

	public void setAliveNext(boolean aliveNext) {
		this.aliveNext = aliveNext;
	}
	

}
