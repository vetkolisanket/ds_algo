/*
String Rotation: Assume you have amethod isSubstring which checks ifone word is asubstring of another. Given two strings, 51 and 52, write code to check if 52 is a rotation of 51 using only one call to isSubstring (e.g.,"waterbottle"is a rotation of"erbottlewat").
*/

boolean isRotation(String s1, String s2){
	int len = s1.length();
	if(len == s2.length() && len > 0){
		String ss = s1 + s1;
		return isSubstring(ss, s2);
	}
	return false;
}
