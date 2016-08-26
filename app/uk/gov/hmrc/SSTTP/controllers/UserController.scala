package uk.gov.hmrc.SSTTP.controllers


import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import scala.concurrent.Future

/**
  * Created by yuan on 25/08/16.
  */
object UserController extends UserController

case class userInput(Reference:String, Rate:Int, Liabilities:Int, IniPayment:Int, IniDate:String,
                     StartDate:String, EndDate:String, Frequency:String)

class UserController extends FrontendController with Controller {
  // a form contents user input information

  def result() = Action.async {
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
      "Start Date" -> nonEmptyText,
      "End Date" -> nonEmptyText,
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

            val begin = new LocalDate(StartDate)
            val end = new LocalDate(EndDate)
            val days:Int = Days.daysBetween(begin, end).getDays()
            val result = Liabilities.toDouble * InterestRate.toDouble * days/36600
          val Answer = "£"+result

          Redirect(routes.UserController.result())
        },
        hasErrors = {
          formWithErrors =>
            BadRequest(uk.gov.hmrc.SSTTP.helloworld.html.hello_world(formWithErrors))
        }
      )
  }
}
