package main.scala.aws.product.api.rest.request

import scala.collection.immutable.SortedMap
import main.scala.aws.product.api.request.AbstractRequest

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/15/13
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 */


class ItemSearchRequest(override val region: String, override val public_key: String,
                        override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.contains("SearchIndex"), "SearchIndex param is required for BrowseNodeLookupRequest")
  require(! params.get("SearchIndex").exists(x => x.toString == ""), "SearchIndex should not be empty. \n" +
    "Please check available search indexes for your locale.")
}

object ItemSearchRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "ItemSearch"
    )
    new ItemSearchRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}
