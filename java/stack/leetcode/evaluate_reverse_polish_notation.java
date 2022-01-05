/*
150. Evaluate Reverse Polish Notation

Evaluate the value of an arithmetic expression in Reverse Polish Notation.

Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Note that division between two integers should truncate toward zero.

It is guaranteed that the given RPN expression is always valid. That means the expression would always evaluate to a result, and there will not be any division by zero operation.

 

Example 1:

Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9
Example 2:

Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
Example 3:

Input: tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
Output: 22
Explanation: ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22
 

Constraints:

1 <= tokens.length <= 10^4
tokens[i] is either an operator: "+", "-", "*", or "/", or an integer in the range [-200, 200].
*/

//Faster soln
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for (String token: tokens) {
            switch(token) {
                case "+":
                    stack.push(stack.pop()+stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop()*stack.pop());
                    break;
                case "-":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a-b);
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a/b);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack();
        for (int i=0;i<tokens.length;i++) {
            String token = tokens[i];
            if (isInteger(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                stack.push(performOperation(token, stack));
            }
        }
        return stack.pop();
    }
    
    private boolean isInteger(String token) {
        if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) return false;
        return true;
    }
    
    private int performOperation(String token, Stack<Integer> stack) {
        int b = stack.pop();
        int a = stack.pop();
        switch(token) {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            default:
                return a/b;
        }
    }
}
