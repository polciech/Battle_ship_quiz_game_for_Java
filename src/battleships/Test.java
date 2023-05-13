package battleships;

import java.awt.*;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {



        Statek statek = new Statek();
        int j=0;
        while (true) {
            try {
                statek.generujStatki(10);
                int[][] lol = statek.getMapaKoncowa();
//                statek.pokazPlansze(plansza);
                ArrayList<Point> poczatkiStatkow= statek.getPoczatkiStatkow();
                ArrayList<Point> konceStatkow = statek.getKonceStatkow();
                for(int i = 0; i<(statek.getPoczatkiStatkow()).size(); i++){
                    System.out.println(poczatkiStatkow.get(i) + " " + konceStatkow.get(i));
                }
                System.out.println("tu sie pokazuje ile obrotow petla musiala zrobic az wygenerowala poprawna plansze: " + j);
                break;
            } catch (Exception e) {
                j++; // Loop will continue
            }
        }
        j=0;
        while (true) {
            try {
                statek.generujStatki(10);
                int[][] lol = statek.getMapaKoncowa();
//                statek.pokazPlansze(plansza);
                ArrayList<Point> poczatkiStatkow= statek.getPoczatkiStatkow();
                ArrayList<Point> konceStatkow = statek.getKonceStatkow();
                for(int i = 0; i<(statek.getPoczatkiStatkow()).size(); i++){
                    System.out.println(poczatkiStatkow.get(i) + " " + konceStatkow.get(i));
                }
                System.out.println("tu sie pokazuje ile obrotow petla musiala zrobic az wygenerowala poprawna plansze: " + j);
                break;
            } catch (Exception e) {
                j++; // Loop will continue
            }
        }
    }
}
