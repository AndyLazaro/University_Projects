/* Coding Assignment 1 */
#include <stdlib.h> 
#include <stdio.h> 
#include <string.h>
#include <time.h>

typedef struct node
{
	int number;
	struct node *next_ptr;
}
NODE;

void AddNodeToLL(int Number, NODE **LinkedListHead)
{
    NODE *new, *temp;
    new = malloc(sizeof(NODE));
    new->number = Number;
    new->next_ptr = NULL;
    if(*LinkedListHead == NULL)
    {
        *LinkedListHead = new;
    }
    else
    {
        temp = *LinkedListHead;   
        while(temp->next_ptr != NULL)
            {
                temp = temp->next_ptr;
            }       
        temp->next_ptr = new;
    }
    
    
    
}

void ReadFileIntoLL(int argc,  char *argv[], NODE **LLH)
{
    int sum,num, loops = 0;
    char data[100];
    if(argc == 1)
    {
        printf("File must be provided on command line...exiting\n");
        exit(0);
    }
    else if(argc > 1)
    {
        char *filename = argv[1];
        FILE *DATA = fopen(filename,"r");
        while(fgets(data, 100, DATA) != NULL)
        {
            sscanf(data, "%d",&num);
            sum = sum + num;
            AddNodeToLL(num,LLH);
            loops++;
        }
    }
    
    printf("%d records were read for a total sum of %d\n", loops, sum);
       
}

void PrintLL(NODE *LLH) 
{ 
    NODE *current;
    current = LLH;
    while (current != NULL)
    {
#ifdef PRINT
        printf("\n%p %d %p\n", current, current->number, current->next_ptr);
#endif
        current = current->next_ptr;   
    }
}

void FreeLL(NODE **LLH) 
{ 
    int sum = 0, loops = 0;
    NODE *temp;
    temp = *LLH;
    while(temp != NULL)
    {        
#ifdef PRINT
        printf("\nFreeing %p %d %p\n", temp, temp->number, temp->next_ptr);
#endif       
        sum = sum + temp->number;       
        temp = temp->next_ptr; 
        free(*LLH);
        *LLH = temp;
        loops++;
 
    }
    printf("\n%d nodes were deleted for a total sum of %d\n", loops, sum);
}

int main(int argc, char *argv[]) 
{ 
	NODE *LLH = NULL;
        clock_t start, end;
	
	/* capture the clock in a start time */
        start = clock();
	ReadFileIntoLL(argc, argv, &LLH);
	/* capture the clock in an end time */
        end = clock();
	printf("\n%ld ticks to write the file into the linked list\n", end-start);
#ifdef PRINT
        start = clock();
	/* capture the clock in a start time */
	PrintLL(LLH);
	/* capture the clock in an end time */
        end = clock();
	printf("\n%ld ticks to print the linked list\n", end-start);
#endif
        start = clock();
	/* capture the clock in a start time */
	FreeLL(&LLH);
	/* capture the clock in an end time */
        end = clock();
        printf("\n%ld ticks to free the linked list\n", end-start);
	
	
	return 0; 
} 
