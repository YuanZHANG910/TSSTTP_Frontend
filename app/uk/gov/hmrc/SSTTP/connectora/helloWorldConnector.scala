package uk.gov.hmrc.SSTTP.connectora

import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.SSTTP.controllers.userInput
import uk.gov.hmrc.play.config.{AppName, RunMode, ServicesConfig}
import uk.gov.hmrc.play.http._
import uk.gov.hmrc.play.http.ws.{WSDelete, WSGet, WSPost, WSPut}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._

/**
  * Created by yuan on 30/08/16.
  */
object helloWorldConnector extends helloWorldConnector with ServicesConfig{
  val Url = baseUrl("hello-world")
  val http = WSHttp
}

trait helloWorldConnector{
  val Url: String
  val http: HttpGet with HttpPost

  def submitDetails(userInformation : userInput)(implicit hc: HeaderCarrier) : Future[String] = {
    println("test1+++++++++++++++++++++")
    val userJson = Json.toJson[userInput](userInformation)
    println("test999999999999999999999999999999999999999")
    val res = http.POST[JsValue, String](s"$Url/SSTTP/hello-world", userJson)

    val actualValue = Await.result(res, 5.seconds)
    val x = 1

    println("\n\n\n\n\ntet3333333333333333333333"+ actualValue )
    res
  }


}

object WSHttp extends WSGet with WSPut with WSPost with WSDelete with AppName with RunMode {
  override val hooks = NoneRequired
}

case class Response(resp : String)

object Response {
  implicit val format = Json.format[Response]
}