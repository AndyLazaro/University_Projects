/*
 * 
 */

/* 
 * File:   main.c
 * Author: Andy Lazaro
 * STD ID: 1001821751
 *
 * Created on July 18, 2021, 2:51 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
/*
 * 
 */


void swap(int *SwapA, int *SwapB) 
{ 
    int temp = *SwapA;
    *SwapA= *SwapB;
    *SwapB= temp;
} 

int partition (int A[], int low, int high) 
{ 
    #if QSM
    int middle = (high+low)/2;
    swap(&A[middle], &A[high]);
    #elif QSRND
    int random = (rand() % (high-low+1)) + low;
    swap(&A[random], &A[high]);
    #endif
    int i, j = 0;
    int pivot = A[high];
    i= (low -1); 
    for (j = low; j < high; j++)
    { 
        if (A[j] < pivot) 
        { 
            i++; 
            swap(&A[i], &A[j]); 
        } 
    } 
    swap(&A[i+ 1], &A[high]);
    return (i+ 1);
}

void QuickSort(int A[], int low, int high) 
{ 
    if (low < high) 
    { 
        int ndx= partition(A, low, high); 
        QuickSort(A, low, ndx-1); 
        QuickSort(A, ndx+ 1, high); 
    } 
}

void Readlines(int argc,  char *argv[], int *temp, int** arr)
{
    int i = 0,num = 0, lines = 0;
    char data[100];
    if(argc == 1)
    {
        printf("File must be provided on command line...exiting\n");
        exit(0);
    }
    char *filename = argv[1];
    FILE *DATA = fopen(filename,"r");
    if(DATA == NULL)
    {
        printf("File given does not exist...exiting\n");
        exit(0);
    }
    while(fgets(data, 100, DATA) != NULL)
    {
        lines++;      
    }
    fseek(DATA,0,SEEK_SET);
    *arr = malloc(lines * sizeof(int));
    while(fgets(data, 100, DATA) != NULL)
    {           
        num = atoi(data);
        (*arr)[i] = num;
        i++;        
    }
    *temp = lines;
    fclose(DATA);
}


void printArr(int arr[], int n)
{
    printf("Printing now\n");
    int i;
    for(i = 0; i < n; i++)
    {
        printf("%d\n", arr[i]);
    }
}

int main(int argc, char** argv) 
{
    clock_t start, end;
    int *arr;
    int lines, i = 0;
    int runs;
    unsigned int sum = 0;
    if(argv[2] != NULL)
    {
        runs = atoi(argv[2]);
        if(runs == 0)
        {
            printf("runs cannot be 0, defaulting to 10\n");
            runs = 10;
        }
    }
    else
    {
        printf("Second argument not entered, defaulting to 10 runs\n");
        runs = 10;
    }
    
    for(i = 1; i <= runs; i++)
    {
        Readlines(argc,argv,&lines, &arr);
    
        #ifdef PRINTARRAY
            printArr(arr,lines);
        #endif  

        start = clock();
        QuickSort(arr, 0, lines-1);
        end = clock();
        printf("Quick Sort run:%d\ttime: %ld\n\n",i, end-start);  
    
        #ifdef PRINTARRAY
            printArr(arr,lines);
        #endif  
        sum = (end-start) + sum;
        
        free(arr);  
    }
    printf("The average run time of %d runs is %d\n", runs, sum / runs);
    return (EXIT_SUCCESS);
}

