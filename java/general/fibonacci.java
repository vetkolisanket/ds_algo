class Fibonacci {

	public static void main(String[] args){
		int n = 5;
		if(args.length>0){
			n = Integer.parseInt(args[0]);
		}
		System.out.printf("%dst/nd/rd/th fibonacci no. is %d", n, fibonacci(n));
	}

	private static int fibonacci(int n){
		if(n <= 0) return 0;
		else if (n == 1) return 1;
		else return fibonacci(n-1) + fibonacci(n-2);
	}

}
