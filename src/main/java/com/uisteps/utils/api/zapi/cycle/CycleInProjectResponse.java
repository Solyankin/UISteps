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
package com.uisteps.utils.api.zapi.cycle;

import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class CycleInProjectResponse extends CycleResponse {

    public static final String totalExecutions = "totalExecutions";
    public static final String totalExecuted = "totalExecuted";
    public static final String projectKey = "projectKey";
    public static final String createdByDisplay = "createdByDisplay";

    public CycleInProjectResponse(JSONObject json) {
        super(json);
    }

    public String getTotalExecutions() {
        return getString(totalExecutions);
    }

    public String getTotalExecuted() {
        return getString(totalExecuted);
    }

    public String getProjectKey() {
        return getString(projectKey);
    }

    public String getCreatedByDisplay() {
        return getString(createdByDisplay);
    }

}
