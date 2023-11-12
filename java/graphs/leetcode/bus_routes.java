/*
815. Bus Routes

You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

 

Example 1:

Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
Example 2:

Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1
 

Constraints:

1 <= routes.length <= 500.
1 <= routes[i].length <= 10^5
All the values of routes[i] are unique.
sum(routes[i].length) <= 10^5
0 <= routes[i][j] < 10^6
0 <= source, target < 10^6

*/

//Soln using bfs in a graph TC O(N^2) SC O(N^2)
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        int n = routes.length;
        List<Integer>[] adjList = new ArrayList[n];
        for (int i=0;i<n;i++) {
            adjList[i] = new ArrayList();
            Arrays.sort(routes[i]);
        }
        Set<Integer> seen = new HashSet();
        Set<Integer> targetSet = new HashSet();
        Queue<Pair<Integer, Integer>> q = new ArrayDeque();
        for (int i=0;i<n;i++) {
            if (Arrays.binarySearch(routes[i], source) >= 0) {
                q.offer(new Pair(i, 0));
                seen.add(i);
            }
            if (Arrays.binarySearch(routes[i], target) >= 0) {
                targetSet.add(i);
            }
            for (int j=i+1;j<n;j++) {
                if (intersect(routes[i], routes[j])) {
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }
        while (!q.isEmpty()) {
            Pair<Integer, Integer> p = q.poll();
            int depth = p.getValue(), node = p.getKey();
            if (targetSet.contains(node)) return depth+1;
            for (int neighbour: adjList[node]) {
                if (!seen.contains(neighbour)) {
                    seen.add(neighbour);
                    q.offer(new Pair(neighbour, depth+1));
                }
            }
        }
        return -1;
    }

    private boolean intersect(int[] arr1, int[] arr2) {
        int i=0, j=0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) return true;
            if (arr1[i] < arr2[j]) i++; else j++;
        }
        return false;
    }
}

//Soln from another attempt TC O(NL(logL + N)) SC O(N^2 + logL)
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        //O(NLlogL)
        for (int[] route: routes) {
            Arrays.sort(route);
        }
        List<Integer>[] adjList = new ArrayList[routes.length];
        //O(N)
        for(int i=0;i<routes.length;i++) {
            adjList[i] = new ArrayList();
        }
        //O(N^2*L)
        for (int i=0;i<routes.length;i++) {
            for (int j=i+1;j<routes.length;j++) {
                if (intersect(routes[i], routes[j])) {
                    adjList[i].add(j);
                    adjList[j].add(i);
                }
            }
        }
        boolean[] visited = new boolean[routes.length];
        Queue<Integer> q = new ArrayDeque();
        //O(NlogL)
        for (int i=0;i<routes.length;i++) {
            if (contains(routes[i], source)) {
                q.offer(i);
                visited[i] = true;
            }
        }
        int ans = 1;
        //O(NlogL)
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int node = q.poll();
                if (contains(routes[node], target)) {
                    return ans;
                }
                for (int neighbour: adjList[node]) {
                    if (visited[neighbour]) continue;
                    q.offer(neighbour);
                    visited[neighbour] = true;
                }
            }
            ans++;
        }
        return -1;
    }

    private boolean intersect(int[] routeA, int[] routeB) {
        int i = 0, j = 0;
        while (i < routeA.length && j < routeB.length) {
            if (routeA[i] == routeB[j]) return true;
            else if (routeA[i] > routeB[j]) j++;
            else i++;
        }
        return false;
    }

    private boolean contains(int[] route, int stop) {
        int start = 0, end = route.length-1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            if (route[mid] == stop) return true;
            else if (route[mid] > stop) end = mid-1;
            else start = mid+1;
        }
        return false;
    }
}
