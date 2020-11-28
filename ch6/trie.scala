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

  def prefixesMatchingString(s: String): Set[Int] = {
    var current = Option(root)
    val output = Set.newBuilder[Int]
    for((c, i) <- s.zipWithIndex if current.nonEmpty) {
      if (current.get.hasValue) output += i
      current = current.get.children.get(c)
    }
    if(current.exists(_.hasValue)) output += s.length
    output.result()
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


  println(s"Prefix matching: manible ${t.prefixesMatchingString("manible")}") // Set(3)
  println(s"Prefix matching: mangosteen ${t.prefixesMatchingString("mangosteen")}") // Set(3, 5)

  val prefixes = t.prefixesMatchingString("mangosteen").map("mangosteen".substring(0, _))
  println(s"Prefix words matching: mangosteen $prefixes") // Set("man", "mango")
