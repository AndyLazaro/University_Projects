"""
 * File:   axl_lab01.py
 * Author: Andy Lazaro
 * ID: 1001821751
 * Lang Version: 3.9
 * OS: Windows
"""

import os


def main():
    sumS = 0  # create the var for size
    sumS = DirSpace(os.getcwd())  # first call for DirSpace
    print("Sum of Directory is: %d" % (sumS))


def DirSpace(target):  # takes the path of the current directory
    Sum = 0
    list = os.listdir(target)  # gets a list of the files in a director
    for path in list:  # for each entry in the list
        Itempath = os.path.join(target, path)  # create a directory path
        if os.path.isfile(Itempath):  # checking for file
            Sum += os.path.getsize(Itempath)  # add the size of the file to sum
        if os.path.isdir(Itempath):  # checking for directories
            Sum += DirSpace(Itempath)  # recursion with the path of the subdirectory, and add the returned value
    return Sum  # return the size of the directory or subdirectory


if __name__ == "__main__":
    main()
"""
The easiest language to write this program in would have to be python
it gave a much easier idea of what you were doing without all the fuss of having to define the data type
along with fuctions from objects, C I had to do mostly everything through logic, java was about the same as python

One way I found through finding the size of a directory is by taking the whole front of the directory and getting the
sizes of all of the subdirectorties and files then adding them to get the total size. That would be how I would 
do it without recursion. the main limitation would be if it is even possible to get the sizes of the entire folders, if 
it is possible to step into each path manually, but then there wouldnt be a way back once you step into a subfolder
"""
