/*
 * Copyright 2020 Ryos.io.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.ryos.examples.benchmark;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.ryos.examples.simulations.UploadAndReadMultipleFilesWithMultiUsersSimulation;
import io.ryos.rhino.sdk.Simulation;

public class UploadAndReadMultipleFilesWithMultiUsersTest {
  private static final String PROPS = "classpath:///rhino.properties";

  public static void main(String... args) {
    var wmServer = TestSetup.init().getWmServer();
    wmServer.stubFor(WireMock.get(urlEqualTo("/api/status")).willReturn(aResponse()
        .withStatus(200)
        .withFixedDelay(200)));

    wmServer.stubFor(WireMock.put(urlEqualTo("/api/files/a/")).willReturn(aResponse()
        .withStatus(201)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.put(urlEqualTo("/api/files/a/b/")).willReturn(aResponse()
        .withStatus(201)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.put(urlEqualTo("/api/files/a/b/c/")).willReturn(aResponse()
        .withStatus(201)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.get(urlEqualTo("/api/files/a/")).willReturn(
        aResponse().withBody("file:123")
        .withStatus(200)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.get(urlEqualTo("/api/files/a/b/")).willReturn(
        aResponse().withBody("file:456")
        .withStatus(200)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.get(urlEqualTo("/api/files/a/b/c/")).willReturn(
        aResponse().withBody("file:789")
        .withStatus(200)
        .withFixedDelay(100)));

    wmServer.stubFor(WireMock.get(urlEqualTo("/api/id/123/")).willReturn(
        aResponse().withBody("file:123")
            .withStatus(200)
            .withFixedDelay(100)));

    wmServer.stubFor(WireMock.get(urlEqualTo("/api/id/456/")).willReturn(
        aResponse().withBody("file:456")
            .withStatus(200)
            .withFixedDelay(100)));

    wmServer.stubFor(WireMock.get(urlEqualTo("/api/id/789/")).willReturn(
        aResponse().withBody("file:789")
            .withStatus(200)
            .withFixedDelay(100)));

    wmServer.stubFor(WireMock.post(urlEqualTo("/token"))
        .willReturn(aResponse()
            .withStatus(200)
            .withBody("{\"access_token\": \"abc123\", \"refresh_token\": \"abc123\"}")));

    Simulation.getInstance(PROPS, UploadAndReadMultipleFilesWithMultiUsersSimulation.class).verify();

    // Stopping
    wmServer.stop();
  }
}
