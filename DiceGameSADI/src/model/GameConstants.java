/**
 * 
 */
package model;

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
	public final static int BET_ZERO = 0;

}
