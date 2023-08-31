/*
 * assemblyTest.c
 * 
 * Copyright 2021  <pi@AXL1751>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


#include <stdio.h>
#include <stdint.h>
#include <stdbool.h>
extern int8_t maxU32(int8_t x, int8_t y);
int main(int argc, char **argv)
{
	int8_t x;
	int8_t y;
	x = -5;
	y = 4;
	printf("%d is the return of maxU32\n", maxU32(x,y));
	
	
	return 0;
}

