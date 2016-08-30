package uk.gov.hmrc.SSTTP.models

import org.joda.time.DateTime
import play.api.libs.json.Json
import uk.gov.hmrc.time.DateTimeUtils

/**
  * Created by paul on 30/08/16.
  */
case class UserInformationSubmit(Reference:String, Rate:String, Liabilities:String, IniPayment:String, IniDate:String,
                                 StartDate:String, EndDate:String, Frequency:String, submissionTime : DateTime)

object UserInformationSubmit{

  implicit val format = Json.format[UserInformationSubmit]

}