package org.betavzw.javaadvanced;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Created by Jef on 7/09/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        List<Singer> singers = new ArrayList<>();
        FileFinder csvFinder = new FileFinder("**.csv", p ->{
                try(Stream<String> lines = Files.lines(p)){
                    lines.forEach( line -> {
                        Singer s = Singer.createSinger(line);
                        singers.add(s);
                    });
            }
        });
        Path srcRoot = Paths.get(".").toAbsolutePath().normalize();
        System.out.println("Searching in " + srcRoot);
        Files.walkFileTree(srcRoot, csvFinder);
        for(Singer s: singers) {
            System.out.printf("%s %s %n", s.getFirstname(), s.getLastname());
        }

        System.out.println("====");
        Files.find(srcRoot, Integer.MAX_VALUE,(path, attrs) -> attrs.isRegularFile() && path.toString().endsWith(".csv"))
                .forEach(p -> {
                    try {
                        Files.lines(p).forEach(line ->
                        {
                            Singer s = Singer.createSinger(line);
                            System.out.printf("%s %s %n", s.getFirstname(), s.getLastname());
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}

interface MyConsumer{
    void accept(Path p) throws IOException;
}
class FileFinder extends SimpleFileVisitor<Path>  {
    private PathMatcher pathMatcher;
    //private Consumer<Path> consumer;
    private MyConsumer consumer;
    public FileFinder(String pattern, MyConsumer consumer){
        pathMatcher = FileSystems.getDefault().getPathMatcher("glob:"+pattern);
        this.consumer = consumer;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file != null && pathMatcher.matches(file)) {
            consumer.accept(file);
        }
        return FileVisitResult.CONTINUE;
    }

}
class Singer{
    private String firstname;
    private String lastname;

    public static Singer createSinger(String line){
        String[] names = line.split(";");
        Singer s = new Singer(names[0], names[1]);
        return s;
    }
    public Singer(){}
    public Singer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
