package utils

import uk.gov.hmrc.play.config.{AppName, RunMode}
import uk.gov.hmrc.play.http.ws.{WSDelete, WSGet, WSPost}


/**
  * Created by paul on 26/08/16.
  */
class WSHttp {

}

object WSHttp extends WSGet with WSPost with WSDelete with AppName with RunMode{
  override val hooks = NoneRequired
}