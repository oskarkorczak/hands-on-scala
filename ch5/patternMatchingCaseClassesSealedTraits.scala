
sealed trait Expression

case class BinOp(left: Expression, op: String, right: Expression) extends Expression

case class Literal(value: Int) extends Expression

case class Variable(name: String) extends Expression

def stringify(expr: Expression): String = expr match {
  case Literal(value: Int) => value.toString
  case Variable(name: String) => name
  case BinOp(l: Expression, op: String, r: Expression) => s"(${stringify(l)} $op ${stringify(r)})"
}

def evaluate(expr: Expression, values: Map[String, Int]): Int = expr match {
  case Literal(value: Int) => value
  case Variable(name: String) => values(name)
  case BinOp(left, "+", right) => evaluate(left, values) + evaluate(right, values)
  case BinOp(left, "-", right) => evaluate(left, values) - evaluate(right, values)
  case BinOp(left, "*", right) => evaluate(left, values) * evaluate(right, values)
}

// x + 1
val exp1 = BinOp(Variable("x"), "+", Literal(1))

// x * (y - 1)
val exp2 = BinOp(
  Variable("x"),
  "*",
  BinOp(Variable("y"), "-", Literal(1))
)

// (x + 1) * (y - 1)
val exp3 = BinOp(
  BinOp(Variable("x"), "+", Literal(1)),
  "*",
  BinOp(Variable("y"), "-", Literal(1))
)

val exp1Str = stringify(exp1)
val exp1Res = evaluate(exp1, Map("x" -> 1))
println(s"$exp1Str for x = 1 is $exp1Res")

val exp2Str = stringify(exp2)
val exp2Res = evaluate(exp2, Map("x" -> 1, "y" -> 2))
println(s"$exp2Str for x = 1 and y = 2 is $exp2Res")

val exp3Str = stringify(exp3)
val exp3Res = evaluate(exp3, Map("x" -> 1, "y" -> 2))
println(s"$exp3Str for x = 1 and y = 2 is $exp3Res")