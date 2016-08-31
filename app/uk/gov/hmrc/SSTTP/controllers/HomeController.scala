//package uk.gov.hmrc.SSTTP.controllers
//
//import uk.gov.hmrc.play.frontend.controller.FrontendController
//import play.api.mvc._
//
//import scala.concurrent.Future
//
//
//object HomeController extends HelloWorld
//
//trait HomeController extends FrontendController {
//  val home = Action.async { implicit request =>
//    val aForm = InputController.show
//    Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world(aForm)))
//  }
//}
