package main.scala.aws.product.api.system

import dispatch._, Defaults._

import com.ning.http.client.AsyncHttpClient
import com.ning.http.client.AsyncHttpClientConfig

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/19/13
 * Time: 4:28 PM
 * To change this template use File | Settings | File Templates.
 */
object HttpService {
  val httpClient = new Http {
    val builder = new AsyncHttpClientConfig.Builder()
    builder.setFollowRedirects(true)
    builder.setRequestTimeoutInMs(5 * 1000)
    builder.setUserAgent("aws-product-rest-client")
    override val client = new AsyncHttpClient(builder.build())
  }
  def fetchUrl(uri:String, s:scala.xml.Elem => Unit, f:Throwable => Unit = (println)) = {
    httpClient(url(uri) OK as.xml.Elem).map(xml => s(xml)).onFailure {case e => f(e)}
  }
}
