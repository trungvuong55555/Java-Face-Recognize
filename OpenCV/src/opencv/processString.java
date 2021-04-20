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
public class processString {
    public static void main(String[] args) {
        String name= "messi1.jpg";
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
        
        System.out.println("chuoi thu duoc la: "+ ten);
    }
}
