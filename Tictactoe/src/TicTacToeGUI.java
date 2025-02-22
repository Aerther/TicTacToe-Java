import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class TicTacToeGUI extends JFrame implements ActionListener {
	// Components
	private JLabel turnLabel;
	private JLabel xLabel;
	private JLabel oLabel;
	
	// Game Class
	private Game game = new Game();
	
	// ArrayList for Buttons
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	
	public TicTacToeGUI() {
		super("Tic-Tac-Toe");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setIconImage(ImageUtils.getImage("hashtag.png"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
		setLayout(null);
		getContentPane().setBackground(Constants.BACKGROUND_COLOR);
		
		
		createElements();
		updateTurn();
	}
	
	// Creates the elements in the window
	public void createElements() {
		// Turn Label
		turnLabel = new JLabel("");
		turnLabel.setOpaque(true);
		turnLabel.setHorizontalAlignment(SwingConstants.CENTER);
		turnLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		turnLabel.setForeground(Constants.LABELFOREGROUND);
		turnLabel.setBounds(Constants.WINDOW_WIDTH/2-100/2, 0, 100, 50);
		getContentPane().add(turnLabel);
		
		// X score
		xLabel = new JLabel("X: 0");
		xLabel.setOpaque(true);
		xLabel.setBackground(Constants.SCOREBACKGROUND);
		xLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		xLabel.setForeground(Constants.X_FOREGROUND);
		xLabel.setBounds(Constants.INICIAL_X, 40, 150, 70);
		getContentPane().add(xLabel);
		
		// O score
		oLabel = new JLabel("O: 0");
		oLabel.setOpaque(true);
		oLabel.setBackground(Constants.SCOREBACKGROUND);
		oLabel.setHorizontalAlignment(SwingConstants.CENTER);
		oLabel.setFont(new Font("Tahoma", Font.PLAIN, 40));
		oLabel.setForeground(Constants.O_FOREGROUND);
		oLabel.setBounds(Constants.INICIAL_X+2*(Constants.BUTTON_WIDTH+Constants.BUTTON_X_SEPARATION), 40, 150, 70);
		getContentPane().add(oLabel);
		
		// Restart Button
		JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(this);
		restartButton.setActionCommand("restart");
		restartButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		restartButton.setBackground(Constants.BUTTON_BACKGROUND);
		restartButton.setBounds(Constants.WINDOW_WIDTH/2-120/2, Constants.WINDOW_HEIGHT-35*2-20, 120, 35);
		getContentPane().add(restartButton);
		
		// Buttons Board
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				JButton btn = new JButton(" ");
				btn.setBackground(Constants.BUTTON_BACKGROUND);
				btn.addActionListener(this);
				btn.setActionCommand(row + ";" + col);
				btn.setFont(Constants.BUTTONFONT);
				
				btn.setBounds(Constants.INICIAL_X + col*(Constants.BUTTON_WIDTH+Constants.BUTTON_X_SEPARATION), 
						Constants.INICIAL_Y + row*(Constants.BUTTON_HEIGHT+Constants.BUTTON_Y_SEPARATION)+Constants.BUTTON_Y_LOWERED, 
						Constants.BUTTON_WIDTH, 
						Constants.BUTTON_HEIGHT);
				
				buttons.add(btn);
				getContentPane().add(btn);
			}
		}
	}
	
	// Update the turn label
	public void updateTurn() {
		if(game.getActivePlayer() == 0) {
			turnLabel.setText("X");
			turnLabel.setBackground(Constants.X_BACKGROUND);
		} else {
			turnLabel.setText("O");
			turnLabel.setBackground(Constants.O_BACKGROUND);
		}
		
		repaint();
		revalidate();
	}
	
	// Update the tictactoe board
	public void updateBoard() {
		int index = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				JButton button = buttons.get(index);
				String letter = game.getBoardPosition(row, col);
				
				button.setText(letter);
				index++;
				
				if(letter.equals("X")) {
					button.setForeground(Constants.X_FOREGROUND);
				} else if(letter.equals("O")) {
					button.setForeground(Constants.O_FOREGROUND);
				}
			}
		}
	}
	
	// Update the score
	public void updateScore() {
		xLabel.setText("X: " + game.getScore()[0]);
		oLabel.setText("O: " + game.getScore()[1]);
	}
	
	// Show a message on the screen
	public void showMessage(String text, String title, int type) {
		JOptionPane.showMessageDialog(null, text, title, type);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("restart")) {
			game.restartGame();
			game.restartScore();
		} else {
			String[] command = e.getActionCommand().split(";");
			
			int row = Integer.parseInt(command[0]);
			int col = Integer.parseInt(command[1]);
			
			if(game.isMoveValid(row, col)) {
				game.makeMove(row, col);
				
				updateBoard();

				if(game.isWinner()) {
					showMessage((game.getActivePlayer()+1) + "° Player Won", "We have a WINNER", JOptionPane.INFORMATION_MESSAGE);
					game.setRestartGame(true);
				} else if (game.isDrawn()) {
					showMessage("It's a Drawn", "We have a DRAWN", JOptionPane.INFORMATION_MESSAGE);
					game.setRestartGame(true);
				}
				
				game.updateGame();
				
				if(game.getRestartGame()) {
					int reply = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Continue...", JOptionPane.YES_NO_OPTION);
					
					if (reply == JOptionPane.NO_OPTION) System.exit(0);
					
					game.restartGame();
				}
			}
		}
		
		// Does all of the updates
		updateBoard();
		updateTurn();
		updateScore();
	}
}