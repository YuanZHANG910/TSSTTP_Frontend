package uk.gov.hmrc.SSTTP.controllers

import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

import scala.concurrent.Future


object HelloWorld extends HelloWorld

trait HelloWorld extends FrontendController {
  val helloWorld = Action.async { implicit request =>
    val aForm = UserController.userInputForm
		Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world(aForm)))
  }




}
