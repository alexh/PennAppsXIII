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
	private ImageIcon background = new ImageIcon(getClass().getResource("man.jpg"));

	public FinalResults(String[] args, double[] argu, String[] rags, double[] ragu) {
		super("Results");
		this.setTitle("Final Results");
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
		
		JLabel keywords = new JLabel("Top Keywords In Response:");
		keywords.setFont(new Font("BiauKai", Font.PLAIN, 20));
		keywords.setForeground(Color.WHITE);
		JLabel answer1 = new JLabel(args[0] + ": " + argu[0]);
		answer1.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer1.setForeground(Color.WHITE);
		JLabel answer2 = new JLabel(args[1] + ": " + argu[1]);
		answer2.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer2.setForeground(Color.WHITE);
		JLabel answer3 = new JLabel(args[2] + ": " + argu[2]);
		answer3.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer3.setForeground(Color.WHITE);
		
		JLabel keywords2 = new JLabel("Top Keywords In Response:");
		keywords2.setFont(new Font("BiauKai", Font.PLAIN, 20));
		keywords2.setForeground(Color.WHITE);
		JLabel answer4 = new JLabel(rags[0] + ": " + ragu[0]);
		answer4.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer4.setForeground(Color.WHITE);
		JLabel answer5 = new JLabel(rags[1] + ": " + ragu[1]);
		answer5.setFont(new Font("BiauKai", Font.PLAIN, 20));
		answer5.setForeground(Color.WHITE);
		JLabel answer6 = new JLabel(rags[2] + ": " + ragu[2]);
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
	public static void main(String[] args) {
		
		FinalAnalysis man = new FinalAnalysis("man.txt");
		FinalAnalysis women = new FinalAnalysis("women.txt");
		
		String[] thiss = man.needs;

		double[] thatt = man.needs_score;
		
		String[] those = women.needs;

		double[] these = women.needs_score;
	
		
		FinalResults test = new FinalResults(thiss, thatt, those, these);
		test.setVisible(true);
	}

}
