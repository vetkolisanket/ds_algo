/*
Get minimum element from stack

You are given N elements and your task is to Implement a Stack in which you can get minimum element in O(1) time.

Example 1:

Input:
push(2)
push(3)
pop()
getMin()
push(1)
getMin()
Output: 3 2 1
Explanation: In the first test case for
query 
push(2)  the stack will be {2}
push(3)  the stack will be {2 3}
pop()    poped element will be 3 the
         stack will be {2}
getMin() min element will be 2 
push(1)  the stack will be {2 1}
getMin() min element will be 1
Your Task:
You are required to complete the three methods push() which take one argument an integer 'x' to be pushed into the stack, pop() which returns a integer poped out from the stack and getMin() which returns the min element from the stack. (-1 will be returned if for pop() and getMin() the stack is empty.)

Expected Time Complexity : O(1) for all the 3 methods.
Expected Auixilliary Space : O(1) for all the 3 methods.

Constraints:
1 <= Number of queries <= 100
1 <= values of the stack <= 100
*/

class GfG
{
    int minEle;
    Stack<Integer> s;

    /*returns min element from stack*/
    int getMin()
    {
	// Your code here
	    if (s == null || s.isEmpty()) return -1;
	    return minEle;
    }
    
    /*returns poped element from stack*/
    int pop()
    {
	// Your code here	
	    if (s == null || s.isEmpty()) return -1;
	    int y = s.pop();
	    int x;
	    if (y < 0) {
	        x = minEle;
	        minEle = minEle - y;
	    } else {
	        x = minEle + y;
	    }
	    return x;
    }

    /*push element x into the stack*/
    void push(int x)
    {
	// Your code here	
	    if (s == null) {
	        s = new Stack();
	    }
	    if (s.isEmpty()) {
	        minEle = x;
	        s.push(0);
	    } else {
	        s.push(x - minEle);
	        minEle = Math.min(minEle, x);
	    }
    }	
}
