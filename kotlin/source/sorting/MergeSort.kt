package source.sorting

fun main() {
    val arr = arrayOf(1, 5, 2, 6, 3, 7, 4, 5, 3, 6, 8)
    mergeSort(arr)
    arr.forEach { print("$it ") }
}

private fun mergeSort(arr: Array<Int>) {
    helper(arr, 0, arr.size-1)
}

private fun helper(arr: Array<Int>, start: Int, end: Int) {
    if (end <= start) return
    val mid = start + (end - start)/2
    helper(arr, start, mid)
    helper(arr, mid+1, end)
    merge(end, start, mid, arr)
}

private fun merge(end: Int, start: Int, mid: Int, arr: Array<Int>) {
    val temp = arrayOfNulls<Int>(end - start + 1)
    var i = start
    var j = mid + 1
    var k = 0
    while (i <= mid && j <= end) {
        if (arr[i] <= arr[j]) {
            temp[k++] = arr[i++]
        } else {
            temp[k++] = arr[j++]
        }
    }
    while (i <= mid) {
        temp[k++] = arr[i++]
    }
    while (j <= end) {
        temp[k++] = arr[j++]
    }
    for (i in 0..end - start) {
        arr[start + i] = temp[i]!!
    }
}