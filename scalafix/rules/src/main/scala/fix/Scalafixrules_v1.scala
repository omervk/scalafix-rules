package fix

import scalafix._
import scalafix.lint.LintMessage

import scala.meta._

final case class ExposedTuples(index: SemanticdbIndex)
    extends SemanticRule(index, "ExposedTuples") {

  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  implicit final class AnyOps[A](self: A) {
    @inline def ===(other: A): Boolean = self == other
    @inline def !==(other: A): Boolean = self != other
  }

  override def description: String = "Linter that reports an error when using tuples in a public API"

  private val message = "A public API's return type should not use tuples; Use a custom case class to add semantic meaning"
  private val category = LintCategory.warning(message)

  def messagesForType(tpe: Type): Seq[LintMessage] = {
    import Type._

    tpe match {
      case _: Tuple =>
        Seq(LintMessage(message, tpe.pos, category))
      case f: Function =>
        f.params.flatMap(messagesForType) ++ messagesForType(f.res)
      case f: ImplicitFunction =>
        f.params.flatMap(messagesForType) ++ messagesForType(f.res)
      case l: Lambda =>
        messagesForType(l.tpe)
      case a: Apply =>
        a.args.flatMap(messagesForType)
      case _ =>
        Nil
    }
  }

  private def traverse(tree: Tree): Seq[LintMessage] = {
    tree match {
      case definition: Defn.Def if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Val if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Var if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Class if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Trait if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Object if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Class if isPrivate(definition.ctor.mods) =>
        Nil

      case definition: Defn.Trait if isPrivate(definition.ctor.mods) =>
        Nil

      case definition: Defn.Def if definition.name.toString === "unapply" =>
        // Unapply is allowed to have tuples
        Nil

      case definition: Defn.Def =>
        val returnTypeMessages: Seq[LintMessage] =
          definition.decltpe.toSeq.flatMap(messagesForType)

        returnTypeMessages ++ messagesForParameters(definition.paramss)

      case definition: Defn.Val =>
        definition.decltpe.toSeq.flatMap(messagesForType)

      case definition: Defn.Var =>
        definition.decltpe.toSeq.flatMap(messagesForType)

      case definition: Defn.Class =>
        messagesForParameters(definition.ctor.paramss) ++ traverseChildren(definition)

      case definition: Defn.Trait =>
        messagesForParameters(definition.ctor.paramss) ++ traverseChildren(definition)

      case _ =>
        traverseChildren(tree)
    }
  }

  private def traverseChildren(tree: Tree) = {
    tree.children
      .map(traverse)
      .fold(Seq.empty[LintMessage])(_ ++ _)
  }

  private def messagesForParameters(paramss: List[List[Term.Param]]) = {
    for {
      paramList <- paramss
      param <- paramList
      tpe <- param.decltpe.toSeq
      message <- messagesForType(tpe)
    } yield {
      message
    }
  }

  private def isPrivate(modifiers: List[Mod]) = {
    // Do not cover private definitions (but we do cover private in package scope)
    modifiers.exists {
      case p: Mod.Private if p.within.toString.isEmpty => true
      case _ => false
    }
  }

  override def check(ctx: RuleCtx): Seq[LintMessage] = {
    traverse(ctx.tree)
  }
}

final case class PublicInference(index: SemanticdbIndex)
    extends SemanticRule(index, "PublicInference") {

  @SuppressWarnings(Array("org.wartremover.warts.Equals"))
  implicit final class AnyOps[A](self: A) {
    @inline def ===(other: A): Boolean = self == other
    @inline def !==(other: A): Boolean = self != other
  }

  override def description: String = "Linter that reports when a public API does not have a type ascription"

  private val message = "Public member must have an explicit type ascription"
  private val category = LintCategory.warning(message)

  def messagesForType(tpe: Type): Seq[LintMessage] = {
    import Type._

    tpe match {
      case _: Tuple =>
        Seq(LintMessage(message, tpe.pos, category))
      case f: Function =>
        f.params.flatMap(messagesForType) ++ messagesForType(f.res)
      case f: ImplicitFunction =>
        f.params.flatMap(messagesForType) ++ messagesForType(f.res)
      case l: Lambda =>
        messagesForType(l.tpe)
      case a: Apply =>
        a.args.flatMap(messagesForType)
      case _ =>
        Nil
    }
  }

  private def traverse(tree: Tree): Seq[LintMessage] = {
    tree match {
      case definition: Defn.Def if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Val if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Var if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Class if isPrivate(definition.mods) =>
        // Do not iterate further into private types
        Nil

      case definition: Defn.Trait if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Object if isPrivate(definition.mods) =>
        Nil

      case definition: Defn.Def if definition.decltpe.isEmpty =>
        Seq(LintMessage(message, definition.pos, category))

      case definition: Defn.Val if definition.decltpe.isEmpty =>
        Seq(LintMessage(message, definition.pos, category))

      case definition: Defn.Var if definition.decltpe.isEmpty =>
        Seq(LintMessage(message, definition.pos, category))

      case _ =>
        tree.children
          .map(traverse)
          .fold(Seq.empty[LintMessage])(_ ++ _)
    }
  }

  private def isPrivate(modifiers: List[Mod]) = {
    // Do not cover private definitions (but we do cover private in package scope)
    modifiers.exists {
      case p: Mod.Private if p.within.toString.isEmpty => true
      case _ => false
    }
  }

  override def check(ctx: RuleCtx): Seq[LintMessage] = {
    traverse(ctx.tree)
  }
}
