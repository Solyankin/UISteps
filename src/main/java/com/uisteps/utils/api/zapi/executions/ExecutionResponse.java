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
import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class ExecutionResponse extends ZapiResponse {

    private final String id = "id";
    private final String executionStatus = "executionStatus";
    private final String executedOn = "executedOn";
    private final String executedBy = "executedBy";
    private final String executedByDisplay = "executedByDisplay";
    private final String defects = "defects";
    private final String comment = "comment";
    private final String htmlComment = "htmlComment";
    private final String cycleId = "cycleId";
    private final String cycleName = "cycleName";
    private final String versionId = "versionId";
    private final String versionName = "versionName";
    private final String projectId = "projectId";
    private final String issueId = "issueId";
    private final String issueKey = "issueKey";
    private final String summary = "summary";
    private final String label = "label";
    private final String component = "component";

    private final Set<DefectResponse> defectsSet = new HashSet();

    public ExecutionResponse(JSONObject json) {
        super(json);
        
        try {
            JSONArray defectsArray = getJSONArray(defects);

            for (int i = 0; i < defectsArray.length(); i++) {
                defectsSet.add(new DefectResponse(defectsArray.getJSONObject(i)));
            }
        } catch (Exception ex) {
        }
    }

    public Set<DefectResponse> getDefects() {
        return defectsSet;
    }

    public String getId() {
        return getString(id);
    }

    public String getExecutionStatus() {
        return getString(executionStatus);
    }

    public String getExecutedOn() {
        return getString(executedOn);
    }

    public String getExecutedBy() {
        return getString(executedBy);
    }

    public String getExecutedByDisplay() {
        return getString(executedByDisplay);
    }

    public String getComment() {
        return getString(comment);
    }

    public String getHtmlComment() {
        return getString(htmlComment);
    }

    public String getCycleId() {
        return getString(cycleId);
    }

    public String getCycleName() {
        return getString(cycleName);
    }

    public String getVersionId() {
        return getString(versionId);
    }

    public String getVersionName() {
        return getString(versionName);
    }

    public String getProjectId() {
        return getString(projectId);
    }

    public String getIssueId() {
        return getString(issueId);
    }

    public String getIssueKey() {
        return getString(issueKey);
    }

    public String getSummary() {
        return getString(summary);
    }

    public String getLabel() {
        return getString(label);
    }

    public String getComponent() {
        return getString(component);
    }

    public Set<DefectResponse> getDefectsSet() {
        return defectsSet;
    }

}
