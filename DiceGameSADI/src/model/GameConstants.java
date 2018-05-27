/**
 * 
 */
package model;

import java.awt.Color;
import java.util.regex.Pattern;

/**
 * @author Navod Bopitiya
 *
 */
public class GameConstants {
	
	public final static String PLAY_BUTTON_ACTION = "Play Game";
	public final static String ADD_PLAYER_ACTION ="Add Player";
	public final static String BET_BUTTON_ACTION ="Bet";
	public final static String ROLL_BUTTON_ACTION ="Roll";
	public static final Pattern PATTERN = Pattern.compile("^\\d+$");
	public static final String HOUSE_TEXT = "House";
	public static final String PLAYER_TEXT = "Player";
	public static final String DEFAULT_RESULT_STRING = "Bet and Roll";
	public static final String HOUSE_WON = "Lost";
	public static final String PLAYER_WON = "Won";
	public static final String DRAW = "Draw";
	public static final Color BG_BLUE = new Color(27, 91, 127);
	public final static int BET_ZERO = 0;

}
