package com.sb.themillgame;

public class EasyAI extends AbstractPlayer{
    public EasyAI(Game.Color c){
        color = c;
    }
    public int readInt(){
        boolean hasPlayed = false;
        int stroke = -1;
        for (int i = 0; i<Board.getInstance().getHouses().size() && hasPlayed == false; i++){

            if (Board.getInstance().getHouses().get(i).getMan().getHouse()==null){
                hasPlayed = true;
                stroke = i;
            }
        }
        return stroke ;
    }

    @Override
    public boolean readYN() {
        return true;
    }
}
