/*
705. Design HashSet

Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 

Example 1:

Input
["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
[[], [1], [2], [1], [3], [2], [2], [2], [2]]
Output
[null, null, null, true, false, null, true, null, false]

Explanation
MyHashSet myHashSet = new MyHashSet();
myHashSet.add(1);      // set = [1]
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(1); // return True
myHashSet.contains(3); // return False, (not found)
myHashSet.add(2);      // set = [1, 2]
myHashSet.contains(2); // return True
myHashSet.remove(2);   // set = [1]
myHashSet.contains(2); // return False, (already removed)
 

Constraints:

0 <= key <= 10^6
At most 10^4 calls will be made to add, remove, and contains.

*/

//Soln using linked list TC O(N/K) SC O(K+M) where N is the no. of operations, K is the hash key value and M is the no. of unique values inserted
class MyHashSet {

    private LinkedList<Integer> arr[];
    private int size;

    public MyHashSet() {
        size = 769;
        arr = new LinkedList[size];
    }

    private int getHash(int key) {
        return key%size;
    }
    
    public void add(int key) {
        int hash = getHash(key);
        if (arr[hash] == null) {
            arr[hash] = new LinkedList();
        }
        if (arr[hash].indexOf(key) == -1) {
            arr[hash].add(key);
        }
    }
    
    public void remove(int key) {
        int hash = getHash(key);
        if (arr[hash] != null) {
            int idx = arr[hash].indexOf(key);
            if (idx != -1) arr[hash].remove(idx);
        }
    }
    
    public boolean contains(int key) {
        int hash = getHash(key);
        if (arr[hash] != null) {
            int idx = arr[hash].indexOf(key);
            return idx != -1;
        }
        return false;
    }
}

class Bucket {

    private LinkedList<Integer> list;

    public Bucket() {
        list = new LinkedList();
    }

    public void add(int val) {
        list.add(val);
    }

    public void delete(int val) {
        int idx = list.indexOf(val);
        if (idx != -1) list.remove(idx);
    }

    public boolean contains(int val) {
        int idx = list.indexOf(val);
        return idx != -1;
    }

}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
