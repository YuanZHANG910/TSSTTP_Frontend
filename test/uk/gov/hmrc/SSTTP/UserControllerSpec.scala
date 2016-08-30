package uk.gov.hmrc.SSTTP

import play.api.http.Status
import org.scalatest._
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.SSTTP.controllers.UserController
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

/**
  * Created by yuan on 26/08/16.
  */
class UserControllerSpecForCon extends UnitSpec with WithFakeApplication{

  val fakeRequest = FakeRequest("GET", "/")

  "GET /" should {
    "return 400" in {
      val result = UserController.check(fakeRequest)
      status(result) shouldBe Status.BAD_REQUEST
    }

    "return HTML" in {
      val result = UserController.check(fakeRequest)
      contentType(result) shouldBe Some("text/html")
      charset(result) shouldBe Some("utf-8")
    }

  }
}

class UserControllerSpecForFun extends FunSuite{

  test("Check number of days"){
    val shouldBe = 10
    val days = UserController.getDFDays("2016-01-16", "2016-01-26")
    assert(shouldBe == days)
  }

  test("Check result"){
    val shouldBe = 5.0
    val days = UserController.getResult(100.00, 5.0, 366)
    assert(shouldBe == days)
  }
}