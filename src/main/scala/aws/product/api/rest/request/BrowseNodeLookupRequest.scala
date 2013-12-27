package main.scala.aws.product.api.rest.request

import main.scala.aws.product.api.request.AbstractRequest
import scala.collection.immutable.SortedMap


/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/15/13
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
class BrowseNodeLookupRequest(override val region: String, override val public_key: String,
  override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.contains("BrowseNodeId"), "BrowseNodeId param is required for BrowseNodeLookupRequest")
  require(params.get("BrowseNodeId").filter(x => x.toString.toInt <= 0).isEmpty, "BrowseNodeId must be more than 0")
}

object BrowseNodeLookupRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "BrowseNodeLookup"
    )
    new BrowseNodeLookupRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}

