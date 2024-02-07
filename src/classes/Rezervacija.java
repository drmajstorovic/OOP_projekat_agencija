package classes;

import java.util.ArrayList;

public class Rezervacija {

    private int id;
    private Klijent klijent;
    private Aranzman aranzman;
    private double ukupnaCijena, placeno;
    public static ArrayList<Rezervacija> all;

    public Rezervacija(int id, Klijent klijent, Aranzman aranzman, double ukupnaCijena, double placeno) {
        this.id = id;
        this.klijent = klijent;
        this.aranzman = aranzman;
        this.ukupnaCijena = ukupnaCijena;
        this.placeno = placeno;
        all.add(this);
    }

    public Rezervacija(Klijent klijent, Aranzman aranzman, double placeno){
        this(0, klijent, aranzman, racunajCijena(aranzman), placeno);
    }

    private static double racunajCijena(Aranzman aranzman){
        return aranzman.getCijena() + aranzman.getSmjestaj().getCijenaPN() * aranzman.getTrajanje();
    }

    public int getId() {
        return id;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public Aranzman getAranzman() {
        return aranzman;
    }

    public double getUkupnaCijena() {
        return ukupnaCijena;
    }

    public double getPlaceno() {
        return placeno;
    }

    public Rezervacija getFromID(int id) {
        for(Rezervacija x: all){
            if(x.getId() == id)
                return x;
        }
        return null;
    }
}