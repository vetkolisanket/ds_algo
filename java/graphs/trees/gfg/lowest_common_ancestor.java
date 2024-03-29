/*
Lowest Common Ancestor in a Binary Tree

Given a Binary Tree with all unique values and two nodes value, n1 and n2. The task is to find the lowest common ancestor of the given two nodes. We may assume that either both n1 and n2 are present in the tree or none of them are present.

Example 1:

Input:
n1 = 2 , n2 = 3  
       1 
      / \ 
     2   3
Output: 1
Explanation:
LCA of 2 and 3 is 1.
Example 2:

Input:
n1 = 3 , n2 = 4
           5    
          /    
         2  
        / \  
       3   4
Output: 2
Explanation:
LCA of 3 and 4 is 2. 
Your Task:
You don't have to read, input, or print anything. Your task is to complete the function lca() that takes nodes, n1, and n2 as parameters and returns the LCA node as output. Otherwise, return a node with a value of -1 if both n1 and n2 are not present in the tree.

Expected Time Complexity:O(N).
Expected Auxiliary Space:O(Height of Tree).

Constraints:
1 ≤ Number of nodes ≤ 10^5
1 ≤ Data of a node ≤ 10^5
*/

//A cleaner soln
class Solution
{
    //Function to return the lowest common ancestor in a Binary Tree.
	Node lca(Node root, int n1,int n2)
	{
		// Your code here
		if (root == null || root.data == n1 || root.data == n2) {
		    return root;
		}
		
		Node left = lca(root.left, n1, n2);
		Node right = lca(root.right, n1, n2);
		
		if (left == null) {
		    return right;
		}
		if (right == null) {
		    return left;
		}
		return root;
	}
}

//My soln
// { Driver Code Starts
import java.util.LinkedList; 
import java.util.Queue; 
import java.io.*;
import java.util.*;

class Node{
    int data;
    Node left;
    Node right;
    Node(int data){
        this.data = data;
        left=null;
        right=null;
    }
}

class GfG {
    
    static Node buildTree(String str){
        
        if(str.length()==0 || str.charAt(0)=='N'){
            return null;
        }
        
        String ip[] = str.split(" ");
        // Create the root of the tree
        Node root = new Node(Integer.parseInt(ip[0]));
        // Push the root to the queue
        
        Queue<Node> queue = new LinkedList<>(); 
        
        queue.add(root);
        // Starting from the second element
        
        int i = 1;
        while(queue.size()>0 && i < ip.length) {
            
            // Get and remove the front of the queue
            Node currNode = queue.peek();
            queue.remove();
                
            // Get the current node's value from the string
            String currVal = ip[i];
                
            // If the left child is not null
            if(!currVal.equals("N")) {
                    
                // Create the left child for the current node
                currNode.left = new Node(Integer.parseInt(currVal));
                // Push it to the queue
                queue.add(currNode.left);
            }
                
            // For the right child
            i++;
            if(i >= ip.length)
                break;
                
            currVal = ip[i];
                
            // If the right child is not null
            if(!currVal.equals("N")) {
                    
                // Create the right child for the current node
                currNode.right = new Node(Integer.parseInt(currVal));
                    
                // Push it to the queue
                queue.add(currNode.right);
            }
            i++;
        }
        
        return root;
    }
    static void printInorder(Node root)
    {
        if(root == null)
            return;
            
        printInorder(root.left);
        System.out.print(root.data+" ");
        
        printInorder(root.right);
    }
    
	public static void main (String[] args) throws IOException{
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	        int t=Integer.parseInt(br.readLine());
    
	        while(t-- > 0){
	            String input[] = br.readLine().trim().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);
	            String s = br.readLine();
    	    	Node root = buildTree(s);
                Solution g = new Solution();
                Node k = g.lca(root,a,b);
    			System.out.println(k.data);
    	        
	        }
	}
}
// } Driver Code Ends


/* A Binary Tree node
class Node
{
    int data;
    Node left, right;

    Node(int item)
    {
        data = item;
        left = right = null;
    }
}*/



class Solution
{
    //Function to return the lowest common ancestor in a Binary Tree.
	Node lca(Node root, int n1,int n2)
	{
		// Your code here
		Stack<Node> stackA = new Stack();
		Stack<Node> stackB = new Stack();
		findNode(root, stackA, n1);
		findNode(root, stackB, n2);
		Node node = new Node(-1);
		while (!stackA.isEmpty() && !stackB.isEmpty() && 
		stackA.peek().data == stackB.peek().data) {
		    node = stackA.pop();
		    stackB.pop();
		}
		return node;
	}
	
	private void findNode(Node node, Stack<Node> stack, int n) {
	    if (node == null) {
	        return;
	    } else if (node.data == n) {
	        stack.push(node);
	    } else {
	        findNode(node.left, stack, n);
	        if (stack.isEmpty()) {
	            findNode(node.right, stack, n);
	        }
	       if (!stack.isEmpty()) {
	            stack.push(node);
	       }
	    }
	}
}


