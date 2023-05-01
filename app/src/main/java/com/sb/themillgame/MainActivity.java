package com.sb.themillgame;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import com.sb.themillgame.databinding.ActivityMainBinding;
import com.sb.themillgame.model.Graph;

import java.util.ArrayList;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private static final int[] idArray = {R.id.touch_piece0,R.id.touch_piece1, R.id.touch_piece2, R.id.touch_piece3, R.id.touch_piece4, R.id.touch_piece5, R.id.touch_piece6, R.id.touch_piece7, R.id.touch_piece8, R.id.touch_piece9, R.id.touch_piece10, R.id.touch_piece11, R.id.touch_piece12, R.id.touch_piece13, R.id.touch_piece14, R.id.touch_piece15, R.id.touch_piece16, R.id.touch_piece17, R.id.touch_piece18, R.id.touch_piece19, R.id.touch_piece20, R.id.touch_piece21, R.id.touch_piece22, R.id.touch_piece23};
    private final AppCompatButton[] intersections = new AppCompatButton[idArray.length];
    ArrayList<AbstractMove> actions = new ArrayList<AbstractMove>();

    private ActivityMainBinding binding ;

    private boolean mill = false;
    private int millToken ;

    boolean hasSelected = false;
    int srcToken ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        Game.getInstance().setUp();


        for (int i = 0; i < intersections.length; i++) {
            intersections[i] = findViewById(idArray[i]);
            final int selectedIntersection = i;
            intersections[i].setOnClickListener(view -> {
                System.out.println("---------------------------");
                System.out.println("turn  " + Game.getInstance().currentTurn.getColour());

                if (Game.getInstance().phase == 1){
                    if (mill == false && Board.getInstance().getHouses().get(selectedIntersection).getMan().getToken()== ' '){
                        // White sets token
                        view.setBackground(getDrawable(R.drawable.white_piece));
                        Game.getInstance().PhaseOne(selectedIntersection);
                        // White did a mill
                        if (Mills.checkMills(selectedIntersection)) {
                            Game.getInstance().changeTurn();
                            mill = true;
                            millToken= selectedIntersection;

                        }
                        else {
                            // Black sets token
                            int destination = Game.getInstance().currentTurn.readInt();
                            System.out.println("ai play first else : " + destination);
                            intersections[destination].setBackground(getDrawable(R.drawable.black_piece));
                            Game.getInstance().PhaseOne(destination);
                            // Black did a mill
                            if (Mills.checkMills(destination)) {
                                mill = true;
                                Game.getInstance().changeTurn();
                                int remove = Game.getInstance().currentTurn.remove();
                                System.out.println("Piece to remove by Black is : "+  remove);
                                millToken = destination;
                                // Black removes a piece from white
                                new Turn(millToken, remove);
                                intersections[remove].setBackground(getDrawable(R.drawable.transparent_round_button));
                                Game.getInstance().changeTurn();
                                mill = false;
                            }
                        }

                    }
                    else if (mill == true) {
                        // White removes a piece from black

                        if (Board.getInstance().getHouses().get(selectedIntersection).getMan().getToken() == 'B') {
                            new Turn(millToken, selectedIntersection);
                            intersections[selectedIntersection].setBackground(getDrawable(R.drawable.transparent_round_button));

                            mill = false;
                            Game.getInstance().changeTurn();
                        }

                        if (Game.getInstance().currentTurn == Game.getInstance().p2){
                            int destination = Game.getInstance().currentTurn.readInt();
                            System.out.println("ai play second else: " + destination);
                            intersections[destination].setBackground(getDrawable(R.drawable.black_piece));
                            Game.getInstance().PhaseOne(destination);
                            if (Mills.checkMills(destination)) {
                                mill = true;
                                Game.getInstance().changeTurn();
                                int remove = Game.getInstance().currentTurn.remove();
                                System.out.println("Piece to remove by Black is : "+  remove);
                                millToken = destination;
                                // Black removes a piece from white
                                new Turn(millToken, remove);
                                intersections[remove].setBackground(getDrawable(R.drawable.transparent_round_button));
                                Game.getInstance().changeTurn();
                                mill = false;
                            }
                        }

                    }
                }
                else if (Game.getInstance().phase == 2 || Game.getInstance().phase == 3) {
                    if (checkResults())
                        return ;

                    if (mill == false){
                        if (hasSelected==false){
                            if (Board.getInstance().getHouses().get(selectedIntersection).getMan().getToken() == 'W'){
                                srcToken = selectedIntersection ;
                                intersections[srcToken].setBackground(getDrawable(R.drawable.white_piece_green_stroke));
                                hasSelected= true;
                            }
                        }
                        else if(hasSelected==true){
                            if (Board.getInstance().getHouses().get(selectedIntersection).getMan().getToken()== ' ' && Board.getInstance().getHouses().get(srcToken).isNeighbour(selectedIntersection)) {

                                intersections[srcToken].setBackground(getDrawable(R.drawable.transparent_round_button));
                                intersections[selectedIntersection].setBackground(getDrawable(R.drawable.white_piece));
                                Game.getInstance().PhaseTwo(srcToken,selectedIntersection);
                                if (checkResults())
                                    return ;
                                System.out.println("turn main :  " + Game.getInstance().currentTurn.getColour());

                                if (Mills.checkMills(selectedIntersection)){
                                    mill= true;
                                    millToken = selectedIntersection;
                                }
                                else {
                                    Game.getInstance().changeTurn();
                                    hasSelected=false;
                                }
                                // Black moves
                                if (mill == false) {
                                    Game.getInstance().p2.move();
                                    int src = Game.getInstance().p2.getSrc();
                                    int dst = Game.getInstance().p2.getDst();
                                    Game.getInstance().PhaseTwo(src,dst);

                                    intersections[src].setBackground(getDrawable(R.drawable.transparent_round_button));
                                    intersections[dst].setBackground(getDrawable(R.drawable.black_piece));
                                    if (checkResults())
                                        return ;
                                    if(Mills.checkMills(dst)){
                                        mill = true;
                                        //Game.getInstance().changeTurn();
                                        int remove = Game.getInstance().currentTurn.remove();
                                        System.out.println("Piece to remove by Black is : "+  remove);
                                        millToken = dst;
                                        // Black removes a piece from white
                                        new Turn(millToken, remove);

                                        intersections[remove].setBackground(getDrawable(R.drawable.transparent_round_button));
                                        if (checkResults())
                                            return ;
                                        Game.getInstance().changeTurn();
                                        mill = false;
                                    }
                                    else {
                                        Game.getInstance().changeTurn();

                                    }
                                }
                            }
                            else if (Board.getInstance().getHouses().get(selectedIntersection).getMan().getToken()== 'W'){
                                intersections[srcToken].setBackground(getDrawable(R.drawable.white_piece));
                                srcToken = selectedIntersection ;
                                intersections[srcToken].setBackground(getDrawable(R.drawable.white_piece_green_stroke));
                            }
                        }
                    }
                    else if(mill == true){
                        if (Board.getInstance().getHouses().get(selectedIntersection).getMan().getToken() == 'B') {
                            new Turn(millToken, selectedIntersection);

                            intersections[selectedIntersection].setBackground(getDrawable(R.drawable.transparent_round_button));
                            if (checkResults())
                                return ;
                            mill = false;
                            hasSelected=false;

                            // Black moves
                            Game.getInstance().changeTurn();
                            Game.getInstance().p2.move();
                            int src = Game.getInstance().p2.getSrc();
                            int dst = Game.getInstance().p2.getDst();
                            Game.getInstance().PhaseTwo(src,dst);

                            intersections[src].setBackground(getDrawable(R.drawable.transparent_round_button));
                            intersections[dst].setBackground(getDrawable(R.drawable.black_piece));
                            if (checkResults())
                                return ;
                            if(Mills.checkMills(dst)){
                                mill = true;
                                int remove = Game.getInstance().currentTurn.remove();
                                System.out.println("Piece to remove by Black is : "+  remove);
                                millToken = dst;
                                // Black removes a piece from white
                                new Turn(millToken, remove);

                                intersections[remove].setBackground(getDrawable(R.drawable.transparent_round_button));
                                if (checkResults())
                                    return ;
                                Game.getInstance().changeTurn();
                                mill = false;
                            }
                            else{
                                Game.getInstance().changeTurn();

                            }
                        }
                    }


                }

                if (checkResults())
                    return ;

                System.out.println("mill " + mill);


            });

        }



    }

    boolean checkResults(){
        boolean playerHasWon = false;
        if((Mills.canMove(Board.getInstance().red)==false && Game.getInstance().currentTurn.getColour()== Game.Color.White)|| Board.getInstance().howManyMen(Board.getInstance().red) < 3){
            ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Noir a gagné", MainActivity.this);
            resultDialog.setCancelable(false) ;
            resultDialog.show();
        }
        if((Mills.canMove(Board.getInstance().blue)==false && Game.getInstance().currentTurn.getColour()== Game.Color.White)|| Board.getInstance().howManyMen(Board.getInstance().blue) < 3){
            ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Blanc a gagné", MainActivity.this);
            resultDialog.setCancelable(false) ;
            resultDialog.show();
            playerHasWon= true;
        }
        return playerHasWon ;

    }
}





