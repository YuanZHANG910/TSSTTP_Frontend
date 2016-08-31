package uk.gov.hmrc.SSTTP.controllers

import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.play.frontend.controller.FrontendController
import play.api.mvc._
import org.joda.time.Days
import org.joda.time.LocalDate
import uk.gov.hmrc.SSTTP.connectora.helloWorldConnector
import uk.gov.hmrc.play.http._
import org.joda.time.DateTime
import play.api.libs.json.Json
import uk.gov.hmrc.SSTTP.models.{UserInformationSubmit, UserInput}
import uk.gov.hmrc.time.DateTimeUtils

import scala.concurrent.Future

/**
  * Created by yuan on 25/08/16.
  */
//object UserController extends UserController

//case class userInput(Reference:String, Rate:String, Liabilities:String, IniPayment:String, IniDate:String,
//                     StartDate:String, EndDate:String, Frequency:String)

//case class UserInformationSubmit(Reference:String, Rate:String, Liabilities:String, IniPayment:String, IniDate:String,
//                     StartDate:String, EndDate:String, Frequency:String, submissionTime : DateTime)

object UserController extends FrontendController with Controller{

//  implicit val format = Json.format[UserInput]
  // a form contents user input information

  implicit def userDetailsCaptureToSubmit(details: UserInput) : UserInformationSubmit = {
    val forSub = new UserInformationSubmit(
      details.Reference,
      details.Rate,
      details.Liabilities,
      details.IniPayment,
      details.IniDate,
      details.StartDate,
      details.EndDate,
      details.Frequency,
      DateTimeUtils.now)
    forSub
  }
//
//
//  object UserInformationSubmit {
//    implicit val format = Json.format[UserInformationSubmit]
//  }

  val userInputForm = Form(
    mapping(
      "Reference" -> nonEmptyText,
      "Interest Rate" -> nonEmptyText,
      "Liabilities" -> nonEmptyText,
      "Initial Payment" -> nonEmptyText,
      "Initial Payment Date" -> nonEmptyText,
      "Start Date" -> nonEmptyText,
      "End Date" -> nonEmptyText,
      "Payment Frequency" -> nonEmptyText
    )
    (UserInput.apply)(UserInput.unapply)
  )
//
  var results:Set[UserInput] = Set.empty
//
  def check = Action{
    implicit request =>
      val input = userInputForm.bindFromRequest()
      userInputForm.bindFromRequest().fold(

        success = {
          Calculate =>
//            val session:Session = request.session +
//              ("Reference" -> input.data("Reference"))+
//              ("InterestRate" ->  input.data("Interest Rate")) +
//              ("Liabilities" ->  input.data("Liabilities")) +
//              ("InitialPayment" ->  input.data("Initial Payment")) +
//              ("InitialPaymentDate" ->  input.data("Initial Payment Date")) +
//              (" StartDate" ->  input.data("Start Date")) +
//              ("EndDate" ->  input.data("End Date"))+
//              ("PaymentFrequency" ->  input.data("Payment Frequency"))
              val Reference = input.data("Reference")
              val InterestRate = input.data("Interest Rate")
              val Liabilities = input.data("Liabilities")
              val InitialPayment = input.data("Initial Payment")
              val InitialPaymentDate = input.data("Initial Payment Date")
              val StartDate = input.data("Start Date")
              val EndDate = input.data("End Date")
              val PaymentFrequency = input.data("Payment Frequency")

            val session:Session = request.session +
              ("Reference" -> Reference) +
              ("InterestRate" -> InterestRate) +
              ("Liabilities" -> Liabilities) +
              ("InitialPayment" -> InitialPayment) +
              ("InitialPaymentDate" -> InitialPaymentDate) +
              (" StartDate" -> StartDate) +
              ("EndDate" -> EndDate)+
              ("PaymentFrequency" -> PaymentFrequency)

//              val Answer = "£"+BigDecimal(getResult(Liabilities.toDouble, InterestRate.toDouble, getDFDays(StartDate, EndDate))).setScale(2,BigDecimal.RoundingMode.HALF_UP).toDouble
//
//              def resultSubmit = new Results(Reference, InterestRate, Liabilities, InitialPayment, InitialPaymentDate,
//              StartDate, EndDate, PaymentFrequency, Answer)
//
//              results += resultSubmit

            val helloWorld = helloWorldConnector
//          val helloWorld = helloWorldConnector.submitDetails(Calculate).flatMap{
//            Future.successful(Redirect(helloWorld.helloRegUrl+"/SSTTP/hello-world").withSession(session))
//          }

          Redirect(helloWorld.helloRegUrl+"/SSTTP/hello-world").withSession(session)
        },
        hasErrors = {
          formWithErrors =>
            BadRequest(uk.gov.hmrc.SSTTP.helloworld.html.hello_world(formWithErrors))
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
