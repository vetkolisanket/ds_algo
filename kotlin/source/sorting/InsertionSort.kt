package source.sorting

fun main() {
    val arr = arrayOf(1, 5, 2, 6, 3, 7, 4, 5, 3, 6, 8)
    insertionSort(arr)
    arr.forEach { print("$it ") }
}

private fun insertionSort(arr: Array<Int>) {
    for (i in arr.indices) {
        val temp = arr[i]
        var j = i-1
        while (j >= 0 && arr[j] > temp) {
            arr[j+1] = arr[j--]
        }
        arr[j+1] = temp
    }
}
