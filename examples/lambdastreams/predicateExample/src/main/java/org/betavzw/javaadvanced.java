package org.betavzw;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by Jef on 2/09/2016.
 */
public class javaadvanced {
    public static void main(String[] args) {
        K3 k3 = new K3();
        String[] names = k3.filter(s -> s.charAt(1) == 'a');
        for(String n: names) {
            System.out.println(n);
        }
    }
}
class K3{
    private String[] k3= {"Karen", "Kristel", "Kathleen", "Josje", "Hanne", "Marthe", "Klaasje"};
    public String[] filter(Predicate<String> p) {
        List<String> filtered = new ArrayList<>();
        for (String n: k3) {
            if (p.test(n)){
                filtered.add(n);
            }
        }
        return filtered.toArray(new String[filtered.size()]);
    }

}
