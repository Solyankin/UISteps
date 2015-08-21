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

import com.uisteps.utils.api.zapi.ZapiResponse;
import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class CycleResponse extends ZapiResponse {

    private final String name = "name";
    private final String build = "build";
    private final String environment = "environment";
    private final String description = "description";
    private final String startDate = "startDate";
    private final String endDate = "endDate";
    private final String projectId = "projectId";
    private final String versionId = "versionId";
    private final String createdBy = "createdBy";
    private final String modifiedBy = "modifiedBy";
    private final String id = "id";

    public CycleResponse(JSONObject json) {
        super(json);
    }

    public String getName() {
        return getString(name);
    }

    public String getBuild() {
        return getString(build);
    }

    public String getEnvironment() {
        return getString(environment);
    }

    public String getDescription() {
        return getString(description);
    }

    public String getStartDate() {
        return getString(startDate);
    }

    public String getEndDate() {
        return getString(endDate);
    }

    public String getProjectId() {
        return getString(projectId);
    }

    public String getVersionId() {
        return getString(versionId);
    }

    public String getCreatedBy() {
        return getString(createdBy);
    }

    public String getModifiedBy() {
        return getString(modifiedBy);
    }

    public String getId() {
        return getString(id);
    }

}
