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

import com.fasterxml.jackson.databind.ObjectMapper;

public class Constants {

  public static final String FILES_ENDPOINT = "http://localhost:8089/api/files";
  public static final String IDS_ENDPOINT = "http://localhost:8089/api/id";
  public static final String X_REQUEST_ID = "X-Request-Id";
  public static final String X_API_KEY = "X-Api-Key";

  public static final String DISCOVERY_ENDPOINT = getEndpoint();
  public static final ObjectMapper MAPPER = new ObjectMapper();

  public static String getEndpoint() {
    return String.format("http://localhost:%s/api/%s", 8089, "discovery");
  }
}

