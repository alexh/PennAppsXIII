//FinalResults.java
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FinalResults extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon background = new ImageIcon(getClass().getResource("images/AnalysisBG.png"));

	public FinalResults(String text1, String text2, String name1, String name2) {
		super("Results");
		this.setTitle("Results");
		RelativeLayout r = new RelativeLayout(RelativeLayout.Y_AXIS);
		setLayout(r);

		JLabel contentPane = new JLabel();
		contentPane.setIcon(background);
		this.setContentPane(contentPane);
		contentPane.setLayout(r);
		contentPane.setLayout(new RelativeLayout(RelativeLayout.X_AXIS));
		this.setPreferredSize(new Dimension(1000, 700));
		this.setMinimumSize(new Dimension(1000, 700));
		this.setMaximumSize(new Dimension(1000, 700));

		// Add Items after this
		
		//Person1 Results
		JPanel person1 = new JPanel();
		RelativeLayout r1 = new RelativeLayout(RelativeLayout.Y_AXIS);
		person1.setOpaque(false);
		person1.setLayout(r1);
		
		//Person2 Results
		JPanel person2 = new JPanel();
		RelativeLayout r2 = new RelativeLayout(RelativeLayout.Y_AXIS);
		person2.setOpaque(false);
		person2.setLayout(r2);
		
		//Example Item
		
		FinalAnalysis dude1 = new FinalAnalysis(text1);
		String[] args = dude1.needs;
		double[] argu = dude1.needs_score;
		
		
		FinalAnalysis dude2 = new FinalAnalysis(text2);
		String[] rags = dude2.needs;
		double[] ragu = dude2.needs_score;
		
		
		JLabel keywords = new JLabel("What " + name1 + " may need:");
		keywords.setFont(new Font("BiauKai", Font.BOLD, 30));
		keywords.setForeground(Color.WHITE);
		JLabel answer1 = new JLabel((int)(100.0 *argu[0])+ "% chance "+ name1+ " needs " + args[0]);
		answer1.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer1.setForeground(Color.WHITE);
		JLabel answer2 = new JLabel((int)(100.0 *argu[1])+ "% chance "+ name1+ " needs " + args[1]);
		answer2.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer2.setForeground(Color.WHITE);
		JLabel answer3 = new JLabel((int)(100.0 *argu[2])+ "% chance "+ name1+ " needs " + args[2]);
		answer3.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer3.setForeground(Color.WHITE);
		
		JLabel keywords2 = new JLabel("What " + name2 + " may need:");
		keywords2.setFont(new Font("BiauKai", Font.BOLD, 30));
		keywords2.setForeground(Color.WHITE);
		JLabel answer4 = new JLabel((int)(100.0 *ragu[0])+ "% chance "+ name2+ " needs " + rags[0]);
		answer4.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer4.setForeground(Color.WHITE);
		JLabel answer5 = new JLabel((int)(100.0 *ragu[1])+ "% chance "+ name2+ " needs " + rags[1]);
		answer5.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer5.setForeground(Color.WHITE);
		JLabel answer6 = new JLabel((int)(100.0 *ragu[2])+ "% chance "+ name2+ " needs " + rags[2]);
		answer6.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer6.setForeground(Color.WHITE);
		
		
		JLabel gap1 = new JLabel();
		gap1.setPreferredSize(new Dimension(50, 50));
		JLabel gap2 = new JLabel();
		gap2.setPreferredSize(new Dimension(50, 50));
		JLabel gap = new JLabel();
		gap.setPreferredSize(new Dimension(50, 50));
		
		person1.add(keywords);
		person1.add(answer1);
		person1.add(answer2);
		person1.add(answer3);
		
		person2.add(keywords2);
		person2.add(answer4);
		person2.add(answer5);
		person2.add(answer6);
		
		add(Box.createGlue(), new Float(1));
		add(person1, new Float(3));
		add(Box.createGlue(), new Float(1));
		add(person2, new Float(3));
		add(Box.createGlue(), new Float(1));
		
		//Add new here
		//use person1.add() and person2.add()

	}


}
