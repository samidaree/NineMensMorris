package com.sb.themillgame.model;

import java.util.ArrayList;

public class Graph {
    private static final int NUMBERVERTECES = 24;
    String[][]Matrix;

    public Graph(String [][] Matrix) {
        this.Matrix = Matrix;
    }

    public Graph() {
        Matrix= new String[24][24];
        for (int i = 0; i < NUMBERVERTECES; i++) {
            for (int j = 0; j < NUMBERVERTECES; j++) {
                String empty = "0";
                Matrix[i][j] = empty;
            }
        }
    }


    public void print() {
        for (int i = 0; i < NUMBERVERTECES; i++) {
            for (int j = 0; j < NUMBERVERTECES; j++) {
                System.out.print(Matrix[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public String[][] getMatrix(){
        return this.Matrix;
    }

    public void addEdge(int id1, int id2, String value){
        Matrix[id1][id2] = value ;
        Matrix[id2][id1] = value;
    }

    public void setMatrix(int id1, int id2, String value){
        Matrix[id1][id2] = value ;
    }
    public ArrayList<String> getNeighbor(int id) {
        ArrayList<String> res = new ArrayList<>();
        for(int i = 0; i< NUMBERVERTECES; i++) {
            if(hasEdge(id,i)) {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    public void printDetails() {
        for (int i = 0; i < NUMBERVERTECES; i++) {
            System.out.println(i +" : " + edgeNumber(i) + " : " + getNeighbor(i));
        }
    }

    //on ajoute une Edge entre deux pions

    //si deux positions ont une Edge
    public boolean hasEdge(int id1, int id2) {
        if(Matrix[id1][id2].equals("1") && Matrix[id2][id1].equals("1")) {
            return true;
        }
        return false;
    }

    //Edge total d'un plateau
    public int totalEdgeNumber() {
        int res =0;
        for (int i = 0; i < NUMBERVERTECES; i++) {
            for (int j = 0; j < NUMBERVERTECES; j++) {
                if(hasEdge(i,j)) {
                    res++;
                }
            }
        }
        return res;
    }

    //le nombre d'Edge d'une position
    public int edgeNumber(int id1) {
        int res =0;
        for(int j = 0; j< NUMBERVERTECES; j++) {
            if(Matrix[id1][j].equals("1") || Matrix[j][id1].equals("1") ) {
                res++;
            }
        }
        return res;
    }





}








































