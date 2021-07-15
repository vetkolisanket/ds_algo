class Reverse {
    public static void main(String args[]) {
        int[] arr = {1,2,3,4};
        System.out.print("Input: ");
	printArray(arr);
        reverseArray(arr); 
        System.out.print("Output: "); 
        printArray(arr);
    }

    private static void reverseArray(int[] arr) {
        int length = arr.length;
        for(int i=0;i<length/2;i++){
            int temp = arr[length-i-1];
            arr[length-i-1] = arr[i];
            arr[i] = temp;
        }
    }

    private static void printArray(int[] arr) {
        int length = arr.length;
        System.out.print("[");
        for(int i=0;i<length-1;i++) {
            System.out.print(arr[i]+",");
        }
        System.out.println(arr[length-1]+"]");
    }

}
