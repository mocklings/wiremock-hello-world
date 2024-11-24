package org.mocklings

import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig
import com.github.tomakehurst.wiremock.junit5.WireMockExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class WireMockHelloWorldTest {

  companion object {
    @RegisterExtension
    val wm: WireMockExtension =
      WireMockExtension.newInstance().options(wireMockConfig().dynamicPort()).build()
  }

  @Test
  fun `wiremock hello world example test`() {
    // Setup WireMock
    wm.stubFor(
      any(anyUrl())
        .willReturn(
          aResponse()
            .withBody("WireMock Hello World")
        )
    )

    // Create the HttpClient
    val client = HttpClient.newBuilder().build()

    // Create the request to the mocked endpoint
    val request = HttpRequest.newBuilder()
      .uri(URI.create("http://localhost:${wm.port}/hello-world"))
      .GET()
      .build()

    // Send the request and get the response
    val response = client.send(request, HttpResponse.BodyHandlers.ofString())

    // Validate the response
    assertEquals(200, response.statusCode())
    assertEquals("WireMock Hello World", response.body())
  }
}