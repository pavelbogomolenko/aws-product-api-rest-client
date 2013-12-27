package main.scala.aws.product.api.rest.request

import main.scala.aws.product.api.request.AbstractRequest
import scala.collection.immutable.SortedMap

class CartClearRequest(override val region: String, override val public_key: String,
                        override val private_key: String, override val params: SortedMap[String, Any])
  extends AbstractRequest(region, public_key, private_key, params) {
  this: SignComponent with UrlComponent =>

  require(params.get("HMAC").exists(x => x.toString != ""), "HMAC should not be empty")
  require(params.get("CartId").exists(x => x.toString != ""), "CartId should not be empty")
}

object CartClearRequest {
  def apply(region: String, public_key: String, private_key: String, params: SortedMap[String, Any]) = {
    val default_params = SortedMap(
      "Operation" -> "CartClear"
    )
    new CartClearRequest(region, public_key, private_key, params ++ default_params) with UrlComponentImpl with SignComponentImpl {
      val signService = new AwsSign
      def urlService(d: String, p: SortedMap[String, Any]) = Url(d, p)
    }
  }
}
