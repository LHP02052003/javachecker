
package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.ComputerPlayer;
import model.HumanPlayer;

import model.Player;



public class OptionPanel extends JPanel {

	private static final long serialVersionUID = -4763875452164030755L;

	/** The checkers window to update when an option is changed. */
	private CheckersWindow window;
	
	/** The button that when clicked, restarts the game. */
	private JButton restartBtn;
	
	/** The combo box that changes what type of player player 1 is. */
	private JComboBox<String> player1Opts;
	

	
	/** The button to perform an action based on the type of player. */
	private JButton player1Btn;

	/** The combo box that changes what type of player player 2 is. */
	private JComboBox<String> player2Opts;


	
	/** The button to perform an action based on the type of player. */
	private JButton player2Btn;
	
	/**
	 * Creates a new option panel for the specified checkers window.
	 * 
	 * @param window	the window with the game of checkers to update.
	 */
	public OptionPanel(CheckersWindow window) {
		super(new GridLayout(0, 1));
		
		this.window = window;
		
		// Initialize the components
		OptionListener ol = new OptionListener();
		final String[] playerTypeOpts = {"Human", "Computer",};
		this.restartBtn = new JButton("Restart");
		this.player1Opts = new JComboBox<>(playerTypeOpts);
		this.player2Opts = new JComboBox<>(playerTypeOpts);
		this.restartBtn.addActionListener(ol);
		this.player1Opts.addActionListener(ol);
		this.player2Opts.addActionListener(ol);
		JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel middle = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.player1Btn = new JButton("Set Connection");
		this.player1Btn.addActionListener(ol);
		this.player1Btn.setVisible(false);
		this.player2Btn = new JButton("Set Connection");
		this.player2Btn.addActionListener(ol);
		this.player2Btn.setVisible(false);
		
		// Add components to the layout
		top.add(restartBtn);
		middle.add(new JLabel("(black) Player 1: "));
		middle.add(player1Opts);
		middle.add(player1Btn);
		bottom.add(new JLabel("(white) Player 2: "));
		bottom.add(player2Opts);
		bottom.add(player2Btn);
		this.add(top);
		this.add(middle);
		this.add(bottom);
	}

	public CheckersWindow getWindow() {
		return window;
	}

	public void setWindow(CheckersWindow window) {
		this.window = window;
	}
	
	
	private static Player getPlayer(JComboBox<String> playerOpts) {
		
		Player player = new HumanPlayer();
		if (playerOpts == null) {
			return player;
		}
		
		// Determine the type
		String type = "" + playerOpts.getSelectedItem();
		if (type.equals("Computer")) {
			player = new ComputerPlayer();
		} 
		
		
		return player;
	}
	

	private class OptionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			// No window to update
			if (window == null) {
				return;
			}
			
			Object src = e.getSource();

			// Handle the user action
			JButton btn = null;
			boolean isNetwork = false, isP1 = true;
			
			if (src == restartBtn) {
				window.restart();
				window.getBoard();
			} else if (src == player1Opts) {
				Player player = getPlayer(player1Opts);
				window.setPlayer1(player);
	
				btn = player1Btn;
			
			} else if (src == player2Opts) {
				Player player = getPlayer(player2Opts);
				window.setPlayer2(player);
		
				btn = player2Btn;
				
				isP1 = false;
			}
			}
		}
	}

