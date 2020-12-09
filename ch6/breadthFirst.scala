/**
 * Breadth First Search Algorithm:
 *
 * Keep a seen set of all nodes you have seen so far, and a queue of nodes waiting to be processed
 *
 * Start with your starting node in both the seen set and queue
 *
 * While the queue is not empty, pull a node off the queue
 *
 * For every node which is reachable from the node you just pulled off the queue, if it isn't present in the
 * seen set, add it to both the seen set and queue
 *
 * When the queue is empty, we have searched the entire graph
 */

def search[T](start: T, graph: Map[T, Seq[T]]): Set[T] = {
  val seen = collection.mutable.Set(start)
  val queue = collection.mutable.ArrayDeque(start)
  while (queue.nonEmpty) {
    val current = queue.removeHead()
    for (next <- graph(current) if !seen.contains(next)) {
      seen.add(next)
      queue.append(next)
    }
  }
  seen.to(Set)
}

val graph1 = Map(
  "a" -> Seq("b", "c"),
  "b" -> Seq("a"),
  "c" -> Seq("b")
)
val res1 = search("c", graph1)
println(s"Graph: $graph1; search: $res1")


val graph2 = Map(
  "a" -> Seq("b", "c"),
  "b" -> Seq("c", "d"),
  "c" -> Seq("d"),
  "d" -> Seq()
)
val res2 = search("a", graph2)
println(s"Graph: $graph2; search: $res2")

val res3 = search("c", graph2)
println(s"Graph: $graph2; search: $res3")
