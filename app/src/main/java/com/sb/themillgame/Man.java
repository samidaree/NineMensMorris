package com.sb.themillgame;

/**
 * @author l
 *
 * Man Class
 * Provides representation of physical Token object
 */
public class Man extends AbstractMan{

	public char getToken(){
		return color.toString().charAt(0);
	}
	public void setHouse(House h) {house = h;}
	
	public Man(Game.Color c){color=c;}

}
