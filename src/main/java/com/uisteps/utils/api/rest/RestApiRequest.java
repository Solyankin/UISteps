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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.CharEncoding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ASolyankin
 */
public class RestApiRequest {

    private final HashMap<String, String> headers = new HashMap();
    private String responseEncoding = CharEncoding.UTF_8;
    private final String host;
    private Object request;
    
    
    public RestApiRequest(String host) {
        this.host = host;
    }

    public RestApiRequest(URL url) {
        this.host = url.toString();
    }

    public void setResponseEncoding(String responseEncoding) {
        this.responseEncoding = responseEncoding;
    }

    public RestApiRequest setProperty(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public RestApiRequest setHeaders(Map<String, String> headers) {

        for (String key : headers.keySet()) {
            setProperty(key, headers.get(key));
        }
        return this;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public String getProperty(String key) {
        return headers.get(key);
    }

    private boolean isJSON(Object request){
        
        try {
            this.request = new JSONObject(request.toString());
            return true;
        } catch (JSONException ex) {
            
        }
        
        try {
            this.request = new JSONArray(request.toString());
            return true;
        } catch (JSONException ex) {
            
        }
        
        this.request = request.toString();
        return false;
    }
    
    public RestApiResponse send(Method method, Object request) throws RestApiException {

        HttpURLConnection connection = null;
        String url = host;
        
        if (isJSON(request) || method == Method.DELETE) {
            setProperty("Content-Type", "application/json");
        }
        
        try {

            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod(method.toString());

            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }

            if (method != Method.GET) {
                connection.setDoOutput(true);
                OutputStream writer = new BufferedOutputStream(connection.getOutputStream());
                writer.write(this.request.toString().getBytes(responseEncoding));
                writer.flush();
            }

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String response = readStreamToString(connection.getInputStream(), responseEncoding);
                return new RestApiResponse(response);
            } else {
                throw new RestApiException("Connection is broken!").setResponseCode(responseCode);
            }

        } catch (IOException ex) {

            throw new RestApiException("Cannot send to " + host + " request:\n" + request + "\n" + ex);

        } catch (RestApiException ex) {

            throw new RestApiException("Cannot send to " + host + " request:\n" + request + "\n" + ex).setResponseCode(ex.getResponseCode());

        }finally {

            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public RestApiResponse get() throws RestApiException {
        return send(Method.GET, "");
    }
    
    public RestApiResponse post(Object request) throws RestApiException {
        return send(Method.POST, request);
    }

    public RestApiResponse put(Object request) throws RestApiException {
        return send(Method.PUT, request);
    }

    public RestApiResponse delete(Object request) throws RestApiException {
        return send(Method.DELETE, request);
    }

    public RestApiResponse delete() throws RestApiException {
        return delete("");
    }
    
    private String readStreamToString(InputStream in, String encoding) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader reader = new InputStreamReader(in, encoding);
        int c;

        while ((c = reader.read()) != -1) {
            builder.append((char) c);
        }

        return builder.toString();
    }

}
