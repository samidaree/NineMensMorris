package com.sb.themillgame;

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
    private static final int[] idArray = {R.id.touch_piece1, R.id.touch_piece2, R.id.touch_piece3, R.id.touch_piece4, R.id.touch_piece5, R.id.touch_piece6, R.id.touch_piece7, R.id.touch_piece8, R.id.touch_piece9, R.id.touch_piece10, R.id.touch_piece11, R.id.touch_piece12, R.id.touch_piece13, R.id.touch_piece14, R.id.touch_piece15, R.id.touch_piece16, R.id.touch_piece17, R.id.touch_piece18, R.id.touch_piece19, R.id.touch_piece20, R.id.touch_piece21, R.id.touch_piece22, R.id.touch_piece23, R.id.touch_piece24};
    private final AppCompatButton[] intersections = new AppCompatButton[idArray.length];

    private static int playerTurn = 1;

    private Graph board = new Graph();

    private static int firstPlayerPiecesSet = 0 ;
    private static int secondPlayerPiecesSet = 0 ;

    private static int firstPlayerOnBoardPieces = 0;
    private static int secondPlayerOnBoardPieces = 0;

    private static boolean selectedPiece = false;
    private static int sourceIntersectionId;
    private static AppCompatButton sourceIntersectionButton;
    private static int step = 1;
    int aimove = 0 ;

    private int [] flyers = {0,0} ;
    private int mill = 0;
    private ActivityMainBinding binding;

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
        for (int i = 0; i < intersections.length; i++) {
            intersections[i] = findViewById(idArray[i]);
            final int selectedIntersection = i;
            intersections[i].setOnClickListener(view -> {
                System.out.println("---------------------------");

                performAction((AppCompatButton) view, selectedIntersection);
                    aiGreedyMove();
            });
        }


    }

    public void performActionAI(AppCompatButton view, int selectedIntersection){
        intersections[selectedIntersection] = player
    }
    public void performAction(AppCompatButton view, int selectedIntersection){
        //System.out.println("step : "+ step);
        System.out.println("turn " + playerTurn);
        System.out.println("white piece : " + firstPlayerOnBoardPieces);
        System.out.println("black piece : "+ secondPlayerOnBoardPieces);
        if (step == 1) {
            if (mill == 1){
                if (board.getMatrix()[selectedIntersection][selectedIntersection].equals(String.valueOf(getOtherPlayerTurn()))){
                    removePiece((AppCompatButton) view, selectedIntersection);
                    mill = 0 ;
                    changePlayerTurn();
                }

            }
            if (mill == 2){
                System.out.println(" mill ai");

                chooseRemovePiece();
                mill = 0 ;

                //changePlayerTurn();

            }

            else{
                setPiece((AppCompatButton) view, selectedIntersection);

            }

        }
        if (step == 2 || step==3) {
            if (mill == playerTurn){
                if (board.getMatrix()[selectedIntersection][selectedIntersection].equals(String.valueOf(getOtherPlayerTurn()))){
                    removePiece((AppCompatButton) view, selectedIntersection);
                    mill = 0 ;
                    changePlayerTurn();
                }

            }
            else {
                if (!selectedPiece && canSelect(selectedIntersection)) {
                    selectPiece((AppCompatButton) view, selectedIntersection);
                } else if (selectedPiece) {
                    if (movePiece(sourceIntersectionButton, (AppCompatButton) view, sourceIntersectionId, selectedIntersection))
                        selectedPiece = false;
                        // If player did not select a correct position, make him select again
                    else {
                        if (!canSelect(selectedIntersection))
                            Toast.makeText(this, "Select an available position maximum 1 unit far away", Toast.LENGTH_LONG).show();

                        movePiece(sourceIntersectionButton, (AppCompatButton) view, sourceIntersectionId, selectedIntersection);
                    }

                }
            }
        }
        checkResults();

    }

    public void aimove(){

        performAction(intersections[aimove], aimove);


        aimove++ ;
    }

    public void aiGreedyMove(){
        int coup = greedy();
        System.out.println(coup) ;
        performAction(intersections[coup], coup);
        if (mill == getOtherPlayerTurn()){
            chooseRemovePiece();
            mill = 0 ;
            changePlayerTurn();
        }

        else if (mill==0){
            performAction(intersections[coup], coup);
        }
    }
    public void setBoard() {
        //1
        addIntersection(0, 1);
        addIntersection(0, 7);

        //2
        addIntersection(1, 2);
        addIntersection(1, 9);

        //3
        addIntersection(2, 3);

        //4
        addIntersection(3, 11);
        addIntersection(3, 4);

        //5
        addIntersection(4, 5);

        //6
        addIntersection(5, 13);
        addIntersection(5, 6);

        //7
        addIntersection(6, 7);

        //8
        addIntersection(7, 15);

        //9
        addIntersection(8, 9);
        addIntersection(8, 15);

        //10
        addIntersection(9, 10);
        addIntersection(9, 17);

        //11
        addIntersection(10, 11);

        //12

        addIntersection(11, 19);
        addIntersection(11, 12);

        //13
        addIntersection(12, 13);


        //14
        addIntersection(13, 21);
        addIntersection(13, 14);


        //15
        addIntersection(14, 15);


        //16
        addIntersection(15, 23);


        //17
        addIntersection(16, 17);
        addIntersection(16, 23);

        //18
        addIntersection(17, 18);

        //19
        addIntersection(18, 19);

        //20
        addIntersection(19, 20);

        //21
        addIntersection(20, 21);

        //22
        addIntersection(21, 22);

        //23
        addIntersection(22, 23);

    }

    public int changePlayerTurn (){
        int currentTurn;
        if (playerTurn == 1){
            playerTurn = 2;
            currentTurn = 2;
        }
        else {
            playerTurn = 1;
            currentTurn = 1 ;
        }
        return currentTurn;
    }


    public int getOtherPlayerTurn(){
        if (playerTurn == 1)
            return 2 ;
        else
            return 1;
    }
    public void addIntersection(int id1, int id2) {

        //maxEdge est le nombre d'Edge maximal que la position peut avoir
        int maxEdgeId1 = getNbEdgeMax(id1);
        int maxEdgeId2 = getNbEdgeMax(id2);
        if (!(board.hasEdge(id1, id2))) {

            if (board.edgeNumber(id1) < maxEdgeId1 && board.edgeNumber(id2) < maxEdgeId2) {
                board.addEdge(id1, id2, "1");
            }
        }
    }


    private void setPiece(AppCompatButton b, int pieceNumber) {
        if (firstPlayerPiecesSet == 9 && secondPlayerPiecesSet == 9 && step==1) {
            Toast.makeText(this, "You must click on a piece to move it ! ", Toast.LENGTH_LONG).show();
            step = 2;
            return;
        }
        if (isIntersectionSelectable(pieceNumber)) {

            if (playerTurn == 1) {
                if (step == 1){
                    firstPlayerPiecesSet++ ;
                    firstPlayerOnBoardPieces++;
                }
                this.board.setMatrix(pieceNumber, pieceNumber, "1");
                b.setBackground(getDrawable(R.drawable.white_piece));
                if (!isMill(pieceNumber))
                    playerTurn = 2;
            } else {
                if (step == 1){
                    secondPlayerPiecesSet++ ;
                    secondPlayerOnBoardPieces++;
                }

                this.board.setMatrix(pieceNumber, pieceNumber, "2");
                b.setBackground(getDrawable(R.drawable.black_piece));
                if (!isMill(pieceNumber))
                    playerTurn = 1;
            }


        }


    }

    private boolean removePiece(AppCompatButton b, int pieceNumber) {
        if (this.board.getMatrix()[pieceNumber][pieceNumber].equals("0") )
            return false;
        else if (mill!=0) {
            if (playerTurn == 2){
                firstPlayerOnBoardPieces--;
            }
            else if (playerTurn == 1){
                secondPlayerOnBoardPieces--;
            }

        }
        b.setBackground(getDrawable(R.drawable.transparent_round_button));
        board.setMatrix(pieceNumber, pieceNumber, "0");
        hasThreePieces();
        return true;

    }


    public boolean isIntersectionSelectable(int pieceID) {
        boolean response = board.getMatrix()[pieceID][pieceID].equals("0");
        return response;
    }

    //d'après le dessin du plateau
    public int getNbEdgeMax(int id) {
        if ((id == 9) || (id == 11) || (id == 13) || (id == 15)) {
            return 4;
        } else if (id % 2 == 1) {
            return 3;
        } else {
            return 2;
        }
    }

    public boolean canSelect(int id) {
        boolean response = true;
        if (!board.getMatrix()[id][id].equals(String.valueOf(playerTurn))) {
            response = false;
            if (sourceIntersectionButton == null)
                Toast.makeText(this, "Select your piece !", Toast.LENGTH_LONG).show();
        }

        return response;
    }



    //retourne une liste de position dont l'id en parametre peut se replacer
    public ArrayList<String> canMove(int id) {
        ArrayList<String> res = new ArrayList<>();
        if (step == 3 && flyers[playerTurn-1] ==1)
            for (int i = 0; i<board.getMatrix().length; i++){
                if (board.getMatrix()[i][i].equals("0"))
                    res.add(String.valueOf(i));

            }
        else {
            ArrayList<String> list = board.getNeighbor(id);
            //System.out.println("voisins de " + id + ": "+ list);
            for (int i = 0; i < list.size(); i++) {
                int tmp = Integer.parseInt(list.get(i));
                if ((board.getMatrix()[tmp][tmp].equals("0"))) {
                    res.add(String.valueOf(tmp));
                }
            }

        }

        return res;
    }

    public void selectPiece(AppCompatButton b, int id) {
        sourceIntersectionId = id;
        sourceIntersectionButton = b;
        if (playerTurn == 1)
            sourceIntersectionButton.setBackground(getDrawable(R.drawable.white_piece_green_stroke));
        else
            sourceIntersectionButton.setBackground(getDrawable(R.drawable.black_piece_green_stroke));
        selectedPiece = true;
    }

    public void unselectPiece() {
        if (playerTurn == 1) {
            sourceIntersectionButton.setBackground(getDrawable(R.drawable.white_piece));
        } else {
            sourceIntersectionButton.setBackground(getDrawable(R.drawable.black_piece));
        }
        sourceIntersectionButton = null;
        sourceIntersectionId = -1;
    }

    //on recupere canMove de id, on regarde si newID est dedant,
    //si oui: on met à 0 la position id ET on met à 1 la posion newId
    public boolean movePiece(AppCompatButton sourceButton, AppCompatButton destinationButton, int sourceId, int destinationId) {

        if (!canMove(sourceId).contains(String.valueOf(destinationId))) {
            if (canSelect(destinationId)) {
                unselectPiece();
                selectPiece(destinationButton, destinationId);
            }
            return false;
        }
        if (playerTurn == 1)
            sourceButton.setBackground(getDrawable(R.drawable.white_piece_green_stroke));
        else
            sourceButton.setBackground(getDrawable(R.drawable.black_piece_green_stroke));

        removePiece(sourceButton, sourceId);
        setPiece(destinationButton, destinationId);
        return true;

    }

    //prend en parametre l'id de la derniere position du pion, retourne une liste
    //de 4 id
    public LinkedList<String> getMillCombinations(int lastPositionOfId) {
        LinkedList<String> res = new LinkedList<>();
        int tmp1;
        int tmp2;
        int tmp3;
        int tmp4;
        if (lastPositionOfId % 2 == 1) {
            if (lastPositionOfId <= 7) {
                tmp1 = (lastPositionOfId + 8);
                tmp2 = (lastPositionOfId + 16);
                tmp3 = (lastPositionOfId - 1);
                tmp4 = (lastPositionOfId + 1) % 8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            } else if (lastPositionOfId <= 15) {
                tmp1 = ((lastPositionOfId - 8));
                tmp2 = ((lastPositionOfId + 8));
                tmp3 = (lastPositionOfId - 1);
                tmp4 = ((lastPositionOfId + 1) % 8) + 8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            } else if (lastPositionOfId <= 23) {
                tmp1 = (lastPositionOfId - 16);
                tmp2 = (lastPositionOfId - 8);
                tmp3 = (lastPositionOfId - 1);
                tmp4 = ((lastPositionOfId + 1) % 8) + 16;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            }
        } else {
            if (lastPositionOfId <= 7) {
                tmp1 = Math.floorMod(lastPositionOfId - 1, 8);
                tmp2 = Math.floorMod(lastPositionOfId - 2, 8);
                tmp3 = (lastPositionOfId + 1);
                tmp4 = (lastPositionOfId + 2) % 8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            } else if (lastPositionOfId <= 15) {
                tmp1 = ((lastPositionOfId + 1) % 8) + 8;
                tmp2 = ((lastPositionOfId + 2) % 8) + 8;
                tmp3 = ((lastPositionOfId - 1) % 8) + 8;
                tmp4 = ((lastPositionOfId - 2) % 8) + 8;
                res.add(String.valueOf(tmp1));
                res.add(String.valueOf(tmp2));
                res.add(String.valueOf(tmp3));
                res.add(String.valueOf(tmp4));
            } else if (lastPositionOfId <= 23) {
                if (lastPositionOfId == 16) {
                    tmp1 = (lastPositionOfId + 1);
                    tmp2 = (lastPositionOfId + 2);
                    tmp3 = ((lastPositionOfId + 7));
                    tmp4 = ((lastPositionOfId + 6));
                } else {
                    tmp1 = ((lastPositionOfId + 1) % 16) + 16;
                    if (lastPositionOfId == 22) {
                        tmp2 = ((lastPositionOfId + 1) % 16) + 9;
                    } else {
                        tmp2 = ((lastPositionOfId + 2) % 16) + 16;
                    }
                    tmp3 = ((lastPositionOfId - 1) % 16) + 16;
                    tmp4 = ((lastPositionOfId - 2) % 16) + 16;

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
    public Boolean isMill(int lastPosition) {

        boolean response = false;
        LinkedList<String> list = getMillCombinations(lastPosition);

        int nb1 = Integer.parseInt(list.get(0));
        int nb2 = Integer.parseInt(list.get(1));
        int nb3 = Integer.parseInt(list.get(2));
        int nb4 = Integer.parseInt(list.get(3));



        if (board.getMatrix()[nb1][nb1].equals(String.valueOf(playerTurn)) && board.getMatrix()[nb2][nb2].equals(String.valueOf(playerTurn))) {
            mill = playerTurn;
            response = true;
            return response;
        } else if (board.getMatrix()[nb3][nb3].equals(String.valueOf(playerTurn)) && board.getMatrix()[nb4][nb4].equals(String.valueOf(playerTurn))) {
            mill = playerTurn;
            response = true;
        }

        return response;
    }

    public int hasThreePieces(){
        int flyer = 0 ;
        if (step ==2 || step ==3){
            if (firstPlayerOnBoardPieces==3){
                step = 3;
                flyer = 1;
                flyers[0] = 1;
            }
            else if (secondPlayerOnBoardPieces==3){
                step = 3;
                flyer = 2;
                flyers[1] = 1 ;
            }
        }
        //System.out.println("flyers : " + flyers[0] + ", "+ flyers[1]);
        return flyer ;
    }

    public int hasTwoPieces(){
        if (firstPlayerOnBoardPieces == 2 && step>1)
            return 1;
        else if (secondPlayerOnBoardPieces == 2 && step>1)
            return 2;
        else
            return 0;
    }

    public boolean firstPlayerCanMove(){
        boolean firstPlayerCanMove = false;
        if (step == 1)
            firstPlayerCanMove = true ;
        else
            for (int i= 0 ; i<board.getMatrix().length && firstPlayerCanMove == false; i++){
                if (board.getMatrix()[i][i].equals("1"))
                    if (!(canMove(i).isEmpty())){
                       // System.out.println(i + " of first player can move :" + canMove(i));
                        firstPlayerCanMove = true;

                    }
            }
        return firstPlayerCanMove;
    }

    public boolean secondPlayerCanMove(){
        boolean secondPlayerCanMove = false;
        if (step == 1)
            secondPlayerCanMove = true;
        else
            for (int i= 0 ; i<board.getMatrix().length && secondPlayerCanMove == false; i++){
                if (board.getMatrix()[i][i].equals("2"))
                    if (!(canMove(i).isEmpty())){
                        //System.out.println(i + " of second player can move : " + canMove(i));
                        secondPlayerCanMove = true;

                    }
            }
        return secondPlayerCanMove;
    }

    public void checkResults(){
        if (hasTwoPieces()==2 || !secondPlayerCanMove()) {
            //System.out.println("First Player has won !!");
            ResultDialog resultDialog = new ResultDialog(MainActivity.this ,  " First player is a Winner ! ", MainActivity.this);
            resultDialog.setCancelable(false) ;
            resultDialog.show();
        }
        else if ( hasTwoPieces() == 1 || !firstPlayerCanMove()) {
            //System.out.println("Second Player has won !!");
            ResultDialog resultDialog = new ResultDialog(MainActivity.this ,  " Second player is a Winner ! ", MainActivity.this);
            resultDialog.setCancelable(false) ;
            resultDialog.show();
        }
    }

    public void restartMatch(){
        for (int i = 0 ; i< intersections.length; i++){
            intersections[i].setBackground(getDrawable(R.drawable.transparent_round_button));
        }

        playerTurn = 1;

        board = new Graph();

        firstPlayerPiecesSet = 0 ;
        secondPlayerPiecesSet = 0 ;

        firstPlayerOnBoardPieces = 0;
        secondPlayerOnBoardPieces = 0;

        selectedPiece = false;
        step = 1;

        flyers = new int[]{0,0} ;
        mill = 0;
    }

    public int evaluerEtatJeu(int[][] etatJeu, int lastPosition, ArrayList<Integer> positionsPossibles) {
        int scoreTotal = 0;

        for (Integer pos : positionsPossibles) {
            int score = 0;
            int nbPionsIA = 0;
            int nbPionsAdversaire = 0;

            if (isMill(lastPosition)) {
                score += 1000;
            } else if (etatJeu[lastPosition][lastPosition] == 1) {
                score++;
                nbPionsIA++;
            } else if (etatJeu[lastPosition][lastPosition] == -1) {
                score--;
                nbPionsAdversaire++;
            } else if (nbPionsIA < 3) {
                score -= 100;
            } else if (nbPionsAdversaire < 3) {
                score += 100;
            }

            scoreTotal += score;
        }

        int evaluation = scoreTotal / positionsPossibles.size();
        return evaluation;
    }

    //algo gloutonne
    public int greedy() {
        int coup = 1;
        int meilleureEvaluation = Integer.MIN_VALUE;


        ArrayList<Integer> positionsPossibles = canMoveAll();

        for (Integer pos : positionsPossibles) {

            int[][] nouveauEtatJeu = copierEtatJeu();
            nouveauEtatJeu[pos][pos] = 1;
            int evaluation = evaluerEtatJeu(nouveauEtatJeu, pos, positionsPossibles);
            if (evaluation > meilleureEvaluation) {
                meilleureEvaluation = evaluation;
                coup = pos;
            }
        }

        return coup;
    }

    //quand on peut se depalcer partout sur le plateau
    public ArrayList<Integer> canMoveAll() {
        ArrayList<Integer> res= new ArrayList<Integer>();
        for(int i= 0; i< board.getMatrix().length;i++) {
            if(board.getMatrix()[i][i].equals("0")) {
                res.add(i);
            }
        }
        return res;
    }

    public int [][] copierEtatJeu() {
        int [][]res= new int[24][24];
        for(int i=0; i< board.getMatrix().length;i++) {
            res[i][i]= Integer.parseInt(board.getMatrix()[i][i]);
        }
        return res;
    }

    public void chooseRemovePiece(){
        boolean hasRemoved = false ;
        for (int i = 0 ; i<board.getMatrix().length && hasRemoved == false ;i++){
            if (board.getMatrix()[i][i].equals("1")){
                System.out.println("chooseRemovePiece");
                hasRemoved = true;
                removePiece(intersections[i], i);
            }
        }
    }

}







