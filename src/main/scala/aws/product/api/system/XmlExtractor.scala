package main.scala.aws.product.api.system

import scala.xml.{Elem, NodeSeq}

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/24/13
 * Time: 3:06 PM
 * To change this template use File | Settings | File Templates.
 */
object XmlExtractor {
  def getNodeByName(ns: NodeSeq, name: String, default:String = "") = {
    getNodesByName(ns, name).headOption match {
      case headOption if headOption.nonEmpty => headOption.get.text
      case _ => default
    }
  }
  def getNodesByName(ns: NodeSeq, name: String): NodeSeq = {
    (ns \\ name)
  }
  def getNodesByName(x: Elem, name: String): NodeSeq = {
    (x \\ name)
  }
}
