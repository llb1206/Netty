package com.netty.c1;


import java.awt.AWTException;

import java.awt.Rectangle;

import java.awt.Robot;

import java.awt.Toolkit;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import javax.imageio.ImageIO;

public class jawoieio {
    public static void main(String[] args) {
        try {
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(); //要截取的宽度

            int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(); //要截取的高度

            Robot robot = new Robot();

            BufferedImage image = robot.createScreenCapture(new Rectangle(width, height));

            image = image.getSubimage(0, 0, 2000, 2000);
            String path ="C:/Users/Administrator/Desktop/Task/" + "date" ;
            ImageIO.write(image, "png", new File(path));

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
