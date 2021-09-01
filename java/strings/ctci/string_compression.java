/*
String Compression: Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed" string would not become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).
*/

String compress(String s){
	int finalLength = computeFinalLength(s);
	if (finalLength > s.length()) return s;

	int count = 0;
	StringBuilder sb = new StringBuilder(finalLength);
	for(int i=0;i<s.length();i++){
		count++;
		if(i+1 >= s.length() || s.charAt(i) != s.charAt(i+1)){
			sb.append(s.charAt(i)).append(count);
			count = 0;
		}
	}
	return sb.toString();
}

int computeFinalLength(String s){
	int finalLength = 0;
	int count = 0;
	for(int i=0;i<s.length();i++){
		count++;
		if(i+1 >= s.length() || s.charAt(i) != s.charAt(i+1)){
			finalLength += 1 + String.valueOf(count).length();
			count = 0;
		}
	}
	return finalLength;
}

//Another approach
String compressString(String s) {
	int length = s.length();
	int count = 1;
	int j = 0;
	StringBuilder sb = new StringBuilder();
	for(int i=1;I<length,j<length;i++){
		if(s.charAt(i) == s.charAt(j)){
			count++;
		} else {
			sb.append(s.charAt(j).toString()).append(count);
			j = i;
			count = 1;
		}
	}
	sb.append(s.charAt(j).toString()).append(count);
	if(sb.length()>length) return s;
	return sb.toString();
}
