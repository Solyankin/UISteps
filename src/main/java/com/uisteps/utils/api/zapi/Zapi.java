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

import com.uisteps.utils.api.rest.Method;
import com.uisteps.utils.api.zapi.executions.ExecutionsRequest;
import com.uisteps.utils.api.zapi.cycle.CycleRequest;
import com.uisteps.utils.api.zapi.cycle.UpdateCycleResponse;
import com.uisteps.utils.api.rest.RestApiException;
import com.uisteps.utils.api.rest.RestApiRequest;
import com.uisteps.utils.api.zapi.cycle.CycleResponse;
import com.uisteps.utils.api.zapi.executions.ExecutionResponse;
import com.uisteps.utils.api.zapi.executions.ExecutionsResponse;
import java.util.Set;
import org.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;

/**
 *
 * @author A.Solyankin
 */
public class Zapi {

    private String host;
    private String login;
    private String password;
    private String authorization;

    public Zapi(String host, String login, String password) {
        this.host = host;
        this.login = login;
        this.password = password;
        setAuthorization();
    }

    public String getHost() {
        return host;
    }

    public Zapi setHost(String host) {
        this.host = host;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Zapi setLogin(String login) {
        this.login = login;
        setAuthorization();
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Zapi setPassword(String password) {
        this.password = password;
        setAuthorization();
        return this;
    }

    public String getAuthorization() {
        return authorization;
    }

    private void setAuthorization() {
        authorization = Base64.encodeBase64String((login + ":" + password).getBytes());
    }

    //Cycle
    public UpdateCycleResponse update(CycleRequest cycle) throws RestApiException {

        String projectId = cycle.get(CycleRequest.projectId);
        String clonedCycleId = cycle.get(CycleRequest.clonedCycleId);
        String cycleId = cycle.get(CycleRequest.id);
        String cycleName = cycle.get(CycleRequest.name);

        if (projectId == null || projectId.isEmpty()) {
            throw new RuntimeException("Project id must not be null or empty!");
        }

        if (cycleName == null || cycleName.isEmpty()) {
            throw new RuntimeException("Cycle name must not be null or empty!");
        }

        if (clonedCycleId != null && !clonedCycleId.isEmpty() && cycleId != null && !cycleId.isEmpty()) {
            throw new RuntimeException("One of parameters must be empty! clonedCycleId = " + clonedCycleId + ", id = " + cycleId);
        }

        RestApiRequest cycleRequest = getRequest("/rest/zapi/latest/cycle");
        JSONObject json;
        
        if(cycleId != null && !cycleId.isEmpty()) {
            json = cycleRequest.putJSON(cycle.getJSON()).toJSONObject();
        } else {
            json = cycleRequest.postJSON(cycle.getJSON()).toJSONObject();
        }
        
        return new UpdateCycleResponse(json);
    }
    
    public CycleResponse getCycle(String id) throws RestApiException {
        return new CycleResponse(getRequest("/rest/zapi/latest/cycle/" + id).get().toJSONObject());
    }

    //Executions
    public ExecutionsResponse get(ExecutionsRequest executions) throws RestApiException {
        return new ExecutionsResponse(getRequest("/rest/zapi/latest/execution?" + executions.toString()).get().toJSONObject());
    }

    public ExecutionsResponse getExecutionsByCycle(String id) throws RestApiException {
        return new ExecutionsResponse(getRequest("/rest/zapi/latest/execution?cycleId=" + id).get().toJSONObject());

    }

    public ExecutionResponse getExecutionByIssue(String id) throws RestApiException {
        return new ExecutionResponse(getRequest("/rest/zapi/latest/execution?issueId=" + id).get().toJSONObject());

    }

    public ExecutionResponse getExecutionById(String id) throws RestApiException {
        return new ExecutionResponse(getRequest("/rest/zapi/latest/execution?id=" + id).get().toJSONObject());

    }

    public void quickExecute(String id, String status) throws RestApiException {
        getRequest("/rest/zapi/latest/execution/" + id + "/quickExecute").postJSON("{'status': '" + status + "'}");
    }

    // Tests
    public void addTestsToCycle(Set<String> issues, String versionId, String cycleId, String projectId) throws RestApiException {
        JSONObject json = new JSONObject();
        RestApiRequest request = getRequest("/rest/zapi/latest/execution/addTestsToCycle/");
        try {
            json
                    .put("versionId", versionId)
                    .put("cycleId", cycleId)
                    .put("projectId", projectId)
                    .put("method", "1");

            for (String issue : issues) {
                json.append("issues", issue);
            }

            request.postJSON(json);
        } catch (JSONException ex) {
            throw new RuntimeException("Cannot send " + json + " to " + request);
        }
    }

    private RestApiRequest getRequest(String url) {
        RestApiRequest request = new RestApiRequest(host + url);
        request.setProperty("Authorization", "Basic " + authorization);
        return request;
    }

}
