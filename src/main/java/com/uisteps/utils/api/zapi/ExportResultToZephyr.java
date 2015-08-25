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

import com.uisteps.utils.api.rest.RestApiException;
import com.uisteps.utils.api.zapi.cycle.CycleRequest;
import com.uisteps.utils.thucydides.Result;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import org.json.JSONException;

/**
 *
 * @author A.Solyankin
 */
public class ExportResultToZephyr {

    private final Result result;
    private final Zapi zapi;

    public ExportResultToZephyr(String path, String charset, String projectKey, String host, String login, String password) throws IOException, JSONException {

        result = new Result(path, charset, projectKey);
        zapi = new Zapi(host, login, password);
    }

    public void extecute(String params) throws RestApiException {
        extecute(params, "#");
    }

    public void extecute(String params, String splitter) throws RestApiException {

        Map<String, String> cycleAttributes = new HashMap();

        for (String paramPaire : params.split(splitter)) {
            String[] param = paramPaire.split("=");
            if (param.length == 2) {
                cycleAttributes.put(param[0], param[1]);
            }
        }

        extecute(cycleAttributes);
    }

    public void extecute(Map<String, String> params) throws RestApiException {

        String cycleName = params.get(CycleRequest.name);

        if (cycleName != null && !cycleName.startsWith("AUTO")) {
            cycleName = "AUTO - " + cycleName;
        }

        params.put(CycleRequest.name, cycleName);

        new CycleUpdate(zapi, result).execute(params);

    }
}
