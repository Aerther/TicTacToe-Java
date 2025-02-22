import java.util.ArrayList;

public class Game {
	private final int[][][] casesWin = {
			{{0, 0}, {0, 1}, {0, 2}},
			{{1, 0}, {1, 1}, {1, 2}},
			{{2, 0}, {2, 1}, {2, 2}},
			{{0, 0}, {1, 0}, {2, 0}},
			{{0, 1}, {1, 1}, {2, 1}},
			{{0, 2}, {1, 2}, {2, 2}},
			{{0, 0}, {1, 1}, {2, 2}},
			{{0, 2}, {1, 1}, {2, 0}}
			};
	private final String[] letters = {"X", "O"};
	private int[] score = {0, 0};
	private int activePlayer = 0;
	private int startPlayer = 0;
	private int turns = 0;
	private boolean restartGame = false;
	
	private ArrayList<ArrayList<String>> board = new ArrayList<>();
	
	public Game() {
		this.initializeBoard();
	}
	
	// Updates the active player and the turns
	public void updateGame() {
		this.activePlayer = (this.activePlayer == 0) ? 1 : 0;
		this.turns++;
	}
	
	// Initialize every space of the board with a space
	private void initializeBoard() {
		this.board.clear();
		for (int i = 0; i < 3; i++) {
			ArrayList<String> row = new ArrayList<>();
			
			for (int j = 0; j < 3; j++) {
				row.add(" ");
			}
			
			board.add(row);
		}
	}
	
	// Checks for a win
	public boolean isWinner() {
		int countLetters;
		for (int i = 0; i < this.casesWin.length; i++) {
			countLetters = 0;
			for (int j = 0; j < this.casesWin[i].length; j++) {
				int[] cases = this.casesWin[i][j];
				if (this.board.get(cases[0]).get(cases[1]).equals(this.letters[this.activePlayer])) {
					countLetters++;
				}
				
			}
			
			if (countLetters == 3) {
				this.score[this.activePlayer]++;
				return true;
			}
		}
		
		return false;
	}
	
	// Checks for a drawn
	public boolean isDrawn() {
		return this.turns == 8;
	}
	
	// Makes a move into the board
	public void makeMove(int row, int col) {
		this.board.get(row).set(col, this.letters[this.activePlayer]);	
	}
	
	// Checks if the move is valid
	public boolean isMoveValid(int row, int col) {
		return this.board.get(row).get(col).equals(" ");
	}
	
	// Get the char that is inside a position in the board
	public String getBoardPosition(int row, int col) {
		return this.board.get(row).get(col);
	}
	
	// Restart all of the game
	public void restartGame() {
		this.initializeBoard();
		this.restartGame = false;
		this.startPlayer = (this.startPlayer == 0) ? 1 : 0;
		this.activePlayer = this.startPlayer; // If activePlayer is 0 it changes to 1 and vice-versa
		this.turns = 0;
	}
	
	// Restart the score
	public void restartScore() {
		int[] restartScore = {0, 0};
		this.score = restartScore;
	}
	
	// Getters and Setters
	public void setActivePlayer(int num) {
		this.activePlayer = num;
	}
	
	public int getActivePlayer() {
		return this.activePlayer;
	}

	public int[] getScore() {
		return this.score;
	}
	
	public void setRestartGame(boolean bool) {
		this.restartGame = bool;
	}
	
	public boolean getRestartGame() {
		return this.restartGame;
	}
}
