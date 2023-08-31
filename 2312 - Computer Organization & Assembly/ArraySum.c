#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>

extern int32_t Sum32(const int32_t arr[], uint32_t count);
extern int32_t Dot32(const int32_t arr[], const int32_t YB[],int32_t count);
extern uint32_t AbvCount(int32_t arr[], int32_t limit, int32_t count);
extern int32_t SumS32(const int32_t arr[4]);
int main(int argc, char **argv)
{
	int32_t ar[] = {10,20,30,40};
	int32_t array[4] = {10,20,30,40};
	int32_t Y[] = {1,2,3,4};
	uint32_t counts = 4;
	printf("The summation in the array is %d\n", Sum32(ar, counts));
	for(int i = 0; i < 4 ; i++)
	{
		printf("%d * %d = %d\n", ar[i], Y[i], ar[i] * Y[i]);
	}
	printf("DotPot = %d\n", Dot32(ar,Y,counts));
	int32_t limit = 20;
	
	printf("There are %u values in ARR[] that are greater than %d\n",AbvCount(ar,limit,counts) , limit);
	
	printf("The sum of the array is %d", SumS32(array));
	return 0;
}
