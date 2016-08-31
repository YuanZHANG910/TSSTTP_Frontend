package uk.gov.hmrc.SSTTP.models

import org.joda.time.DateTime
import play.api.libs.json.Json
import uk.gov.hmrc.SSTTP.controllers.userInput
import uk.gov.hmrc.time.DateTimeUtils

/**
  * Created by yuan on 31/08/16.
  */



//
//
//object user_information {
//  implicit val format = Json.format[userInput]
//  // a form contents user input information
//
//  implicit def userDetailsCaptureToSubmit(details: userInput) : UserInformationSubmit = {
//    val forSub = new UserInformationSubmit(
//      details.Reference,
//      details.Rate,
//      details.Liabilities,
//      details.IniPayment,
//      details.IniDate,
//      details.StartDate,
//      details.EndDate,
//      details.Frequency,
//      DateTimeUtils.now)
//    forSub
//  }
//
//}
//
//object UserInformationSubmit {
//  implicit val format = Json.format[UserInformationSubmit]
//}