/*
2336. Smallest Number in Infinite Set

You have a set which contains all positive integers [1, 2, 3, 4, 5, ...].

Implement the SmallestInfiniteSet class:

SmallestInfiniteSet() Initializes the SmallestInfiniteSet object to contain all positive integers.
int popSmallest() Removes and returns the smallest integer contained in the infinite set.
void addBack(int num) Adds a positive integer num back into the infinite set, if it is not already in the infinite set.
 

Example 1:

Input
["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
[[], [2], [], [], [], [1], [], [], []]
Output
[null, null, 1, 2, 3, null, 1, 4, 5]

Explanation
SmallestInfiniteSet smallestInfiniteSet = new SmallestInfiniteSet();
smallestInfiniteSet.addBack(2);    // 2 is already in the set, so no change is made.
smallestInfiniteSet.popSmallest(); // return 1, since 1 is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 2, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 3, and remove it from the set.
smallestInfiniteSet.addBack(1);    // 1 is added back to the set.
smallestInfiniteSet.popSmallest(); // return 1, since 1 was added back to the set and
                                   // is the smallest number, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 4, and remove it from the set.
smallestInfiniteSet.popSmallest(); // return 5, and remove it from the set.
 

Constraints:

1 <= num <= 1000
At most 1000 calls will be made in total to popSmallest and addBack.

*/

//Soln using tree set TC pop worst-case O(logN) add O(logN) SC O(N)
class SmallestInfiniteSet {

    private int lowerBound;
    private TreeSet<Integer> set;

    public SmallestInfiniteSet() {
        lowerBound = 1;
        set = new TreeSet();
    }
    
    public int popSmallest() {
        if (set.isEmpty()) return lowerBound++;
        int val = set.higher(0);
        set.remove(val);
        return val;
    }
    
    public void addBack(int num) {
        if (num >= lowerBound) return;
        set.add(num);
    }
}

/**
 * Your SmallestInfiniteSet object will be instantiated and called as such:
 * SmallestInfiniteSet obj = new SmallestInfiniteSet();
 * int param_1 = obj.popSmallest();
 * obj.addBack(num);
 */

//Soln using priority queue and hash set TC O((M+N)logN) SC O(N) where M is the no. of pops and N is the no. of add elements called
class SmallestInfiniteSet {

    private int cur;
    private Set<Integer> set;
    private PriorityQueue<Integer> pq;

    public SmallestInfiniteSet() {
        cur = 1;
        set = new HashSet();
        pq = new PriorityQueue();
    }
    
    public int popSmallest() {
        int ans;
        if (!pq.isEmpty()) {
            ans = pq.poll();
            set.remove(ans);
        } else ans = cur++;
        return ans;
    }
    
    public void addBack(int num) {
        if (num >= cur || set.contains(num)) return;
        pq.offer(num);
        set.add(num);
    }
}

//Soln using sorted set TC O((M+N)logN) SC O(N) where M is the no. of pops and N is the no. of add element calls
class SmallestInfiniteSet {

    private int cur;
    private SortedSet<Integer> set;

    public SmallestInfiniteSet() {
        cur = 1;
        set = new TreeSet();
    }
    
    public int popSmallest() {
        int ans;
        if (!set.isEmpty()) {
            ans = set.first();
            set.remove(ans);
        } else ans = cur++;
        return ans;
    }
    
    public void addBack(int num) {
        if (num >= cur || set.contains(num)) return;
        set.add(num);
    }
}
