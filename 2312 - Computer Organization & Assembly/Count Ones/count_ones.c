

#include <stdio.h>
#include <stdint.h>
#include <stdlib.h>
extern uint32_t countOnes32(uint32_t x);

int main(int argc, char **argv)
{
	uint32_t a,c;
	a = 0x12345678;
	c = countOnes32(a);
	printf("the number of 1 bits in 0x%08x is %u\n", a, c);
	uint32_t b = 0x80000000;
	int count;
	uint32_t d;
	while (b != 0)
	{
		d = a & b;
		if(d != 0)
		{
			//printf("%d and %d == %d\n", a,b,d);
			count = count + 1;
		}
		b = b >> 1;
	}
	printf("\n\n%d\n\n", count);
	return EXIT_SUCCESS;
}

