// Coding Assignment 5 - Andy Lazaro - 1001821751

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <time.h>
 
#define HASHTABLESIZE 30
 
/* Node for storing information */
typedef struct Items
{
    char *ItemName;
    float StatUp;
    char FirstLetter;
    char *NameStatUp;
    int ItemQuality;
    struct Items*next_ptr;
}ITEMS;

 
/* This function creates an index corresponding to the input key */
int HashFuncAlpha(char HashName[])
{
    int sum = 0;
    for(int i = 0; i < strlen(HashName); i++)
    {       
        sum += HashName[i];
    }
    return sum % HASHTABLESIZE;
}

void AddNode(ITEMS *NewNode, ITEMS *ItemGuide[])
{
	int HashIndex = HashFuncAlpha(NewNode->ItemName);
	
	/* a linked list does not exist for this cell of the array */
	if (ItemGuide[HashIndex] == NULL) 
	{
            #if PRINTINSERT
            printf("\nInserting %s at index %d\n", NewNode->ItemName, HashIndex);
            #endif
            ItemGuide[HashIndex] = NewNode;
	}
        else   /* A Linked List is present at given index of Hash Table */ 
        {
            ITEMS *TempPtr = ItemGuide[HashIndex];
	
		/* Traverse linked list */
            while (TempPtr->next_ptr != NULL) 
            {
                TempPtr = TempPtr->next_ptr;
            }
            TempPtr->next_ptr = NewNode;
            #if PRINTINSERT
            printf("\nInserting %s at index %d\n", NewNode->ItemName, HashIndex);
            #endif
	}
}

void FreeDynamicMemory(ITEMS *ItemGuide[])
{
    ITEMS *TempPtr = NULL, *NextPtr = NULL;	
    for (int i = 0; i < HASHTABLESIZE; i++)
    {
        TempPtr = ItemGuide[i];
        if (TempPtr != NULL) 
        {
            while (TempPtr != NULL) 
            {
                free(TempPtr->ItemName);
                free(TempPtr->NameStatUp);
                NextPtr = TempPtr->next_ptr;
                free(TempPtr);
                TempPtr = NextPtr;
            }	
        }
    }
}


/* Remove an element from Hash Table */ 
int DeleteNode(ITEMS *ItemGuide[])
{
    char LookupName[100] = {};
    int result = 0;	
    printf("Enter the name of the Item to delete from the Item Guide ");
    while ((getchar()) != '\n');
    fgets(LookupName,100,stdin);
    LookupName[strcspn(LookupName, "\n")] = '\0';
    int HashIndex = HashFuncAlpha(LookupName);
    /* Get linked list at key in array */
    ITEMS *TempPtr = ItemGuide[HashIndex];
    ITEMS *PrevPtr = NULL;
    /* This array index does not have a linked list; therefore, no keys */
    if (TempPtr == NULL) 
    {
        printf("\n\nItem %s does not exist in the Item Guide\n\n", LookupName);
        result = 0;
    }
    else 
    {
        while (TempPtr != NULL) 
        {
            if (strcmp(TempPtr->ItemName, LookupName) == 0)
            {
                /* If the node being deleted is the head node */
                if (TempPtr == ItemGuide[HashIndex])
                {
                    /* The node the head was pointing at is now the head */
                    ItemGuide[HashIndex] = TempPtr->next_ptr;
                    printf("\nItem %s has been deleted from the Item Guide\n\n", TempPtr->ItemName);
                    free(TempPtr);
                    TempPtr = NULL;
                }
                /* Found node to be deleted - node is not the head */
                else
                {
                    PrevPtr->next_ptr = TempPtr->next_ptr;
                    printf("\nItem %s has been deleted from the Item Guide\n\n", TempPtr->ItemName);
                    free(TempPtr);
                    TempPtr = NULL;
                }
                result = 1;
            }
            /* this is not the node that needs to be deleted but save */
            /* its info and move to the next node in the linked list  */
            else
            {
                PrevPtr = TempPtr;
                TempPtr = TempPtr->next_ptr;
            }
        }
        if (result == 0)
        {
            printf("\n\nItem %s does not exist in the Item Guide\n\n", LookupName);
        }
    }
        return result;
}

/* display the contents of the Hash Table */
void DisplayHashTable(ITEMS *ItemGuide[])
{
    int i;
    ITEMS *TempPtr = NULL;	
    for (i = 0; i < HASHTABLESIZE; i++) 
    {
        TempPtr = ItemGuide[i];
        printf("\nItem Guide[%d]-", i);		
        if (TempPtr != NULL) 
        {
            while (TempPtr != NULL)
            {
                printf("%s|", TempPtr->ItemName);
                TempPtr = TempPtr->next_ptr;
            }
        }
    }
}

void ShowByLetter(ITEMS *ItemGuide[])
{
    int i;
    ITEMS *TempPtr = NULL;
    char LookupLetter = 'A';
    printf("\n\nEnter a letter of the alphabet ");
    scanf(" %c", &LookupLetter);
    LookupLetter = toupper(LookupLetter);

    for (i = 0; i < HASHTABLESIZE; i++) 
    {
        TempPtr = ItemGuide[i];
        if (TempPtr != NULL) 
        {
            while (TempPtr != NULL)
            {
                if (toupper(TempPtr->ItemName[0]) == LookupLetter)
                {
                    printf("%s\n", TempPtr->ItemName);
                }
                TempPtr = TempPtr->next_ptr;
            }
        }
    }
}

void ShowByName(ITEMS *ItemGuide[])
{
    ITEMS *TempPtr = NULL;
    char StatUp[10] = {};
    char feet[10] = {};
    char inches[10] = {};
    char LookupName[100] = {};
    int FoundIt = 0;
	
    printf("\n\nEnter Item's name: ");
    while ((getchar()) != '\n');
    fgets(LookupName,100,stdin);
    LookupName[strcspn(LookupName, "\n")] = '\0';
    #if TIMING
    clock_t start, end;
    start = clock();
    #endif
    for (int i = 0; i < HASHTABLESIZE; i++) 
    {
        TempPtr = ItemGuide[i];
        if (TempPtr != NULL) 
        {
            while (TempPtr != NULL)
            {
                if (strcmp(TempPtr->ItemName, LookupName) == 0)
                {
                    #if TIMING
                    end = clock();
                    printf("\n\nSearch took %ld tics\n\n", end-start);
                    #endif
                    FoundIt = 1;
                    printf("\n\n%s\n", TempPtr->ItemName);	
                    printf("Stat Up\t\t");
                    sprintf(StatUp, "%.2f", TempPtr->StatUp);
                    printf("%s\n",StatUp);
                    printf("First Letter\t%c\n", TempPtr->FirstLetter);
                    printf("Category\t%s\n", TempPtr->NameStatUp);
                    printf("Item Quality\t%d\n", TempPtr->ItemQuality);
                }
                TempPtr = TempPtr->next_ptr;
            }
        }
    }
	
    if (FoundIt == 0)
        printf("\n\nItem %s not found in Item Guide\n\n", LookupName);
}

void AddNewItem(ITEMS *ItemGuide[])
{
    int HashIndex = 0;
    ITEMS *NewNode;
    char TempBuffer[100] = {};
    NewNode = malloc(sizeof(ITEMS));
    NewNode->next_ptr = NULL;

    printf("\n\nEnter new Item's name: ");
    scanf("%s", TempBuffer);
    NewNode->ItemName = malloc(strlen(TempBuffer)*sizeof(char)+1);
    strcpy(NewNode->ItemName, TempBuffer);
    printf("\n\nEnter %s's Stat Up as #.#: ", NewNode->ItemName);
    scanf("%f", &(NewNode->StatUp));	
    printf("\n\nEnter %s's First Letter: ", NewNode->ItemName);
    scanf(" %c", &(NewNode->FirstLetter));
    NewNode->FirstLetter = toupper(NewNode->FirstLetter);	
    // Extra fgets to clear stdin
    fgets(TempBuffer, sizeof(TempBuffer)-1, stdin);	
    printf("\n\nEnter %s's Stat Up Name: ", NewNode->ItemName);
    fgets(TempBuffer, sizeof(TempBuffer)-1, stdin); 
    TempBuffer[strlen(TempBuffer)-1] = '\0';
    NewNode->NameStatUp = malloc(strlen(TempBuffer)*sizeof(char)+1);
    strcpy(NewNode->NameStatUp, TempBuffer);
    printf("\n\nEnter %s's Item Quality: ", NewNode->ItemName);
    scanf("%d", &(NewNode->ItemQuality));
    AddNode(NewNode, ItemGuide);
}

int ReadFileIntoHashTable(int argc, char *argv[], ITEMS *ItemGuide[])
{
    FILE *FH = NULL;
    char FileLine[100] = {};
    char *token = NULL;
    int counter = 0;
    int HashIndex = 0;
    ITEMS *NewNode;
    if (argc > 1)
    {
        FH = fopen(argv[1], "r");
        if (FH == NULL)
        {
            perror("Exiting ");
            exit(0);
        }	
        while (fgets(FileLine, sizeof(FileLine)-1, FH))
        {
            token = strtok(FileLine, "|");
            NewNode = malloc(sizeof(ITEMS));
            NewNode->next_ptr = NULL;		
            NewNode->ItemName = malloc(strlen(token)*sizeof(char)+1);
            strcpy(NewNode->ItemName, token);
            token = strtok(NULL, "|");
            NewNode->StatUp = atof(token);
            token = strtok(NULL, "|");
            NewNode->FirstLetter = *token;		
            token = strtok(NULL, "|");
            NewNode->NameStatUp = malloc(strlen(token)*sizeof(char)+1);
            strcpy(NewNode->NameStatUp, token);
            token = strtok(NULL, "|");
            NewNode->ItemQuality = atoi(token);		
            AddNode(NewNode, ItemGuide);
            counter++;
        }
    }
    else
    {
        printf("File must be provided on command line...exiting\n");
        exit(0);
    }	
    fclose(FH);
    return counter;
}

int main(int argc, char *argv[]) 
{
    int MenuChoice = 0;
    int counter = 0;
    ITEMS *ItemGuide[HASHTABLESIZE] = {};

    enum Menu {SHOWBYLETTER=1, SHOWBYNAME, COUNT, DISPLAY, ADD, DELETE, EXIT};
 
    counter = ReadFileIntoHashTable(argc, argv, ItemGuide);
 
    do
    {
        printf("\n\nItem Guide Menu\n\n"
                    "1. Show all Item in Item Guide for a given letter\n"
                    "2. Look up Item by name\n"
                    "3. How many Item are in the Item Guide?\n"
                    "4. Display the Item Guide\n"
                    "5. Add a new Item\n"
                    "6. Delete a Item from the Item Guide\n"
                    "7. Exit\n\n"
                    "Enter menu choice ");
	
        scanf("%d", &MenuChoice);
        printf("\n\n");
        switch (MenuChoice)
        {	
            case SHOWBYLETTER:
                ShowByLetter(ItemGuide);
                break;
            case SHOWBYNAME:
                ShowByName(ItemGuide);
                break;
            case COUNT:
                printf("Your Item Guide contains %d Item\n", counter); 
                break;
            case DISPLAY:
                DisplayHashTable(ItemGuide);
                break;
            case ADD:
                AddNewItem(ItemGuide);
                counter++;
                break;
            case DELETE:
                if (DeleteNode(ItemGuide))
                {
                    counter--;
                }
                break;
            case EXIT:
                break;
            default : 
                printf("Invalid menu choice\n\n"); 
        }
    }
    while (MenuChoice != EXIT);	
    FreeDynamicMemory(ItemGuide);
	return 0;
}			  
