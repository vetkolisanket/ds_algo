/*
Is Unique: Implement an algorithm to determine if a string has all unique characters. What if you cannot use additional data structures?
*/

boolean isUniqueChars(String s){
	int checker = 0;
	int length = s.length();
	for(int i=0;i<length;i++){
		int val = s.charAt(i) - 'a';
		if((checker && (1 << val)) > 0) return false;
		checker |= 1 << val;
	}
	return true;
}

boolean isUniqueChars(String s){
	int length = s.length();
	if (length > 128) return false;
	boolean char_set = new boolean[128];
	for(int i=0;i<length;i++){
		if(char_set[s.charAt(i)]) return false;
		char_set[s.charAt(i)] = true;
	}
	return true;
}

boolean isUnique(String s){
	Map<Character, Boolean> map = new HashMap();
	int length = s.length();
	//If its ASCII characters then below line
	if (length>128) return false;
	for(int i=0;i<length;i++){
		if(map.containsKey(s.charAt(i)) return false;
		map.put(s.charAt(i), true);
	}
	return true;
}
