package org.betavzw.javadvanced;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jef on 15/09/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Path srcroot = Paths.get("..", "..", "..").toAbsolutePath().normalize();
        System.out.println(srcroot);
        MyFileVisitor visitor = new MyFileVisitor("**src**java");
        Files.walkFileTree(srcroot, visitor);
        for(Path pad: visitor){
            System.out.println(pad);
        }

    }
}
class MyFileVisitor extends SimpleFileVisitor<Path> implements Iterable<Path>{
    private final List<Path> paden = new ArrayList<>();
    PathMatcher matcher;

    public MyFileVisitor(String globPatroon) {
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + globPatroon);
    }
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (file !=null && matcher.matches(file)){
            paden.add(file);
        }
        return FileVisitResult.CONTINUE;
    }
    @Override
    public Iterator<Path> iterator() {
        return paden.iterator();
    }
}

