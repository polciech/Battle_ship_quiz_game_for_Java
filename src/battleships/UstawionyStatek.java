package battleships;

import java.awt.*;

public class UstawionyStatek {
    private Point poczatekStatku;
    private Point koniecStatku;

    public UstawionyStatek(Point poczatekStatku, Point koniecStatku) {
        this.setPoczatekStatku(poczatekStatku);
        this.setKoniecStatku(koniecStatku);
    }

    public Point getPoczatekStatku() {
        return poczatekStatku;
    }

    public Point getKoniecStatku() {
        return koniecStatku;
    }

    public void setKoniecStatku(Point koniecStatku) {
        this.koniecStatku = koniecStatku;
    }

    public void setPoczatekStatku(Point poczatekStatku) {
        this.poczatekStatku = poczatekStatku;
    }
}
