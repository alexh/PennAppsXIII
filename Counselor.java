

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

/**
 * A Sound Recorder program in Java Swing.
 * @author www.codejava.net
 *
 */
public class Counselor extends JFrame implements ActionListener {
	
	
	
	

	private JButton buttonRecord = new JButton();
	private JLabel labelRecordTime = new JLabel("Record Time: 00:00:00");

	private SoundRecordingUtil recorder = new SoundRecordingUtil();
	private AudioPlayer player = new AudioPlayer();
	private Thread playbackThread;
	private RecordTimer timer;

	private boolean isRecording = false;

	private String saveFilePath;
	private String questionText;
	private String name1;
	private String name2;
	private String currentPlayerName;
	JLabel questionLabel = new JLabel("Hello! Press the microphone and say hello to me");
	JLabel name1Label = new JLabel(name1);
	JLabel name2Label = new JLabel(name2);

	// Icons used for buttons
	private ImageIcon iconRecord = new ImageIcon(getClass().getResource(
			"images/microphone.png"));
	private ImageIcon iconStop = new ImageIcon(getClass().getResource(
			"images/pause.png"));
	
	private ImageIcon background = new ImageIcon(getClass().getResource(
			"images/PennAppsBG.png"));

	public Counselor(String questionText, String name1, String name2) {
		super("Counselor");
		this.setTitle("Marriage Counselor");
		setLayout(new BorderLayout());
		this.questionText = questionText;
		this.name1 = name1;
		this.name2 = name2;
		
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setFont(new Font("BiauKai", Font.BOLD, 20));
		
		setQuestion(questionText);

		JLabel contentPane = new JLabel();
		contentPane.setIcon( background );
		contentPane.setLayout( new BorderLayout() );
		this.setContentPane( contentPane );

		buttonRecord.setFont(new Font("BiauKai", Font.BOLD, 14));
		buttonRecord.setIcon(iconRecord);
		buttonRecord.setOpaque(false);
		buttonRecord.setContentAreaFilled(false);
		buttonRecord.setBorderPainted(false);
		labelRecordTime.setFont(new Font("BiauKais", Font.BOLD, 12));
		labelRecordTime.setForeground(Color.WHITE);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setOpaque(false);
		add(centerPanel, BorderLayout.SOUTH);
		JPanel controls = new JPanel();
		controls.setLayout(new FlowLayout());
		JLabel rightbuffer = new  JLabel();
		rightbuffer.setPreferredSize(new Dimension(450,200));
		centerPanel.add(rightbuffer, BorderLayout.EAST);
		
		JLabel leftbuffer = new  JLabel();
		leftbuffer.setPreferredSize(new Dimension(450,200));
		centerPanel.add(leftbuffer, BorderLayout.WEST);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setOpaque(false);
		JLabel test1 = new JLabel();
		labelRecordTime.setPreferredSize(new Dimension(300,250));
		JPanel bcPanel = new JPanel();
		bcPanel.setOpaque(false);
		bcPanel.add(labelRecordTime);
		bottomPanel.add(bcPanel, BorderLayout.CENTER);
		name1Label = new JLabel(name1);
		 name2Label = new JLabel(name2);
		name1Label.setFont(new Font("BiauKai", Font.BOLD, 75));
		name2Label.setFont(new Font("BiauKai", Font.BOLD, 75));
		name1Label.setForeground(Color.GRAY);
		name2Label.setForeground(Color.GRAY);
		if(currentPlayerName == name1){
		name1Label.setForeground(Color.WHITE);
		}
		if(currentPlayerName == name2){
			name2Label.setForeground(Color.WHITE);
			}
		bottomPanel.add(name1Label, BorderLayout.WEST);
		bottomPanel.add(name2Label, BorderLayout.EAST);
		centerPanel.add(bottomPanel, BorderLayout.SOUTH);
		
		JPanel topPanel = new JPanel();
		topPanel.setPreferredSize(new Dimension(1200,200));
		topPanel.setOpaque(false);
		centerPanel.add(topPanel, BorderLayout.NORTH);
		topPanel.add(questionLabel, BorderLayout.CENTER);
	

		

		centerPanel.add(buttonRecord, BorderLayout.CENTER);

		buttonRecord.addActionListener(this);

		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	/**
	 * Handle click events on the buttons.
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton button = (JButton) event.getSource();
		if (button == buttonRecord) {
			if (!isRecording) {
				startRecording();
			} else {
				stopRecording();
				setQuestion("Fetch New Question");
			}

			}
		}


	/**
	 * Start recording sound, the time will count up.
	 */
	private void startRecording() {
		Thread recordThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					isRecording = true;
					buttonRecord.setIcon(iconStop);

					recorder.start();

				} catch (LineUnavailableException ex) {
					JOptionPane.showMessageDialog(Counselor.this,
							"Error", "Could not start recording sound!",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		recordThread.start();
		timer = new RecordTimer(labelRecordTime);
		timer.start();
	}

	/**
	 * Stop recording and save the sound into a WAV file
	 */
	private void stopRecording() {
		isRecording = false;
		try {
			timer.cancel();
			buttonRecord.setIcon(iconRecord);
			
			setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

			recorder.stop();

			setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			saveFile();

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(Counselor.this, "Error",
					"Error stopping sound recording!",
					JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		
		
	}

	/**
	 * Start playing back the sound.
	 */
	

	/**
	 * Save the recorded sound into a WAV file.
	 */
	private void saveFile() {
		JFileChooser fileChooser = new JFileChooser();
		FileFilter wavFilter = new FileFilter() {
			@Override
			public String getDescription() {
				return "Sound file (*.WAV)";
			}

			@Override
			public boolean accept(File file) {
				if (file.isDirectory()) {
					return true;
				} else {
					return file.getName().toLowerCase().endsWith(".wav");
				}
			}
		};

		fileChooser.setFileFilter(wavFilter);
		fileChooser.setAcceptAllFileFilterUsed(false);

		/*int userChoice = fileChooser.showSaveDialog(this);
		if (userChoice == JFileChooser.APPROVE_OPTION) {
			saveFilePath = fileChooser.getSelectedFile().getAbsolutePath();
			if (!saveFilePath.toLowerCase().endsWith(".wav")) {
				saveFilePath += ".wav";
			}
			*/

			File wavFile = new File("input.wav");
			wavFile.setWritable(true);
			

			try {
				recorder.save(wavFile);


			} catch (IOException ex) {
				JOptionPane.showMessageDialog(Counselor.this, "Error",
						"Error saving to sound file!",
						JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
	
	public void setQuestion(String q){
		if (currentPlayerName == name1 || currentPlayerName == null){
			currentPlayerName = name2;
			name2Label.setForeground(Color.WHITE);
			name1Label.setForeground(Color.GRAY);
		} else{
			currentPlayerName = name1;
			name1Label.setForeground(Color.WHITE);
			name2Label.setForeground(Color.GRAY);
		}
		
		questionLabel.setText(currentPlayerName + ", " +q);
		
	}
	
	public void analyze(){
		//send to tristrum's code textToSpeech();
		//open results pop up
		//set next question
		new ResultsPopUp().setVisible(true);
	}
	public String textToSpeech(){
		return HoundInputText.doInput("input.wav");
	}
	

	
	
	

}