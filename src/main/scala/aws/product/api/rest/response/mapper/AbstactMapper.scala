package main.scala.aws.product.api.rest.response.mapper

import main.scala.aws.product.api.rest.response.mapper.model.AbstractModel

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/24/13
 * Time: 1:43 PM
 * To change this template use File | Settings | File Templates.
 */
trait AbstactMapper {
  def map(x: xml.Elem): Seq[AbstractModel]
}
