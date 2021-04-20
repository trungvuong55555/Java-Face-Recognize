/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;



/**
 *
 * @author Admin
 */
public class demo {
    public static void main(String[] args) {
       
            Face_recognize_improve face= new Face_recognize_improve();
            System.out.println("nhan vat trong buc anh nay la: "+ face.identifyFace("C:\\Users\\Admin\\Downloads\\Java_OpenCV\\testDrogba.jpg"));
    }
}
