/*
225. Implement Stack using Queues

Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

void push(int x) Pushes element x to the top of the stack.
int pop() Removes the element on the top of the stack and returns it.
int top() Returns the element on the top of the stack.
boolean empty() Returns true if the stack is empty, false otherwise.
Notes:

You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.
 

Example 1:

Input
["MyStack", "push", "push", "top", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 2, 2, false]

Explanation
MyStack myStack = new MyStack();
myStack.push(1);
myStack.push(2);
myStack.top(); // return 2
myStack.pop(); // return 2
myStack.empty(); // return False
 

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, top, and empty.
All the calls to pop and top are valid.
 

Follow-up: Can you implement the stack using only one queue?
*/

//Soln using one queue
class MyStack {
    
    Queue<Integer> q;

    public MyStack() {
        q = new ArrayDeque();
    }
    
    public void push(int x) {
        q.offer(x);
        int size = q.size();
        while (size > 1) {
            q.offer(q.poll());
            size--;
        }
    }
    
    public int pop() {
        return q.poll();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

//My soln
class MyStack {
    
    Queue<Integer> q1,q2;
    boolean isCurQq1;

    public MyStack() {
        q1 = new ArrayDeque();
        q2 = new ArrayDeque();
        isCurQq1 = true;
    }
    
    public void push(int x) {
        Queue<Integer> curQ = isCurQq1 ? q1 : q2;
        curQ.offer(x);
    }
    
    public int pop() {
        int popValue = -1;
        Queue<Integer> curQ = isCurQq1 ? q1 : q2;
        Queue<Integer> tempQ = isCurQq1 ? q2 : q1;
        while (!curQ.isEmpty()) {
            int val = curQ.poll();
            if (curQ.isEmpty()) {
                popValue = val;
            } else {
                tempQ.offer(val);
            }
        }
        isCurQq1 = !isCurQq1;
        return popValue;
    }
    
    public int top() {
        int topValue = -1;
        Queue<Integer> curQ = isCurQq1 ? q1 : q2;
        Queue<Integer> tempQ = isCurQq1 ? q2 : q1;
        while (!curQ.isEmpty()) {
            int val = curQ.poll();
            if (curQ.isEmpty()) {
                topValue = val;
            }
            tempQ.offer(val);
        }
        isCurQq1 = !isCurQq1;
        return topValue;
    }
    
    public boolean empty() {
        return isCurQq1 ? q1.isEmpty() : q2.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
