package main.scala.aws.product.api.rest.controller

import scala.collection.immutable.SortedMap
import main.scala.aws.product.api.rest.request.RequestBuilder
import main.scala.aws.product.api.system.HttpService

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/24/13
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
object AwsProduct {
  /**
   *
   * @param operation
   * @param locale
   * @param pubKey
   * @param privatKey
   * @param params
   */
  def operationAction(operation: String, locale: String, pubKey: String, privatKey: String,
                      params: SortedMap[String, Any], onSuccess:scala.xml.Elem => Unit,
                      onFailure:Throwable => Unit) = {
    val requestUrl = RequestBuilder.create(operation, locale, pubKey, privatKey, params).toString
    HttpService.fetchUrl(
      requestUrl,
      (resp: xml.Elem) => {
        onSuccess(resp)
      },
      (e: Throwable) => {
        onFailure(e)
      }
    )
  }
}
