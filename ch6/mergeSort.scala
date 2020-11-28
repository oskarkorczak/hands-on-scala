// Merge sort implementation

def mergeSort(a: Array[Int]): Array[Int] = {
  if (a.length <= 1) a
  else {
    val halfSize: Int = a.length / 2
    val (left, right) = a.splitAt(halfSize)
    val (sortedLeft, sortedRight) = (mergeSort(left), mergeSort(right))
    var (leftIdx, rightIdx) = (0, 0)
    val output = Array.newBuilder[Int]
    while (leftIdx < sortedLeft.length || rightIdx < sortedRight.length) {
      val takeLeft = (leftIdx < sortedLeft.length, rightIdx < sortedRight.length) match {
        case (true, false) => true
        case (false, true) => false
        case (true, true) => sortedLeft(leftIdx) < sortedRight(rightIdx)
      }
      if (takeLeft) {
        output += sortedLeft(leftIdx)
        leftIdx += 1
      } else {
        output += sortedRight(rightIdx)
        rightIdx += 1
      }
    }
    output.result()
  }
}

// try to run mergeSort from amm console
// amm --predef mergeSort.scala
val a = Array(4, 0, 1, 5, 3, 2)
val res = mergeSort(a)
println(res)