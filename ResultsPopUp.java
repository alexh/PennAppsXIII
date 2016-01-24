import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ResultsPopUp extends JFrame {
	
	String topNoun;
	

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
	
	public String getNextQuestion(){
		String[] questions = {"Have you ever considered having an affair?", 
				"What Issues Do You Think Are Affecting Our Marriage the Most?", 
				"Are We Going Through a Bad Phase?", 
			"How Do You Truly Feel About the Relationship?", "What Bothers You Most About Me?", 
			"What Kind of Love Do You Feel?", "Do You Trust Me?", 
			"Are You Satisfied With Our Intimacy?", "What Are the Reasons You Want to Work Things Out?", 
			"Are There Any Past Conflicts We Should Resolve?", 
			"Do you Feel You Can Communicate With Me?", 
			"Do You Feel Accepted?", "How Do You See our Future?", "Describe the last date you and your partner went on.",
			"Describe the last time your partner cooked for you.",
			"What is your biggest fear in this relationship?",
			"What are the strengths in this relationship?",
			"Can you describe your first date with your partner?"};
		
		String[] nounQuestions = {"How do you feel about " + topNoun + " in regards to your relationship?",
				"Do you agree that " + topNoun + " has an effect on your relationship?", "Talk about " + topNoun,
				"Why do you think " + topNoun + " is affecting your relationship?"};
		
		if (topNoun != ""){
			if(Math.random() > .4){
				Random r = new Random();
				int i1 = r.nextInt(nounQuestions.length);
				return nounQuestions[i1];
			}
		}
		Random r = new Random();
		int i2 = r.nextInt(questions.length);
		return questions[i2];
	}

}
