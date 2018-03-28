# Omer's Scalafix Rules
[![Build Status](https://travis-ci.org/omervk/scalafix-rules.svg?branch=master)](https://travis-ci.org/omervk/scalafix-rules)

This project is a work in progress of rules being migrated from [WartRemover](http://wartremover.org/) to [scalafix](https://scalacenter.github.io/scalafix/).
For more rules from WartRemover, see [ScalafixWartremover](https://github.com/vovapolu/ScalafixWartremover).

## Running

1. [Install the scalafix sbt plugin](https://scalacenter.github.io/scalafix/docs/users/installation)
2. Run `sbt "scalafixCli --rules github:omervk/scalafix-rules/v1"` 

## Rules

### ExposedTuples

Tuples are described not by their semantic meaning, but by their types alone, which requires users of your API to either create that meaning themselves using unapply or to use the ugly _1, _2, ... accessors.

Public API should refrain from exposing tuples and should instead consider using custom case classes to add semantic meaning.

```scala
// Won't compile:
// | Avoid using tuples in public interfaces, as they only supply type information.
// | Consider using a custom case class to add semantic meaning.
def badFoo(customerTotal: (String, Long)) = {
  // Code
}
```
```scala
// Custom case class with added semantic meaning
final case class CustomerAccount(customerId: String, accountTotal: Long)

// Will compile
def goodFoo(customerTotal: CustomerAccount) = {
  // Code
}
```

### PublicInference

Type inference of public members can expose extra type information, that can break encapsulation.

```scala
class c {
  // Won't compile: Public member must have an explicit type ascription
  def f() = new c with t

  val name: String = "abc" // Compiles
}
```