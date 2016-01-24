import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ResultsPopUp extends JFrame{
	
	private ImageIcon background = new ImageIcon(getClass().getResource(
			"images/AnalysisBG.png"));
	
	
	public ResultsPopUp(){
		super("Results");
		this.setTitle("Question Results");
		setLayout(new BorderLayout());
		
		JLabel contentPane = new JLabel();
		contentPane.setIcon( background );
		contentPane.setLayout( new BorderLayout() );
		this.setContentPane( contentPane );
		
		this.setVisible(true);
	}
	
	

}
