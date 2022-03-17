/*
Kth smallest element 

Given an array arr[] and an integer K where K is smaller than size of array, the task is to find the Kth smallest element in the given array. It is given that all array elements are distinct.

Example 1:

Input:
N = 6
arr[] = 7 10 4 3 20 15
K = 3
Output : 7
Explanation :
3rd smallest element in the given 
array is 7.
Example 2:

Input:
N = 5
arr[] = 7 10 4 20 15
K = 4
Output : 15
Explanation :
4th smallest element in the given 
array is 15.
Your Task:
You don't have to read input or print anything. Your task is to complete the function kthSmallest() which takes the array arr[], integers l and r denoting the starting and ending index of the array and an integer K as input and returns the Kth smallest element.
 
 
Expected Time Complexity: O(n)
Expected Auxiliary Space: O(log(n))
Constraints:
1 <= N <= 10^5
1 <= arr[i] <= 10^5
1 <= K <= N
*/

//Soln using max priority queue
class Solution{
    
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    { 
        //Your code here
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for (int num: arr) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
    
}

//Soln using min priority queue
class Solution{
    
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    { 
        //Your code here
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int num: arr) pq.offer(num);
        while (k > 1) {
            pq.poll();
            k--;
        }
        return pq.peek();
    }
    
}

//Soln using quick select
class Solution{
    
    public static int kthSmallest(int[] arr, int l, int r, int k) 
    { 
        //Your code here
        k--;
        shuffle(arr);
        while (l <= r) {
            int i = l;
            for (int j=l+1;j<=r;j++) {
                if (arr[l]>arr[j]) {
                    swap(arr, ++i, j);
                }
            }
            swap(arr, l, i);
            
            if (i < k) l = i+1;
            else if (i > k) r = i-1;
            else break;
        }
        return arr[k];
    }
    
    private static void shuffle(int[] arr) {
        Random random = new Random();
        for (int i=0;i<arr.length;i++) {
            int j = random.nextInt(i+1);
            swap(arr, i, j);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
