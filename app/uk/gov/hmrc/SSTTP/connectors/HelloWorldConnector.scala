package uk.gov.hmrc.SSTTP.connectors

import uk.gov.hmrc.SSTTP.WSHttp
import uk.gov.hmrc.play.config.ServicesConfig
import uk.gov.hmrc.play.http._

/**
  * Created by paul on 26/08/16.
  */

trait HelloWorldConnector extends ServicesConfig{
  val serviceUrl:String
  lazy val http = WSHttp

}

object HelloWorldConnector extends HelloWorldConnector{
  override val serviceUrl = baseUrl("helloWorld")
}
