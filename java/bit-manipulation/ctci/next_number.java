/*
Next Number: Given a positive integer, print the next smallest and the next largest number that
have the same number of 1 bits in their binary representation.
*/
int getNext(int n) {
	int c = n;
	int c0 = 0;      //To store no. of trailing 0's
	int c1 = 1;      //To store no. of continuous 1's after that

	while ((c & 1 == 0) && c != 0) {
		c0++;
		c >>= 1;
	}

	while (c & 1 == 1) {
		c1++;
		c >>= 1;
	}

	if ((c0 + c1 == 31) || (c0 + c1 == 0)) return -1;

	int p = c0 + c1;
	n |= (1 << p);
	n &= ~((1 << p) - 1);
	n |= (1 << (c1 - 1)) - 1;
	return n;
}

int getPrev(int n) {
	int c = n;
	int c0 = 0;      //No. of continuous 0's after trailing 1's
	int c1 = 0;      //No. of trailing 1's

	while (c & 1 == 1) {
		c1++;
		c >>= 1;
	}

	if (c == 0) return -1;

	while ((c & 1 == 0) && c != 0) {
		c0++;
		c >>= 1;
	}

	int p = c0 + c1;

	n &= (~0 << (p+1));
	int mask = ((1 << (c1 + 1)) - 1);
	n |= (mask << (c0 - 1));
	return n;
}

//Arithmetic approach
int getNextArith(int n) {
	//Same calculation for c0 & c1 as before
	return n + (1 << c0) + (1 << (c1-1)) - 1;
}

int getPrevArith(int n) {
	//Same calculation for c0 and c1 as before
	return n - (1 << c1) - (1 << (c0 - 1)) + 1;
}
