/*
Flip Bit to Win: You have an integer and you can flip exactly one bit from a 13 to a 1.Write code to
find the length of the longest sequence of ls you could create. EXAMPLE
Input: 1775 (or : 1110111101111)
Output: 8
*/
int flipBit(int n) {
	if (~n == 0) return Integer.BYTES * 8;

	int currentLength = 0;
	int previousLength = 0;
	int maxLength = 1;
	while (n != 0) {
		if (n & 1 == 1) currentLength++;
		else {
			previousLength = (a & 2 == 0) ? 0 : currentLength;
			currentLength = 0;
		}
		maxLength = Math.max(currentLength + previousLength + 1, maxLength);
		n >>>= 1;
	}
	return maxLength;
}
