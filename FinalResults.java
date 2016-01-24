import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinalResults extends JFrame {

	private ImageIcon background = new ImageIcon(getClass().getResource("images/AnalysisBG.png"));

	public FinalResults() {
		super("Results");
		this.setTitle("Final Results");
		RelativeLayout r = new RelativeLayout(RelativeLayout.Y_AXIS);
		setLayout(r);

		JLabel contentPane = new JLabel();
		contentPane.setIcon(background);
		this.setContentPane(contentPane);
		contentPane.setLayout(r);
		contentPane.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(1000, 700));
		this.setMinimumSize(new Dimension(1000, 700));
		this.setMaximumSize(new Dimension(1000, 700));

		// Add Items after this
		
		
		//Example Item
		JLabel keywords = new JLabel("Top Keywords In Response: " + "dog" + ", " + "cat" + 
				", " + "pig" + ", " + "squirrel");
		keywords.setFont(new Font("BiauKai", Font.PLAIN, 20));
		keywords.setForeground(Color.WHITE);
		
		//Example Spacer
		JLabel gap = new JLabel();
		gap.setPreferredSize(new Dimension(40, 40));
		add(gap);
		add(keywords);

	}

}
