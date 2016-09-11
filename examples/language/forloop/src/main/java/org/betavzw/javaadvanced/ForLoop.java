package org.betavzw.javaadvanced;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jef on 23/08/2016.
 */
public class ForLoop {
    public static void main(String[] args) {
        K3 k3 = new K3();
        // before Java 1.5
        for (Iterator<String> it = k3.iterator();it.hasNext();){
            System.out.println(it.next());
        }
        //enhanced for
        for (String name :k3) {
            System.out.println(name);
        }
    }
}

class K3 implements Iterable<String> {
    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>(){
            private String current = "";
            @Override
            public boolean hasNext() {
                return ! "Kathleen".equals(current);
            }

            @Override
            public String next() {
                switch(current) {
                    case "":
                        current="Karen";
                        break;
                    case "Karen":
                        current="Kristel";
                        break;
                    case "Kristel":
                        current="Kathleen";
                        break;
                    case "Kathleen":
                        throw new NoSuchElementException();
                }
                return current;
            }
        };
    }

}
