import javax.swing.JFrame;

public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MainWindow(int width, int height) {
		
		this.setSize(width, height);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
	    this.setTitle("Game of life");    
	    FieldPanel panel = new FieldPanel(width, height - 40, 80, 80);
	    add(panel);
	    
	}

}
