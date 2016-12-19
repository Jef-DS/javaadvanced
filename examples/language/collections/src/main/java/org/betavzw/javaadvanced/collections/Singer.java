/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betavzw.javaadvanced.collections;

/**
 *
 * @author Jef
 */
public class Singer {
    private int id;
    private String naam;

    @Override
    public String toString() {
        return "Zangeres{" + "id=" + id + ", naam=" + naam + '}';
    }

    public Singer(int id, String naam) {
        this.id = id;
        this.naam = naam;
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
}
