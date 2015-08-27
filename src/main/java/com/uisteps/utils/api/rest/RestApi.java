/*
 * Copyright 2015 A.Solyankin.
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
package com.uisteps.utils.api.rest;

import java.util.Base64;

/**
 *
 * @author A.
 */
public class RestApi {

    private String host;
    private String login;
    private String password;
    private String basicAuthorization;

    public RestApi(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }

    public RestApi setHost(String host) {
        this.host = host;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getBasicAuthorization() {
        return basicAuthorization;
    }

    public RestApi setAuthorization(String login, String password) {
        this.login = login;
        this.password = password;
        basicAuthorization = null;
        return this;
    }

    public RestApi setBase64BasicAuthorization(String login, String password) {
        setAuthorization(login, password);
        basicAuthorization = Base64.getEncoder().encodeToString((login + ":" + password).getBytes());
        return this;
    }

    public RestApiRequest getRequest(String url) {
        RestApiRequest request = new RestApiRequest(getHost() + url);
        if (basicAuthorization != null) {
            request.setProperty("Authorization", "Basic " + getBasicAuthorization());
        }
        return request;
    }
}
