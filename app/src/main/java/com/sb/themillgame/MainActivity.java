package com.sb.themillgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sb.themillgame.databinding.ActivityMainBinding;
import com.sb.themillgame.model.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static int[] idArray = {R.id.touch_piece1, R.id.touch_piece2, R.id.touch_piece3, R.id.touch_piece4, R.id.touch_piece5, R.id.touch_piece6, R.id.touch_piece7, R.id.touch_piece8, R.id.touch_piece8, R.id.touch_piece9, R.id.touch_piece10, R.id.touch_piece11, R.id.touch_piece12, R.id.touch_piece13, R.id.touch_piece14, R.id.touch_piece15, R.id.touch_piece16, R.id.touch_piece17, R.id.touch_piece18, R.id.touch_piece19, R.id.touch_piece20, R.id.touch_piece21, R.id.touch_piece22, R.id.touch_piece23, R.id.touch_piece24};

    private AppCompatButton[]intersections = new AppCompatButton[idArray.length];

    private static int playerTurn = 1 ;

    private Graph board = new Graph();
    private ActivityMainBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        setBoard();

        // Une classe MainActivity ou on initialise le board et les 3 phases du jeu
        // Une classe pour chaque phase
        // Une classe abstraite IA
        // 3 sous classes de IA pour chaque niveau
        // (1 classe joueur ou piece) à voir
        for (int i = 0 ; i<intersections.length; i++){
            intersections[i] = findViewById(idArray[i]);
            final int selectedIntersection = i ;
            intersections[i].setOnClickListener(view -> {
                setPiece((AppCompatButton) view, selectedIntersection);
            });
        }




    }

    public void setBoard() {
        //1
        addIntersection(0,1);
        addIntersection(0,7);

        //2
        addIntersection(1,2);
        addIntersection(1,9);

        //3
        addIntersection(2,3);

        //4
        addIntersection(3,11);
        addIntersection(3,4);

        //5
        addIntersection(4,5);

        //6
        addIntersection(5,13);
        addIntersection(5,6);

        //7
        addIntersection(6,7);

        //8
        addIntersection(7,15);

        //9
        addIntersection(8,9);
        addIntersection(8,15);

        //10
        addIntersection(9,10);
        addIntersection(9,17);

        //11
        addIntersection(10,11);

        //12

        addIntersection(11,19);
        addIntersection(11,12);

        //13
        addIntersection(12,13);


        //14
        addIntersection(13,21);
        addIntersection(13,14);


        //15
        addIntersection(14,15);


        //16
        addIntersection(15,23);


        //17
        addIntersection(16,17);
        addIntersection(16,23);

        //18
        addIntersection(17,18);

        //19
        addIntersection(18,19);

        //20
        addIntersection(19,20);

        //21
        addIntersection(20,21);

        //22
        addIntersection(21,22);

        //23
        addIntersection(22,23);

    }
    public void addIntersection(int id1, int id2) {

        //maxEdge est le nombre d'Edge maximal que la position peut avoir
        int maxEdgeId1= getNbEdgeMax(id1);
        int maxEdgeId2= getNbEdgeMax(id2);
        if(!(board.hasEdge(id1,id2))) {

            if(board.edgeNumber(id1) < maxEdgeId1 && board.edgeNumber(id2)< maxEdgeId2) {
                board.addEdge(id1, id2, "1");
            }
        }
    }

    private void setPiece(AppCompatButton b, int pieceNumber){
        if (isIntersectionSelectable(pieceNumber)){
            if (playerTurn == 1){
                this.board.setMatrix(pieceNumber, pieceNumber, "1");
                b.setBackground(getDrawable(R.drawable.white_piece));
                playerTurn = 2 ;
            }
            else{
                this.board.setMatrix(pieceNumber, pieceNumber, "2");
                b.setBackground(getDrawable(R.drawable.black_piece));
                playerTurn = 1 ;
            }
        }

        //b.setBackground(getDrawable(R.drawable.white_button));
    }

    public boolean isIntersectionSelectable(int pieceID){
        boolean response = false;
        if (board.getMatrix()[pieceID][pieceID].equals("0")){
            response = true ;
        }
        return response ;
    }

    //d'après le dessin du plateau
    public int getNbEdgeMax(int id) {
        if((id == 9) || (id == 11) || (id == 13) || (id ==15) ){
            return 4;
        }else if(id%2 == 1) {
            return 3;
        }else {
            return 2;
        }
    }


    //retourne une liste de position dont l'id en parametre peut se replacer
    public ArrayList<String> canMove(int id) {
        ArrayList<String>res = new ArrayList<>();
        ArrayList<String>list = board.getNeighbor(id);
        for(int i =0; i< list.size();i++) {
            int tmp = Integer.valueOf(list.get(i));
            if((board.getMatrix()[tmp][tmp].equals("0"))) {
                res.add(String.valueOf(tmp));
            }
        }
        return res;
    }

    //on recupere canMove de id, on regarde si newID est dedant,
    //si oui: on met à 0 la position id ET on met à 1 la posion newId
    public boolean movePiece(int id, int newId) {
        if(!canMove(id).contains(String.valueOf(newId))) {
            return false;
        }else {
            board.setMatrix(id,id,"0");
            board.setMatrix(newId, newId, "1");
            return true;
        }
    }

    //prend en parametre l'id de la derniere position du pion, retourne une liste
    //de 4 id
    public LinkedList<String> getMillCombinations(int lastPositionOfId) {
        LinkedList<String>res = new LinkedList<>();
        int tmp1;
        int tmp2;
        int tmp3;
        int tmp4;
        if(lastPositionOfId % 2 ==1) {
            if(lastPositionOfId <= 7) {
                tmp1 = (lastPositionOfId+8);
                tmp2 = (lastPositionOfId+16);
                tmp3 = (lastPositionOfId-1);
                tmp4 = (lastPositionOfId+1)%8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            }

            else if(lastPositionOfId <= 15) {
                tmp1 = ((lastPositionOfId-8));
                tmp2 = ((lastPositionOfId+8));
                tmp3 = (lastPositionOfId-1);
                tmp4 = ((lastPositionOfId+1)%8)+8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            }
            else if(lastPositionOfId <= 23) {
                tmp1 = (lastPositionOfId-16);
                tmp2 = (lastPositionOfId-8);
                tmp3 = (lastPositionOfId-1);
                tmp4 = ((lastPositionOfId+1)%8)+16;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            }
        }else {
            if(lastPositionOfId <= 7) {
                tmp1 = Math.floorMod(lastPositionOfId-1,8);
                tmp2 = Math.floorMod(lastPositionOfId-2,8);
                tmp3 = (lastPositionOfId+1);
                tmp4 = (lastPositionOfId+2)%8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            }

            else if(lastPositionOfId <= 15) {
                tmp1 = ((lastPositionOfId+1)%8)+8;
                tmp2 = ((lastPositionOfId+2)%8)+8;
                tmp3 = ((lastPositionOfId-1)%8)+8;
                tmp4 = ((lastPositionOfId-2)%8)+8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            }
            else if(lastPositionOfId <= 23) {
                if(lastPositionOfId == 16) {
                    tmp1 = (lastPositionOfId+1);
                    tmp2 = (lastPositionOfId+2);
                    tmp3 = ((lastPositionOfId+7));
                    tmp4 = ((lastPositionOfId+6));
                }else {
                    tmp1 = ((lastPositionOfId+1)%16)+16;
                    if(lastPositionOfId == 22) {
                        tmp2 = ((lastPositionOfId+1)%16)+9;
                    }else {
                        tmp2 = ((lastPositionOfId+2)%16)+16;
                    }
                    tmp3 = ((lastPositionOfId-1)%16)+16;
                    tmp4 = ((lastPositionOfId-2)%16)+16;

                }
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            }

        }
        return res;
    }

    //test si l'id en paremetre possède un alignement
    public Boolean isMill(int lastPosition){
        LinkedList<String>list = getMillCombinations(lastPosition);

        int nb1 = Integer.parseInt(list.get(0));
        int nb2 = Integer.parseInt(list.get(1));
        int nb3 = Integer.parseInt(list.get(2));
        int nb4 = Integer.parseInt(list.get(3));


        if(board.getMatrix()[nb1][nb1].equals("1") && board.getMatrix()[nb2][nb2].equals("1")) {
            return true;
        }else return board.getMatrix()[nb3][nb3].equals("1") && board.getMatrix()[nb4][nb4].equals("1");

    }
}
