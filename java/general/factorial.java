class Factorial {

	public static void main(String[] args) {
		int n = 5;
		if(args.length > 0){
			n = Integer.parseInt(args[0]);
		}
		System.out.printf("Factorial of %d is %d", n, factorial(n));
	}

	private static int factorial(int n) {
		if(n < 0) return -1;
		else if(n==0) return 1;
		return n*factorial(n-1);
	}

}
