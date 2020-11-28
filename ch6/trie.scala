class Trie() {

  class Node(var hasValue: Boolean,
             val children: collection.mutable.Map[Char, Node] = collection.mutable.Map())

  val root = new Node(false)

  def add(s: String): Unit = {
    var current = root
    for (c <- s) {
      current = current.children.getOrElseUpdate(c, new Node(false))
    }
    current.hasValue = true
  }

  def contains(s: String): Boolean = {
    var current = Option(root)
    for (c <- s if current.nonEmpty) {
      current = current.get.children.get(c)
    }
    current.exists(_.hasValue)
  }
}

  val t = new Trie()
  t.add("mango")
  t.add("mandarin")
  t.add("map")
  t.add("man")

  println(s"Has mango: ${t.contains("mango")}") // true
  println(s"Has mang: ${t.contains("mang")}") // false
  println(s"Has man: ${t.contains("man")}") // true
  println(s"Has mandarin: ${t.contains("mandarin")}") //true
  println(s"Has mandarine: ${t.contains("mandarine")}") //false
