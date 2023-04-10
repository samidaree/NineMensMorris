package com.sb.themillgame;

public class EasyAI extends AbstractPlayer{
    boolean end = false;
    public EasyAI(Game.Color c){
        color = c;
    }
    public int readInt(){
        boolean hasPlayed = false;
        int stroke = -1;
        if (!end)
            for (int i = 0; i<Board.getInstance().getHouses().size() && hasPlayed == false; i++){

                if (Board.getInstance().getHouses().get(i).getMan().getToken()==' '){
                    System.out.println(i  + " is empty : " + Board.getInstance().getHouses().get(i).getMan().getColor());
                    hasPlayed = true;
                    stroke = i;
                    end = true;
                }
            }
        else
            for (int i = Board.getInstance().getHouses().size() - 1; i>0 && hasPlayed == false; i--){

                if (Board.getInstance().getHouses().get(i).getMan().getToken()==' '){
                    System.out.println(i  + " is empty : " + Board.getInstance().getHouses().get(i).getMan().getColor());
                    hasPlayed = true;
                    stroke = i;
                    end = false;
                }
            }
        return stroke ;
    }

    public int remove(){
        boolean hasRemoved = false;
        int stroke = -1;
        for (int i = 0; i<Board.getInstance().getHouses().size() && hasRemoved == false; i++){

            if (Board.getInstance().getHouses().get(i).getMan().getToken() == 'W'){
                hasRemoved = true;
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
