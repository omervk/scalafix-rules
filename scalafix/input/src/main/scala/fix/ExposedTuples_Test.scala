/*
rule = "class:fix.ExposedTuples"
*/
package fix

object ExposedTuples_Test {

  // can't expose a tuple from a public method
  object Test1 {
    def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public method
  object Test2 {
    def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected method
  object Test3 {
    protected def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private method
  object Test4 {
    private def bar(): (Int, String) = ???
  }

  // can't expose a tuple from a private method for a scope
  object Test5 {
    private[fix] def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a method inside another type
  object Test6 {
    def bar(): Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a method if it's the base type of another type
  object Test7 {
    def bar(): Map[Int, String] = ???
  }

  // can't expose a tuple from a public method as a parameter
  object Test8 {
    def bar(baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public method as a parameter
  object Test9 {
    def bar(baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected method as a parameter
  class Test10 {
    protected def bar(baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private method as a parameter
  object Test11 {
    private def bar(baz: (Int, String)) = ???
  }

  // can't expose a tuple from a private method as a parameter for a scope
  object Test12 {
    private[fix] def bar(baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a method as a parameter inside another type
  object Test13 {
    def bar(baz: Seq[(Int, String)]) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a method as a parameter if it's the base type of another type
  object Test14 {
    def bar(baz: Map[Int, String]) = ???
  }

  // can't expose a tuple from a public value
  object Test15 {
    val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public value
  object Test16 {
    val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected value
  class Test17 {
    protected val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private value
  object Test18 {
    private val bar: (Int, String) = ???
  }

  // can't expose a tuple from a private value for a scope
  object Test19 {
    private[fix] val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a value inside another type
  object Test20 {
    val bar: Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a value if it's the base type of another type
  object Test21 {
    val bar: Map[Int, String] = ???
  }

  // can't expose a tuple from a public variable
  object Test22 {
    var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public variable
  object Test23 {
    var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected variable
  class Test24 {
    protected var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private variable
  object Test25 {
    private var bar: (Int, String) = ???
  }

  // can't expose a tuple from a private variable for a scope
  object Test26 {
    private[fix] var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a variable inside another type
  object Test27 {
    var bar: Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a variable if it's the base type of another type
  object Test28 {
    var bar: Map[Int, String] = ???
  }

  // can't expose a tuple from a public lazy value
  object Test29 {
    lazy val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public lazy value
  object Test30 {
    lazy val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected lazy value
  class Test31 {
    protected lazy val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private lazy value
  object Test32 {
    private lazy val bar: (Int, String) = ???
  }

  // can't expose a tuple from a private lazy value for a scope
  object Test33 {
    private[fix] lazy val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a lazy value inside another type
  object Test34 {
    lazy val bar: Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a lazy value if it's the base type of another type
  object Test35 {
    lazy val bar: Map[Int, String] = ???
  }

  // can't expose a tuple from a public implicit method
  object Test36 {
    implicit def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public implicit method
  object Test37 {
    implicit def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected implicit method
  class Test38 {
    protected implicit def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private implicit method
  object Test39 {
    private implicit def bar(): (Int, String) = ???
  }

  // can't expose a tuple from a private implicit method for a scope
  object Test40 {
    private[fix] implicit def bar(): (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from an implicit method inside another type
  object Test41 {
    implicit def bar(): Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from an implicit method if it's the base type of another type
  object Test42 {
    implicit def bar(): Map[Int, String] = ???
  }

  // can't expose a tuple from a public implicit value
  object Test43 {
    implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public implicit value
  object Test44 {
    implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected implicit value
  class Test45 {
    protected implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private implicit value
  object Test46 {
    private implicit val bar: (Int, String) = ???
  }

  // can't expose a tuple from a private implicit value for a scope
  object Test47 {
    private[fix] implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from an implicit value inside another type
  object Test48 {
    implicit val bar: Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from an implicit value if it's the base type of another type
  object Test49 {
    implicit val bar: Map[Int, String] = ???
  }

  // can't expose a tuple from a public implicit variable
  object Test50 {
    implicit var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public implicit variable
  object Test51 {
    implicit var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected implicit variable
  class Test52 {
    protected implicit var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private implicit variable
  object Test53 {
    private implicit var bar: (Int, String) = ???
  }

  // can't expose a tuple from a private implicit variable for a scope
  object Test54 {
    private[fix] implicit var bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from an implicit variable inside another type
  object Test55 {
    implicit var bar: Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from an implicit variable if it's the base type of another type
  object Test56 {
    implicit var bar: Map[Int, String] = ???
  }

  // can't expose a tuple from a public implicit lazy value
  object Test57 {
    lazy implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public implicit lazy value
  object Test58 {
    lazy implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected implicit lazy value
  class Test59 {
    protected lazy implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private implicit lazy value
  object Test60 {
    private lazy implicit val bar: (Int, String) = ???
  }

  // can't expose a tuple from a private implicit lazy value for a scope
  object Test61 {
    private[fix] lazy implicit val bar: (Int, String) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from an implicit lazy value inside another type
  object Test62 {
    lazy implicit val bar: Seq[(Int, String)] = ??? // assert: ExposedTuples
  }

  // can expose a tuple from an implicit lazy value if it's the base type of another type
  object Test63 {
    lazy implicit val bar: Map[Int, String] = ???
  }

  // can't expose a tuple from a public method as an implicit parameter
  object Test64 {
    def bar(implicit baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a public method as an implicit parameter
  object Test65 {
    def bar(implicit baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected method as an implicit parameter
  class Test66 {
    protected def bar(implicit baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private method as an implicit parameter
  object Test67 {
    private def bar(implicit baz: (Int, String)) = ???
  }

  // can't expose a tuple from a private method as an implicit parameter for a scope
  object Test68 {
    private[fix] def bar(implicit baz: (Int, String)) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a method as an implicit parameter inside another type
  object Test69 {
    def bar(implicit baz: Seq[(Int, String)]) = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a method as an implicit parameter if it's the base type of another type
  object Test70 {
    def bar(implicit baz: Map[Int, String]) = ???
  }

  // can expose a tuple from the unapply method of a case class
  object Test80 {

    case class Foo(a: Int, b: Int)

  }

  // can expose a tuple from a custom unapply method
  object Test81 {

    class Foo(val a: Int, val b: Int)

    object Foo {
      def unapply(foo: Foo): Option[(Int, Int)] = Some((foo.a, foo.b))
    }

    // Testing to make sure unapply is properly defined
    val foo = new Foo(1, 2)
    val Foo(a, b) = foo
  }

  // can't expose a tuple from a public constructor
  object Test82 {

    class Foo(tuple: (Int, String)) // assert: ExposedTuples
  }

  // can't expose a tuple from a protected constructor
  object Test83 {

    class Foo protected(tuple: (Int, String)) // assert: ExposedTuples
  }

  // can expose a tuple from a private constructor
  object Test84 {

    class Foo private(tuple: (Int, String))

  }

  // can't expose a tuple from a private scoped constructor
  object Test85 {

    class Foo private[fix](tuple: (Int, String)) // assert: ExposedTuples
  }

  // can expose a tuple from a local def
  object Test86 {
    def foo: Unit = {
      def bar(): (String, Int) = ???
    }
  }

  // can expose a tuple as a local def parameter
  object Test88 {
    def foo: Unit = {
      def bar(baz: (String, Int)) = ???
    }
  }

  // can expose a tuple from a local def inside a class
  object Test89 {
    def foo: Unit = {
      def bar(): (String, Int) = ???
    }
  }

  // can expose a tuple as a local def parameter inside a class
  object Test90 {
    def foo: Unit = {
      def bar(baz: (String, Int)) = ???
    }
  }

  // can expose a tuple as a local value
  object Test91 {
    def foo: Unit = {
      val bar: (String, Int) = ???
    }
  }

  // can expose a tuple as a local value lambda
  object Test92 {
    def foo: Unit = {
      val bar: Unit => (String, Int) = ???
    }
  }

  // can expose a tuple as a local value lambda's parameter
  object Test93 {
    def foo: Unit = {
      val bar: ((String, Int)) => Unit = ???
    }
  }

  // can expose a tuple as a local variable
  object Test94 {
    def foo: Unit = {
      var barb: (String, Int) = ???
    }
  }

  // can expose a tuple as a local variable lambda
  object Test95 {
    def foo: Unit = {
      var barb: Unit => (String, Int) = ???
    }
  }

  // can expose a tuple as a local variable lambda's parameter
  object Test96 {
    def foo: Unit = {
      var barb: ((String, Int)) => Unit = ???
    }
  }

  // can expose a tuple as a local lazy value
  object Test97 {
    def foo: Unit = {
      lazy val barc: (String, Int) = ???
    }
  }

  // can expose a tuple as a local lazy value lambda
  object Test98 {
    def foo: Unit = {
      lazy val barc: Unit => (String, Int) = ???
    }
  }

  // can expose a tuple as a local lazy value lambda's parameter
  object Test99 {
    def foo: Unit = {
      lazy val barc: ((String, Int)) => Unit = ???
    }
  }

  // can't expose a tuple as a value lambda
  object Test100 {
    val bar: ((String, Int)) => Unit = ??? // assert: ExposedTuples
  }

  // can't expose a tuple as a variable lambda
  object Test101 {
    var bar: ((String, Int)) => Unit = ??? // assert: ExposedTuples
  }

  // can't expose a tuple as a lazy value lambda
  object Test102 {
    lazy val bar: ((String, Int)) => Unit = ??? // assert: ExposedTuples
  }

  // can't expose a tuple as a value lambda's return value
  object Test103 {
    val bar: Unit => (String, Int) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple as a variable lambda's return value
  object Test104 {
    var bar: Unit => (String, Int) = ??? // assert: ExposedTuples
  }

  // can't expose a tuple as a lazy value lambda's return value
  object Test105 {
    lazy val bar: Unit => (String, Int) = ??? // assert: ExposedTuples
  }
  
  // can't expose a tuple from a public implicit method
  object Test106 {
    implicit def bar(foo: (Int, String)): String = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from a protected implicit method
  class Test107 {
    protected implicit def bar(foo: (Int, String)): String = ??? // assert: ExposedTuples
  }

  // can expose a tuple from a private implicit method
  object Test108 {
    private implicit def bar(foo: (Int, String)): String = ???
  }

  // can't expose a tuple from a private implicit method for a scope
  object Test109 {
    private[fix] implicit def bar(foo: (Int, String)): String = ??? // assert: ExposedTuples
  }

  // can't expose a tuple from an implicit method inside another type
  object Test110 {
    implicit def bar(foo: Seq[(Int, String)]): String = ??? // assert: ExposedTuples
  }

  // can expose a tuple from an implicit method if it's the base type of another type
  object Test111 {
    implicit def bar(foo: Map[Int, String]): String = ???
  }

  object Test112 {
    implicit class Foo(foo: (Int, String)) { } // assert: ExposedTuples
  }
  
  object Test113 {
    private implicit class Foo(foo: (Int, String)) { }
  }

  object Test114 {
    private[fix] implicit class Foo(foo: (Int, String)) { } // assert: ExposedTuples
  }

  class Test115 {
    protected implicit class Foo(foo: (Int, String)) { } // assert: ExposedTuples
  }

  object Test116 {
    implicit class Foo(foo: Seq[(Int, String)]) { } // assert: ExposedTuples
  }

  object Test117 {
    implicit class Foo(foo: Map[Int, String]) { }
  }

  object Test118 {
    implicit class Foo private[fix] (foo: (Int, String)) { } // assert: ExposedTuples
  }

  class Test119 {
    protected class Foo(tuple: (Int, String)) // assert: ExposedTuples
  }

  object Test120 {
    private class Foo(tuple: (Int, String))
  }

  object Test121 {
    private[fix] class Foo(tuple: (Int, String)) // assert: ExposedTuples
  }
}
