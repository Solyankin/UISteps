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
package com.uisteps.utils.thucydides;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class Result {

    private final String path;
    private final String charset;
    private final String projectKey;
    private final JSONArray result = new JSONArray();
    private final Map<String, Test> tests = new HashMap();

    public static final String COMPONENT = "component";
    public static final String TITLE = "title";
    public static final String ISSUE = "issue";
    public static final String STATUS = "status";

    public Result(String path, String charset, String projectKey) throws IOException, JSONException {
        this.path = path;
        this.charset = charset;
        this.projectKey = projectKey;

        init(path, charset, projectKey);
    }

    public class Test {

        private final String component;
        private final String title;
        private final String issue;
        private final String status;
        private String zapiStatus = "-1";

        private Test(String component, String title, String issue, String status) {
            this.component = component;
            this.title = title;
            this.issue = issue;
            this.status = status;
            
            switch(status) {
                case "SUCCESS":
                    zapiStatus = "1";
                    break;
                case "FAILED":
                    zapiStatus = "2";
                    break;
            }
        }

        public String getComponent() {
            return component;
        }

        public String getTitle() {
            return title;
        }

        public String getIssue() {
            return issue;
        }

        public String getStatus() {
            return status;
        }

        public String getZapiStatus() {
            return zapiStatus;
        }
        
        
    }

    public String getPath() {
        return path;
    }

    public String getCharset() {
        return charset;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public JSONArray getJSON() {
        return result;
    }

    public Map<String, Test> getTests() {
        return tests;
    }

    private void init(String path, String charset, String projectKey) throws IOException, JSONException {

        File file = new File(path);
        List<String> lines = Files.readAllLines(file.toPath(), Charset.forName(charset));

        for (int i = 1; i < lines.size(); i++) {

            String line = lines.get(i);
            JSONObject item = new JSONObject();

            String[] attrs = line.replace("\"", "").split(",");
            String component = attrs[0];

            Pattern pattern = Pattern.compile("(.*)\\s(" + projectKey + "\\d+)");
            Matcher matcher = pattern.matcher(attrs[1]);
            matcher.find();

            String title = matcher.group(1);
            String issue = matcher.group(2);

            String status = attrs[2];

            item
                    .put(COMPONENT, component)
                    .put(TITLE, title)
                    .put(ISSUE, issue)
                    .put(STATUS, status);

            result.put(item);
            
            tests.put(issue, new Test(component, title, issue, status));
        }

    }
}
