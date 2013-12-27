package main.scala.aws.product.api.rest.response.mapper

import scala.xml
import main.scala.aws.product.api.rest.response.mapper.model.{ErrorResponse, AbstractModel, BrowseNodeLookupModel}
import main.scala.aws.product.api.system.XmlExtractor

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/24/13
 * Time: 1:26 PM
 * To change this template use File | Settings | File Templates.
 */
object BrowseNodeLookupResponseMapper extends AbstactMapper {
  override def map(x: xml.Elem): Seq[AbstractModel] = {
    def _map(x: xml.Elem) = {
      val nodes = XmlExtractor.getNodesByName(XmlExtractor.getNodesByName(x, "BrowseNodeLookupResponse"), "BrowseNode").map { nodes =>
        val browseNodeId = XmlExtractor.getNodeByName(nodes, "BrowseNodeId")
        val name = XmlExtractor.getNodeByName(nodes, "Name")
        val isRootCategory = XmlExtractor.getNodeByName(nodes, "IsCategoryRoot", "0").toInt
        val children = XmlExtractor.getNodesByName(XmlExtractor.getNodesByName(nodes, "Children"), "BrowseNode").map { childNodes =>
          val childBrowseNodeId = XmlExtractor.getNodeByName(childNodes, "BrowseNodeId")
          val childName = XmlExtractor.getNodeByName(childNodes, "Name")
          BrowseNodeLookupModel(childBrowseNodeId, childName)
        }
        val ancestors = XmlExtractor.getNodesByName(XmlExtractor.getNodesByName(nodes, "Ancestors"), "BrowseNode").map { ancestorNodes =>
          val ancestorBrowseNodeId = XmlExtractor.getNodeByName(ancestorNodes, "BrowseNodeId")
          val ancestorName = XmlExtractor.getNodeByName(ancestorNodes, "Name")
          BrowseNodeLookupModel(ancestorBrowseNodeId, ancestorName)
        }
        BrowseNodeLookupModel(browseNodeId, name, children, ancestors, isRootCategory)
      }
      nodes
    }
    val errors = XmlExtractor.getNodesByName(XmlExtractor.getNodesByName(x, "BrowseNodeLookupResponse"), "Errors").map { resp =>
      ErrorResponse(XmlExtractor.getNodeByName(resp, "Code"), XmlExtractor.getNodeByName(resp, "Message"))
    }
    if(errors.nonEmpty) {
      errors
    } else {
      _map(x)
    }
  }
}
