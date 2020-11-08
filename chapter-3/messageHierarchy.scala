/**
 * Exercise: Write a recursive method printMessages that can receive an array of Msg class instances,
 * each with an optional parent ID, and use it to print out a threaded fashion. That means that child
 * messages are print out indented underneath their parents, and the nesting can be arbitrarily deep.
 */

class Msg(val id: Int, val parent: Option[Int], val txt: String)

def printMessages(messages: Array[Msg]): Unit = {
  def iter(parent: Option[Int], indent: String): Unit = {
    for (m <- messages if m.parent == parent) {
      println(s"$indent#${m.id} ${m.txt}")
      iter(Some(m.id), indent + "   ")
    }
  }
  iter(None, "")
}

printMessages(Array(
  new Msg(0, None, "Hello"),
  new Msg(1, Some(0), "World"),
  new Msg(2, None, "I am Cow"),
  new Msg(3, Some(2), "Hear me moo"),
  new Msg(4, Some(2), "Here I stand"),
  new Msg(5, Some(2), "I am Cow"),
  new Msg(6, Some(5), "Here me moo, moo")
))