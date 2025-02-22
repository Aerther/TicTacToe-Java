import java.awt.Color;
import java.awt.Font;

public class Constants {
	// Window constants
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	
	// Colors constants
	public static final Color BACKGROUND_COLOR = Color.decode("#ffa2b5");
	public static final Color BUTTON_BACKGROUND = Color.decode("#fbf1f9");
	public static final Color O_FOREGROUND = Color.decode("#0000ff");
	public static final Color X_FOREGROUND = Color.decode("#ff0000");
	public static final Color O_BACKGROUND = Color.decode("#0000ff");
	public static final Color X_BACKGROUND = Color.decode("#ff0000");
	public static final Color LABELFOREGROUND = Color.decode("#ffffff");
	public static final Color SCOREBACKGROUND = Color.decode("#fdd6ff");
	
	// Font constants
	public static final Font BUTTONFONT = new Font("Tahoma", Font.PLAIN, 160);
	
	// Buttons(board) constants
	public static final int BUTTON_WIDTH = 150;
	public static final int BUTTON_HEIGHT = 150;
	public static final int BUTTON_X_SEPARATION = 10;
	public static final int BUTTON_Y_SEPARATION = 10;
	public static final int BUTTON_Y_LOWERED = 10;
	public static final int INICIAL_X = WINDOW_WIDTH/2-BUTTON_WIDTH/2-BUTTON_WIDTH-BUTTON_X_SEPARATION;
	public static final int INICIAL_Y = WINDOW_HEIGHT/2-BUTTON_HEIGHT/2-BUTTON_HEIGHT-BUTTON_Y_SEPARATION;
}
