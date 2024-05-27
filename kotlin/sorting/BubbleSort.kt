package sorting

fun main() {
    val arr = arrayOf(1, 5, 2, 6, 3, 7, 4, 5, 3, 6, 8)
    bubbleSort(arr)
    arr.forEach { print("$it ") }
}

private fun bubbleSort(arr: Array<Int>) {
    for (i in arr.indices) {
        for (j in arr.size-1 downTo i+1) {
            if (arr[j] < arr[j-1]) {
                swap(arr, j, j-1)
            }
        }
    }
}

private fun swap(arr: Array<Int>, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}