package uk.gov.hmrc.SSTTP.connectora


import play.api.libs.json.{JsValue, Json}
import uk.gov.hmrc.play.config.ServicesConfig
import uk.gov.hmrc.play.http._

/**
  * Created by yuan on 30/08/16.
  */
object helloWorldConnector extends helloWorldConnector with ServicesConfig{
  lazy val hello = baseUrl("hello-world")
}

trait helloWorldConnector{
  val businessRegUrl: String
  val http: HttpGet with HttpPost

  def submitDetails(userInformation : BetaUserInformationSubmit)(implicit hc: HeaderCarrier) : Future[BetaUserInformationSubmit] = {
    val userJson = Json.toJson[BetaUserInformationSubmit](userInformation)
    http.POST[JsValue, BetaUserInformationSubmit](s"$businessRegUrl/business-registration/beta-sign-up", userJson)
  }

  def getUserDetailsForTests(queryParameter : String)
                            (implicit hc : HeaderCarrier, rds : HttpReads[BetaUserInformationSubmit])
  : Future[Option[BetaUserInformationSubmit]] = {
    http.GET[Option[BetaUserInformationSubmit]](s"$businessRegUrl/business-registration/test-only/get-beta-user/$queryParameter")
  }

  def clearUsersForTests()(implicit hc : HeaderCarrier) : Future[Option[Response]] = {
    http.GET[Option[Response]](s"$businessRegUrl/business-registration/test-only/clear-beta-users")
  }
}