class IsPrime{

	public static void main(String[] args){
		int num = 53;
		if(args.length>0){
			num = Integer.parseInt(args[0]);
		}
		System.out.println(isPrime(num));
	}


	private static boolean isPrime(int num){
		for(int i=2;i*i<=num;i++){
			if(num%i==0) return false;
		}
		return true;
	}

}
