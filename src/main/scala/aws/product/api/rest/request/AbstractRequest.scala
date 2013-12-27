package main.scala.aws.product.api.request

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import scala.collection.immutable.SortedMap
import main.scala.aws.product.api.rest.request.{SignComponent, UrlComponent}

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/15/13
 * Time: 9:30 AM
 * To change this template use File | Settings | File Templates.
 */
abstract class AbstractRequest(val region: String, val public_key: String,
  val private_key: String, val params: SortedMap[String, Any] = SortedMap()) {
  this: SignComponent with UrlComponent =>

  val validRegions = List("com", "de", "co.uk", "co.jp", "es", "ca", "cn", "it", "fr", "in")
  require(validRegions.contains(region), "Locale mismatch. Please try one of those: " + validRegions.toString())
  val method  = "GET"
  val default_aws_params = SortedMap(
    "Service" -> "AWSECommerceService",
    "AWSAccessKeyId" -> public_key,
    "Timestamp" -> new DateTime().toString(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z")),
    "Version" -> "2011-08-01",
    "AssociateTag" -> "assoc_tag"
  )
  val url = urlService(region, default_aws_params ++ params)
  val canonizedUrl = method + "\n" + url.host + "\n" + url.path + "\n" + url.encodeParams
  override def toString = url + "&Signature=" + signService.sign(private_key, canonizedUrl)
}

