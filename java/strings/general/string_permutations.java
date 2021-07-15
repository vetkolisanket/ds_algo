class StringPermutations{

	public static void main(String[] args){
		String s = "abc";
		if(args.length > 0) {
			s = args[0];
		}
		stringPermutation(s,"");
	}

	private static void stringPermutation(String s, String prefix){
		if(s.length()==0){
			System.out.println(prefix);
		} else {
			for(int i=0;i<s.length();i++){
				String rem = s.substring(0,i) + s.substring(i+1);
				stringPermutation(rem, prefix+s.charAt(i));
			}
		}
	}

}
