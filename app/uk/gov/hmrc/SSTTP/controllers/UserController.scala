package uk.gov.hmrc.SSTTP.controllers

import org.joda.time.{DateTime, LocalDate}
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

case class userInput(Reference:String, Rate:Int, Liabilities:Int, IniPayment:Int, IniDate:String,
                     StartDate:LocalDate, EndDate:LocalDate, Frequency:String)

class UserController extends FrontendController with Controller {
  // a form contents user input information

  def result(ref:String) = Action.async {
    implicit request =>
      Future.successful(Ok(uk.gov.hmrc.SSTTP.helloworld.html.result()))
  }

  val userInputForm = Form(
    mapping(
      "Reference" -> nonEmptyText,
      "Interest Rate" -> number,
      "Liabilities" -> number,
      "Initial Payment" -> number,
      "Initial Payment Date" -> nonEmptyText,
      "Start Date" -> jodaLocalDate,
      "End Date" -> jodaLocalDate,
      "Payment Frequency" -> nonEmptyText
    )
    (userInput.apply)(userInput.unapply)
  )
  def check = Action{
    implicit request =>
      val input = userInputForm.bindFromRequest()
      userInputForm.bindFromRequest().fold(
        success = {
          Calculate =>
          val Reference = input.data("Reference")
          val InterestRate = input.data("Interest Rate")
          val Liabilities = input.data("Liabilities")
          val InitialPayment = input.data("Initial Payment")
          val InitialPaymentDate = input.data("Initial Payment Date")
          val StartDate = input.data("Start Date")
          val EndDate = input.data("End Date")
          val PaymentFrequency = input.data("Payment Frequency")


            val days = Tax.numberOfDays(StartDate,EndDate)

            val calculate = Tax.calculateInterestRate(Liabilities,InterestRate,days)

          Redirect(routes.UserController.result())
        },
        hasErrors = {
          formWithErrors =>
            print("has errr")
            BadRequest(uk.gov.hmrc.SSTTP.helloworld.html.hello_world(formWithErrors))
        }
      )

//      def calculateInterestRate(amountIP:Double, interestRate:Double, numberOfDays:Int): Double ={
//        var total:Double = amountIP*interestRate*numberOfDays/36600
//
//
//        total = BigDecimal(total).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
//
//        total
//      }



  }
}
