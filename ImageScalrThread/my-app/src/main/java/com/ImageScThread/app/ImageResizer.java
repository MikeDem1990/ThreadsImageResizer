package com.ImageScThread.app;

//import jdk.javadoc.internal.doclets.toolkit.util.DocFinder;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageResizer implements Runnable {



    private File[] files;
    private int newWidth;
    private String folder;
    private String newfolder;
    private long start;


    public ImageResizer(File[] files, int newWidth, String originalfolder, String folder, long start) {


        this.files = files;
        this.newWidth = newWidth;
        this.folder = originalfolder;
        this.start = start;
        this.newfolder = folder;

    }



    @Override
    public  void run() {

        try {

            int i = 0;



            for (File fileImg : files) {
                BufferedImage image = ImageIO.read(fileImg);

                if (image == null)
                {
                    continue;
                }

                BufferedImage resizeImage = Scalr.resize(image, newWidth );

                File resizedFiled = new File(newfolder + "/" + fileImg.getName());
                ImageIO.write(resizeImage,"jpg", resizedFiled);


                i++;
                image.flush();
                resizeImage.flush();



            }



        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println(
                Thread.currentThread().getName() + " finish -  duration: " + (System.currentTimeMillis() - start));

    }
}
