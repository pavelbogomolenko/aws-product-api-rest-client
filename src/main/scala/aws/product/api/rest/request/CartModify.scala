package main.scala.aws.product.api.rest.request

import main.scala.aws.product.api.request.AbstractRequest
import scala.collection.immutable.SortedMap

class CartModifyRequest(override val region: String, override val public_key: String,
                     override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.get("HMAC").exists(x => x.toString != ""), "HMAC should not be empty")
  require(params.get("CartId").exists(x => x.toString != ""), "CartId should not be empty")
  require(params.get("CartItemId").exists(x => x.toString != ""), "CartItemId should not be empty")
  val patternCartItemId = "(Item\\.[0-9]\\.CartItemId)".r
  require(params.exists(x => x._1 match {
    case patternCartItemId(y) => true
    case _ => false
  }), "Specify the Item to modify. Typical construction is Item.[0-9].CartItemId=[ID]")

  val patternQuantity = "(Item\\.[0-9]\\.Quantity)".r
  require(params.exists(x => x._1 match {
    case patternQuantity(y) => true
    case _ => false
  }), "Quantity param should be defined. Typical construction is Item.[0-9].Quantity=[ID]")
}

object CartModifyRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "CartModify"
    )
    new CartModifyRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}
