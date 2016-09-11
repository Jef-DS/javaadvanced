package org.betavzw.javaadvanced;






import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;


/**
 * Created by Jef on 6/09/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Singer[] singers = {new Singer(1, "Karen", "Daemen", LocalDate.of(1974, Month.OCTOBER, 28)),
                new Singer(2, "Kristel", "Verbeke", LocalDate.of(1975, Month.DECEMBER, 10)),
                new Singer(3, "Kathleen", "Aerts", LocalDate.of(1978, Month.JUNE, 18)),
                new Singer(4, "Josje", "Huisman", LocalDate.of(1986, Month.FEBRUARY, 16)),
                new Singer(5, "Hanne", "Verbruggen", LocalDate.of(1994, Month.MARCH, 3)),
                new Singer(6, "Marthe", "De Pillecyn", LocalDate.of(1996, Month.JULY, 16)),
                new Singer(7, "Klaasje", "Meijer", LocalDate.of(1995, Month.MARCH, 2))
        };
        Collector<JsonObject, ?, JsonArrayBuilder> jsonCollector
                = Collector.of(Json::createArrayBuilder, JsonArrayBuilder::add,
                (left, right) -> {
                    left.add(right);
                    return left;
                });
        JsonArray jsarray = Arrays.stream(singers)
                .map(Singer::convert)
                .collect(jsonCollector).build();
        System.out.println(jsarray.toString());
        try(FileWriter fw = new FileWriter("K3.json")){
            fw.write(jsarray.toString());
            fw.flush();
        }
        JsonParser parser = Json.createParser(new FileReader("K3.json"));
        boolean eof = false;
        List<Singer> singers2 = new ArrayList<>();
        Singer s=null;
        ParseSinger function = null;
        while(!eof) {
            JsonParser.Event event = parser.next();
            switch (event) {
                case START_ARRAY:

                    break;
                case START_OBJECT:
                    s=new Singer();
                    break;
                case KEY_NAME:
                    String key = parser.getString();
                    switch(key){
                        case "id":
                            function = (singer, text) -> singer.setId(Long.parseLong(text));
                            break;
                        case "firstname":
                            function = (singer, text) -> singer.setFirstName(text);
                            break;
                        case "lastname":
                            function = (singer, text) -> singer.setLastName(text);
                            break;
                        case "birthdate":
                            function = (singer, text) -> singer.setBirthdate(LocalDate.parse(text));
                            break;
                    }
                    break;
                case VALUE_STRING:
                    function.parse(s, parser.getString());
                    break;
                case VALUE_NUMBER:
                    function.parse(s, parser.getLong()+"");
                    break;
                case END_OBJECT:
                    singers2.add(s);
                    break;
                case END_ARRAY:
                    eof=true;
                    break;
                default:
                    eof=true;
            }
        }
        singers2.forEach(sing -> System.out.printf("%d: %s %s %s \n", sing.getId(), sing.getFirstName(), sing.getLastName(), sing.getBirthdate()));
    }
}
@FunctionalInterface
interface ParseSinger{
    void parse(Singer s, String value);
}
