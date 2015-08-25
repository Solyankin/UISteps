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
package com.uisteps.utils.api.zapi.executions;

import com.uisteps.utils.api.zapi.defects.DefectResponse;
import com.uisteps.utils.api.zapi.ZapiResponse;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class ExecutionsResponse extends ZapiResponse {

    public static final String executions = "executions";
    private final Set<ExecutionResponse> executionsSet = new HashSet();

    public ExecutionsResponse(JSONObject json) {
        super(json);

        JSONArray executionsArray = getJSONArray(executions);

        for (int i = 0; i < executionsArray.length(); i++) {

            try {
                executionsSet.add(new ExecutionResponse(executionsArray.getJSONObject(i)));
            } catch (JSONException ex) {
            }
        }
    }

    public Set<ExecutionResponse> getExecutions() {

        return executionsSet;
    }
}
