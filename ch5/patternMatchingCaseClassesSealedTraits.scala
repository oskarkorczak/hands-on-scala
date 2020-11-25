
sealed trait Expression
case class BinOp(left: Expression, op: String, right: Expression) extends Expression
case class Literal(value: Int) extends Expression
case class Variable(name: String) extends Expression

def stringify(expr: Expression): String = {
  expr match {
    case Literal(value: Int) => value.toString
    case Variable(name: String) => name
    case BinOp(l: Expression, op: String, r: Expression) => s"${stringify(l)} $op ${stringify(r)}"
  }
}

// x + 1
val xPlus1 = BinOp(Variable("x"), "+", Literal(1))

// x * (y - 1)
val semiComplex = BinOp(
  Variable("x"),
  "*",
  BinOp(Variable("y"), "-", Literal(1))
)

// (x + 1) * (y - 1)
val complex = BinOp(
  BinOp(Variable("x"), "+", Literal(1)),
  "*",
  BinOp(Variable("y"), "-", Literal(1))
)

println(stringify(xPlus1))
println(stringify(semiComplex))
println(stringify(complex))