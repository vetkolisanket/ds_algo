/*
Conversion: Write a function to determine the number of bits you would need to flip to convert
integer A to integer B.
EXAMPLE
Input: 29 (or: 111131), 15 (or: 131111) Output: 2
*/
//Better soln
int conversion(int a, int b) {
	int count = 0;
	for (int c = a ^ b; c != 0; c = c & (c-1)) {
		count++;
	}
	return count;
}

//My soln
int conversion(int a, int b) {
	int x = a ^ b;
	int count = 0;
	while (x != 0) {
		if (x & 1 == 1) count++;
		x >>>= 1;
	}
	return count;
}
