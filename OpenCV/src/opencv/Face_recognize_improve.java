package opencv;

import org.opencv.core.*; //matofrect
import org.opencv.features2d.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.IOException;
import java.io.File;
import java.util.Objects;

public class Face_recognize_improve {
    
    private File DATABASE;

    public void setDATABASE(String Path) {
        this.DATABASE= new File(Path);
    }

    public Face_recognize_improve() {
        this.DATABASE= new File("C:\\Users\\Admin\\Downloads\\Java_OpenCV\\face");
    }
    
    public Face_recognize_improve(String Path)
    {
        try{
        this.DATABASE= new File(Path);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
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
    
    private static Mat detectFaces(String file_name)
    {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            
            
            Mat image= Imgcodecs.imread(file_name);
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
                rectCrop = new Rect(rect.x, rect.y, rect.width, rect.height);
            }
            
            
            Mat markedImage = new Mat(image,rectCrop);
            
            return markedImage;
    }
    
    public String identifyFace(String file_name)
    {
        
        Mat image= detectFaces(file_name);
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
    
    public int count_Face(String file_name)
    {
        int count=0;
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            
            
        Mat image= Imgcodecs.imread(file_name); //Mat la lop matric
            
        CascadeClassifier faceDetector = new CascadeClassifier("C:/Users/Admin/Downloads/Java_OpenCV/lbpcascade_frontalface.xml");
            
 
        MatOfRect faceDetections = new MatOfRect();// mat of rect la lop ke thua tu lop mat
        faceDetector.detectMultiScale(image, faceDetections);
            
        for (Rect rect : faceDetections.toArray())
        {
            count++;
        }
            
        return count;
    }
}
