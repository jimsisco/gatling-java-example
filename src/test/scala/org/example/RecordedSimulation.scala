package org.example

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RecordedSimulation extends Simulation {

	val httpProtocol = http
		.baseUrl("https://www.kindercare.com")
		.inferHtmlResources()
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("en-US,en;q=0.9")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Safari/537.36")

	val headers_0 = Map(
		"Accept-Encoding" -> "gzip, deflate",
		"Pragma" -> "no-cache",
		"Proxy-Connection" -> "keep-alive")

	val headers_2 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "none",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map(
		"Accept" -> "application/json, text/javascript, */*; q=0.01",
		"Sec-Fetch-Dest" -> "empty",
		"Sec-Fetch-Mode" -> "cors",
		"Sec-Fetch-Site" -> "same-origin",
		"X-Requested-With" -> "XMLHttpRequest")

	val headers_4 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "same-origin",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_6 = Map(
		"Content-Type" -> "application/json",
		"Origin" -> "https://www.kindercare.com",
		"Sec-Fetch-Dest" -> "empty",
		"Sec-Fetch-Mode" -> "cors",
		"Sec-Fetch-Site" -> "same-origin",
		"accept" -> "application/json")

	val headers_8 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Origin" -> "https://www.kindercare.com",
		"Sec-Fetch-Dest" -> "document",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "same-origin",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_10 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Sec-Fetch-Dest" -> "iframe",
		"Sec-Fetch-Mode" -> "navigate",
		"Sec-Fetch-Site" -> "same-origin",
		"Sec-Fetch-User" -> "?1",
		"Upgrade-Insecure-Requests" -> "1")

    val uri2 = "http://www.gstatic.com/generate_204"

	val scn = scenario("RecordedSimulation")
		.exec(http("request_0")
			.get(uri2)
			.headers(headers_0))
		.pause(6)
		.exec(http("request_1")
			.get(uri2)
			.headers(headers_0))
		.pause(6)
		.exec(http("request_2")
			.get("/")
			.headers(headers_2))
		.pause(1)
		.exec(http("request_3")
			.get("/data/nearby-centers")
			.headers(headers_3))
		.pause(9)
		.exec(http("request_4")
			.get("/our-centers/results")
			.headers(headers_4)
			.resources(http("request_5")
			.get("/data/nearby-centers")
			.headers(headers_3)))
		.pause(5)
		.exec(http("request_6")
			.post("/xdb/event")
			.headers(headers_6)
			.body(RawFileBody("org/example/recordedsimulation/0006_request.json")))
		.pause(9)
		.exec(http("request_7")
			.post("/log/event")
			.headers(headers_6)
			.body(RawFileBody("org/example/recordedsimulation/0007_request.json"))
			.resources(http("request_8")
			.post("/our-centers/search")
			.headers(headers_8)
			.formParam("location", "97236")
			.formParam("distance", "15")
			.formParam("edpId", ""),
            http("request_9")
			.get("/data/nearby-centers")
			.headers(headers_3)))
		.pause(6)
		.exec(http("request_10")
			.get("/our-centers/contact/300902")
			.headers(headers_10))
		.pause(3)
		.exec(http("request_11")
			.post("/xdb/event")
			.headers(headers_6)
			.body(RawFileBody("org/example/recordedsimulation/0011_request.json")))

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}