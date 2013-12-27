package main.scala.aws.product.api.rest.request

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import scala.Predef.String
import org.apache.commons.codec.binary.Base64
import java.net.URLEncoder
import main.scala.aws.product.api.system.StringHelper

/**
 * Created with IntelliJ IDEA.
 * User: pavelbogomolenko
 * Date: 12/19/13
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
trait SignComponent {
  val signService:SignService
  trait SignService {
    def sign(private_key: String, str: String): String
  }
}

trait SignComponentImpl extends SignComponent {
  class AwsSign extends SignService {
    val HMAC_SHA256_ALGORITHM = "HmacSHA256"
    val UTF8_CHARSET = "UTF-8"
    def sign(private_key: String, stringToSign: String) = {
      val secretyKeyBytes = private_key.getBytes(UTF8_CHARSET)
      val secretKeySpec = new SecretKeySpec(secretyKeyBytes, HMAC_SHA256_ALGORITHM)
      val mac = Mac.getInstance(HMAC_SHA256_ALGORITHM)
      mac.init(secretKeySpec)

      val data = stringToSign.getBytes(UTF8_CHARSET)
      val rawHmac = mac.doFinal(data)
      val encoder = new Base64()
      val signature = URLEncoder.encode(new String(encoder.encode(rawHmac)), UTF8_CHARSET)

      StringHelper %%(signature)
    }
  }
}
