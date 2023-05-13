package battleships;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Plansza  {
    private final int rozmiarPlanszy;
    private JPanel plansza;
    private JButton[][] listaKomorek;
    private int[][] czyStatek;
    private int[][] typyGracza;
    Statek statek = new Statek();
    JFrame f = new JFrame();

    //konstruktor
    public Plansza(int rozmiarPlanszy) {
        this.rozmiarPlanszy = rozmiarPlanszy;
        this.setPlansza(new JPanel());
        this.getPlansza().setLayout(new GridLayout(this.getRozmiarPlanszy()+1, this.getRozmiarPlanszy()+1));
        this.setListaKomorek(new JButton[this.getRozmiarPlanszy()+1][this.getRozmiarPlanszy()+1]);
        this.setCzyStatek(new int[this.getRozmiarPlanszy()+1][this.getRozmiarPlanszy()+1]);
        this.setTypyGracza(new int[this.getRozmiarPlanszy()+1][this.getRozmiarPlanszy()+1]);
    }

    //generacja planszy
    public void stworzPlansze() {
        for(int i=0; i < this.getRozmiarPlanszy() + 1; i++) {
            for(int j=0; j < this.getRozmiarPlanszy() + 1; j++) {
                this.getListaKomorek()[i][j] = new JButton();
                this.getListaKomorek()[i][j].setPreferredSize(new Dimension(35, 35));

                if (i == 0) {
                    this.getListaKomorek()[i][j].setBackground(Color.WHITE);
                    this.getListaKomorek()[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 3));
                    this.getListaKomorek()[i][j].setEnabled(false);
                }

                else if (j == 0) {
                    this.getListaKomorek()[i][j].setBackground(Color.WHITE);
                    this.getListaKomorek()[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 3));
                    this.getListaKomorek()[i][j].setEnabled(false);
                }

                else {
                    this.getListaKomorek()[i][j].setBackground(Color.gray);
                    this.getListaKomorek()[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 3));
                    this.getTypyGracza()[i][j] = 0;
                    this.getCzyStatek()[i][j] = 0;

                    int finalI = i;
                    int finalJ = j;

                    this.getListaKomorek()[i][j].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            zmienKomorke(finalI, finalJ);
                        }
                    });
                }

                this.getPlansza().add(this.getListaKomorek()[i][j]);
            }
        }
    }

    //funkcja zmiany rodzaju komorki
    public void zmienKomorke (int rzad, int kolumna) {
        Color kolor = this.getListaKomorek()[rzad][kolumna].getBackground();

        if (kolor == Color.gray) {
            this.getListaKomorek()[rzad][kolumna].setBackground(Color.BLACK);
            this.getTypyGracza()[rzad][kolumna] = 1;
            if(this.sprawdzWygrana()){
                System.out.println(1);
                JOptionPane.showMessageDialog(f,"WE ARE WINNIG DAD");
            }
            else if(this.sprawdzWierszeKolumny()){
                System.out.println(1);
                JOptionPane.showMessageDialog(f,"WE ARE WINNIG DAD");
            }
        }

        else if (kolor == Color.BLUE) {
            this.getListaKomorek()[rzad][kolumna].setBackground(Color.BLACK);
            this.getTypyGracza()[rzad][kolumna] = 1;
            if(this.sprawdzWygrana()){
                System.out.println(1);
                JOptionPane.showMessageDialog(f,"WE ARE WINNIG DAD");
            }
            else if(this.sprawdzWierszeKolumny()){
                System.out.println(1);
                JOptionPane.showMessageDialog(f,"WE ARE WINNIG DAD");
            }
        }

        else if (kolor == Color.BLACK) {
            this.getListaKomorek()[rzad][kolumna].setBackground(Color.gray);
            this.getTypyGracza()[rzad][kolumna] = 0;
            if(this.sprawdzWygrana()){
                System.out.println("we are winning son");
            }
        }
    }

    public void zmienKolor (int rzad, int kolumna) {
            this.getListaKomorek()[rzad][kolumna].setBackground(Color.gray);
            this.getTypyGracza()[rzad][kolumna] = 0;
    }

    public void polozStatek(UstawionyStatek statek) {
        for (int i= (int) statek.getPoczatekStatku().getY() ; i <= (int) statek.getKoniecStatku().getY(); i++) {
            for (int j = (int) statek.getPoczatekStatku().getX() ; j <= (int) statek.getKoniecStatku().getX(); j++) {
                this.getListaKomorek()[i][j].setBackground(Color.BLACK);
                //this.getListaKomorek()[i][j].setEnabled(false);
                this.getCzyStatek()[i][j] = 1;
            }
        }
    }

    public int ileWWierszu(int wiersz) {
        int licznik = 0;

        for (int j = 0; j < this.getRozmiarPlanszy() + 1; j++) {
            if (this.getCzyStatek()[wiersz][j] == 1) {
                licznik++;
            }
        }
        return licznik;
    }

    public int ileWKolumnie(int kolumna) {
        int licznik = 0;

        for (int i = 0; i < this.getRozmiarPlanszy() + 1; i++) {
            if (this.getCzyStatek()[i][kolumna] == 1) {
                licznik++;
            }
        }
        return licznik;
    }

    public int ileWWierszuTypyGracza(int wiersz) {
        int licznik = 0;

        for (int j = 0; j < this.getRozmiarPlanszy() + 1; j++) {
            if (this.getTypyGracza()[wiersz][j] == 1) {
                licznik++;
            }
        }
        return licznik;
    }

    public int ileWKolumnieTypyGracza(int kolumna) {
        int licznik = 0;

        for (int i = 0; i < this.getRozmiarPlanszy() + 1; i++) {
            if (this.getTypyGracza()[i][kolumna] == 1) {
                licznik++;
            }
        }
        return licznik;
    }

    public void odswiezWiersze() {
        for (int i = 0; i < this.getRozmiarPlanszy()+1; i++) {
            if (i != 0) {
                this.getListaKomorek()[i][0].setText(ileWWierszu(i) + "");
                this.getListaKomorek()[i][0].setText("<html><font size = 6>" + ileWWierszu(i) + "</font></html>");
            }
        }
    }

    public void odswiezKolumny() {
        for (int j = 0; j < this.getRozmiarPlanszy()+1; j++) {
            if (j != 0) {
                this.getListaKomorek()[0][j].setText("<html><font size = 6>" + ileWKolumnie(j) + "</font></html>");
            }
        }
    }

    public boolean sprawdzWygrana() {
        boolean sprawdzenie = true;
        for(int x = 0; x<rozmiarPlanszy; x++){
            for(int y=0; y<rozmiarPlanszy; y++){
                if((this.getTypyGracza())[x][y]!=(this.getCzyStatek())[x][y]){
                    sprawdzenie = false;
                }
            }
        }
        if(sprawdzenie == false){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean sprawdzWierszeKolumny() {
        boolean sprawdzenie = true;
        for(int i = 0; i<rozmiarPlanszy; i++){
            if(ileWWierszu(i) != ileWWierszuTypyGracza(i)){
                sprawdzenie = false;
            }
            else if(ileWKolumnie(i) != ileWKolumnieTypyGracza(i)){
                sprawdzenie = false;
            }
        }
        if(sprawdzenie == false){
            return false;
        }
        else{
            return true;
        }
    }

    //potrzebne gettery oraz settery
    public JPanel getPlansza() {
        return this.plansza;
    }

    public JButton[][] getListaKomorek() {
        return this.listaKomorek;
    }

    public int getRozmiarPlanszy() {
        return  this.rozmiarPlanszy;
    }

    public void setPlansza(JPanel panel) {
        this.plansza = panel;
    }

    public void setListaKomorek(JButton[][] lista) {
        this.listaKomorek = lista;
    }

    public int[][] getCzyStatek() {
        return czyStatek;
    }

    public void setCzyStatek(int[][] czyStatek) {
        this.czyStatek = czyStatek;
    }

    public int[][] getTypyGracza() {
        return typyGracza;
    }

    public void setTypyGracza(int[][] typyGracza) {
        this.typyGracza = typyGracza;
    }
    public void getKolor(JButton kolor){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plansza)) return false;
        Plansza plansza = (Plansza) o;
        return Arrays.equals(getCzyStatek(), plansza.getCzyStatek()) && Arrays.equals(getTypyGracza(), plansza.getTypyGracza());
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(getCzyStatek());
        result = 31 * result + Arrays.hashCode(getTypyGracza());
        return result;
    }
}
