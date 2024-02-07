package classes;

import java.util.ArrayList;

public class BankovniRacun {

    private String brojRacuna, JMBG;
    private Double stanje;
    public static ArrayList<BankovniRacun> all;

    public BankovniRacun(String brojRacuna, String JMBG, Double stanje){
        this.brojRacuna = brojRacuna;
        this.JMBG = JMBG;
        this.stanje = stanje;
        all.add(this);
    }

    public String getBrojRacuna() {
        return brojRacuna;
    }

    public String getJMBG() {
        return JMBG;
    }

    public Double getStanje() {
        return stanje;
    }

    public BankovniRacun getFromID(String br) {
        for(BankovniRacun x: all){
            if(x.getBrojRacuna().equals(br))
                return x;
        }
        return null;
    }

    public BankovniRacun getFromJMBG(String br) {
        for(BankovniRacun x: all){
            if(x.getBrojRacuna().equals(br))
                return x;
        }
        return null;
    }
}