package main.scala.aws.product.api.rest.request

import main.scala.aws.product.api.request.AbstractRequest
import scala.collection.immutable.SortedMap

class SimilarityLookupRequest(override val region: String, override val public_key: String,
                        override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.contains("ItemId"), "ItemId param is required for SimilarityLookupRequest")
  require(! params.get("ItemId").exists(x => x.toString == ""), "ItemId should not be empty")
}

object SimilarityLookupRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "SimilarityLookup"
    )
    new SimilarityLookupRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}
