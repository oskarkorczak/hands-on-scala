// Regular use case for implicits

class Foo(val value: Int)
implicit val fou = new Foo(5)

def bar(implicit foo: Foo) = foo.value + 10

/**
 * Typeclass - associates values to types utilising implicit parameters.
 * Term comes from Haskell and has nothing to do with types and classes in Scala.
 *
 * Problem statement
 * Consider the task of parsing command-line arguments, given as Strings, into Scala values of various
 * types: Ints, Booleans, Doubles etc.
 */


/**
 * 1st problem sketch, where it is rather impossible for `parseFromString` to know how to convert
 * the given String into an arbitrary T type.
 */
//def parseFromString[T](s: String): T = ???

val args = Seq("123", "true", "7.5")
//val myInt = parseFromString[Int](args(0))
//val myBoolean = parseFromString[Boolean](args(1))
//val myDouble = parseFromString[Double](args(2))


/**
 * 2nd problem sketch utilises trait and its implementations, which is rather natural and works.
 * However, this approach has another problem: what if developer wants to parse value coming from Console,
 * not the string directly?
 */

trait StrParser[T] { def parse(s: String): T }
object ParseInt extends StrParser[Int]  { override def parse(s: String): Int = s.toInt }
object ParseBoolean extends StrParser[Boolean] { override def parse(s: String): Boolean = s.toBoolean }
object ParseDouble extends StrParser[Double] { override def parse(s: String): Double = s.toDouble }

val myInt = ParseInt.parse(args(0))
val myBoolean = ParseBoolean.parse(args(1))
val myDouble = ParseDouble.parse(args(2))

println("=" * 30)
println(myInt)
println(myBoolean)
println(myDouble)


/**
 * 3rd approach uses parseFromConsole function, which takes as parameter the StrParser.
 * Now the problem is fact that we need pass StrParser implementations to all different
 * kinds of parsing functions like: parseFromConsole, parseFromFile, parseFromNetwork etc.
 * Another thing is that most likely there is a single ParseInt parser needed for all those functions.
 * It is rather clumsy to pass it around all the time, even though it is still the same parser.
 */

def parseFromConsole[T](parser: StrParser[T]): T = parser.parse(scala.Console.in.readLine())

//val myInt2 = parseFromConsole[Int](ParseInt)
//val myBoolean2 = parseFromConsole[Boolean](ParseBoolean)
//val myDouble2 = parseFromConsole[Double](ParseDouble)


/**
 * Solution: Implicit parsers.
 * This solution looks very similar to initial approach. It also allows to create other functions
 * reading from different sources and using same parsers implicitly without passing around all the time
 * same instance.
 */

trait StringParser[T] { def parse(s:String): T }
object StringParser {
  implicit object IntParser extends StringParser[Int] {
    override def parse(s: String): Int = s.toInt
  }

  implicit object BooleanParser extends StringParser[Boolean] {
    override def parse(s: String): Boolean = s.toBoolean
  }

  implicit object DoubleParser extends StringParser[Double] {
    override def parse(s: String): Double = s.toDouble
  }
}

def parseFromString[T](s: String)(implicit parser: StringParser[T]): T = {
  parser.parse(s)
}

val myInt3 = parseFromString[Int](args(0))
val myBoolean3 = parseFromString[Boolean](args(1))
val myDouble3 = parseFromString[Double](args(2))

println("=" * 30)
println(myInt3)
println(myBoolean3)
println(myDouble3)


def parseFromConsole2[T](implicit parser: StringParser[T]): T = {
  parser.parse(scala.Console.in.readLine())
}

val consoleInt = parseFromConsole2[Int]