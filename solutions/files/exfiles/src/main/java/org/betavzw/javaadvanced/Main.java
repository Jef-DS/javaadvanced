package org.betavzw.javaadvanced;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jef on 7/09/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        FileFinder csvFinder = new FileFinder("**.csv");
        Path srcRoot = Paths.get("..").normalize();
        Files.walkFileTree(srcRoot, csvFinder);
        List<Singer> singers = new ArrayList<>();
        for(Path p: csvFinder){
            Files.lines(p).forEach( line -> {
                Singer s = Singer.createSinger(line);
                singers.add(s);
            });
        }
        for(Singer s: singers) {
            System.out.printf("%s %s \n", s.getFirstname(), s.getLastname());
        }
    }
}

class FileFinder extends SimpleFileVisitor<Path> implements Iterable<Path> {
    private PathMatcher pathMatcher;
    private List<Path> foundPaths = new ArrayList<>();
    public FileFinder(String pattern){
        pathMatcher = FileSystems.getDefault().getPathMatcher("glob:"+pattern);
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file != null && pathMatcher.matches(file)) {
            foundPaths.add(file);
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
