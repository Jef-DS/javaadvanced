/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betavzw.javaadvanced.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Jef
 */
public class Main {
    public static void main(String[] args) {
        String[] names = {"Hanne", "Marthe", "Klaasje"};
        List<Singer> K3 = new ArrayList<Singer>();
        for(int i=0;i<names.length;i++){
            Singer z = new Singer(i, names[i]);
            K3.add(z);
        }
        //alternative: K3 = Arrays.asList(names);
        //or: K3=new ArrayList(Arrays.asList(names));
        Singer z = new Singer(1, "Karen");
        K3.add(z);
        for(Singer zangeres: K3){
            System.out.println(zangeres);
        }
        //Alternative using iterator
        Iterator<Singer> iter = K3.iterator();
        while(iter.hasNext()){
            Singer zangeres = iter.next();
            System.out.println(zangeres);
        }
        Map<Integer, Singer> map = new HashMap<Integer, Singer>();
        for(Singer zangeres: K3){
            map.put(z.getId(), z);
        }
        for(Integer key: map.keySet()){
            Singer zangeres = K3.get(key);
            System.out.println(zangeres);
        }
    }
    
}
