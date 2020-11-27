/**
 * Exercise: Define a function that uses pattern matching on the Expr s we saw earlier to perform simple
 * algebraic simplifications:
 * (1 + 1)                          2
 * ((1 + 1) * x)                    (2 * x)
 * ((2 - 1) * x)                    x
 * (((1 + 1) * y) + ((1 - 1) * x))  (2 * y)
 */

sealed trait Expression
case class Literal(value: Int) extends Expression
case class Variable(name: String) extends Expression
case class BinaryOp(left: Expression, op: String, right: Expression) extends Expression


def reduce(expr: Expression): Expression = {
  val res = expr match {
    case BinaryOp(Literal(leftVal), "+", Literal(rightVal)) => Literal(leftVal + rightVal)
    case BinaryOp(Literal(leftVal), "-", Literal(rightVal)) => Literal(leftVal - rightVal)
    case BinaryOp(Literal(leftVal), "*", Literal(rightVal)) => Literal(leftVal * rightVal)

    case BinaryOp(Literal(0), "+", right) => reduce(right)
    case BinaryOp(left, "+", Literal(0)) => reduce(left)

    case BinaryOp(Literal(1), "*", right) => reduce(right)
    case BinaryOp(left, "*", Literal(1)) => reduce(left)

    case BinaryOp(left, "-", Literal(0)) => reduce(left)

    case BinaryOp(_, "*", Literal(0)) => Literal(0)
    case BinaryOp(Literal(0), "*", _) => Literal(0)

    case BinaryOp(left, "+", right) => BinaryOp(reduce(left), "+", reduce(right))
    case BinaryOp(left, "-", right) => BinaryOp(reduce(left), "-", reduce(right))
    case BinaryOp(left, "*", right) => BinaryOp(reduce(left), "*", reduce(right))

    case Literal(value) => Literal(value)
    case Variable(name) => Variable(name)
  }

  // We may need to re-simplify an expression multiple times in order to achieve
  // all the simplifications we want; only stop re-sim  plifying it if it stops changing
  if (res == expr) res
  else reduce(res)
}

val exp1 = BinaryOp(Literal(1), "+", Literal(1))
val res1 = reduce(exp1)
println("=" * 30)
println(s"$exp1 is reduced to $res1, which is ${res1 == Literal(2)}")

val exp2 = BinaryOp(BinaryOp(Literal(1), "+", Literal(1)), "*", Variable("x"))
val res2 = reduce(exp2)
println("=" * 30)
println(s"$exp2 is reduced to $res2, which is ${res2 == BinaryOp(Literal(2), "*", Variable("x"))}")

val exp3 = BinaryOp(BinaryOp(Literal(2), "-", Literal(1)), "*", Variable("x"))
val res3 = reduce(exp3)
println("=" * 30)
println(s"$exp3 is reduced to $res3, which is ${res3 == Variable("x")}")

val exp4 = BinaryOp(
  BinaryOp(BinaryOp(Literal(1), "+", Literal(1)), "*", Variable("y")),
  "+",
  BinaryOp(BinaryOp(Literal(1), "-", Literal(1)), "*", Variable("x")))
val res4 = reduce(exp4)
println("=" * 30)
println(s"$exp4 is reduced to $res4, which is ${res4 == BinaryOp(Literal(2), "*", Variable("y"))}")
