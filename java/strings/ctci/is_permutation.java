/*
Given two strings, write a method to decide if one is a permutation of the other.
*/

import java.util.*;

class IsPermutation{

	public static void main(String[] args){
		if(args.length != 2){
			System.out.println("You should pass two string as arguments!");
			return;
		}
		String s1 = args[0];
		String s2 = args[1];
		if(isPermutation(s1, s2)) System.out.println(s1 + " and " + s2 + " are permutation of each other.");
		else System.out.println(s1 + " and " + s2 + " are not permutations of each other.");
	}

	private static boolean isPermutation(String s1, String s2){
		if(s1.length() != s2.length()) return false;
		int length = s1.length();
		Map<Character, Integer> map = new HashMap();
		for(int i=0;i<length;i++){
			char c = s1.charAt(i);
			map.put(c, map.getOrDefault(c, 0)+1);
		}
		for(int i=0;i<length;i++){
			char c = s2.charAt(i);
			if(map.getOrDefault(c, 0)<=0) return false;
			map.put(c, map.getOrDefault(c, 0)-1);
		}
		return true;
	}

}
