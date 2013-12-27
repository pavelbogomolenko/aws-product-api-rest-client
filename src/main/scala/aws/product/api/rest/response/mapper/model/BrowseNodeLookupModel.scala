package main.scala.aws.product.api.rest.response.mapper.model

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/24/13
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
case class BrowseNodeLookupModel(val browseNodeId: String, val name: String,
                                 val children: Seq[BrowseNodeLookupModel] = Seq(),
                                 val ancestors: Seq[BrowseNodeLookupModel] = Seq(),
                                 val isCategoryRoot: Int = 0) extends AbstractModel {

  override def toString = "\n" + "browseNodeId: " + browseNodeId +
                          "\n" + "name: " + name +
                          "\n" + "children: "  + children +
                          "\n" + "ancestors: "  + ancestors +
                          "\n" + "isCategoryRoot: " + isCategoryRoot
}
