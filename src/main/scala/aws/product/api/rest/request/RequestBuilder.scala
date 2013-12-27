package main.scala.aws.product.api.rest.request

import scala.collection.immutable.SortedMap

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/25/13
 * Time: 2:02 PM
 * To change this template use File | Settings | File Templates.
 */
object RequestBuilder {
  def create(operation: String, locale: String, pubKey: String, privatKey: String, params: SortedMap[String, Any]) = {
    val request = operation match {
      case "BrowseNodeLookup" => BrowseNodeLookupRequest(locale, pubKey, privatKey, params)
      case "ItemSearch" => ItemSearchRequest(locale, pubKey, privatKey, params)
      case "ItemLookup" => ItemLookupRequest(locale, pubKey, privatKey, params)
      case "SimilarityLookup" => SimilarityLookupRequest(locale, pubKey, privatKey, params)
      case "CartCreate" => CartCreateRequest(locale, pubKey, privatKey, params)
      case "CartClear" => CartClearRequest(locale, pubKey, privatKey, params)
      case "CartGet" => CartGetRequest(locale, pubKey, privatKey, params)
      case "CartModify" => CartModifyRequest(locale, pubKey, privatKey, params)
      case "CartAdd" => CartAddRequest(locale, pubKey, privatKey, params)
      case _ => throw RequestBuilderException("Not existing operation name given. " + operation + " is not exist or implemented.")
    }
    request
  }
}

case class RequestBuilderException(val s: String) extends Exception(s)


