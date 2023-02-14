package com.sb.themillgame.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    final int nb = 24;
    String[][]Matrix;

    public Graph(String [][] Matrix) {
        this.Matrix = Matrix;
    }

    public Graph() {
        Matrix= new String[24][24];
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                String empty = "0";
                Matrix[i][j] = empty;
            }
        }
    }

    public void board() {
        //1
        addEdge(0,1);
        addEdge(0,7);

        //2
        addEdge(1,2);
        addEdge(1,9);

        //3
        addEdge(2,3);

        //4
        addEdge(3,11);
        addEdge(3,4);

        //5
        addEdge(4,5);

        //6
        addEdge(5,13);
        addEdge(5,6);

        //7
        addEdge(6,7);

        //8
        addEdge(7,15);

        //9
        addEdge(8,9);
        addEdge(8,15);

        //10
        addEdge(9,10);
        addEdge(9,17);

        //11
        addEdge(10,11);

        //12

        addEdge(11,19);
        addEdge(11,12);

        //13
        addEdge(12,13);


        //14
        addEdge(13,21);
        addEdge(13,14);


        //15
        addEdge(14,15);


        //16
        addEdge(15,23);


        //17
        addEdge(16,17);
        addEdge(16,23);

        //18
        addEdge(17,18);

        //19
        addEdge(18,19);

        //20
        addEdge(19,20);

        //21
        addEdge(20,21);

        //22
        addEdge(21,22);

        //23
        addEdge(22,23);

    }

    public void affiche() {
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                System.out.print(Matrix[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public ArrayList<String> getNeighbor(int id) {
        ArrayList<String> res = new ArrayList<>();
        for(int i =0; i<nb;i++) {
            if(hasEdge(id,i)) {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    public void getAfficheDetaille() {
        for (int i = 0; i < nb; i++) {
            System.out.println(i +" : " + nbEdge(i) + " : " + getNeighbor(i));
        }
    }

    //on ajoute une Edge entre deux pions
    public void addEdge(int id1, int id2) {

        //maxEdge est le nombre d'Edge maximal dont la position peut avoir
        int maxEdgeId1= getNbEdgeMax(id1);
        int maxEdgeId2= getNbEdgeMax(id2);
        if(!hasEdge(id1,id2)) {

            if(nbEdge(id1) < maxEdgeId1 && nbEdge(id2)< maxEdgeId2) {
                Matrix[id1][id2] ="1";
                Matrix[id2][id1] ="1";
            }
        }
    }

    //si deux positions ont une Edge
    public boolean hasEdge(int id1, int id2) {
        if(Matrix[id1][id2].equals("1") && Matrix[id2][id1].equals("1")) {
            return true;
        }
        return false;
    }

    //Edge total d'un plateau
    public int nbEdgeTotal() {
        int res =0;
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                if(hasEdge(i,j)) {
                    res++;
                }
            }
        }
        return res;
    }

    //le nombre d'Edge d'une position
    public int nbEdge(int id1) {
        int res =0;
        for(int j =0; j< nb;j++) {
            if(Matrix[id1][j].equals("1") || Matrix[j][id1].equals("1") ) {
                res++;
            }
        }
        return res;
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
        ArrayList<String>list = getNeighbor(id);
        for(int i =0; i< list.size();i++) {
            int tmp = Integer.valueOf(list.get(i));
            if((Matrix[tmp][tmp].equals("0"))) {
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
            Matrix[id][id]="0";
            Matrix[newId][newId]="1";
            return true;
        }
    }

    public void setPiece(int id) {
        Matrix[id][id]="1";
    }

    //prend en parametre l'id de la derniere position du pion, retourne une liste
    //de 4 id
    public LinkedList<String> getListForAlignement(int lastPositionOfId) {
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
    public Boolean isAlignement(int lastPosition){
        LinkedList<String>list = getListForAlignement(lastPosition);

        int nb1 = Integer.valueOf(list.get(0));
        int nb2 = Integer.valueOf(list.get(1));
        int nb3 = Integer.valueOf(list.get(2));
        int nb4 = Integer.valueOf(list.get(3));


        if(Matrix[nb1][nb1].equals("1") && Matrix[nb2][nb2].equals("1")) {
            return true;
        }else if(Matrix[nb3][nb3].equals("1") && Matrix[nb4][nb4].equals("1")) {
            return true;
        }else {
            return false;
        }

    }

}





















