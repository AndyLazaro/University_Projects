/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: Andy Lazaro
 * STD ID: 1001821751
 *
 * Created on August 12, 2021, 12:31 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
/*
 * 
 */
#define HASHTABLESIZE 10


int HashFuncAlpha(char HashName[])
{
    int sum = 0;
    for(int i = 0; i < strlen(HashName); i++)
    {       
        sum += HashName[i];
    }
    return sum % HASHTABLESIZE;
}

int main(int argc, char** argv) {
    char HashName[20];
    printf("Enter a string: ");
    scanf("%s",HashName);
    printf("The Hash Value for %s is %d\n",HashName,HashFuncAlpha(HashName));  
    return (EXIT_SUCCESS);
}

