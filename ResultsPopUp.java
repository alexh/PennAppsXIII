import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ResultsPopUp extends JFrame{
	
	private ImageIcon background = new ImageIcon(getClass().getResource(
			"images/AnalysisBG.png"));
	
	
	public ResultsPopUp(String transcript, String PrevQ){
		super("Results");
		this.setTitle("Question Results");
		setLayout(new BorderLayout());
		
		JLabel contentPane = new JLabel();
		contentPane.setIcon( background );
		contentPane.setLayout( new BorderLayout() );
		this.setContentPane( contentPane );
		this.setPreferredSize(new Dimension(1000,700));
		this.setMinimumSize(new Dimension(1000, 700));
		this.setMaximumSize(new Dimension(1000, 700));
	}
	
	

}
