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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Path srcRoot = Paths.get(".").toAbsolutePath().normalize();
        System.out.println("Searching in " + srcRoot);
        Files.walkFileTree(srcRoot, csvFinder);
        for(Path p: csvFinder){
        }
        for(Singer s: singers) {
            System.out.printf("%s %s \n", s.getFirstname(), s.getLastname());
        }
    }
}

class FileFinder extends SimpleFileVisitor<Path> implements Iterable<Path> {
    private PathMatcher pathMatcher;
    private List<Path> foundPaths = new ArrayList<>();
    private Consumer<Path> consumer;
    public FileFinder(String pattern, Consumer<Path> consumer){
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

    @Override
    public Iterator<Path> iterator() {
        return foundPaths.iterator();
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
