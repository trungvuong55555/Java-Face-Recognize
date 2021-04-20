
package opencv;

//import nu.pattern.OpenCV;
import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
//import org.opencv.videoio.VideoCapture;

import java.io.IOException;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.io.File;
import java.util.Objects;

public class Face_recognize {
    private static final File DATABASE = new File("C:\\Users\\Admin\\Downloads\\Java_OpenCV\\face");
    
    public static void main(String[] args)throws IOException
    {
      
        String nhan;
        nhan= identifyFace(detectFaces());
        System.out.println("ket qua du doan nhan vat nay la: "+ nhan);
    }
    /*
    private static void capture()
    {
         //load the OpenCV core library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        //Reading the Image from the file and storing it in to a Matrix object
        Mat src= Imgcodecs.imread("C:/Users/Admin/Downloads/Java_OpenCV/testRonaldo.jpg");
        
        //Instantiating the CascadeClassifier
        //muc dich de cho may biet ma tim kiem duoc hinh dang khuon mat
        CascadeClassifier classifier = new CascadeClassifier("C:/Users/Admin/Downloads/Java_OpenCV/lbpcascade_frontalface.xml");
        
        //Detecting the face in the snap
        MatOfRect faceDetections = new MatOfRect();
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
        //Imgcodecs.imwrite("C:/Users/Admin/Downloads/Java_OpenCV/detectFace_test2.jpg", src);
    }
    */
    private static Mat detectFaces()
    {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            
            
            Mat image= Imgcodecs.imread("C:/Users/Admin/Downloads/Java_OpenCV/testRonaldo1.jpg");
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
            
            return markedImage;
    }
    
    private static String identifyFace(Mat image)
    {
        int errorThreshold = 1;
        int mostSimilar = -1;
        File mostSimilarFile = null;

        for (File capture : Objects.requireNonNull(DATABASE.listFiles()))
        {
            int similarities = compareFaces(image, capture.getAbsolutePath());

            if (similarities > mostSimilar)
            {
                mostSimilar = similarities;
                mostSimilarFile = capture;
            }
        }

        if (mostSimilarFile != null && mostSimilar > errorThreshold)
        {
            String name = mostSimilarFile.getName();
            int socuoi=0;
            for(int i=0;i<name.length();i++)
            {
                if(name.charAt(i)>'0'&& name.charAt(i)<'9')
                {
                    socuoi= i;
                    break;
                }
            }
            String ten= name.substring(0,socuoi).trim();
            return ten;
        }
        else
            return "???";
    }
    
    private static int compareFaces(Mat currentImage, String fileName)
    {
        Mat compareImage = Imgcodecs.imread(fileName);
        ORB orb = ORB.create();
        int similarity = 0;

        MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
        MatOfKeyPoint keypoints2 = new MatOfKeyPoint();
        orb.detect(currentImage, keypoints1);
        orb.detect(compareImage, keypoints2);

        Mat descriptors1 = new Mat();
        Mat descriptors2 = new Mat();
        orb.compute(currentImage, keypoints1, descriptors1);
        orb.compute(compareImage, keypoints2, descriptors2);

        if (descriptors1.cols() == descriptors2.cols())
        {
            MatOfDMatch matchMatrix = new MatOfDMatch();
            DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE_HAMMING).match(descriptors1, descriptors2, matchMatrix);

            for (DMatch match : matchMatrix.toList())
                if (match.distance <= 50)
                    similarity++;
        }

        return similarity;
    }
    
    /*private static void saveImage(Mat image, String name)
    {
        File destination;
        String extension = ".png";
        String baseName = DATABASE + File.separator + name;
        File basic = new File(baseName + extension);

        if (!basic.exists())
            destination = basic;
        else
        {
            int index = 0;

            do
                destination = new File(baseName + " (" + index++ + ")" + extension);
            while (destination.exists());
        }

        Imgcodecs.imwrite(destination.toString(), image);
    }*/
    
    /*private static void displayFatalError(String message)
    {
        JOptionPane.showMessageDialog(null, message, "Fatal Error", JOptionPane.ERROR_MESSAGE);
    }*/
}
