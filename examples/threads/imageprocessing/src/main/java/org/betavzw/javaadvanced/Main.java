package org.betavzw.javaadvanced;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.IntStream;

/**
 * Created by Jef on 9/09/2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("current path:" +Paths.get(".").toAbsolutePath());
        File f = new File("target/classes/earth-zoom.jpg");
        BufferedImage img = ImageIO.read(f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", baos);
        int width = img.getWidth();
        int height = img.getHeight();
        System.out.printf("Image (%d, %d) is loaded.%n", width, height);
        long start = System.nanoTime();
        IntStream.range(0, width * height).forEach( i ->
        {
            int row = i / width;
            int col = i - i/ width * width;//i % width;
            int pixel = img.getRGB(col, row);
            int a = pixel / (256*256*256);
            int arest = a * 256 * 256 * 256;
            int r = (pixel - arest)/(256 * 256);
            int rrest = r * 256 * 256;
            int g = (pixel - arest - rrest)/256;
            int grest = g * 256;
            int b = pixel - arest - rrest - grest;
            int avg = (r+g+b )/3;
            int newPixel = a*256*256*256 + avg * 256*256 + avg*256 + avg;
            img.setRGB(col, row, newPixel);

        });
        /*for (int i=0;i<height; i++){
            for(int j=0;j<width; j++) {
                int pixel = img.getRGB(j, i);
                int a = (pixel>>24)&0xff;
                int r = (pixel>>16)&0xff;
                int g = (pixel>>8)&0xff;
                int b = pixel&0xff;
                int avg = (r+g+b )/3;
                int newPixel = (a<<24) | (avg<<16) | avg<<8 | avg;
                img.setRGB(j, i, newPixel);
            }
        }*/
        long end = System.nanoTime();
        System.out.printf("this took %d milliseonds.%n ", (end-start)/1000000);
        File fout = new File("target/classes/earth-zoom_gray.jpg");
        ImageIO.write(img, "jpg", fout);

    }
}
