

#include <stdint.h>
#include <stdlib.h>
#include <stdio.h>
extern void uint32ToBinary(char str[], uint32_t x);
extern uint32_t bro32(uint32_t x);
extern uint32_t countOnes32(uint32_t x);
int main(int argc, char **argv)
{
	uint32_t a = 0x12345678;
	uint32_t c = countOnes32(a);
	printf("Number of 1s in c is %d\n", c);
	uint32_t d = bro32(a);
	printf("0x%8x becomes 0x%8x when reversed\n", a, d);
	return 0;
}

