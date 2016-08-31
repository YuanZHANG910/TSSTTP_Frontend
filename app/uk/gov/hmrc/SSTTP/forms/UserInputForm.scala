package uk.gov.hmrc.SSTTP.forms

import play.api.data.Form
import play.api.data.Forms._
import uk.gov.hmrc.SSTTP.models.UserInput

/**
  * Created by paul on 31/08/16.
  */
object UserInputForm {

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

}
