/**
 * 
 */
package model;

import model.interfaces.DicePair;

/**
 * @author Navod Bopitiya
 *
 */
public class DicePairImpl implements DicePair {
	
	private int dice1, dice2, numFaces;
	
	public DicePairImpl(int dice1, int dice2, int numFaces){
		this.dice1 = dice1;
		this.dice2 = dice2;
		this.numFaces = numFaces;
	}

	@Override
	public int getDice1() {
		//Returns value of Dice One
		return this.dice1;
	}

	@Override
	public int getDice2() {
		//Returns value of Dice Two
		return this.dice2;
	}

	@Override
	public int getNumFaces() {
		//Returns number of faces of the die
		return this.numFaces;
	}
	
	public String toString(){
		//Returns a human readable string
		int total = dice1 + dice2;
		return "Dice 1: "+dice1+",  Dice 2: "+dice2+" .. Total: "+total;
	}

}
