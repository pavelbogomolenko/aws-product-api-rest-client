package main.scala.aws.product.api

import scala.collection.immutable.SortedMap
import main.scala.aws.product.api.rest.controller.AwsProduct

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/14/13
 * Time: 8:08 PM
 * To change this template use File | Settings | File Templates.
 */
object Main {
  def main (args: Array[String]) {
    //301668 - styles
    AwsProduct.operationAction(
      "BrowseNodeLookup",
      "com",
      "[AWS_PUBLIC_KEY]",
      "[AWS_PRIVATE_KEY]",
      SortedMap(
        "BrowseNodeId" -> 301668
      ),
      s => println(s),
      f => f.printStackTrace()
    )
  }
}
