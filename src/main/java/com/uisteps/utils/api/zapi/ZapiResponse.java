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
package com.uisteps.utils.api.zapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class ZapiResponse {

    private final JSONObject json;

    public ZapiResponse(JSONObject json) {
        this.json = json;
    }
    
    public String getString(String key) {
        
        try {
            return json.getString(key);
        } catch (JSONException ex) {
            throw new RuntimeException("Cannot get " + key + " from " + json + "\n" + ex);
        }
    }

    public Object get(String key) {
        
        try {
            return json.get(key);
        } catch (JSONException ex) {
            throw new RuntimeException("Cannot get " + key + " from " + json + "\n" + ex);
        }

    }

    public JSONObject getJSONObject(String key) {
        
        try {
            return json.getJSONObject(key);
        } catch (JSONException ex) {
            throw new RuntimeException("Cannot get " + key + " from " + json + "\n" + ex);
        }
    }

    public JSONArray getJSONArray(String key) {
        
        try {
            return json.getJSONArray(key);
        } catch (JSONException ex) {
            throw new RuntimeException("Cannot get " + key + " from " + json + "\n" + ex);
        }
    }

    public JSONObject getJSON() {
        return json;
    }

    @Override
    public String toString() {
        return json.toString();
    }

}
