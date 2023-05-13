package battleships;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;



public class PanelGry extends JPanel implements Runnable {
    private static Plansza plansza = new Plansza(Gra.getRozmiarPlanszy());
    private Thread gameThread;
    static int rozmiar = plansza.getRozmiarPlanszy();
    GridBagConstraints gbc = new GridBagConstraints();

    public PanelGry() throws IOException {

        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(800, 800));
        init();
    }

    public void init() throws IOException {
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.insets = new Insets(20, 20, 20, 20);


        add(plansza.getPlansza(), panelConstraints);


        BufferedImage tyt = ImageIO.read(new File("zrzut/tlo.png"));
        JLabel tytul = new JLabel(new ImageIcon(tyt));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth=3;
        tytul.setVisible(true);
        add(tytul,gbc);

        JButton przyciskStart = new JButton("Nowa gra");
        przyciskStart.setPreferredSize(new Dimension(100, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        add(przyciskStart,gbc);

        JButton przyciskZapisGra = new JButton("Gra z zapisu");
        przyciskZapisGra.setPreferredSize(new Dimension(100, 50));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        add(przyciskZapisGra,gbc);

        JButton przyciskWyjscie = new JButton("Wyjscie");
        przyciskWyjscie.setPreferredSize(new Dimension(100, 50));
        gbc.gridx = 2;
        gbc.gridy= 1;
        gbc.gridwidth=1;
        add(przyciskWyjscie,gbc);

        JButton latwy = new JButton("łatwy");
        latwy.setPreferredSize(new Dimension(100, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        latwy.setVisible(false);
        add(latwy,gbc);

        JButton sredni = new JButton( "średni");
        sredni.setPreferredSize(new Dimension(100, 50));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth=1;
        sredni.setVisible(false);
        add(sredni,gbc);

        JButton trudny = new JButton("trudny");
        trudny.setPreferredSize(new Dimension(100, 50));
        gbc.gridx = 2;
        gbc.gridy= 1;
        gbc.gridwidth=1;
        trudny.setVisible(false);
        add(trudny,gbc);



        przyciskStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                przyciskStart.setVisible(false);
                przyciskWyjscie.setVisible(false);
                przyciskZapisGra.setVisible(false);

                latwy.setVisible(true);
                sredni.setVisible(true);
                trudny.setVisible(true);
            }


        });

        przyciskZapisGra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                przyciskStart.setVisible(false);
                przyciskWyjscie.setVisible(false);
                przyciskZapisGra.setVisible(false);
            }
        });

        //latwy poziom trudnosci
        latwy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tytul.setVisible(false);
                latwy.setVisible(false);
                sredni.setVisible(false);
                trudny.setVisible(false);

                plansza.stworzPlansze();

                Statek planszaStatkow = new Statek();
                int j=0;
                while (true) {
                    try {
                        planszaStatkow.generujStatki(2);
                        break;
                    } catch (Exception g) {
                        j++; // Loop will continue
                    }
                }

                ArrayList<Point> poczatkiStatkow = planszaStatkow.getPoczatkiStatkow();
                ArrayList<Point> konceStatkow = planszaStatkow.getKonceStatkow();
                for(int i = 0; i<poczatkiStatkow.size(); i++) {
                    plansza.polozStatek(new UstawionyStatek(poczatkiStatkow.get(i), konceStatkow.get(i)));
                }
                plansza.odswiezWiersze();
                plansza.odswiezKolumny();

                for(int x = 0; x< planszaStatkow.rozmiarPlanszy; x++){
                    for(int y = 0; y<planszaStatkow.rozmiarPlanszy; y++){
                        plansza.zmienKolor(x,y);
                    }
                }

                plansza.getPlansza().setVisible(true);
            }
        });

        //sredni poziom trudnosci
        sredni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tytul.setVisible(false);
                latwy.setVisible(false);
                sredni.setVisible(false);
                trudny.setVisible(false);

                plansza.stworzPlansze();

                Statek planszaStatkow = new Statek();
                int j=0;
                while (true) {
                    try {
                        planszaStatkow.generujStatki(3);
                        break;
                    } catch (Exception g) {
                        j++; // Loop will continue
                    }
                }

                ArrayList<Point> poczatkiStatkow = planszaStatkow.getPoczatkiStatkow();
                ArrayList<Point> konceStatkow = planszaStatkow.getKonceStatkow();
                for(int i = 0; i<poczatkiStatkow.size(); i++) {
                    plansza.polozStatek(new UstawionyStatek(poczatkiStatkow.get(i), konceStatkow.get(i)));
                }
                plansza.odswiezWiersze();
                plansza.odswiezKolumny();

                for(int x = 0; x< planszaStatkow.rozmiarPlanszy; x++){
                    for(int y = 0; y<planszaStatkow.rozmiarPlanszy; y++){
                        plansza.zmienKolor(x,y);
                    }
                }

                plansza.getPlansza().setVisible(true);
            }
        });

        //poziom trudny
        trudny.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tytul.setVisible(false);
                latwy.setVisible(false);
                sredni.setVisible(false);
                trudny.setVisible(false);

                plansza.stworzPlansze();

                Statek planszaStatkow = new Statek();
                int j=0;
                while (true) {
                    try {
                        planszaStatkow.generujStatki(4);
                        break;
                    } catch (Exception g) {
                        j++; // Loop will continue
                    }
                }

                ArrayList<Point> poczatkiStatkow = planszaStatkow.getPoczatkiStatkow();
                ArrayList<Point> konceStatkow = planszaStatkow.getKonceStatkow();
                for(int i = 0; i<poczatkiStatkow.size(); i++) {
                    plansza.polozStatek(new UstawionyStatek(poczatkiStatkow.get(i), konceStatkow.get(i)));
                }
                plansza.odswiezWiersze();
                plansza.odswiezKolumny();

                for(int x = 0; x< planszaStatkow.rozmiarPlanszy; x++){
                    for(int y = 0; y<planszaStatkow.rozmiarPlanszy; y++){
                        plansza.zmienKolor(x,y);
                    }
                }

                plansza.getPlansza().setVisible(true);
            }
        });

        przyciskWyjscie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });

        gameThread = new Thread(this);
        gameThread.start();

}

    @Override
    public void run() {
        System.out.println("1");
        System.out.println(plansza.getTypyGracza());
        while(!plansza.sprawdzWygrana()) {
            if (plansza.sprawdzWygrana()) {
                plansza.getPlansza().setVisible(false);
            }
        }
        System.out.println("1");
    }
    public static void eksportujJpeg (PanelGry component){

        BufferedImage obraz = new BufferedImage(component.getWidth(),component.getHeight(),BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = obraz.createGraphics();
        component.printAll(g2d);
        try{
            String nazwa = JOptionPane.showInputDialog("Podaj nazwę pliku:");
            ImageIO.write(obraz, "jpg", new File( "zrzut/"+nazwa+".jpg"));
        }catch (IOException e){
            e.printStackTrace();
        }
        //Release graphic objects
        g2d.dispose();
    }
    public static void  zapisywanieGry(){
        JFrame g = new JFrame();
        File zapis = new File("saves/zapis.txt");
        File rozw = new File("saves/rozwiazanie.txt");
       StringBuilder budowa = new StringBuilder();
       StringBuilder rozwiazanie = new StringBuilder();
        for(int x = 1; x<rozmiar; x++) {
            for (int y = 1; y < rozmiar; y++) {
                budowa.append(plansza.getTypyGracza()[x][y]+"");
                if(y < rozmiar-1 ){budowa.append(",");}
            }
            budowa.append("\n");
        }

        for(int x = 1; x<rozmiar; x++) {
            for (int y = 1; y < rozmiar; y++) {
                rozwiazanie.append(plansza.getCzyStatek()[x][y]+"");
                if(y < rozmiar - 1){rozwiazanie.append(",");}
            }
            rozwiazanie.append("\n");
        }


        try(FileWriter writer = new FileWriter(zapis);
            BufferedWriter bw = new BufferedWriter(writer);
            FileWriter writer1 = new FileWriter(rozw);
            BufferedWriter bf = new BufferedWriter(writer1)){
            bw.write(budowa.toString());
            bf.write(rozwiazanie.toString());
            bw.close();
            bf.close();


            JOptionPane.showMessageDialog(g,"Poprawnie zapisano grę mordzia");

        } catch (IOException ioe) {
            System.err.format("IOException: %s%n",ioe);
        }
}}


