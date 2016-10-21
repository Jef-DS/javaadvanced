package org.betavzw.javadvanced;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jef on 21/10/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Persoon> lijst = new ArrayList<>();
        lijst.add(new Persoon(1, "Karen", "Studio 100"));
        lijst.add(new Persoon(2, "Kristel", "Studio 100"));
        lijst.add(new Persoon(3, "Kathleenn", "Studio 100"));
        try(ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("lijst.obj"))){
            out.writeObject(lijst);
        }
        List<Persoon> lijst2;
        try(ObjectInputStream in = new ObjectInputStream(new
                FileInputStream("lijst.obj"))){
            lijst2 = (List<Persoon>)in.readObject();
        }
        lijst2.forEach(p-> System.out.printf("%d: %s%n", p.getId(), p.getNaam()));
    }
}

class Adres{
    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    private String straat;
}
class BasePersoon implements Serializable{
    public BasePersoon(){
        System.out.println("In BasePersoon");
    }
}
class Persoon extends BasePersoon implements Serializable{
    private static final long serialVersionUID= 1;
    private int id;
    private transient Adres adres = new Adres();;
    public Persoon(int id, String naam, String straat) {
        System.out.println("Persoon");
        this.id = id;
        this.naam = naam;
        adres.setStraat(straat);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    private String naam;
    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();
        out.writeObject(adres.getStraat());
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        adres = new Adres();
        adres.setStraat((String)in.readObject());
    }
}

