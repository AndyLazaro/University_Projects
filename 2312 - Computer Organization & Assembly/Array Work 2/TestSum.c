
#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>

extern float SumS32(const float arr[],int32_t count);
extern double Prod64(const double arr[],int32_t count);
extern double Dot64(const double arr[], const double arr1[], int32_t count);
extern double Max64(const double arr[], int32_t count);
int main(int argc, char **argv)
{
	float x[] = {10.1,20.2,30.3,40.4,50.5};
	int32_t count = 5;
	double y[] = {800.3,100,75.0930,1.20848943857, 0};
	int32_t count2 = 5;
	double z[] = {1,2,3,4,5};
	printf("Sum of the array is %f\n", SumS32(x,count));
	printf("Product of array is %lf\n", Prod64(z,count));
	printf("Dot product of arrays is %lf\n",Dot64(y,z,count));
	printf("Maximum value in the array is %lf\n", Max64(y,count));	
	return 0;
}

