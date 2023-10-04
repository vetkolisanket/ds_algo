/*
706. Design HashMap

Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 

Example 1:

Input
["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
[[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
Output
[null, null, null, 1, -1, null, 1, null, -1]

Explanation
MyHashMap myHashMap = new MyHashMap();
myHashMap.put(1, 1); // The map is now [[1,1]]
myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 

Constraints:

0 <= key, value <= 10^6
At most 10^4 calls will be made to put, get, and remove.

*/

//My soln TC on get, put and remove in worst case would be O(N)
class Holder {
    int key, value;

    public Holder(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Holder)) return false;
        Holder holder = (Holder) o;
        return this.key == holder.key;
    }

}

class MyHashMap {

    private List<Holder>[] arr;
    private final int size = 2069;

    public MyHashMap() {
        arr = new ArrayList[size];
    }
    
    public void put(int key, int value) {
        int idx = key%size;
        if (arr[idx] == null) arr[idx] = new ArrayList();
        Holder holder = new Holder(key, value);
        int listIdx = arr[idx].indexOf(holder);
        if (listIdx == -1) {
            arr[idx].add(holder);
        } else {
            arr[idx].get(listIdx).value = value;
        }
    }
    
    public int get(int key) {
        int idx = key%size;
        if (arr[idx] == null) return -1;
        Holder dummyHolder = new Holder(key, 0);
        int listIdx = arr[idx].indexOf(dummyHolder);
        if (listIdx == -1) return -1;
        return arr[idx].get(listIdx).value;
    }
    
    public void remove(int key) {
        int idx = key%size;
        if (arr[idx] == null) return;
        Holder holder = new Holder(key, 0);
        int listIdx = arr[idx].indexOf(holder);
        if (listIdx == -1) return;
        arr[idx].remove(holder);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
