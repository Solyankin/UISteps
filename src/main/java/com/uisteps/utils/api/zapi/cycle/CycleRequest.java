/*
 * Copyright 2015 A.Solyankin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use CycleRequest file except in compliance with the License.
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

import com.uisteps.utils.api.zapi.ZapiRequest;

/**
 *
 * @author A.Solyankin
 */
public class CycleRequest extends ZapiRequest<CycleRequest> {

    public static final String clonedCycleId = "clonedCycleId";
    public static final String id = "id";
    public static final String name = "name";
    public static final String build = "build";
    public static final String environment = "environment";
    public static final String description = "description";
    public static final String startDate = "startDate";
    public static final String endDate = "endDate";
    public static final String projectId = "projectId";
    public static final String versionId = "versionId";

    public CycleRequest() {
        set(clonedCycleId, "");
        set(name, "");
        set(build, "");
        set(environment, "");
        set(description, "");
        set(startDate, "");
        set(endDate, "");
        set(projectId, "");
        set(versionId, "");
    }

    public String getClonedCycleId() {
        return get(clonedCycleId);
    }

    public CycleRequest setClonedCycleId(String clonedCycleId) {
        return set(CycleRequest.clonedCycleId, clonedCycleId);
    }

    public String getId() {
        return get(id);
    }

    public CycleRequest setId(String id) {
        return set(CycleRequest.id, id);
    }
    
    public String getName() {
        return get(name);
    }

    public CycleRequest setName(String name) {
        return set(CycleRequest.name, name);
    }

    public String getBuild() {
        return get(build);
    }

    public CycleRequest setBuild(String build) {
        return set(CycleRequest.build, build);
    }

    public String getEnvironment() {
        return get(environment);
    }

    public CycleRequest setEnvironment(String environment) {
        return set(CycleRequest.environment, environment);
    }

    public String getDescription() {
        return get(description);
    }

    public CycleRequest setDescription(String description) {
        return set(CycleRequest.description, description);
    }

    public String getStartDate() {
        return get(startDate);
    }

    public CycleRequest setStartDate(String startDate) {
        return set(CycleRequest.startDate, startDate);

    }

    public String getEndDate() {
        return get(endDate);
    }

    public CycleRequest setEndDate(String endDate) {
        return set(CycleRequest.endDate, endDate);
    }

    public String getProjectId() {
        return get(projectId);
    }

    public CycleRequest setProjectId(String projectId) {
        return set(CycleRequest.projectId, projectId);
    }

    public String getVersionId() {
        return get(versionId);
    }

    public CycleRequest setVersionId(String versionId) {
        
        if(versionId == null || versionId.isEmpty()) {
            return set(CycleRequest.versionId, "-1");
        } else {
            return set(CycleRequest.versionId, versionId);
        }
    }

}
