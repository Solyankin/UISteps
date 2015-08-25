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

    public static final String name = "name";
    public static final String build = "build";
    public static final String environment = "environment";
    public static final String description = "description";
    public static final String startDate = "startDate";
    public static final String endDate = "endDate";
    public static final String projectId = "projectId";
    public static final String versionId = "versionId";
    public static final String createdBy = "createdBy";
    public static final String modifiedBy = "modifiedBy";
    public static final String id = "id"; 
    
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
