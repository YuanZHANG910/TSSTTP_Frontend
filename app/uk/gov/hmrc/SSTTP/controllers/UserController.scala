package uk.gov.hmrc.SSTTP.controllers

import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by yuan on 25/08/16.
  */
object UserController extends UserController

class UserController extends FrontendController with Controller {
  // a form contents user input information

  val userInputForm = Form(
    tuple(
      "Reference" -> nonEmptyText,
      "Interest Rate" -> nonEmptyText,
      "Liabilities" -> nonEmptyText,
      "Initial Payment" -> nonEmptyText,
      "Initial Payment Date" -> nonEmptyText,
      "Start Date" -> nonEmptyText,
      "End Date" -> nonEmptyText,
      "Payment Frequency" -> nonEmptyText
    )
  )
  def check = Action.async{
    implicit request =>
      val input = userInputForm.bindFromRequest()

        val Reference = input.data("Reference")
        val InterestRate = input.data("Interest Rate").toDouble
        val Liabilities = input.data("Liabilities")
        val InitialPayment = input.data("Initial Payment").toDouble
        val InitialPaymentDate = input.data("Initial Payment Date")
        val StartDate = input.data("Start Date")
        val EndDate = input.data("End Date")
        val PaymentFrequency = input.data("Payment Frequency")


      calculateInterestRate(InitialPayment,InterestRate,100)

      Future.successful(Redirect(routes.HelloWorld.helloWorld()))
  }

  def calculateInterestRate(amountIP:Double, interestRate:Double, numberOfDays:Int): Double ={
    val total:Double = amountIP*interestRate*numberOfDays/36600

    total
  }

}
