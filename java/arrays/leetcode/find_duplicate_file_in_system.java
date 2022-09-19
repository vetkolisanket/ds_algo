/*
609. Find Duplicate File in System

Given a list paths of directory info, including the directory path, and all the files with contents in this directory, return all the duplicate files in the file system in terms of their paths. You may return the answer in any order.

A group of duplicate files consists of at least two files that have the same content.

A single directory info string in the input list has the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"
It means there are n files (f1.txt, f2.txt ... fn.txt) with content (f1_content, f2_content ... fn_content) respectively in the directory "root/d1/d2/.../dm". Note that n >= 1 and m >= 0. If m = 0, it means the directory is just the root directory.

The output is a list of groups of duplicate file paths. For each group, it contains all the file paths of the files that have the same content. A file path is a string that has the following format:

"directory_path/file_name.txt"
 

Example 1:

Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)","root 4.txt(efgh)"]
Output: [["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
Example 2:

Input: paths = ["root/a 1.txt(abcd) 2.txt(efgh)","root/c 3.txt(abcd)","root/c/d 4.txt(efgh)"]
Output: [["root/a/2.txt","root/c/d/4.txt"],["root/a/1.txt","root/c/3.txt"]]
 

Constraints:

1 <= paths.length <= 2 * 104
1 <= paths[i].length <= 3000
1 <= sum(paths[i].length) <= 5 * 105
paths[i] consist of English letters, digits, '/', '.', '(', ')', and ' '.
You may assume no files or directories share the same name in the same directory.
You may assume each given directory info represents a unique directory. A single blank space separates the directory path and file info.
 

Follow up:

Imagine you are given a real file system, how will you search files? DFS or BFS?
If the file content is very large (GB level), how will you modify your solution?
If you can only read the file by 1kb each time, how will you modify your solution?
What is the time complexity of your modified solution? What is the most time-consuming part and memory-consuming part of it? How to optimize?
How to make sure the duplicated files you find are not false positive?

*/

//My soln TC O(nx) SC O(nx) where n is the no. of strings, x is the average length of the string
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap();
        for (String path: paths) {
            String[] arr = path.split(" ");
            for (int i=1;i<arr.length;i++) {
                String content = getContent(arr[i]);
                map.putIfAbsent(content, new ArrayList());
                map.get(content).add(arr[0] + "/" + getPath(arr[i]));
            }
        }
        List<List<String>> list = new ArrayList();
        for(Map.Entry<String, List<String>> entry: map.entrySet()) {
            List<String> temp = entry.getValue();
            if (temp.size() > 1) {
                list.add(temp);
            }
        }
        return list;
    }
    
    private String getContent(String content) {
        int index = content.indexOf("(");
        return content.substring(index+1, content.length()-1);
    }
    
    private String getPath(String path) {
        int openIndex = path.indexOf('(');
        return path.substring(0, openIndex);
    }
}

/*
Follow Up

BFS can be used for great concurrency. Also, seek time would be greatly reduced as the files are co-located. Where as DFS would be requiring a lock on root node, if you are simultaneous processing the contents.

If the files are too large, its better to Navigate first to get a list of file paths and then process the hash-map.

Check the sizes of the files, if they match we need further processing.

Maintain a checksum for the similar sizes, hash functions like sha256(hashes hardly collide) can be used to calculate checksums.

If we can read only, 1Kb at a time, we can still use checksum for the blocks and calculate till the point they differ.

May be read 0.5 kb from file 1 and 0.5 kb from file2 to check if they differ.

import hashlib
m = hashlib.sha256()
m.update(b"Nobody inspects")
m.update(b" the spammish repetition")
m.hexdigest()
'19197dc4d03829df858011c6c87600f994a858103bbc19005f20987aa19a97e2'
Time Complexty:

For navigating, the files O(n), where n is the total number of files.

For, calculating the checksums in case of similar sizes, we could approximate this as O( c + xb), where b is the number of blocks required to read till the files being compared differ, x is the constant for per-block overhead, and c is the constant for initialization and finalization.

Further O(n) time is required to loop over the hashmap(key = f(size, checksum till they branch)) and return the once having more than 1 file path.

Shloud consider to read the whole file content byte to byte.

*/
