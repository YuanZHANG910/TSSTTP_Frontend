package uk.gov.hmrc.SSTTP.controllers

import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
import scala.concurrent.Future

/**
  * Created by paul on 26/08/16.
  */
class CallBack {

  val holder : WSRequestHolder = WS.url("localhost:9001")

  val complexHolder : WSRequestHolder =
    holder.withHeaders("Accept" -> "application/json")
    .withRequestTimeout(10000)
    .withQueryString("search" -> "play")

  val futureResponse : Future[WSResponse] = complexHolder.get()

}
