import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class FieldPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int width;
	private int height;
	private int nrOfColumns;
	private int nrOfRows;
	private int cellWidth;
	private int cellHeight;
	private Cell[][] cell2DArray;
	private Random random = new Random();
	public final static int INTERVAL = 100;
	private Timer timer;
		
	
	// constructor	
	public FieldPanel (int width, int height, int nrOfColumns, int nrOfRows) {

		setWidth(width);
		setHeight(height);
		setNrOfColumns(nrOfColumns);
		setNrOfRows(nrOfRows);
		
		cell2DArray = new Cell [nrOfColumns][nrOfRows];
		setSize(width, height);
		cellWidth = getWidth() / nrOfColumns;
		cellHeight = getHeight() / nrOfRows;
		initCells();
		repaint();
				
		  ActionListener taskPerformer = new ActionListener() {
		      public void actionPerformed(ActionEvent evt) {
		    	  calculateCellStatus();
		    	  updateCellStatus();
		    	  repaint();
		      }
		  };
		  
		  timer = new Timer(INTERVAL, taskPerformer);
		  timer.start();
	}
	
	
	
	// getters and setters
	
	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		if (width <= 1) {
			this.width = 200; // set default value
		} else {
			this.width = width;
		}
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		if (height <= 1) {
			this.height = 200; // set default value
		} else {
			this.height = height;
		}
	}


	public int getNrOfColumns() {
		return nrOfColumns;
	}


	public void setNrOfColumns(int nrOfColumns) {
		if (nrOfColumns <= 1) {
			this.nrOfColumns = 10; // set default value
		} else {
			this.nrOfColumns = nrOfColumns;
		}
	}


	public int getNrOfRows() {
		return nrOfRows;
	}


	public void setNrOfRows(int nrOfRows) {
		if (nrOfRows <= 1) {
			this.nrOfRows = 10; // set default value
		} else {
			this.nrOfRows = nrOfRows;
		}
	}


	
	public void initCells () {		
		for (int i = 0; i < nrOfColumns; i++ ) {			
			for (int j = 0; j < nrOfRows; j++) {
				
				Cell newCell = new Cell();				
				
				if (random.nextInt(2) == 0) {
					newCell.setAlive(true);
				} else {
					newCell.setAlive(false);
				}				
				cell2DArray[i][j] = newCell;				
			}						
		}		
	}
	
	
	public void calculateCellStatus() {
		
		int sum;
			
		for (int i = 0; i < nrOfColumns; i++ ) {			
			for (int j = 0; j < nrOfRows; j++) {
				
				
				// count the alive neighbours
								
				sum = 0;
				
				// top left cell				
				if (getNeighbourCell(i - 1, j - 1).isAlive()) {					
					sum++;
				}
				
				
				// top center cell				
				if (getNeighbourCell(i, j - 1).isAlive()) {					
					sum++;
				}
				
				
				// top right cell				
				if (getNeighbourCell(i + 1, j - 1).isAlive()) {					
					sum++;
				}
				
				
				// left cell			
				if (getNeighbourCell(i - 1, j).isAlive()) {					
					sum++;
				}
				
				
				// right cell				
				if (getNeighbourCell(i + 1, j).isAlive()) {					
					sum++;
				}
				
				
				// bottom left cell				
				if (getNeighbourCell(i - 1, j + 1).isAlive()) {					
					sum++;
				}
				
				
				// bottom center cell				
				if (getNeighbourCell(i, j + 1).isAlive()) {					
					sum++;
				}
				
				
				// bottom right cell				
				if (getNeighbourCell(i + 1, j + 1).isAlive()) {					
					sum++;
				}
					
				
				// apply the rules of game of life
				
				if ((cell2DArray[i][j].isAlive()) && (sum < 2)) {
					cell2DArray[i][j].setAliveNext(false);
				}
				
				
				if ((cell2DArray[i][j].isAlive() && ((sum == 2) || (sum == 3)))) {
					cell2DArray[i][j].setAliveNext(true);
				}
				
				
				if (cell2DArray[i][j].isAlive() && (sum > 3)) {
					cell2DArray[i][j].setAliveNext(false);
				}
				
				
				if (!cell2DArray[i][j].isAlive() && (sum == 3)) {
					cell2DArray[i][j].setAliveNext(true);
				}				
			}						
		}			
	}
	

	
	public void updateCellStatus() {
		// replace the current value of each cell with the value of the next generation
		for (int i = 0; i < nrOfColumns; i++ ) {			
			for (int j = 0; j < nrOfRows; j++) {
				cell2DArray[i][j].setAlive(cell2DArray[i][j].isAliveNext());				
			}
		}		
	}
	
	
	private Cell getNeighbourCell (int column, int row) {

		if (column < 0) {
			// If we are at the left edge, the cell at the right edge becomes the left neighbour cell.
			column = nrOfColumns - 1;
		}
		
		if (column >= nrOfColumns) {
			// If we are at the right edge, the cell at the left edge becomes the right neighbour cell.
			column = 0;
		}
		
		if (row < 0) {
			// If we are at the bottom edge, the cell at the top edge becomes the bottom neighbour cell.
			row = nrOfRows - 1;
		}
		
		if (row >= nrOfRows) {
			// If we are at the top edge, the cell at the bottom edge becomes the top neighbour cell.
			row = 0;
		}
		
		return cell2DArray[column][row];		
	}
	
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color lightBlue = new Color(37, 114, 202);
       
		// paint background
		g.setColor(lightBlue);
		g.fillRect(0, 0, getWidth(), getHeight());
        
        for (int i = 0; i < nrOfColumns; i++ ) {
        	for (int j = 0; j < nrOfRows; j++) {
        		
        		if (cell2DArray[i][j].isAlive()) {
        			// paint alive cell
        			g.setColor(Color.white);
        			g.fillRect(i * cellWidth  + 1, j * cellHeight + 1, cellHeight - 2, cellHeight - 2);
        		} else {
        			// paint dead cell
        			g.setColor(lightBlue);
        			g.fillRect(i * cellWidth, j * cellHeight, cellWidth, cellHeight);
        		}      		
        	}      	
        }       
    }
	
}
