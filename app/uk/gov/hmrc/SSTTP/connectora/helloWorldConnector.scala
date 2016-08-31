//package uk.gov.hmrc.SSTTP.connectora
//
//
//import play.api.libs.json.{JsValue, Json}
//import uk.gov.hmrc.SSTTP.WSHttp
//import uk.gov.hmrc.SSTTP.models.{UserInformationSubmit, UserInput}
//import uk.gov.hmrc.play.config.ServicesConfig
//import uk.gov.hmrc.play.http._
//
//import scala.concurrent.Future
//
///**
//  * Created by yuan on 30/08/16.
//  */
//object helloWorldConnector extends helloWorldConnector with ServicesConfig{
//  override lazy val helloRegUrl = baseUrl("hello-world")
//  val http = WSHttp
//}
//
//trait helloWorldConnector{
//  val helloRegUrl: String
//  val http: HttpGet with HttpPost
//
//  def submitDetails(userInformation : UserInformationSubmit)(implicit hc: HeaderCarrier) : Future[UserInformationSubmit] = {
//    val userJson = Json.toJson[UserInformationSubmit](userInformation)
//    http.POST[JsValue, UserInformationSubmit](s"$helloRegUrl", userJson)
//  }
//
//  def getUserDetailsForTests(queryParameter : String)
//                            (implicit hc : HeaderCarrier, rds : HttpReads[UserInformationSubmit])
//  : Future[Option[UserInformationSubmit]] = {
//    http.GET[Option[UserInformationSubmit]](s"$helloRegUrl$queryParameter")
//  }
//
////  def clearUsersForTests()(implicit hc : HeaderCarrier) : Future[Option[Response]] = {
////    http.GET[Option[Response]](s"$businessRegUrl/business-registration/test-only/clear-beta-users")
////  }
//}