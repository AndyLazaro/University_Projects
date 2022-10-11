/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.c
 * Author: Andy Lazaro
 * STD ID: 1001821751
 * Created on August 1, 2021, 12:42 AM
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

#define VertexCount 6
#define MAX 50
typedef struct
{
    char label[5];
    int distance;
    int previous;
    int visited;  
    
}Vertex;

void Readlines(int argc,  char *argv[],Vertex VertArr[], int MatArr[][VertexCount], int *lines)
{
    char data[100] = {};
    char *tok = NULL;
    int i = 0, index = 0;
    int len1 = 0, len2 = 0;
    int vertInt = 0;
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
        len1 = strlen(data);
        tok = strtok(data, ",");        
        strcpy(VertArr[index].label, tok);
        len2 = (len1 - strlen(tok)) / 4;
        for(i = 0; i < len2;i++)
        {
            
            tok = strtok(NULL, ",");
            vertInt = atoi(tok);
            tok = strtok(NULL, ",");
            MatArr[index][vertInt] = atoi(tok);
        }
        index++;
    }
        for(i = 0; i < VertexCount; i++)
    {
        VertArr[i].distance = INT_MAX;
        VertArr[i].visited = 0;
        VertArr[i].previous = 0;
    }
    #ifdef PRINTIT
    for(int f = 0; f < index; f++)
    {
        for(int j = 0; j < index; j++)
        {
            printf("%5d\t", MatArr[f][j]);
        }
        printf("\n");
    }
    #endif
    *lines = index;
}

void Dijkstra(Vertex VertexArray[], int AdjMatrix[][VertexCount],int StartVertex, int *ShortPath)
{
    
    int i,x,dofu,cofuv,dofv;
    int CurrentVertex = StartVertex;
    VertexArray[StartVertex].distance = 0;
    VertexArray[StartVertex].previous = -1;
    VertexArray[StartVertex].visited = 1;
    for (x = 0; x < VertexCount - 1; x++)
    {
        for(i= 0; i< VertexCount; i++)
        {
            if (AdjMatrix[CurrentVertex][i] != -1 && !VertexArray[i].visited)
            {
                dofu= VertexArray[CurrentVertex].distance;
                cofuv= AdjMatrix[CurrentVertex][i];
                dofv= VertexArray[i].distance;
                if (dofu+ cofuv< dofv)
                {
                    dofv= dofu+ cofuv;
                    VertexArray[i].distance = dofv;
                    VertexArray[i].previous = CurrentVertex;
                }
            }
        }
        int SmallestVertexIndex= -1;
        int SmallestVertex= INT_MAX;
        for(i = 0; i < VertexCount; i++)
        {
            if (!VertexArray[i].visited)
            {
                if (VertexArray[i].distance < SmallestVertex)
                {
                    SmallestVertex= VertexArray[i].distance;
                    SmallestVertexIndex = i;
                }
            }
        }
        CurrentVertex = SmallestVertexIndex;
        VertexArray[CurrentVertex].visited = 1;
        if(VertexArray[x+1].distance == INT_MAX)
            VertexArray[x+1].distance = 0;
    } 
}



int main(int argc, char** argv) {
    Vertex VertexArray[VertexCount];
    int MatrixArray[VertexCount][VertexCount];
    char start[5],end[5];
    int StartInd, EndInd;
    int ShortPath = 0, lines = 0;
    for(int i = 0; i < VertexCount; i++)
    {
        for(int j = 0; j < VertexCount; j++)
        {
            MatrixArray[i][j] = -1;
        }
    }
    
    Readlines(argc,argv,VertexArray, MatrixArray, &lines);
    
    printf("\nWhat is the starting vertex? ");
    scanf("%s", start);
    for(int i = 0; i < VertexCount;i++)
    {
        if(strcmp(VertexArray[i].label,start) == 0)
        {
            StartInd = i;
        }
    }
    
    
    Dijkstra(VertexArray, MatrixArray, StartInd, &ShortPath);

    
    
    #ifdef PRINTIT
    printf("\n\nI\tL\tD\tP\tV\n");
    for(int i = 0; i < lines; i++)
    {
        printf("%d\t%s\t%d\t%d\t%d\n", i, VertexArray[i].label,VertexArray[i].distance,VertexArray[i].previous,VertexArray[i].visited);
    }
    printf("\n");
    #endif
    printf("\nWhat is the destination vertex? ");
    scanf("%s", end);
    for(int i = 0; i < VertexCount;i++)
    {
        if(strcmp(VertexArray[i].label,end) == 0)
        {
            EndInd = i;
        }
    }
    int Path[VertexCount];
    Path[0] = EndInd;
    for(int i = 1; i < VertexCount;i++)
    {
        Path[i] = -100;
    }
    for(int i = 1; i < VertexCount; i++)
    {
        
        if(VertexArray[Path[i-1]].previous == -1)
        {     
            i = VertexCount + 1;           
        }
        Path[i] = VertexArray[Path[i-1]].previous;
    }
    int arrow = 0;
    for(int i = VertexCount - 1; i > -1; i--)
    {
    if(Path[i] != -100)
    {
        printf("%s",VertexArray[Path[i]].label);
        arrow = 1;
    }
    if(arrow == 1 && i != 0)
        printf("->");
    }
    printf(" at a distance of %d\n", VertexArray[EndInd].distance);
    return (EXIT_SUCCESS);
}

