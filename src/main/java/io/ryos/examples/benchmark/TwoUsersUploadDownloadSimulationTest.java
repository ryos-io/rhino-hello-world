package io.ryos.examples.benchmark;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.ryos.examples.simulations.TwoUsersUploadDownloadSimulation;
import io.ryos.rhino.sdk.Simulation;

public class TwoUsersUploadDownloadSimulationTest {

  private static final String PROPS = "classpath:///rhino.properties";

  public static void main(String... args) throws InterruptedException {

    var wmServer = TestSetup.init().getWmServer();

    wmServer.stubFor(WireMock.get(urlEqualTo("/api/status")).willReturn(aResponse()
        .withStatus(200)
        .withFixedDelay(200)));

    wmServer.stubFor(WireMock.get(urlMatching("/api/files/.*")).willReturn(aResponse()
        .withStatus(200)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.put(urlMatching("/api/files/.*")).willReturn(aResponse()
        .withStatus(201)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.post(urlEqualTo("/token"))
        .willReturn(aResponse()
            .withStatus(200)
            .withBody("{\"access_token\": \"abc123\", \"refresh_token\": \"abc123\"}")));

    Simulation.getInstance(PROPS, TwoUsersUploadDownloadSimulation.class).start();

    Thread.sleep(60000);
    // Stopping
    wmServer.stop();
  }
}
