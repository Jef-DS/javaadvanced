package org.betavzw.javaadvanced;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Jef on 24/08/2016.
 */
public class VarArgs {
    public static void main(String[] args) {
        Group K3_old = new Group(new String[]{"Karen", "Kristel", "Kathleen"});
        Group K3_new = new Group("Karen", "Kristel", "Kathleen");
        System.out.println("Using older syntax:");
        for (String s: K3_old){
            System.out.println(s);
        }
        System.out.println("Using newer syntax");
        for(String s: K3_new){
            System.out.println(s);
        }

    }
}
class Group implements Iterable<String> {
    private final String[] participants;

    public Group(String[] participants) {
        if (participants.length == 0) throw new IllegalArgumentException("At least one participant is needed.");
        this.participants = participants.clone();
    }
    public Group(String p1, String... participants) {
        this.participants = new String[participants.length + 1];
        this.participants[0] = p1;
        System.arraycopy(participants, 0, this.participants, 1, participants.length);
    }

    @Override
    public Iterator<String> iterator() {
        return Arrays.asList(this.participants).iterator();
    }
}
