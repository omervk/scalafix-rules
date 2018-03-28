package fix

import scalafix._
import scala.meta._

final case class ExposedTuples_v1(index: SemanticdbIndex)
    extends SemanticRule(index, "ExposedTuples_v1") {

  override def fix(ctx: RuleCtx): Patch = {
    ctx.debugIndex()
    println(s"Tree.syntax: " + ctx.tree.syntax)
    println(s"Tree.structure: " + ctx.tree.structure)
    Patch.empty
  }

}
