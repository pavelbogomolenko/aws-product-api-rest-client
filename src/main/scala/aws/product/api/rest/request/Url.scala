package main.scala.aws.product.api.rest.request

import scala.collection.immutable.SortedMap
import java.net.URLEncoder
import main.scala.aws.product.api.system.StringHelper

trait UrlComponent {
  def urlService(d: String, p: SortedMap[String, Any]): UrlService
  trait UrlService {
    def encodeParams:String
    val scheme:String
    val path:String
    val host:String
  }
}

trait UrlComponentImpl extends UrlComponent{
  class Url(val domain: String = "com", val params: SortedMap[String, Any]) extends UrlService {
    def this() = this("com", SortedMap())
    def this(params: SortedMap[String, Any]) = this("com", params)
    private val _scheme  = "http://"
    private val _path    = "/onca/xml"
    private val _host    = "webservices.amazon." + domain
    val scheme = _scheme
    val path = _path
    val host  = _host
    def encodeParams = params map %% map { case (k, v) => URLEncoder.encode(k, "UTF-8") + "=" + URLEncoder.encode(v, "UTF-8") } mkString "&"
    def ?(s: String) = s match {
      case "" => s
      case _ => "?" + s
    }
    override def toString = scheme + host + path + ?(encodeParams)
    //normalize URL string
    private def %%(t: (String, Any)): (String, String) = (StringHelper %%(t._1), StringHelper %%(t._2.toString))
  }

  object Url {
    def apply(d: String, p: SortedMap[String, Any]) = new Url(d, p)
  }
}


