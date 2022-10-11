/*
 * 
 */

/* 
 * File:   main.c
 * Author: Andy Lazaro
 * STD ID: 1001821751
 *
 * Created on July 4, 2021, 2:51 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
/*
 * 
 */



void merge(int arr[], int left, int middle, int right)
{ 
    int i, j, k; 
    int n1 = middle -left + 1; 
    int n2 = right -middle; 
    int L[n1], R[n2]; 
    for (i= 0; i< n1; i++)    
        L[i] = arr[left + i]; 
    for (j = 0; j < n2; j++) 
        R[j] = arr[middle + 1 + j];
    i= 0;
    j = 0; 
    k = left;
    while (i< n1 && j < n2) 
    { 
        if (L[i] <= R[j]) 
        { 
            arr[k] = L[i]; i++;
        } 
        else
        { 
            arr[k] = R[j]; 
            j++; 
        } 
        k++; 
    } 
    while (i< n1) 
    { 
        arr[k] = L[i]; i++; k++; 
    } 
    while (j < n2) 
    { 
        arr[k] = R[j]; 
        j++;
        k++;
    }
} 

void mergeSort(int arr[], int L, int R) 
{ 
    if (L < R) 
    { 
        int M = (L+R)/2;
        mergeSort(arr, L, M);

        mergeSort(arr, M+1, R);

        merge(arr, L, M, R); 
    } 
} 

void insertSort(int arr[], int elements)
{
    int i, j, key;
    for(i = 1; i < elements; i++)
    {
        key = arr[i];
        j = i - 1;
        
        while(j >= 0 && arr[j] > key)
        {
            arr[j+1] = arr[j];
            j = j - 1;
        }
        arr[j+1] = key;
    }
    
}

void printArr(int arr[], int n)
{
    int i;
    for(i = 0; i < n; i++)
    {
        printf("%d\n", arr[i]);
    }
}

int main(int argc, char** argv) 
{
    clock_t start, end;
    int i = 0, num, lines = 0;
    char data[100];
    if(argc == 1)
    {
        printf("File must be provided on command line...exiting\n");
        exit(0);
    }
    else if(argc > 1)
    {
        char *filename = argv[1];
        FILE *DATA;
        if((DATA = fopen(filename,"r")) != NULL)
        {
            int* arr;
            while(fgets(data, 100, DATA) != NULL)
            {           
                lines++;
            }
        
            fseek(DATA,0,SEEK_SET);
        
            arr = (int*)malloc(lines * sizeof(int));
            while(fgets(data, 100, DATA) != NULL)
            {           
                sscanf(data,"%d",&num);
                arr[i] = num;
                i++;
            }
            //Merge Sort
            start = clock();
            mergeSort(arr, 0, lines-1);
            end = clock();
            printf("\nMerge Sort run time: %ld\n", end-start);
            
#ifdef PRINTARRAY
        printArr(arr,lines);
#endif  
            free(arr);        
            fseek(DATA,0,SEEK_SET);
            i = 0;       
            arr = (int*)malloc(lines * sizeof(int));
            while(fgets(data, 100, DATA) != NULL)
            {           
                sscanf(data,"%d",&num);
                arr[i] = num;
                i++;
            }
            //Insertion Sort
            start = clock();
            insertSort(arr,lines);
            end = clock();
            printf("\nInsertion Sort run time: %ld\n", end-start);
            
#ifdef PRINTARRAY
        printArr(arr,lines);
#endif         
            free(arr);        
        }
        else
        {
            printf("\nFile Inputed does not exist\n");
        }
    }
    return (EXIT_SUCCESS);
}

