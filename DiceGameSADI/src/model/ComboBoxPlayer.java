/**
 * 
 */
package model;

import model.interfaces.Player;

/**
 * @author Navod Bopitiya
 *
 */
public class ComboBoxPlayer extends PlayerDecorator{

	public ComboBoxPlayer(Player player) {
		super(player);
	}
	
	@Override
	public String toString(){
		return this.getPlayerName();
	}

}
