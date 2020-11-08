/**
 * TASK: FizzBuzz Kata
 * Write a short program that prints each number from 1 to 100 on a new line.
 * For each multiple of 3, print "Fizz" instead of the number.
 * For each multiple of 5, print "Buzz" instead of the number.
 * For numbers which are multiples of both 3 and 5, print "FizzBuzz" instead of the number.
*/

for (i <- Range.inclusive(0, 100)) {
  println(
    if (i % 3 == 0 && i % 5 == 0) "FizzBuzz"
    else if (i % 3 == 0) "Fizz"
    else if (i % 5 == 0) "Buzz"
    else i
  )
}

println("\nFizzBuzz returned as a Seq")
val fizzbuzz = for (i <- Range.inclusive(0, 100)) yield {
    if (i % 3 == 0 && i % 5 == 0) "FizzBuzz"
    else if (i % 3 == 0) "Fizz"
    else if (i % 5 == 0) "Buzz"
    else i.toString()
}
println(fizzbuzz)

