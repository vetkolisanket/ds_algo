/*
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

//What the interviewer might expect
class MyHashSet {
    
    Bucket[] buckets;
    int hashKey = 769;

    public MyHashSet() {
        buckets = new Bucket[hashKey];
        for (int i=0;i<hashKey;i++) {
            buckets[i] = new Bucket();
        }
    }
    
    protected int _hash(int key) {
        return key%hashKey;
    }
    
    public void add(int key) {
        buckets[_hash(key)].insert(key);
    }
    
    public void remove(int key) {
        buckets[_hash(key)].delete(key);
    }
    
    public boolean contains(int key) {
        return buckets[_hash(key)].find(key);
    }
    
    class Bucket {
        List<Integer> list;
        
        public Bucket() {
            list = new LinkedList();
        }
        
        public void insert(int key) {
            int index = list.indexOf(key);
            if (index == -1) {
                list.add(key);
            }
        }
        
        public void delete(int key) {
            int index = list.indexOf(key);
            if (index != -1) {
                list.remove(index);
            }
        }
        
        public boolean find(int key) {
            int index = list.indexOf(key);
            return index != -1;
        }
    }
    
}

//My simple soln
class MyHashSet {
    
    boolean[] set;

    public MyHashSet() {
        set = new boolean[1000001];
    }
    
    public void add(int key) {
        set[key] = true;
    }
    
    public void remove(int key) {
        set[key] = false;
    }
    
    public boolean contains(int key) {
        return set[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
