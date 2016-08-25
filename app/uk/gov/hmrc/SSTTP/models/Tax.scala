package uk.gov.hmrc.SSTTP.models

/**
  * Created by paul on 25/08/16.
  */
case class Tax (ref:String, intRate:String, liability:String, initPay:String, initPayD: String, startD:String, endD:String, payfreq:String){

}

object Tax{

  val interest:Double = 0.0

  def calculateInterest(): Unit ={

  }
}