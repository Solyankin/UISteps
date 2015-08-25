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

import com.uisteps.utils.api.zapi.ZapiRequest;

/**
 *
 * @author A.Solyankin
 */
public class CyclesRequest extends ZapiRequest<CyclesRequest> {

    public static final String projectId = "projectId";
    public static final String versionId = "versionId";
    public static final String offset = "offset";
    public static final String expand = "expand";

    public CyclesRequest() {
        set(projectId, "");
        set(versionId, "-1");
        set(offset, "0");
        set(expand, "executionSummaries");

    }

    public String getProjectId() {
        return get(projectId);
    }

    public CyclesRequest setProjectId(String projectId) {
        return set(CyclesRequest.projectId, projectId);
    }

    public String getVersionId() {
        return get(versionId);
    }

    public CyclesRequest setVersionId(String versionId) {
        return set(CyclesRequest.versionId, versionId);
    }

    public String getOffset() {
        return get(offset);
    }

    public CyclesRequest setOffset(String offset) {
        return set(CyclesRequest.offset, offset);
    }

    public String getExpand() {
        return get(expand);
    }

    public CyclesRequest setExpand(String expand) {
        return set(CyclesRequest.expand, expand);
    }

}
