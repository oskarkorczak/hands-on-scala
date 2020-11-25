

def fizzBuzzTupleInt(): Unit = {
  for (i <- Range.inclusive(0, 100)) {
    val s = (i % 3, i % 5) match {
      case (0, 0) => "FizzBuzz"
      case (0, _) => "Fizz"
      case (_, 0) => "Buzz"
      case _ => i
    }
    println(s)
  }
}

def fizzBuzzTupleBool(): Unit = {
  for (i <- Range.inclusive(0, 100)) {
    val s = (i % 3 == 0, i % 5 == 0) match {
      case (true, true) => "FizzBuzz"
      case (true, false) => "Fizz"
      case (false, true) => "Buzz"
      case (false, false) => i
    }
    println(s)
  }
}

fizzBuzzTupleInt()
fizzBuzzTupleBool()