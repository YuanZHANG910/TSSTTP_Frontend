package uk.gov.hmrc.SSTTP.models

import org.joda.time.{DateTime, Days}


/**
  * Created by paul on 25/08/16.
  */
case class Tax (ref:String, intRate:String, liability:String, initPay:String, initPayD: DateTime, startD:DateTime, endD:DateTime, payfreq:String){

}

object Tax{

  val interest:Double = 0.0

  def calculateInterestRate(amountIP:Double, interestRate:Double, numberOfDays:Int): Double ={
    var total:Double = amountIP*interestRate*numberOfDays/36600


    total = BigDecimal(total).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

    total
  }

  def numberOfDays(startDate:DateTime, endDate:DateTime): Int ={
    var days:Int = 0

    days = Days.daysBetween(startDate,endDate).getDays

    days

  }

}