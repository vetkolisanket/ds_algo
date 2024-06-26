/*
232. Implement Queue using Stacks

Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.
 

Example 1:

Input
["MyQueue", "push", "push", "peek", "pop", "empty"]
[[], [1], [2], [], [], []]
Output
[null, null, null, 1, 1, false]

Explanation
MyQueue myQueue = new MyQueue();
myQueue.push(1); // queue is: [1]
myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
myQueue.peek(); // return 1
myQueue.pop(); // return 1, queue is [2]
myQueue.empty(); // return false
 

Constraints:

1 <= x <= 9
At most 100 calls will be made to push, pop, peek, and empty.
All the calls to pop and peek are valid.
 

Follow-up: Can you implement the queue such that each operation is amortized O(1) time complexity? In other words, performing n operations will take overall O(n) time even if one of those operations may take longer.


*/

//My soln where peek and pop take O(N) time others take O(1) time
class MyQueue {

    Stack<Integer> stack1, stack2;

    public MyQueue() {
        stack1 = new Stack();
        stack2 = new Stack();
    }
    
    public void push(int x) {
        stack1.push(x);
    }
    
    public int pop() {
        while (stack1.size() > 1) {
            stack2.push(stack1.pop());
        }
        int val = stack1.pop();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return val;
    }
    
    public int peek() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int val = stack2.peek();
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return val;
    }
    
    public boolean empty() {
        return stack1.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */

//Amortized O(1) TC soln
class MyQueue {

    Deque<Integer> stack1, stack2;
    int front;

    public MyQueue() {
        stack1 = new ArrayDeque();
        stack2 = new ArrayDeque();
    }
    
    public void push(int x) {
        if (stack1.isEmpty()) {
            front = x;
        }
        stack1.push(x);
    }
    
    public int pop() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
    
    public int peek() {
        if (!stack2.isEmpty()) {
            return stack2.peek();
        }
        return front;
    }
    
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

//Another soln
class MyQueue {

    Stack<Integer> a, b;

    public MyQueue() {
        a = new Stack<>();
        b = new Stack<>();
    }
    
    public void push(int x) {
        if (a.isEmpty() && b.isEmpty()) {
            a.push(x);
        } else {
            b.push(x);
        }
    }
    
    public int pop() {
        if (a.isEmpty()) {
            moveStack();
        }
        return a.pop();
    }
    
    public int peek() {
        if (a.isEmpty()) {
            moveStack();
        }
        return a.peek();
    }

    private void moveStack() {
        while (!b.isEmpty()) {
            a.push(b.pop());
        }
    }
    
    public boolean empty() {
        return a.isEmpty() && b.isEmpty();
    }
}
