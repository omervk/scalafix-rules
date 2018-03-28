/*
rule = "class:fix.PublicInference"
*/
package fix

object PublicInference_Test {
  val test1 = "" // assert: PublicInference
  var test2 = "" // assert: PublicInference
  lazy val test3 = "" // assert: PublicInference
  def test4 = "" // assert: PublicInference
  implicit val test5 = 1 // assert: PublicInference
  implicit var test6 = 1.1 // assert: PublicInference
  implicit lazy val test7 = None // assert: PublicInference
  implicit def test8 = List.empty // assert: PublicInference

  private object Private {
    val test1 = ""
    var test2 = ""
    lazy val test3 = ""
    def test4 = ""
    implicit val test5 = 1
    implicit var test6 = 1.1
    implicit lazy val test7 = None
    implicit def test8 = List.empty
  }

  protected class Protected {
    val test1 = "" // assert: PublicInference
    var test2 = "" // assert: PublicInference
    lazy val test3 = "" // assert: PublicInference
    def test4 = "" // assert: PublicInference
    implicit val test5 = 1 // assert: PublicInference
    implicit var test6 = 1.1 // assert: PublicInference
    implicit lazy val test7 = None // assert: PublicInference
    implicit def test8 = List.empty // assert: PublicInference
  }

  private[fix] object PrivatePackage {
    val test1 = "" // assert: PublicInference
    var test2 = "" // assert: PublicInference
    lazy val test3 = "" // assert: PublicInference
    def test4 = "" // assert: PublicInference
    implicit val test5 = 1 // assert: PublicInference
    implicit var test6 = 1.1 // assert: PublicInference
    implicit lazy val test7 = None // assert: PublicInference
    implicit def test8 = List.empty // assert: PublicInference
  }

  class Public {
    val test1 = "" // assert: PublicInference
    var test2 = "" // assert: PublicInference
    lazy val test3 = "" // assert: PublicInference
    def test4 = "" // assert: PublicInference
    implicit val test5 = 1 // assert: PublicInference
    implicit var test6 = 1.1 // assert: PublicInference
    implicit lazy val test7 = None // assert: PublicInference
    implicit def test8 = List.empty // assert: PublicInference
  }

  class Overriding extends Public {
    override val test1 = "" // assert: PublicInference
    override lazy val test3 = "" // assert: PublicInference
    override def test4 = "" // assert: PublicInference
    override implicit val test5 = 1 // assert: PublicInference
    override implicit lazy val test7 = None // assert: PublicInference
    override implicit def test8 = List.empty // assert: PublicInference
  }
}
