import org.apache.tools.ant.filters.StringInputStream
import org.scalatest.{FunSuite, Matchers}

import scala.io._

class GivenScenario extends FunSuite with Matchers{

  test("spike system out") {

    val out = new java.io.ByteArrayOutputStream()

    Console.withIn(new StringInputStream("Gabriele")) {
      Console.withOut(out) {
        println("What is your name?")
        val name = StdIn.readLine()
        print(s"Hello $name")
      }
    }
    out.toString() should be("What is your name?\n" +
      "Hello Gabriele")
  }
}
