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

    public static final String id = "id";
    public static final String executionStatus = "executionStatus";
    public static final String executedOn = "executedOn";
    public static final String executedBy = "executedBy";
    public static final String executedByDisplay = "executedByDisplay";
    public static final String defects = "defects";
    public static final String comment = "comment";
    public static final String htmlComment = "htmlComment";
    public static final String cycleId = "cycleId";
    public static final String cycleName = "cycleName";
    public static final String versionId = "versionId";
    public static final String versionName = "versionName";
    public static final String projectId = "projectId";
    public static final String issueId = "issueId";
    public static final String issueKey = "issueKey";
    public static final String summary = "summary";
    public static final String label = "label";
    public static final String component = "component";

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
