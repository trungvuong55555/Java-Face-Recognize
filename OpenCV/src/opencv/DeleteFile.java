/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import java.io.File;

/**
 *
 * @author Admin
 */
public class DeleteFile {
    public static void main(String[] args) {
        File myObj= new File("C:\\Users\\Admin\\Downloads\\Java_OpenCV\\test.jpg");
     if (myObj.delete()) { 
      System.out.println("Deleted the file: " + myObj.getName());
    } else {
      System.out.println("Failed to delete the file.");
    } 
    }
}
