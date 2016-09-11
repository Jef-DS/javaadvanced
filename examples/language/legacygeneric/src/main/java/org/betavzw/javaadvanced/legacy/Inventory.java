package org.betavzw.javaadvanced.legacy;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jef on 1/09/2016.
 */
public class Inventory {
    private Collection parts = new ArrayList();
    public void addCollection(Collection parts){
        this.parts.addAll(parts);
    }
    public Collection getCollection(){
        return this.parts;
    }
}
