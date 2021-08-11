/*
Palindrome Permutation: Given a string, write a function to check if it is a permutation of a palin- drome. A palindrome is a word or phrase that is the same forwards and backwards. A permutation is a rearrangement of letters.The palindrome does not need to be limited to just dictionary words.
EXAMPLE
Input: Tact Coa
Output: True (permutations: "taco cat". "atco cta". etc.)
*/

boolean isPermutationOfPalindrome(String phrase){
	int bitVector = createBitVector(phrase);
	return bitvector == 0 || checkExactlyOneBitSet(bitVector);
}

boolean checkExactlyOneBitSet(int bitVector){
	return (bitVector & (bitVector - 1)) == 0;
}

int createBitVector(String phrase){
	int bitVector = 0;
	for(char c: phrase.toCharArray()){
		int x = getCharNumber(c);
		bitVector = toggle(bitVector, x);
	}
	return bitVector;
}

int toggle(int bitVector, int index){
	if (index < 0) return bitVector;

	int mask = 1 << index;
	if((bitVector & mask) == 0){
		bitVector |= mask;
	} else {
		bitVector &= ~mask;
	}
	return bitVector;
}

int getCharNumber(Character c){
	int a = Character.getNumericValue('a');
	int z = Character.getNumericValue('z');
	int val = Character.getNumericValue(c);
	if(a <= val && val <= z) {
		return val - a;
	}
	return -1;
}
