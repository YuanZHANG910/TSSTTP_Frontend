//package uk.gov.hmrc.SSTTP.controllers
//
//import uk.gov.hmrc.play.frontend.controller.FrontendController
//import play.api.mvc._
//import uk.gov.hmrc.SSTTP.connectora.helloWorldConnector
//
//
//import scala.concurrent.Future
//
//
//object ResultsController extends ResultsController
//
//
//trait ResultsController extends FrontendController with Controller{
//
//  val helloWorld = helloWorldConnector
//
//  val ResultsController = Action.async { implicit request =>
//    val results = UserController.results
//    Future.successful(Ok(helloWorld.helloRegUrl+"/SSTTP/hello-world"))
//  }
//}
//

//class ResultsController extends FrontendController with Controller{
//  val ResultsController = Action.async { implicit request =>
//    val results = UserController.results
//    Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world_results(results)))
//  }
//}