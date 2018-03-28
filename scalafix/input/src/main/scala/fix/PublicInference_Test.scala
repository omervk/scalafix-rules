/*
rule = "class:fix.PublicInference"
*/
package fix

object PublicInference_Test {
  val test1 = "" // assert: PublicInference
  var test2 = "" // assert: PublicInference
  lazy val test3 = "" // assert: PublicInference
  def test4 = "" // assert: PublicInference

  private object Private {
    val test1 = ""
    var test2 = ""
    lazy val test3 = ""
    def test4 = ""
  }

  protected class Protected {
    val test1 = "" // assert: PublicInference
    var test2 = "" // assert: PublicInference
    lazy val test3 = "" // assert: PublicInference
    def test4 = "" // assert: PublicInference
  }

  private[fix] object PrivatePackage {
    val test1 = "" // assert: PublicInference
    var test2 = "" // assert: PublicInference
    lazy val test3 = "" // assert: PublicInference
    def test4 = "" // assert: PublicInference
  }

  class Public {
    val test1 = "" // assert: PublicInference
    var test2 = "" // assert: PublicInference
    lazy val test3 = "" // assert: PublicInference
    def test4 = "" // assert: PublicInference
  }

  class Overriding extends Public {
    override val test1 = "" // assert: PublicInference
    override lazy val test3 = "" // assert: PublicInference
    override def test4 = "" // assert: PublicInference
  }
}
