package main.scala.aws.product.api.rest.response.mapper.model

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/26/13
 * Time: 5:37 PM
 * To change this template use File | Settings | File Templates.
 */
case class ErrorResponse(val code: String, val message: String) extends AbstractModel
