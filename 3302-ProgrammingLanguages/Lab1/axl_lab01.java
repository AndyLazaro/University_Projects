/* 
 * File:   axl_lab01.java
 * Author: Andy Lazaro
 * ID: 1001821751
 * Lang Version: JDK 16, 16.0.1
 * OS: Windows
 */
import java.io.*;
/**
 *
 * @author Andy Lazaro 1001821751
 */
public class axl_lab01
{

    public static void main(String[] args)
    {
        long sum = 0;                                                   //var to hold the size of bytes
        String cwd = System.clearProperty("user.dir");                  //grab the current working directory
       
        File info = new File(cwd);                                      //open the cwd as a file
        sum = DirSpace(info.listFiles());                               //first call to DirSpace
        System.out.println("Sum of files in directory is: " + sum);
	        
    }
    
    public static long DirSpace(File[] directory)                       //takes a file array as a parameter for traversal through the files
    {
     
        long sum = 0;
        
        for(File files: directory)                                      //for each File in the directory
        {
    
            if(files.isDirectory())                                     //if the current "files" is a directory
            {
                sum += DirSpace(files.listFiles());                     //call the function DirSpace to traverse the subdirectory
            }
            if(files.isFile())                                          //if the current files is a file
            {
                sum += files.length();                                  //add the size of the file to the sum of the directory
 
            }
        }
        return sum;                                                     //return size of directory or subdirectory
    }
}
