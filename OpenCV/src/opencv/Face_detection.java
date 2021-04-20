/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import org.opencv.core.Core;  
import org.opencv.core.Mat; 
import org.opencv.core.MatOfRect; 
import org.opencv.core.Point; 
import org.opencv.core.Rect; 
import org.opencv.core.Scalar; 

import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Face_detection {
    public static void main(String[] args) {
        //load the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        //Reading the Image from the file and storing it in to a Matrix object
        Mat src= Imgcodecs.imread("C:/Users/Admin/Downloads/Java_OpenCV/testRonaldo.jpg");
        
        //Instantiating the CascadeClassifier
        //muc dich de cho may biet ma tim kiem duoc hinh dang khuon mat
        CascadeClassifier classifier = new CascadeClassifier("C:/Users/Admin/Downloads/Java_OpenCV/lbpcascade_frontalface.xml");
        
        //Detecting the face in the snap
        MatOfRect faceDetections = new MatOfRect();// MatOfRect la lop duoc ke thua tu lop Mat voi muc dich la de luu tru doi tuong
        classifier.detectMultiScale(src, faceDetections);
        
        //Drawing boxes
        for(Rect rect:faceDetections.toArray())
        {
            Imgproc.rectangle(src,// where to draw the box
                    new Point(rect.x, rect.y), // botton left
                    new Point(rect.x + rect.width, rect.y+rect.height)// top right
                    ,new Scalar(0,0,255),
                    2 // RGB color
            );
        }
        
        //Writing the image
        Imgcodecs.imwrite("C:/Users/Admin/Downloads/Java_OpenCV/detectFace_testRonaldo.jpg", src);
                
    }
}
