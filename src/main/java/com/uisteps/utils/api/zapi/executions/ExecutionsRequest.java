/*
 * Copyright 2015 A.Solyankin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use return set(this file except in compliance with the License.
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

import com.uisteps.utils.api.zapi.ZapiRequest;

/**
 *
 * @author A.Solyankin
 */
public class ExecutionsRequest extends ZapiRequest<ExecutionsRequest> {

    private final String action = "action";
    private final String contentOnly = "contentOnly";
    private final String cycleId = "cycleId";
    private final String decorator = "decorator";
    private final String noTitle = "noTitle";
    private final String offset = "offset";
    private final String projectId = "projectId";
    private final String sorter = "sorter";
    private final String versionId = "versionId";

    public ExecutionsRequest() {
        set(action, "expand");
        set(contentOnly, "true");
        set(cycleId, "");
        set(decorator, "none");
        set(noTitle, "true");
        set(offset, "0");
        set(projectId, "");
        set(sorter, "ID: DESC");
        set(versionId, "");
    }

    public String getAction() {
        return get(action);
    }

    public ExecutionsRequest setAction(String action) {
        return set(this.action, action);
    }

    public String getContentOnly() {
        return get(contentOnly);
    }

    public ExecutionsRequest setContentOnly(String contentOnly) {
        return set(this.contentOnly, contentOnly);
    }

    public String getCycleId() {
        return get(cycleId);
    }

    public ExecutionsRequest setCycleId(String cycleId) {
        return set(this.cycleId, cycleId);
    }

    public String getDecorator() {
        return get(decorator);
    }

    public ExecutionsRequest setDecorator(String decorator) {
        return set(this.decorator, decorator);
    }

    public String getNoTitle() {
        return get(noTitle);
    }

    public ExecutionsRequest setNoTitle(String noTitle) {
        return set(this.noTitle, noTitle);
    }

    public String getOffset() {
        return get(offset);
    }

    public ExecutionsRequest setOffset(String offset) {
        return set(this.offset, offset);
    }

    public String getProjectId() {
        return get(projectId);
    }

    public ExecutionsRequest setProjectId(String projectId) {
        return set(this.projectId, projectId);
    }

    public String getSorter() {
        return get(sorter);
    }

    public ExecutionsRequest setSorter(String sorter) {
        return set(this.sorter, sorter);
    }

    public String getVersionId() {
        return get(versionId);
    }

    public ExecutionsRequest setVersionId(String versionId) {
        return set(this.versionId, versionId);
    }

}
