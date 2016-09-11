package org.betavzw.javaadvanced;

import java.time.LocalDate;

/**
 * Created by Jef on 24/08/2016.
 */
public class CoContraVarianceArrays {
    public static void main(String[] args) {
        //Covariant arrays reading
        String[] K3_old = {"Karen", "Kristel", "Kathleen"};
        String[] K3_less_old = {"Karen", "Kristel", "Josje"};
        if (equalArrays(K3_old, K3_less_old)){
            System.out.println("Arrays are equal");
        }else {
            System.out.println("Arrays are not equal");
        }
        //ArrayStoreException at runtime while writing
        doSomethingWithArray(K3_old);

    }

    //works CoVariant: also OK for child classes
    public static boolean equalArrays(Object[] o1, Object[] o2) {
        //skip null checking
        if (o1.length != o2.length) return false;
        for(int i=0;i<o1.length;i++){
            if (! o1[i].equals(o2[i])) return false;
        }
        return true;
    }
    public static void doSomethingWithArray(Object[] objects) {
        //reads are OK
        Object o = objects[0];
        System.out.println("First object is "+o);
        //writes give error at runtime
        //objects[0] = LocalDate.now();
    }


}
