package cpaint

import org.apache.tools.ant.filters.StringInputStream
import org.scalatest.{FunSuite, Matchers}

class GivenScenario extends FunSuite with Matchers {

  test("draw a canvas") {
    check(
      withInput(
        "C 20 5"),

      outputIs("enter command: ",
        "----------------------",
        "|                    |",
        "|                    |",
        "|                    |",
        "|                    |",
        "|                    |",
        "----------------------",
        "enter command: "))
  }

  private def check(input: StringInputStream, expectedOutput: String): Any = {
    val out = new java.io.ByteArrayOutputStream()
    Console.withIn(input) {
      Console.withOut(out) {
        new ConsolePaint().execute()
      }
    }

    out.toString() should be(expectedOutput)
  }

  private def withInput(inputs: String*) = {
    new StringInputStream(joinWithLineSeparator(inputs))
  }

  private def outputIs(expectedOutput: String*) = {
    joinWithLineSeparator(expectedOutput)
  }

  private def joinWithLineSeparator(input: Seq[String]) = {
    input.mkString(System.lineSeparator())
  }
}
