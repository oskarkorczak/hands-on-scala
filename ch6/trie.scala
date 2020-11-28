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

  def prefixesMatchingString0(s: String): Set[Int] = {
    var current = Option(root)
    val output = Set.newBuilder[Int]
    for((c, i) <- s.zipWithIndex if current.nonEmpty) {
      if (current.get.hasValue) output += i
      current = current.get.children.get(c)
    }
    if(current.exists(_.hasValue)) output += s.length
    output.result()
  }

  def prefixesMatchingString(s: String): Set[String] = {
    prefixesMatchingString0(s).map(s.substring(0, _))
  }
  def stringsMatchingPrefix(s: String): Set[String] = {
    var current = Option(root)
    for (c <- s if current.nonEmpty) current = current.get.children.get(c) // initial walk
    if (current.isEmpty) Set()
    else {
      val output = Set.newBuilder[String]

      def recurse(current: Node, path: List[Char]): Unit = {
        if (current.hasValue) output += (s + path.reverse.mkString)
        for ((c, n) <- current.children) recurse(n, c :: path)
      }

      recurse(current.get, Nil) // recursive walk
      output.result()
    }
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


  println(s"Prefix matching: manible ${t.prefixesMatchingString0("manible")}") // Set(3)
  println(s"Prefix matching: mangosteen ${t.prefixesMatchingString0("mangosteen")}") // Set(3, 5)

  // Set("man", "mango")
  println(s"Prefix words matching: mangosteen ${t.prefixesMatchingString("mangosteen")}")

  // Set("man", "mandarin", "mango")
  println(s"Strings matching prefix: man ${t.stringsMatchingPrefix("man")}")
  // Set("map", "man", "mandarin", "mango")
  println(s"Strings matching prefix: man ${t.stringsMatchingPrefix("ma")}")
  // Set("map")
  println(s"Strings matching prefix: man ${t.stringsMatchingPrefix("map")}")
  // Set("mandarin")
  println(s"Strings matching prefix: man ${t.stringsMatchingPrefix("mand")}")
  // Set()
  println(s"Strings matching prefix: man ${t.stringsMatchingPrefix("mando")}")