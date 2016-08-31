package uk.gov.hmrc.SSTTP.controllers

import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import org.joda.time.{DateTime, Days, LocalDate}
import play.api.libs.json.Json
import uk.gov.hmrc.SSTTP.connectora.helloWorldConnector
import uk.gov.hmrc.time.DateTimeUtils

import scala.concurrent.Future


/**
  * Created by yuan on 25/08/16.
  */

case class userInput(Reference:String, Rate:Int, Liabilities:Int, IniPayment:Int, IniDate:String,
                     StartDate:String, EndDate:String, Frequency:String)

case class UserInformationSubmit(Reference:String, Rate:Int, Liabilities:Int, IniPayment:Int, IniDate:String,
                                 StartDate:String, EndDate:String, Frequency:String)

object userInput{
  implicit val format = Json.format[userInput]
  // a form contents user input information

  implicit def userDetailsCaptureToSubmit(details: userInput) : UserInformationSubmit = {
    UserInformationSubmit(
      details.Reference,
      details.Rate,
      details.Liabilities,
      details.IniPayment,
      details.IniDate,
      details.StartDate,
      details.EndDate,
      details.Frequency)
  }
}

object UserInformationSubmit {
  implicit val format = Json.format[UserInformationSubmit]
}

object UserController extends FrontendController with Controller{
  val helloWorld = helloWorldConnector
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

  def check = Action.async{ implicit request =>

      val input = userInputForm.bindFromRequest()

      input.fold(
        success = {
          Calculate =>
            helloWorld.submitDetails(Calculate).flatMap {
              _ => Future.successful(Redirect(routes.ResultsController.ResultsController()))
                //Future.successful(Redirect(helloWorld.Url+"/SSTTP/hello-world").withSession(session))
            }
        },
        hasErrors = {
          formWithErrors =>
            Future.successful(BadRequest(uk.gov.hmrc.SSTTP.helloworld.html.hello_world(formWithErrors)))
        }
      )
  }

  def getDFDays(StartDate:String, EndDate:String): Int = {
    Days.daysBetween(new LocalDate(StartDate),new LocalDate(EndDate) ).getDays()
  }

  def getResult(Liabilities:Double, InterestRate:Double, days:Int): Double ={
    var result = 0.00
    result = Liabilities * InterestRate * days/36600
    result
  }

}

//            val session:Session = request.session +
//              ("Reference" -> input.data("Reference"))+
//              ("InterestRate" ->  input.data("Interest Rate")) +
//              ("Liabilities" ->  input.data("Liabilities")) +
//              ("InitialPayment" ->  input.data("Initial Payment")) +
//              ("InitialPaymentDate" ->  input.data("Initial Payment Date")) +
//              (" StartDate" ->  input.data("Start Date")) +
//              ("EndDate" ->  input.data("End Date"))+
//              ("PaymentFrequency" ->  input.data("Payment Frequency"))
//            val Reference = input.data("Reference")
//            val InterestRate = input.data("Interest Rate")
//            val Liabilities = input.data("Liabilities")
//            val InitialPayment = input.data("Initial Payment")
//            val InitialPaymentDate = input.data("Initial Payment Date")
//            val StartDate = input.data("Start Date")
//            val EndDate = input.data("End Date")
//            val PaymentFrequency = input.data("Payment Frequency")
//
//            val session: Session = request.session +
//              ("Reference" -> Reference) +
//              ("InterestRate" -> InterestRate) +
//              ("Liabilities" -> Liabilities) +
//              ("InitialPayment" -> InitialPayment) +
//              ("InitialPaymentDate" -> InitialPaymentDate) +
//              (" StartDate" -> StartDate) +
//              ("EndDate" -> EndDate) +
//              ("PaymentFrequency" -> PaymentFrequency)

//              val Answer = "£"+BigDecimal(getResult(Liabilities.toDouble, InterestRate.toDouble, getDFDays(StartDate, EndDate))).setScale(2,BigDecimal.RoundingMode.HALF_UP).toDouble
//
//              def resultSubmit = new Results(Reference, InterestRate, Liabilities, InitialPayment, InitialPaymentDate,
//              StartDate, EndDate, PaymentFrequency, Answer)
//
//              results += resultSubmit