/**
 * Exercise: Modify the def retry function earlier that takes a by-name parameter
 * and make it perform an exponential backoff, sleeping between retries,
 * with a configurable initial delay in milliseconds:
 */

def exponentialBackoff[T](initDelayMs: Int)(f: => T): T = {
  var delay = initDelayMs
  var result: Option[T] = None
  while (result.isEmpty) {
    try { result = Some(f) }
    catch {
      case _: Throwable => {
        println(delay)
        Thread.sleep(delay)
        delay *= 2
      }
    }
  }
  result.get
}

val res = exponentialBackoff(1) {
  if(scala.util.Random.nextInt % 5 != 0) throw new RuntimeException("There was smth wrong")
  else 42
}

println("Retry result: " + res)
