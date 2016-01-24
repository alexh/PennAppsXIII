import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Runner {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Counselor c = new Counselor("First Question", "Tristrum", "Alex");
				c.setVisible(true);
			}
		});
	}
}
