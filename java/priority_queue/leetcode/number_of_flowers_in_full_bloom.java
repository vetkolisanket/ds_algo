/*
2251. Number of Flowers in Full Bloom

You are given a 0-indexed 2D integer array flowers, where flowers[i] = [starti, endi] means the ith flower will be in full bloom from starti to endi (inclusive). You are also given a 0-indexed integer array persons of size n, where persons[i] is the time that the ith person will arrive to see the flowers.

Return an integer array answer of size n, where answer[i] is the number of flowers that are in full bloom when the ith person arrives.

 

Example 1:


Input: flowers = [[1,6],[3,7],[9,12],[4,13]], persons = [2,3,7,11]
Output: [1,2,2,2]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
Example 2:


Input: flowers = [[1,10],[3,3]], persons = [3,3,2]
Output: [2,2,1]
Explanation: The figure above shows the times when the flowers are in full bloom and when the people arrive.
For each person, we return the number of flowers in full bloom during their arrival.
 

Constraints:

1 <= flowers.length <= 5 * 10^4
flowers[i].length == 2
1 <= starti <= endi <= 10^9
1 <= persons.length <= 5 * 10^4
1 <= persons[i] <= 10^9
*/

//Using tree map
class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        // 0: Bloom, 1: Person 2: Wilt
        TreeMap<Integer, Integer> map = new TreeMap();
        for (int[] flower: flowers) {
            map.put(flower[0], map.getOrDefault(flower[0], 0)+1);
            map.put(flower[1]+1, map.getOrDefault(flower[1]+1, 0)-1);
        }
        TreeMap<Integer, Integer> sum = new TreeMap();
        int pre = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            pre += entry.getValue();
            sum.put(entry.getKey(), pre);
        }
        int[] res = new int[persons.length];
        for (int i=0;i<persons.length;i++) {
            Map.Entry<Integer, Integer> entry = sum.floorEntry(persons[i]);
            if (entry != null) {
                res[i] = entry.getValue();
            }
        }
        return res;
    }
}

//Using priority queue
class Solution {
    // 0: Bloom 1: Person 2: Wilt/Drop
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) ->
           a[0] == b[0] ? a[1]-b[1] : a[0] - b[0]
        );
        for (int i=0;i<persons.length;i++) {
            pq.offer(new int[]{persons[i], 1, i});
        }
        for (int[] flower: flowers) {
            pq.offer(new int[]{flower[0], 0});
            pq.offer(new int[]{flower[1], 2});
        }
        int[] res = new int[persons.length];
        int blooms = 0;
        while (!pq.isEmpty()) {
            int[] val = pq.poll();
            if (val[1] == 0) {
                blooms++;
            } else if (val[1] == 2) {
                blooms--;
            } else {
                res[val[2]] = blooms;
            }
        }
        return res;
    }
}

//Soln using priority queue TC O(NlogN + M(logM + logN)) SC O(M+N) where N is the no. of people and M is the no. of flowers
class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        int[] sortedPeople = Arrays.copyOf(people, people.length);
        Arrays.sort(sortedPeople);
        Arrays.sort(flowers, (a,b) -> Arrays.compare(a, b));
        PriorityQueue<Integer> pq = new PriorityQueue();
        Map<Integer, Integer> map = new HashMap();

        int i=0;
        for (int person: sortedPeople) {
            while (i < flowers.length && flowers[i][0] <= person) {
                pq.offer(flowers[i++][1]);
            }
            while (!pq.isEmpty() && pq.peek() < person) {
                pq.poll();
            }
            map.put(person, pq.size());
        }
        int[] ans = new int[people.length];
        for (i=0;i<people.length;i++) {
            ans[i] = map.get(people[i]);
        }
        return ans;
    }
}
