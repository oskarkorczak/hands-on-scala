// Regular use case for implicits

class Foo(val value: Int)
implicit val fou = new Foo(5)

def bar(implicit foo: Foo) = foo.value + 10

