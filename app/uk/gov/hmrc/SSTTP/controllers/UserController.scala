package uk.gov.hmrc.SSTTP.controllers

import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

/**
  * Created by yuan on 25/08/16.
  */


object UserController extends FrontendController {
  // a form contents user input information

  val userInputForm = Form(
    tuple(
      "Reference" -> nonEmptyText,
      "Interest Rate" -> nonEmptyText,
      "Liabilitie" -> nonEmptyText,
      "Initial Payment" -> nonEmptyText,
      "Initial Payment Date" -> nonEmptyText,
      "Start Date" -> nonEmptyText,
      "End Date" -> nonEmptyText,
      "Payment Frequency" -> nonEmptyText
    )
  )
  def check() = Action {
    implicit request =>
     Ok((uk.gov.hmrc.SSTTP.helloworld.html.hello_world()))
  }
}
