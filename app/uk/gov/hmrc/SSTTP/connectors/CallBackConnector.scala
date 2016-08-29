package uk.gov.hmrc.SSTTP.connectors

import uk.gov.hmrc.play.config.ServicesConfig
import uk.gov.hmrc.play.http.{HttpReads, HttpResponse}


/**
  * Created by paul on 29/08/16.
  */

trait CallBackConnector extends ServicesConfig with RawResponseReads{
  lazy val serviceURL = baseUrl("hello-world")
}

trait RawResponseReads{
  implicit val httpReads: HttpReads[HttpResponse] = new HttpReads[HttpResponse] {
    override def read(method: String, url: String, response: HttpResponse) = response
  }
}