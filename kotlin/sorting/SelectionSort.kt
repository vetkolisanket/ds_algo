package sorting

fun main() {
    val arr = arrayOf(1, 5, 2, 6, 3, 7, 4, 5, 3, 6, 8)
    selectionSort(arr)
    arr.forEach { print("$it ") }
}

private fun selectionSort(arr: Array<Int>) {
    var minValIdx: Int
    for (i in arr.indices) {
        minValIdx = i
        for (j in i + 1 until arr.size) {
            if (arr[j] < arr[minValIdx]) {
                minValIdx = j
            }
        }
        swap(arr, i, minValIdx)
    }
}

private fun swap(arr: Array<Int>, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}