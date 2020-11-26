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

