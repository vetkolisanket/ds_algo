/*
Insertion: You are given two 32-bit numbers, Nand M, and two bit positions, i and j. Write a method to insert Minto N such that M starts at bit j and ends at bit i. You can assume that the bits j through i have enough space to fit all of M. That is, if M= 10011, you can assume that there are at least 5 bits between j and i. You would not, for example, have j = 3 and i = 2, because Mcould not fully fit between bit 3 and bit 2.
EXAMPLE
Input: N 10011, i 2, j 6
Output: N 10001001100
*/
int insertion(int n, int m, int j, int i) {
	int mask = ~((((1 << (j-i) - 1) << i);
	int shiftedM = m << i;
	int updatedN = n & mask;
	return updateN | shiftedM;
}
