import java.io.{BufferedReader, BufferedWriter}
import java.nio.file.{Files, Paths}

/**
 * Exercise: Define a pair of methods withFileWriter and withFileReader that can be called as shown
 * below. Each method should take the name of a file, and a function value that is called with a
 * java . io . BufferedReader or java . io . BufferedWriter that it can use to read or write data. Opening
 * and closing of the reader/writer should be automatic, such that a caller cannot forget to close the file.
 * This is similar to Python "context managers" or Java "try-with-resource" syntax.
 */

def withFileWriter[T](filename: String)(fun: BufferedWriter => T): Unit = {
  val out = Files.newBufferedWriter(Paths.get(filename))
  try fun(out)
  finally out.close()
}

def withFileReader[T](filename: String)(fun: BufferedReader => T): Unit = {
  val in = Files.newBufferedReader(Paths.get(filename))
  try fun(in)
  finally in.close()
}


withFileWriter("File.txt") { writer =>
  writer.write("Hello\n"); writer.write("World!\n")
}
val result: String = withFileReader("File.txt") { reader =>
  reader.readLine() + "\n" + reader.readLine() + "\n" + reader.readLine()
}
println(result + "TEST")
assert(result == "Hello\nWorld!")