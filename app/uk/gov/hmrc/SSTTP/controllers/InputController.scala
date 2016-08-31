package uk.gov.hmrc.SSTTP.controllers

import play.api.mvc.Action
import uk.gov.hmrc.SSTTP.forms.UserInputForm
import uk.gov.hmrc.SSTTP.helloworld.html.{hello_world, hello_world_results}
import uk.gov.hmrc.SSTTP.models.{UserInformationSubmit, UserInput}
import uk.gov.hmrc.play.frontend.controller.FrontendController

import scala.concurrent.Future

/**
  * Created by paul on 31/08/16.
  */
trait InputController extends FrontendController{

  val show = Action.async{
    implicit request =>
      val inputForm = UserInputForm.userInputForm.fill(new UserInput("","","","","","","",""))
      Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.hello_world(inputForm)))
  }

}

object InputController
