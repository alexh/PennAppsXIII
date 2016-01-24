import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ResultsPopUp extends JFrame {

	private ImageIcon background = new ImageIcon(getClass().getResource("images/AnalysisBG.png"));

	public ResultsPopUp(String transcript, String PrevQ, double value, String[] words) {
		super("Results");
		this.setTitle("Question Results");
		RelativeLayout r = new RelativeLayout(RelativeLayout.Y_AXIS);
		setLayout(r);

		JLabel contentPane = new JLabel();
		contentPane.setIcon(background);
		this.setContentPane(contentPane);
		contentPane.setLayout(new RelativeLayout(RelativeLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(1000, 700));
		this.setMinimumSize(new Dimension(1000, 700));
		this.setMaximumSize(new Dimension(1000, 700));

		String sign;
		String signVerb;
		if (value < 0) {
			sign = "Negative";
			signVerb = "avoided";
		} else {
			sign = "Positive";
			signVerb = "discussed more";
		}
		JLabel score = new JLabel("" + (int) (value * 100.0) + "% " + sign + " Response");
		score.setFont(new Font("BiauKai", Font.BOLD, 30));
		if (value < 0) {
			score.setForeground(Color.RED);
		} else if (value > 0) {
			score.setForeground(Color.GREEN);
		} else {
			score.setForeground(Color.WHITE);
		}
		
		JLabel gap1 = new JLabel();
		gap1.setPreferredSize(new Dimension(200,200));
		add(gap1);
		add(score);
		
		String word1 = "";
		String word2 = "";
		String word3 = "";
		String word4 = "";
		if (words.length > 0){
		word1 = words[0].substring(0, words[0].indexOf('_'));
		if (words.length > 1){
		word2 = words[1].substring(0, words[1].indexOf('_'));
		if (words.length > 2){
		word3 = words[2].substring(0, words[2].indexOf('_'));
		if (words.length > 3){
		word4 = words[3].substring(0, words[3].indexOf('_'));
		}}}}
		String topNoun = "";
		String key = "_NN";
		for(String s : words){
			if(s.contains(key)){
				topNoun = s.substring(0, s.indexOf('_'));
			}
		}
		
		

		JLabel keywords = new JLabel("Top Keywords In Response: " + word1 + ", "
		+ word2 + ", " + word3 + ", " + word4);
		keywords.setFont(new Font("BiauKai", Font.PLAIN, 20));
		keywords.setForeground(Color.WHITE);
		JLabel gap = new JLabel();
		gap.setPreferredSize(new Dimension(40,40));
		add(gap);
		add(keywords);
		
		JLabel gap2 = new JLabel();
		gap2.setPreferredSize(new Dimension(40,40));
		add(gap2);
		
		if(topNoun != ""){
			JLabel nounSentence = new JLabel("It looks like talking about " + topNoun +
					" makes you think about your partner in a "+ sign.toLowerCase());
			nounSentence.setFont(new Font("BiauKai", Font.PLAIN, 20));
			nounSentence.setForeground(Color.WHITE);
			JLabel nounSentence2 = new JLabel(" way, this topic should be " + signVerb + " in the future" );
			nounSentence2.setFont(new Font("BiauKai", Font.PLAIN, 20));
			nounSentence2.setForeground(Color.WHITE);
			add(nounSentence);
			add(nounSentence2);
		}
		
	}

}
