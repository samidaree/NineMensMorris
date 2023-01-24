package com.sb.themillgame.model;

import java.util.ArrayList;

public class Graph {

    int nb = 24;
    String[][]Matrice;

    public Graph(String [][] matrice) {
        this.Matrice = matrice;
    }

    public Graph() {
        Matrice= new String[24][24];
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                String vide = new String("0");
                Matrice[i][j] = vide;
            }
        }
    }

    public void plateau() {
        //1
        addArete(0,1);
        addArete(0,7);

        //2
        addArete(1,2);
        addArete(1,9);

        //3
        addArete(2,3);

        //4
        addArete(3,11);
        addArete(3,4);

        //5
        addArete(4,5);

        //6
        addArete(5,13);
        addArete(5,6);

        //7
        addArete(6,7);

        //8
        addArete(7,15);

        //9
        addArete(8,9);
        addArete(8,15);

        //10
        addArete(9,10);
        addArete(9,17);

        //11
        addArete(10,11);

        //12

        addArete(11,19);
        addArete(11,12);

        //13
        addArete(12,13);


        //14
        addArete(13,21);
        addArete(13,14);


        //15
        addArete(14,15);


        //16
        addArete(15,23);


        //17
        addArete(16,17);
        addArete(16,23);

        //18
        addArete(17,18);

        //19
        addArete(18,19);

        //20
        addArete(19,20);

        //21
        addArete(20,21);

        //22
        addArete(21,22);

        //23
        addArete(22,23);

    }

    public void affiche() {
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                System.out.print(Matrice[i][j]+ " ");
            }
            System.out.println();
        }
    }

    public ArrayList<String> getVoisin(int id) {
        ArrayList<String> res = new ArrayList<>();
        for(int i =0; i<nb;i++) {
            if(hasArete(id,i)) {
                res.add(String.valueOf(i));
            }
        }
        return res;
    }

    public void getAfficheDetaille() {
        for (int i = 0; i < nb; i++) {
            System.out.println(i +" : " + nbArete(i) + " : " + getVoisin(i));
        }
    }

    //on ajoute une arete entre deux pions
    public void addArete(int id1, int id2) {

        //maxArete est le nombre d'arete maximal dont la position peut avoir
        int maxAreteId1= getNbAreteMax(id1);
        int maxAreteId2= getNbAreteMax(id2);
        if(!hasArete(id1,id2)) {

            if(nbArete(id1) < maxAreteId1 && nbArete(id2)< maxAreteId2) {
                Matrice[id1][id2] ="1";
                Matrice[id2][id1] ="1";
            }
        }
    }

    //si deux positions ont une arete
    public boolean hasArete(int id1, int id2) {
        if(Matrice[id1][id2].equals("1") && Matrice[id2][id1].equals("1")) {
            return true;
        }
        return false;
    }

    //arete total d'un plateau
    public int nbAreteTotal() {
        int res =0;
        for (int i = 0; i < nb; i++) {
            for (int j = 0; j < nb; j++) {
                if(hasArete(i,j)) {
                    res++;
                }
            }
        }
        return res;
    }

    //le nombre d'arete d'une position
    public int nbArete(int id1) {
        int res =0;
        for(int j =0; j< nb;j++) {
            if(Matrice[id1][j].equals("1") || Matrice[j][id1].equals("1") ) {
                res++;
            }
        }
        return res;
    }

    //d'après le dessin du plateau
    public int getNbAreteMax(int id) {
        if((id == 9) || (id == 11) || (id == 13) || (id ==15) ){
            return 4;
        }else if(id%2 == 1) {
            return 3;
        }else {
            return 2;
        }
    }



}



















