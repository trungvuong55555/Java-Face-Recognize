
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


public class CutFace {
    public static void main(String[] args) {
        //load the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        //Reading the Image from the file and storing it in to a Matrix object
       
        int n=10;
        String name="ronaldo";
        for(int i=1;i<=n;i++)
        {
            Mat image= Imgcodecs.imread("C:\\Users\\Admin\\Downloads\\Java_OpenCV\\image\\"+ name + i +".jpg");
            //Instantiating the CascadeClassifier
        //muc dich de cho may biet ma tim kiem duoc hinh dang khuon mat
            CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Admin/Downloads/Java_OpenCV/lbpcascade_frontalface.xml");
            
            // Detecting faces
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);
            
            // Creating a rectangular box showing faces detected
            Rect rectCrop=null;
            for (Rect rect : faceDetections.toArray())
            {
                Imgproc.rectangle(image, new Point(rect.x, rect.y),
                 new Point(rect.x + rect.width, rect.y + rect.height),
                                               new Scalar(0, 255, 0));
                rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
            }
            
            
            Mat markedImage = new Mat(image,rectCrop);
            Imgcodecs.imwrite("C:\\Users\\Admin\\Downloads\\Java_OpenCV\\face\\"+name+i+".jpg",markedImage );
            
        }
    }
}
