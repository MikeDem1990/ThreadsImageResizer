package com.ImageScThread.app;


import java.io.File;
import java.util.Objects;


public class App {

    public static void main(String[] args) {

        int newWidth = 600;

        //папка исходная
        String originalFolder = "C:\\Users\\1\\Documents\\Java_HomeWork\\11 модуль\\11.4 Домашняя работа 11.1\\image";
        String targetFolder = "C:\\Users\\1\\Documents\\Java_HomeWork\\11 модуль\\11.4 Домашняя работа 11.1\\imageTarget";

        File srcDir = new File(originalFolder);



        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();



        int step = Objects.requireNonNull(files).length / Runtime.getRuntime().availableProcessors();

        int var = 0;
        File[] temp = new File[step];

        for (File file : files) {
            temp[var++] = file;
            if (var == step) {
               new Thread( new ImageResizer(temp,newWidth,originalFolder,targetFolder,start)).start();
                temp = new File[step];
                var = 0;
            }
        }


    }

}