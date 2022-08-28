/*
1329. Sort the Matrix Diagonally

A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and going in the bottom-right direction until reaching the matrix's end. For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].

Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

 

Example 1:


Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
Example 2:

Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 100
1 <= mat[i][j] <= 100
*/

//My sol TC O((m+n)xlog(x)) SC O(x) where m,n is the no. of rows and cols respectively and x = Math.min(m,n)
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int len = Math.min(m, n);
        int[] arr = new int[len];
        for (int i=0;i<m;i++) {
            Arrays.fill(arr, Integer.MAX_VALUE);
            for (int row=i, col=0;row<m && col<n; row++, col++) {
                arr[col] = mat[row][col];
            }
            Arrays.sort(arr);
            for (int row=i, col=0;row<m && col < n; row++, col++) {
                mat[row][col] = arr[col];
            }
        }
        for (int i=0;i<n;i++) {
            Arrays.fill(arr, Integer.MAX_VALUE);
            for (int row=0, col=i;row<m && col<n; row++, col++) {
                arr[row] = mat[row][col];
            }
            Arrays.sort(arr);
            for (int row=0, col=i;row<m && col < n; row++, col++) {
                mat[row][col] = arr[row];
            }
        }
        return mat;
    }
}

//A slower but more readable soln TC O(mnlog(x)) SC O(mn)
class Solution {
    public int[][] diagonalSort(int[][] mat) {
        // Store the matrix dimensions
        int m = mat.length;
        int n = mat[0].length;
        
        // Data structure to store the diagonals.
        Map<Integer, Queue<Integer>> diagonals = new HashMap<>();
        
        // Insert values into the HashMap.
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) { 
                diagonals.putIfAbsent(row - col, new PriorityQueue<>());
                diagonals.get(row - col).add(mat[row][col]);
            }   
        }

        // Take values back out of the HashMap.
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                mat[row][col] = diagonals.get(row - col).remove();
            }
        }
        
        return mat;
    }
}

//A slightly more space efficient soln TC O(mnlog(x)) SC O(x)
class Solution {
    
    public int[][] diagonalSort(int[][] mat) {
        // Store the matrix dimensions.
        int m = mat.length;
        int n = mat[0].length;
        
        // Sort each diagonal that starts on a row.
        for (int row = 0; row < m; row++) {
            sortDiagonal(row, 0, mat);       
        } 

        // Sort each diagonal that starts on a col.
        // Note that we've already sorted the one starting
        // at col = 0; this is the same as the one starting
        // at row = 0.
        for (int col = 1; col < n; col++) {
            sortDiagonal(0, col, mat);  
        } 
        
        return mat;
    }

    private void sortDiagonal(int row, int col, int[][] mat) {
        int m = mat.length; 
        int n = mat[0].length;
        Queue<Integer> diagonal = new PriorityQueue<>();
        
        int diagonalLength = Math.min(m - row, n - col);
        // Put values on this diagonal into the heap.
        for (int i = 0; i < diagonalLength; i++) {
            diagonal.add(mat[row + i][col + i]);
        }
        // Put values on heap back into this diagonal
        for (int i = 0; i < diagonalLength; i++) {
            mat[row + i][col + i] = diagonal.remove();
        }
    }
}

//Using counting sort TC O(mn) SC O(x) given difference between min and max values in matrix is small and can be considered constant
class Solution {
    
    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int row = 0; row < m; row++) {
            sortDiagonal(row, 0, mat);
        }

        for (int col = 1; col < n; col++) {
            sortDiagonal(0, col, mat);
        }

        return mat;
    }

    private List<Integer> countingSort(List<Integer> nums) {
        // The problem constraints allow us to assume that
        // 1 <= mat[i][j] <= 100.
        // You should, however, confirm with the interviewer
        // that it is OK to hardcode values like this.
        int min = 1; // You could also use: Collections.min(nums); 
        int max = 100; // You could also use: Collections.max(nums);
        
        // Calculate the range of values, and then create a list
        // of the size of the range.
        int len = max - min + 1;
        int[] count = new int[len];
        // Tally up the values in nums.
        for (int num : nums) {
            count[num - min]++;
        }

        // Flatten the list of counts into a sorted list.
        List<Integer> sortedNums = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int times = count[i]; times > 0; times--) {
                sortedNums.add(i + min);
            }
        }

        return sortedNums;
    }

    private void sortDiagonal(int row, int col, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        List<Integer> diagonal = new ArrayList<>();

        int diagonalLength = Math.min(m - row, n - col);
        for (int i = 0; i < diagonalLength; i++) {
            diagonal.add(mat[row + i][col + i]);
        }

        // Sort the diagonal using our counting sort method.
        diagonal = countingSort(diagonal);

        for (int i = 0; i < diagonalLength; i++) {
            mat[row + i][col + i] = diagonal.remove(0);
        }
    }
}
