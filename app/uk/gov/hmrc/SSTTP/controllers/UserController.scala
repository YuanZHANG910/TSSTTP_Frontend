package uk.gov.hmrc.SSTTP.controllers

import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import uk.gov.hmrc.SSTTP.models.Tax

import scala.concurrent.Future

/**
  * Created by yuan on 25/08/16.
  */
object UserController extends UserController

class UserController extends FrontendController with Controller {
  // a form contents user input information

  val result = Action.async {
    implicit request =>
      Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.result()))
  }


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

  val userInputForm2 = Form(
    mapping(
      "Reference" -> nonEmptyText,
      "Interest Rate" -> nonEmptyText,
      "Liabilities" -> nonEmptyText,
      "Initial Payment" -> nonEmptyText,
      "Initial Payment Date" -> nonEmptyText,
      "Start Date" -> nonEmptyText,
      "End Date" -> nonEmptyText,
      "Payment Frequency" -> nonEmptyText
    )(Tax.apply)(Tax.unapply)
  )


  def check = Action{
    implicit request =>
      val input = userInputForm2.bindFromRequest()

        val Reference = input.data("Reference")
        val InterestRate = input.data("Interest Rate")
        val Liabilities = input.data("Liabilities")
        val InitialPayment = input.data("Initial Payment")
        val InitialPaymentDate = input.data("Initial Payment Date")
        val StartDate = input.data("Start Date")
        val EndDate = input.data("End Date")
        val PaymentFrequency = input.data("Payment Frequency")

      Ok(uk.gov.hmrc.SSTTP.helloworld.html.result())

  }



  def calculateInterestRate(amountIP:Double, interestRate:Double, numberOfDays:Int): Double ={
    var total:Double = amountIP*interestRate*numberOfDays/36600


    total = BigDecimal(total).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

    total
  }

}
