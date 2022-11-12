/*
295. Find Median from Data Stream

The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 

Example 1:

Input
["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
[[], [1], [2], [], [3], []]
Output
[null, null, null, 1.5, null, 2.0]

Explanation
MedianFinder medianFinder = new MedianFinder();
medianFinder.addNum(1);    // arr = [1]
medianFinder.addNum(2);    // arr = [1, 2]
medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
medianFinder.addNum(3);    // arr[1, 2, 3]
medianFinder.findMedian(); // return 2.0
 

Constraints:

-10^5 <= num <= 10^5
There will be at least one element in the data structure before calling findMedian.
At most 5 * 10^4 calls will be made to addNum and findMedian.
 

Follow up:

If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
*/

//A slightly faster soln TC O(log(N)) SC O(N)
class MedianFinder {
    
    PriorityQueue<Integer> minHeap, maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }
    
    public void addNum(int num) {
        int size1 = maxHeap.size(), size2 = minHeap.size();
        if (size1 == 0 && size2 == 0) {
            maxHeap.offer(num);
            return;
        }
        if (size1 == size2) {
            int num2 = minHeap.peek();
            if (num > num2) {
                maxHeap.offer(minHeap.poll());
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
            }
        } else {
            int num1 = maxHeap.peek();
            if (num < num1) {
                minHeap.offer(maxHeap.poll());
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
        }
    }
    
    public double findMedian() {
        int size1 = maxHeap.size(), size2 = minHeap.size();
        if (size1 > size2) {
            return (double) maxHeap.peek();
        }
        int num1 = maxHeap.peek();
        int num2 = minHeap.peek();
        return (num1 + num2)/((double)2);
    }
}

//Another soln

class MedianFinder {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    
    public MedianFinder() {
        minHeap = new PriorityQueue();
        maxHeap = new PriorityQueue(Collections.reverseOrder());
    }
    
    public void addNum(int num) {
        if (minHeap.size() == maxHeap.size()) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
        balanceHeaps();
    }
    
    private void balanceHeaps() {
        if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
            while (minHeap.peek() < maxHeap.peek()) {
                int a = minHeap.poll();
                int b = maxHeap.poll();
                minHeap.offer(b);
                maxHeap.offer(a);
            }
        }
    }
    
    public double findMedian() {
        int size = minHeap.size() + maxHeap.size();
        if (size % 2 != 0) {
            return minHeap.peek();
        } else {
            return (minHeap.peek()+maxHeap.peek())/2.0;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
