package battleships;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static battleships.PanelGry.eksportujJpeg;
import static battleships.PanelGry.zapisywanieGry;

public class Gra {
    private final static int rozmiarPlanszy = 15;



    public static void main(String[] args) throws IOException {
        JFrame ramka = new JFrame("Battleships puzzle");
        JFrame f = new JFrame();
        ramka.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelGry panel = new PanelGry();
        ramka.getContentPane().add(panel);
        ramka.setPreferredSize(new Dimension(800, 800));
        ramka.setResizable(false);
        ramka.pack();
        ramka.setLocationRelativeTo(null);
        ramka.setVisible(true);


        JMenuBar menu = new JMenuBar();
        ramka.setJMenuBar(menu);
        menu.setVisible(true);

        JMenu opcjeGry = new JMenu("Opcje gry");
        menu.add(opcjeGry);

        JMenuItem wyjscieBezZapisu = new JMenuItem("Wyjdz bez zapisu");
        opcjeGry.add(wyjscieBezZapisu);

        JMenuItem zapisGry = new JMenuItem("Zapisz grę");
        opcjeGry.add(zapisGry);

        JMenuItem eksport = new JMenuItem("Wyeksportuj obrazek gry");
        opcjeGry.add(eksport);

        wyjscieBezZapisu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        eksport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eksportujJpeg(panel);

            }
        });

        zapisGry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zapisywanieGry();
            }
        });

//        public static void  zapisywanieGry(){
//
//                try(FileWriter writer = new FileWriter(zapis);
//                    BufferedWriter bw = new BufferedWriter(writer)){
//                    bw.write(String.valueOf(rozmiarPlanszy));
//
//                    JOptionPane.showMessageDialog(f,"Poprawnie zapisano grę mordzia");
//
//                } catch (IOException ioe) {
//                    System.err.format("IOException: %s%n",ioe);
//                }


    }


    public static int getRozmiarPlanszy() {
        return rozmiarPlanszy;
    }




}
