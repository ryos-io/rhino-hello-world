/*
 * Copyright 2018 Ryos.io.
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

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

public class TestSetup {
  private static final String AUTH_ENDPOINT = "test.oauth2.endpoint";
  private static final String WIREMOCK_PORT = "wiremock.port";
  private static TestSetup INSTANCE;

  public static final String X_REQUEST_ID = "X-Request-Id";
  public static final String X_API_KEY = "X-Api-Key";
  public static final String DISCOVERY_ENDPOINT = TestSetup.init().getEndpoint("discovery");
  public static final String FILES_ENDPOINT = TestSetup.init().getEndpoint("files");
  public static final ObjectMapper MAPPER = new ObjectMapper();

  private TestSetup() {
    Integer portNumber = startServer();
    overridePorts(portNumber);
  }

  private WireMockServer wmServer;

  public static synchronized TestSetup init() {
    if (INSTANCE == null) {
      INSTANCE = new TestSetup();
    }

    return INSTANCE;
  }

  private void overridePorts(int port) {
    System.setProperty(AUTH_ENDPOINT, "http://localhost:" + wmServer.port() + "/token");
    System.setProperty(WIREMOCK_PORT, Integer.toString(wmServer.port()));
  }

  public String getEndpoint(String forAPI) {
    return String.format("http://localhost:%s/api/%s", wmServer.port(), forAPI);
  }

  private Integer startServer() {

    wmServer = new WireMockServer(wireMockConfig().dynamicPort()
        .jettyAcceptors(2)
        .jettyAcceptQueueSize(100)
        .containerThreads(100));
    wmServer.start();

    return wmServer.port();
  }

  public WireMockServer getWmServer() {
    return wmServer;
  }
}

