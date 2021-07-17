/*
Print even and odd numbers in increasing order using two threads in Java
*/

class PrintEvenOdd {

	private int val = 1;
	public static int maxVal = 10; 

	public void printEven(){
		synchronized (this) {
			while (val<maxVal){
				while(val%2==1){
					try {
						wait();
					} catch(InterruptedException e) {}
				}
				System.out.print(val + " ");
				val++;
				notify();
			}
		}
	}

	public void printOdd(){
		synchronized (this) {
			while(val<maxVal){
				while(val%2==0){
					try{
						wait();
					} catch (InterruptedException e){}
				}
				System.out.print(val + " ");
				val++;
				notify();
			}
		}
	}

	public static void main(String[] args){
		PrintEvenOdd obj = new PrintEvenOdd();
		Thread t1 = new Thread(obj::printOdd);
		Thread t2 = new Thread(obj::printEven);
		t1.start();
		t2.start();
	}

}
