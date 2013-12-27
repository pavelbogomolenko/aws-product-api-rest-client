package main.scala.aws.product.api.system

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/24/13
 * Time: 9:04 AM
 * To change this template use File | Settings | File Templates.
 */
object StringHelper {
  def %%(str: String): String = str replace("+", "%20") replace("*", "%2A") replace("%7E", "~")
}
