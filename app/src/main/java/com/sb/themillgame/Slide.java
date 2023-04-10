package com.sb.themillgame;

import java.util.ArrayList;

/**
 * @author l
 *
 * Slide Class
 * Provides check and execution of sliding token moves
 */
public class Slide extends AbstractMove {	
	//The following numbering is based on number-pad location of numbers.
	//Increase of usability quality aspect.

	final static int[][] neighbours = {{1,9}, {0,2,4}, {1,14}, {4,10}, {1,3,5,7}, {4,13}, {7,11}, {4,6,8}, {7,12}, {0,10,21}, {3,9,11,18}, {6,10,15},
			{8,13,17},{5,12,14,20},{2,13,23},{11,16}, {15,17,19}, {12,16}, {10,19}, {16,18,20,22},{13,19},{9,22}, {19,21,23},{14,22}
	};

	/*final int UP = 2;
	final int LEFT = 4;
	final int RIGHT = 6;
	final int DOWN = 8; */
	
	//check if the slide is possible
	/*public boolean check(int src, int direction){
		if ( Board.getInstance().minHouseId <= src && src <= Board.getInstance().maxHouseId){
			source = Board.getInstance().getHouses().get(src);
			
			if (Board.getInstance().getHouses().get(src).getMan().getColor() 
					!= Game.getInstance().currentTurn.getColour()){
				System.out.println("Choose a house that belongs to you.");
				return false;
			}
			man = (Man) source.getMan();
			//or, change it to a house on one of the neighbors id
			switch(direction){
			case UP:
				destination = source.getUp();
				break;
			case LEFT:
				destination = source.getLeft();
				break;
			case RIGHT:
				destination = source.getRight();
				break;
			case DOWN:
				destination = source.getDown();
				break;
			default:
				//print out something is wrong
				System.out.println("Enter a valid direction.");
				return false;
			}
			
			if (destination!= null)
				if (destination.getMan().equals(Board.getInstance().nullman))
					return true;
				else
					System.out.println("Chosen destinationination is not empty.");
			else
				System.out.println("Chose an existing neighbour!");
			return false;
		}
		else
			System.out.println("The chosen destinationination is not on the board.");
		return false;
	} */
	
	//execute the move
	public void exec(){
		destination.setMan(man);
		man.setHouse(destination);
		source.setMan(Board.getInstance().nullman);
	}

	public boolean check(int src, int dst){
		source = Board.getInstance().getHouses().get(src);
		System.out.println("src slide " + source.toString());
		man = (Man) source.getMan();
		boolean response = false;
		for (int i= 0; i<neighbours[src].length && response == false; i++){
			if (neighbours[src][i]==dst){
				response = true;
				destination = Board.getInstance().getHouses().get(dst);
			}
		}
		return response ;
	}
}
