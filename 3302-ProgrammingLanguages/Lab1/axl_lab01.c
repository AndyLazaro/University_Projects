/* 
 * File:   axl_lab01.c
 * Author: Andy Lazaro
 * ID: 1001821751
 * Lang Version: C11, gcc version: 10.2.0
 * OS: Windows
 */
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>


#define FOLDER 4                //Base value for d_type, if == 4, then it is a subdirectory/folder
#define FILE 8                  //base value for d_type, if == 8, then the current path is a file
long DirSpace(DIR* directory,char* path);

int main(int argc, char** argv) 
{
    long sum = 0;               //Creating the number that will hold the size of the directory in bytes           
    DIR *dr = opendir(".");     //Creating a Dir var which holds the current working directory
    char path[256];             //Path will hold the original working directory, this is important later
    getcwd(path,sizeof(path));
    sum = DirSpace(dr,path);    //The first call to the DirSpace function
    printf("Sum of files in Directory is : %ld \n", sum);
    
    return (EXIT_SUCCESS);
}

long DirSpace(DIR* directory,char* path)        //passes the current directory being traversed AS WELL as the previous working directory
{
    long sum = 0;                               //sum for the size
    struct stat info;                           //holds various info about the given file
    struct dirent *dir;                         //used to get information about the directory currently being pointed at
    char Path[256];                             //holds the path of the current directory, also used for directory entry manipulation
    char LastKnownGood[256];                    //last known good path of directory
    strcpy(LastKnownGood, path);                //copy the path passes into the function into the last known good path in case we need to backtrack
    while((dir = readdir(directory)) != NULL)   //while the current directory still has files, do work
    {
        if(!(strcmp(dir ->d_name, ".") == 0) && !(strcmp(dir->d_name,"..") == 0))   //ignore files starting with "." and ".."
        {
            if(dir ->d_type == FOLDER)              //if current path of dir has a d_type of FOLDER then it is a directory that needs to be looked into
            {      
                strcat(path,"/");                   //add a "/" to the current path passed in
                strcat(path,dir->d_name);           //add the name of the current folder we are looking at
                DIR *dr = opendir(path);            //open the directory path concatenated with the folder path
                if(dr == NULL)                      //if the subdirectory does not exist  
                {
                    getcwd(Path,sizeof(Path));      //get the current working directory
                    strcpy(path, Path);             //replace the concatenated folder path with the original directory the program main started with
                    strcat(path,"/");               //add "/" back into it
                    strcat(path,dir->d_name);       //add the name of the next folder looked into
                    dr = opendir(path);             //open the folder
                }       
                    sum += DirSpace(dr, path);      //recursion call
                    strcpy(path,LastKnownGood);     //copy the lastknowngood path into the regular path, this is so we know if we are already in a subdirectory, and we try to concatenate a new directory
                                                    //that is not in the subdirectory, that we backtracked and went out 
            }
            if(dir ->d_type == FILE)                //if the current path is a file
            {
                strcpy(Path,path);                  //concatenate the file into the Path, this var
                strcat(Path,"/");
                strcat(Path,dir->d_name);
                if(stat(Path,&info) == -1)          //if the file does not exist
                {
                    getcwd(Path,sizeof(Path));      //grab the current working directory
                    if(stat(strcat(strcat(Path,"/"), dir ->d_name), &info) == 0)    //check to see if the file exists outside of the current path, such as the lastknowngood path
                    {                                                               //this catches files that may exist outside the current path, and are mistakenly concatenated
                                                                                    //onto the current path
                        strcpy(path,Path);
                        strcat(path,"/");
                        strcat(path,dir ->d_name);
                        stat(path,&info);
                    }
                    else                                                            //else if the file does exist in the directory
                    {
                        stat(strcat(strcat(LastKnownGood,"/"),dir-> d_name ), &info);   //grab the info of the file
                    }
                }
                long size = info.st_size;                                           //get the size of the current file from a struct
                sum += size;                                                        //add the sum of the size of the files
            }
        }
    }
    closedir(directory);                                                            //close the directory that was being traversed
    return sum;                                                                     //return the size of the directory, or subdirectory
}
