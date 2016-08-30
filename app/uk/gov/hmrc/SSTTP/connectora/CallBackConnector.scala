package uk.gov.hmrc.SSTTP.connectora

import uk.gov.hmrc.play.config.ServicesConfig

/**
  * Created by yuan on 30/08/16.
  */
class CallBackConnector {

}

object CallBackConnector extends ServicesConfig{
  lazy val cul = baseUrl("cul")
}