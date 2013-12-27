package main.scala.aws.product.api.rest.response.mapper

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/26/13
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
object ResponseMapperBuilder {
  def build(x: xml.Elem) = {
    val response = x match {
      case <BrowseNodeLookupResponse>{_*}</BrowseNodeLookupResponse> => BrowseNodeLookupResponseMapper.map(x)
      case _ => ""
    }
    response
  }
}
