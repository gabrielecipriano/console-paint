import org.scalatest.{Matchers, FunSuite}

class GivenScenario extends FunSuite with Matchers{

  test("spike system out") {

    val stream = new java.io.ByteArrayOutputStream()

    Console.withOut(stream) {
      new ConsolePaint()
    }

    stream.toString() should be("")
  }
}
