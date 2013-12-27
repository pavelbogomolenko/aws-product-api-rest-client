package main.scala.aws.product.api.rest.request

import main.scala.aws.product.api.request.AbstractRequest
import scala.collection.immutable.SortedMap

class CartGetRequest(override val region: String, override val public_key: String,
                       override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.get("HMAC").exists(x => x.toString != ""), "HMAC should not be empty")
  require(params.get("CartId").exists(x => x.toString != ""), "CartId should not be empty")
  require(params.get("CartItemId").exists(x => x.toString != ""), "CartItemId should not be empty")
}

object CartGetRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "CartGet"
    )
    new CartGetRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}
