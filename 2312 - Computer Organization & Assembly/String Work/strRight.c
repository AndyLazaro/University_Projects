



#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
extern void strRight(char *str, char *str1, uint32_t offset);
int main(int argc, char **argv)
{
	char str[] = "Hello World";
	char str1[20];
	int chartoPrint =5;
	strRight(str, str1,chartoPrint);
	printf("the chunk of %s after printing %d characters from the right is %s\n",str,chartoPrint, str1);
	
	return 0;
}

