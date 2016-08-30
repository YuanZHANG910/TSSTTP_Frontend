package uk.gov.hmrc.SSTTP

import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.SSTTP.controllers.ResultsController
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

/**
  * Created by yuan on 26/08/16.
  */
class ResultsControllerSpec extends UnitSpec with WithFakeApplication{

  val fakeRequest = FakeRequest("GET", "/")

  "GET /" should {
    "return 200" in {
      val result = ResultsController.ResultsController(fakeRequest)
      status(result) shouldBe Status.OK
    }

    "return HTML" in {
      val result = ResultsController.ResultsController(fakeRequest)
      contentType(result) shouldBe Some("text/html")
      charset(result) shouldBe Some("utf-8")
    }

  }



}
