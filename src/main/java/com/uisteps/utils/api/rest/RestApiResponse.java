/*
 * Copyright 2015 ASolyankin.
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASolyankin
 */
public class RestApiResponse {

    private final String response;

    RestApiResponse(String response) {
        this.response = response;
    }

    

    @Override
    public String toString() {
        return response;
    }

    public JSONObject toJSONObject() throws RestApiException {
        try {
            return new JSONObject(response);
        } catch (JSONException ex) {
            throw new RestApiException("Cannot get JSONObject from response: \n" + response + "\n" + ex);
        }
    }

    public JSONArray toJSONArray() throws RestApiException {
        try {
            return new JSONArray(response);
        } catch (JSONException ex) {
            throw new RestApiException("Cannot get JSONArray from response \n" + response + "\n" + ex);
        }
    }

}
