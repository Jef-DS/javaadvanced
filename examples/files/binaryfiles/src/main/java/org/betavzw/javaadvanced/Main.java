package org.betavzw.javaadvanced;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jef on 6/09/2016.
 */
public class Main {
    private static final int NAME_MAX_LENGTH =50;
    public static void main(String[] args) throws IOException {
        Singer[] singers = {new Singer(1, "Karen", "Daemen", LocalDate.of(1974, Month.OCTOBER, 28)),
                new Singer(2, "Kristel", "Verbeke", LocalDate.of(1975, Month.DECEMBER, 10)),
                new Singer(3, "Kathleen", "Aerts", LocalDate.of(1978, Month.JUNE, 18)),
                new Singer(4, "Josje", "Huisman", LocalDate.of(1986, Month.FEBRUARY, 16)),
                new Singer(5, "Hanne", "Verbruggen", LocalDate.of(1994, Month.MARCH, 3)),
                new Singer(6, "Marthe", "De Pillecyn", LocalDate.of(1996, Month.JULY, 16)),
                new Singer(7, "Klaasje", "Meijer", LocalDate.of(1995, Month.MARCH, 2))
        };
        try(DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("K3.bin")))){
            for(Singer s: singers){
                out.writeLong(s.getId());
                writeString(out, s.getFirstName(), NAME_MAX_LENGTH);
                writeString(out, s.getLastName(), NAME_MAX_LENGTH);
                out.writeInt(s.getBirthdate().getYear());
                out.writeInt(s.getBirthdate().getMonth().ordinal());
                out.writeInt(s.getBirthdate().getDayOfMonth());
            }
        }
        List<Singer> singerList = new ArrayList<>();
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream("K3.bin")))){
            Month[] months = Month.values();
            while (true) {
                long id = in.readLong();
                String firetname = readString(in, NAME_MAX_LENGTH);
                String lastName = readString(in, NAME_MAX_LENGTH);
                int year = in.readInt();
                Month month = months[in.readInt()];
                int day = in.readInt();
                Singer s = new Singer(id, firetname, lastName, LocalDate.of(year, month, day));
                singerList.add(s);
            }
        }catch(EOFException ex) {
            System.out.println("End of file reached");
        }
        singerList.forEach(s -> System.out.printf("%d: %s %s %s\n", s.getId(), s.getFirstName(), s.getLastName(), s.getBirthdate()));

    }
    private static String readString(DataInputStream in, int max_len) throws IOException {
        char[] chars = new char[max_len];
        int charPosition = 0;
        char character = in.readChar();
        while (character != 0){
            chars[charPosition] = character;
            charPosition++;
            character = in.readChar();
        }
        in.skipBytes((NAME_MAX_LENGTH -charPosition - 1)*2);
        String text = String.valueOf(chars, 0, charPosition);
        return text;
    }
    private static void writeString(DataOutputStream out, String text, int max_len) throws IOException {
        out.writeChars(text);
        for (int i=text.length(); i< max_len;i++){
            out.writeChar(0);
        }
    }
}
