/*
23. Merge k Sorted Lists

You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

 

Example 1:

Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6
Example 2:

Input: lists = []
Output: []
Example 3:

Input: lists = [[]]
Output: []
 

Constraints:

k == lists.length
0 <= k <= 10^4
0 <= lists[i].length <= 500
-10^4 <= lists[i][j] <= 10^4
lists[i] is sorted in ascending order.
The sum of lists[i].length won't exceed 10^4.
*/

class Solution {
    public static ListNode mergeKLists(ListNode[] lists){
        if(lists == null || lists.length==0) return null;
        return partition(lists, 0, lists.length-1);
    }
    
    private static ListNode partition(ListNode[] lists, int start, int end) {
        if(start == end) return lists[start];
        int mid = (start+end)/2;
        ListNode a = partition(lists, start, mid);
        ListNode b = partition(lists, mid+1, end);
        return merge(a, b);
    }
    
    private static ListNode merge(ListNode a, ListNode b) {
        if(a==null) return b;
        if(b==null) return a;
        if(a.val<b.val) {
            a.next = merge(a.next, b);
            return a;
        } else {
            b.next = merge(a, b.next);
            return b;
        }
    }
}

//Using priority queue

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length==0) return null;
        int length = lists.length;
        PriorityQueue<ListNode> queue = new PriorityQueue(length, new Comparator<ListNode>(){
            public int compare(ListNode a, ListNode b) {
                return a.val - b.val;
            }
        });
        
        ListNode head = new ListNode(0);
        ListNode node = head;
        
        for(int i=0;i<length;i++)
            if(lists[i] != null)
                queue.add(lists[i]);
        
        while(!queue.isEmpty()) {
            node.next = queue.poll();
            node = node.next;
            
            if(node.next != null)
                queue.add(node.next);
        }
        return head.next;
    }
}

//Recursion using list

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode head=null;
        ListNode former=null;
        while (l1!=null&&l2!=null) {
            if (l1.val>l2.val) {
                if (former==null) former=l2; else former.next=l2;
                if (head==null) head=former; else former=former.next;
                l2=l2.next;
            } else {
                if (former==null) former=l1; else former.next=l1;
                if (head==null) head=former; else former=former.next;
                l1=l1.next;
            }
        }
        if (l2!=null) l1=l2;
        former.next=l1;
        return head;
        
    }
    
    public ListNode mergeKLists(ListNode[] l) {
        List<ListNode> lists = new ArrayList(l.length);
        for(int i=0;i<l.length;i++) lists.add(l[i]);
        return mergeKLists(lists);
    }
    
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size()==0) return null;
        if (lists.size()==1) return lists.get(0);
        if (lists.size()==2) return mergeTwoLists(lists.get(0), lists.get(1));
        return mergeTwoLists(mergeKLists(lists.subList(0, lists.size()/2)), 
            mergeKLists(lists.subList(lists.size()/2, lists.size())));
    }
}

//nk solution
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists == null) return null;
        int length = lists.length;
        if(length == 0) return null;
        ListNode head = null;
        ListNode smallestNode = null;
        ListNode node = null;
        int smallestNodeIndex = getSmallestNodeIndex(lists);
        while (smallestNodeIndex != -1) {
            smallestNode = lists[smallestNodeIndex];
            if(node == null) {
                head = smallestNode;
                node = smallestNode;
            } else {
                node.next = smallestNode;
                node = node.next;
            }
            lists[smallestNodeIndex] = lists[smallestNodeIndex].next;
            smallestNodeIndex = getSmallestNodeIndex(lists);
        }
        return head;
    }
    
    private int getSmallestNodeIndex(ListNode[] lists) {
        int length = lists.length;
        int smallestNodeIndex = -1;
        int smallestVal = Integer.MAX_VALUE;
        for(int i=0;i<length;i++) {
            if(lists[i] != null && lists[i].val < smallestVal) {
                smallestVal = lists[i].val;
                smallestNodeIndex = i;
            }
        }
        return smallestNodeIndex;
    }
}
