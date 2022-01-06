/*
 *  Copyright 2021 Collate
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.openmetadata.catalog.elasticsearch;

import java.util.Map;
import javax.validation.constraints.NotEmpty;

public class ElasticSearchConfiguration {

  public ElasticSearchConfiguration() {}

  public ElasticSearchConfiguration(Map<String, Object> config) {
    host = (String) config.get("host");
    port = (Integer) config.get("port");
    username = (String) config.get("username");
    password = (String) config.get("password");
    scheme = (String) config.get("scheme");
    truststorePath = (String) config.get("truststorePath");
    truststorePassword = (String) config.get("truststorePassword");
    if (config.containsKey("connectionTimeoutSecs")) {
      connectionTimeoutSecs = (Integer) config.get("connectionTimeoutSecs");
    }
    if (config.containsKey("socketTimeoutSecs")) {
      socketTimeoutSecs = (Integer) config.get("socketTimeoutSecs");
    }
  }

  @NotEmpty private String host;

  @NotEmpty private Integer port;

  private String username;

  private String password;

  private String scheme;

  private String truststorePath;

  private String truststorePassword;

  private Integer connectionTimeoutSecs = 5;

  private Integer socketTimeoutSecs = 60;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getScheme() {
    return scheme;
  }

  public void setScheme(String scheme) {
    this.scheme = scheme;
  }

  public String getTruststorePath() {
    return truststorePath;
  }

  public void setTruststorePath(String truststorePath) {
    this.truststorePath = truststorePath;
  }

  public String getTruststorePassword() {
    return truststorePassword;
  }

  public void setTruststorePassword(String truststorePassword) {
    this.truststorePassword = truststorePassword;
  }

  public Integer getConnectionTimeoutSecs() {
    return connectionTimeoutSecs;
  }

  public void setConnectionTimeoutSecs(Integer connectionTimeoutSecs) {
    this.connectionTimeoutSecs = connectionTimeoutSecs;
  }

  public Integer getSocketTimeoutSecs() {
    return socketTimeoutSecs;
  }

  public void setSocketTimeoutSecs(Integer socketTimeoutSecs) {
    this.socketTimeoutSecs = socketTimeoutSecs;
  }

  @Override
  public String toString() {
    return "ElasticSearchConfiguration{"
        + "host='"
        + host
        + '\''
        + ", port="
        + port
        + ", username='"
        + username
        + '\''
        + '}';
  }
}