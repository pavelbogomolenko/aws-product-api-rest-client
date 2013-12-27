package main.scala.aws.product.api.rest.request

import main.scala.aws.product.api.request.AbstractRequest
import scala.collection.immutable.SortedMap

class CartCreateRequest(override val region: String, override val public_key: String,
                        override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.get("Item.1.OfferListingId").exists(x => x.toString != "") ||
    params.get("Item.1.ASIN").exists(x => x.toString != ""), "At least one OfferListingId or ASIN should exist in CartCreateRequest")
  require(params.get("Item.1.Quantity").exists(x => x.toString != ""), "Quantity param should be defined")
}

object CartCreateRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "CartCreate"
    )
    new CartCreateRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}
