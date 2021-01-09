/*
    Nama    : Almas Fauzia Wibawa
    NIM     : 17/409427/PA/17734
 */
package imagetransformation;

import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import static java.lang.Math.*;

public class ImageTransformation {

    public static void main(String[] args) {
        BufferedImage img = null;
        File file;
        double gamma = 1.7;
        int c = 70;
        
        //read the image file
        try {
            file = new File("D:\\Kuliah\\Semester 5\\Tugas\\"
                    + "Pengolahan Citra Digital (1)\\ImageTransformation\\"
                    + "bw2.jpg");
            img = ImageIO.read(file);
        } catch(IOException e) {
            System.out.println(e);
        }
        
        imageNegative(img);
        powerLaw(img, gamma, c);
        
    }
    
    public static void imageNegative(BufferedImage img) {
        File file;
        
        //get image's width and height
        int width = img.getWidth();
        int height = img.getHeight();
        
        //change the pixel to its negatives
        int pixel, alpha, red, green, blue;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = img.getRGB(x, y);
                alpha = (pixel >> 24)&0xff;
                red = (pixel >> 16)&0xff;
                green = (pixel >> 8)&0xff;
                blue = pixel&0xff;
                
                red = 255 - red;
                green = 255 - green;
                blue = 255 - blue;
                
                pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                img.setRGB(x, y, pixel);
            }
        }
        
        //make the output file
        try {
            file = new File("D:\\Kuliah\\Semester 5\\Tugas\\"
                    + "Pengolahan Citra Digital (1)\\ImageTransformation\\"
                    + "outputImageNegative.jpg");
            ImageIO.write(img, "jpg", file);
        } catch(IOException e) {
            System.out.println(e);
        }
    }
    
    public static void powerLaw(BufferedImage img, double gamma, int c) {
        File file;
        
        //get image's width and height
        int width = img.getWidth();
        int height = img.getHeight();
        
        int pixel, alpha, red, green, blue;
        //get minimum and maximum value
        double redMax = -1, greenMax = -1, blueMax = -1;
        int redMin = 999, greenMin = 999, blueMin = 999;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = img.getRGB(x, y);
                alpha = (pixel >> 24)&0xff;
                red = (pixel >> 16)&0xff;
                green = (pixel >> 8)&0xff;
                blue = pixel&0xff;
                
                red = (int) (c * pow(red, gamma));
                green = (int) (c * pow(green, gamma));
                blue = (int) (c * pow(blue, gamma));
                
                if (red < redMin) {
                    redMin = red;
                }
                else if (red > redMax) {
                    redMax = red;
                }
                if (green < greenMin) {
                    greenMin = green;
                }
                else if (green > greenMax) {
                    greenMax = green;
                }
                if (blue < blueMin) {
                    blueMin = blue;
                }
                else if (blue > blueMax) {
                    blueMax = blue;
                }
            }
        }
        
        //change the pixel with the power law formula
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixel = img.getRGB(x, y);
                alpha = (pixel >> 24)&0xff;
                red = (pixel >> 16)&0xff;
                green = (pixel >> 8)&0xff;
                blue = pixel&0xff;
                
                red = (int) (c * pow(red, gamma));
                green = (int) (c * pow(green, gamma));
                blue = (int) (c * pow(blue, gamma));
                
                red = (int) ((red-redMin)/(redMax-redMin) * 255);
                green = (int) ((green-greenMin)/(greenMax-greenMin) * 255);
                blue = (int) ((blue-blueMin)/(blueMax-blueMin) * 255);
                
                pixel = (alpha << 24) | (red << 16) | (green << 8) | blue;
                img.setRGB(x, y, pixel);
            }
        }
        
        //make the output file
        try {
            file = new File("D:\\Kuliah\\Semester 5\\Tugas\\"
                    + "Pengolahan Citra Digital (1)\\ImageTransformation\\"
                    + "outputPowerLaw.jpg");
            ImageIO.write(img, "jpg", file);
        } catch(IOException e) {
            System.out.println(e);
        }
    }
}
