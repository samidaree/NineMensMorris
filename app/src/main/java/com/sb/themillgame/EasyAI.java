package com.sb.themillgame;

public class EasyAI extends AbstractPlayer{
    boolean end = false;

    int stroke ;
    int src;
    int dst ;
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

    public void move(){
        boolean hasMoved = false;
        for (int i = 0 ; i<Board.getInstance().getHouses().size() && hasMoved == false; i++ ) {
            if (Board.getInstance().getHouses().get(i).getMan().getToken() == 'B'){
                src = i ;
                House h = Board.getInstance().getHouses().get(i);
                House [] destinations = {h.getRight(), h.getDown(), h.getLeft(), h.getUp()};
                for (int j= 0; j <destinations.length; j++ ){
                    if (destinations[j] !=null)
                        if(destinations[j].getMan().getToken() == ' '){
                            dst = destinations[j].getId();
                            System.out.println("black src " + src);
                            System.out.println("black dst " + dst);
                            hasMoved= true;
                        }
                }
            }
        }
    }

    public int getSrc(){
        return src ;
    }

    public int getDst(){
        return dst ;
    }

    @Override
    public boolean readYN() {
        return true;
    }
}
