

// By name param DEF :: def func(arg: => String) = ???


// ===================
// Avoiding evaluation
// ===================
var logLevel = 1

def log(level: Int, msg: => String) = {
  if (level > logLevel) println(msg)
}

log(2, "hello" + 232 + "world")

logLevel = 3
log(2, "hello" + 232 + "world")


// ===================
// Wrapping evaluation
// ===================

def measureTime(f: => Unit) = {
  val start = System.currentTimeMillis()
  f
  val end = System.currentTimeMillis()
  println("Evaluation took " + (end - start) + "ms")
}

measureTime {
  new Array[String](10 * 1000 * 1000).hashCode()
}


// ====================
// Repeating evaluation
// ====================
def retry[T](max: Int)(f: => T): T = {
  var tries = 0
  var result: Option[T] = None
  while (result == None) {
    try { result = Some(f) }
    catch {
      case e: Throwable => tries += 1
        if (tries > max) throw e
        else println(s"failed, retry #$tries")
    }
  }
  result.get
}


// succeeds only with 200 response
val res = retry(max = 5) {
  if(scala.util.Random.nextInt % 4 != 0) throw new RuntimeException("There was smth wrong")
  else 42
}

println("Retry result: " + res)