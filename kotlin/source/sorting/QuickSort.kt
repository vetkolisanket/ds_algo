package source.sorting

import kotlin.random.Random

fun main() {
    val arr = arrayOf(1, 5, 2, 6, 3, 7, 4, 5, 3, 6, 8)
    quickSort(arr)
    arr.forEach { print("$it ") }
}

private fun quickSort(arr: Array<Int>) {
    helper(arr, 0, arr.size-1)
}

private fun helper(arr: Array<Int>, start: Int, end: Int) {
    if (start >= end) return
    val pivotIdx = Random.nextInt(start, end+1)
    swap(arr, start, pivotIdx)
    var smaller = start
    for (bigger in start+1..end) {
        if (arr[bigger] < arr[start]) {
            smaller++
            swap(arr, smaller, bigger)
        }
    }
    swap(arr, start, smaller)
    helper(arr, start, smaller-1)
    helper(arr, smaller+1, end)
}

private fun swap(arr: Array<Int>, a: Int, b: Int) {
    val temp = arr[a]
    arr[a] = arr[b]
    arr[b] = temp
}