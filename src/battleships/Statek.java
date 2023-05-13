package battleships;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Statek {
    private Point poczatekStatku;
    private Point koniecStatku;
    ArrayList<Point> poczatkiStatkow = new ArrayList<Point>();
    ArrayList<Point> konceStatkow = new ArrayList<Point>();
    int rozmiarPlanszy = Gra.getRozmiarPlanszy();
    int[][] mapaPoczatkowa = new int[rozmiarPlanszy+1][rozmiarPlanszy+1];
    int[][] mapaKoncowa = new int[rozmiarPlanszy+1][rozmiarPlanszy+1];

    public void generujStatki(int liczbaStatkow){ //liczba statkow to tez maksymalna dlugosc tych statkow

        Random rand = new Random();

        for(int i = liczbaStatkow; i>0; i--){
            int xStatku = rand.nextInt(rozmiarPlanszy); // polozenie poczatku statku
            int yStatku = rand.nextInt(rozmiarPlanszy);
            boolean kierunekStatku = rand.nextBoolean(); //true - pion, false - poziom

            if(kierunekStatku == true){
                if( yStatku + i >= rozmiarPlanszy+1){ // zabezpieczenie zeby nie wyjsc poza plansze, ale tylko troche, trzeba poustwiac poziomy trudnosci potem
                    yStatku = yStatku - i;
                }
            }
            else{
                if(xStatku + i >= rozmiarPlanszy+1){
                    xStatku = xStatku - i;
                }
            }
            boolean wolnePole = true;

            if(kierunekStatku == true){
                for(int m = yStatku; m < yStatku + i; m++){
                    if(mapaPoczatkowa[m][xStatku] != 0){
                        wolnePole = false;
                        break;
                    }
                }
            }
            else{
                for (int n = xStatku; n < xStatku+i; n++){
                    if(mapaPoczatkowa[yStatku][n] != 0){
                        wolnePole = false;
                        break;
                    }
                }
            }

            if(wolnePole == false){ // sprawdza czy któreś z pól, na które natrafiły pętle są już zajęte, a jeśli tak to wydłuża działanie całej pętli tak by wszystkie statki się wygenerowały na mapie
                i++;
                continue;
            }

            if(kierunekStatku == true){
                for(int m = -1; m<2; m++){
                    for(int n = -1; n<i+1; n++){
                        if(xStatku+m<rozmiarPlanszy+1){
                            if(yStatku+n<rozmiarPlanszy+1){
                                if(m!=0){
                                    if(n!=0){
                                        mapaPoczatkowa[yStatku+n][xStatku+m] = -1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else{
                for(int m = -1; m<i+1; m++){
                    for(int n = -1; n<2; n++){
                        if(xStatku+m<rozmiarPlanszy+1){
                            if(yStatku+n<rozmiarPlanszy+1){
                                if(m!=0){
                                    if(n!=0){
                                        mapaPoczatkowa[yStatku+n][xStatku+m] = -1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            for(int j = 0; j<i; j++){
                mapaPoczatkowa[yStatku][xStatku] = i;
                if(j==0){
                    poczatkiStatkow.add(new Point(xStatku, yStatku));
                }

                if(kierunekStatku == true){
                    if(j+1 == i){
                        konceStatkow.add(new Point(xStatku, yStatku));
                    }
                    yStatku++;
                }
                else
                {
                    if(j+1 == i){
                        konceStatkow.add(new Point(xStatku, yStatku));
                    }
                    xStatku++;
                }
            }
        }
        for(int i = 0; i<rozmiarPlanszy+1; i++){
            for(int j = 0; j<rozmiarPlanszy+1; j++){
                if(mapaPoczatkowa[i][j] == 0){
                    mapaKoncowa[i][j] = 0;
                }
                else if(mapaPoczatkowa[i][j] == -1){
                    mapaKoncowa[i][j] = 0;
                }
                else{
                    mapaKoncowa[i][j] = 1;
                }
            }
        }
//        return mapaKoncowa;
    }

    public void pokazPlansze(int plansza[][]){
        for(int i = 0; i<plansza.length; i++){
            for(int j = 0; j<plansza.length; j++){
                System.out.print(plansza[i][j]);
            }
            System.out.println();
        }
    }


    public ArrayList<Point> getPoczatkiStatkow() {
        return poczatkiStatkow;
    }

    public ArrayList<Point> getKonceStatkow() {
        return konceStatkow;
    }

    public int[][] getMapaPoczatkowa() {
        return mapaPoczatkowa;
    }

    public int[][] getMapaKoncowa() {
        return mapaKoncowa;
    }
}
