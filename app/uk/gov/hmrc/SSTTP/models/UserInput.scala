package uk.gov.hmrc.SSTTP.models

import uk.gov.hmrc.time.DateTimeUtils

/**
  * Created by paul on 30/08/16.
  */
case class UserInput(Reference:String, Rate:String, Liabilities:String, IniPayment:String, IniDate:String,
                     StartDate:String, EndDate:String, Frequency:String)

object UserInput{
  implicit def userDetailsCaptureToSubmit(details: UserInput) : UserInformationSubmit = {
    UserInformationSubmit(
      details.Reference,
      details.Rate,
      details.Liabilities,
      details.IniPayment,
      details.IniDate,
      details.StartDate,
      details.EndDate,
      details.Frequency,
      DateTimeUtils.now)
    //    forSub
  }
}