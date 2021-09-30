/*
Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as possible (e.g., bit 13 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
*/
//CTCI soln
int swapEvenOddBits(int x) {
	return (((x & 0xaaaaaaaa) >>> 1) | ((x & 0x55555555) << 1) );
}

//My soln, might not work :(
int pairwiseSwap(int n) {
	int a = n << 1;
	int b = n >>> 1;
	int c = 0;
	int d = 0;
	for (int i = 0; i < Integer.BYTES*4;i++) {
		c = (c | 1) << 1;
		c = (c & (Integer.MAX_VAL - 1)) << 1;
		d = (d & (Integer.MAX_VAL - 1)) << 1;
		d = (d | 1) << 1;
	}
	a = a & c;
	b = b & d;
	return a | b;
}
