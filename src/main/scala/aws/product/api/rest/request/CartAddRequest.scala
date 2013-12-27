package main.scala.aws.product.api.rest.request

import main.scala.aws.product.api.request.AbstractRequest
import scala.collection.immutable.SortedMap

class CartAddRequest(override val region: String, override val public_key: String,
                        override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.get("HMAC").exists(x => x.toString != ""), "HMAC should not be empty")
  require(params.get("CartId").exists(x => x.toString != ""), "CartId should not be empty")
  val patternListingId = "(Item\\.[0-9]\\.OfferListingId|Item\\.[0-9]\\.ASIN)".r
  require(params.exists(x => x._1 match {
    case patternListingId(y) => true
    case _ => false
  }), "Specify the Item to add. Typical construction is Item.[0-9].OfferListingId=[ID] or Item.1.ASIN=[ID]")

  val patternQuantity = "(Item\\.[0-9]\\.Quantity)".r
  require(params.exists(x => x._1 match {
    case patternQuantity(y) => true
    case _ => false
  }), "Quantity param should be defined. Typical construction is Item.[0-9].Quantity=[ID]")
}

object CartAddRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "CartAdd"
    )
    new CartAddRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}
