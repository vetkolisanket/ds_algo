class FibonacciMemoization {

	public static void main(String[] args){
		int n = 5;
		if(args.length>0){
			n = Integer.parseInt(args[0]);
		}
		int[] mem = new int[n+1];
		for(int i=0;i<n;i++){
			System.out.print(fibonacci(i,mem) + " ");
		}
	}

	private static int fibonacci(int n, int[] mem){
		if(n<=0) return 0;
		else if(n==1) return 1;
		else if(mem[n]>0) return mem[n];
		else {
			mem[n] = fibonacci(n-1,mem) + fibonacci(n-2, mem);
			return mem[n];
		}
	}

}
