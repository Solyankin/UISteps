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

import com.uisteps.utils.api.rest.RestApi;
import com.uisteps.utils.api.rest.RestApiException;
import com.uisteps.utils.thucydides.Result;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author A.Solyankin
 */
public class Zapi {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
    private final Map<String, String> months = new HashMap();

    public Zapi() {
        months.put("01", "Jan");
        months.put("02", "Feb");
        months.put("03", "Mar");
        months.put("04", "Apr");
        months.put("05", "May");
        months.put("06", "Jun");
        months.put("07", "Jul");
        months.put("08", "Aug");
        months.put("09", "Sep");
        months.put("10", "Oct");
        months.put("11", "Nov");
        months.put("12", "Dec");
    }

    /**
     *
     * @param json
     * @throws JSONException
     * @throws RestApiException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    public void execute(String json) throws JSONException, RestApiException, IOException, ParserConfigurationException, SAXException, TransformerException {
        execute(new JSONObject(json));
    }

    /**
     *
     * @param args
     *
     * mode=setup path=path_to_pom.xml host=jira_host login=jira_login
     * password=jira_password projectKey=projectKey versionName=versionName
     * [cycleId=cycleId] [clonedCycleId=clonedCycleId] [cycleName=cycleName]
     * [criteriaField=criteriaField] [criteria=[starts|ends|contains:]criteria]
     * [environment=environment] [description=description]
     * [metafilter=metafilter]
     *
     *
     * mode=result path=path_to_result pom=path_to_pom.xml host=jira_host
     * login=jira_login password=jira_password [charset=charset]
     *
     *
     * @throws JSONException
     * @throws RestApiException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    public void execute(String[] args) throws IOException, JSONException, RestApiException, ParserConfigurationException, SAXException, TransformerException {

        JSONObject json = new JSONObject();

        for (String arg : args) {
            String[] keyValueObj = arg.split("=");

            if (keyValueObj.length == 2) {
                json.put(keyValueObj[0], keyValueObj[1]);
            }
        }
        execute(json);
    }

    /**
     *
     * @param json
     *
     * {
     * "mode":"setup", "path":"path_to_pom.xml", "host":"jira_host",
     * "login":"jira_login", "password":"jira_password",
     * "projectKey":"projectKey", "versionName":"versionName",
     * ["cycleId":"cycleId"], ["clonedCycleId":"clonedCycleId"],
     * ["cycleName":"cycleName"], ["criteriaField":"criteriaField"],
     * ["criteria":"[starts|ends|contains:]criteria"],
     * ["environment":"environment"], ["description":"description"],
     * ["metafilter":"metafilter"] }
     *
     * {
     * "mode":"result", "path":"path_to_result", "pom":"path_to_pom.xml",
     * "host":"jira_host", "login":"jira_login", "password":"jira_password",
     * ["charset":"charset"] }
     *
     * @throws JSONException
     * @throws RestApiException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     */
    public void execute(JSONObject json) throws JSONException, RestApiException, IOException, ParserConfigurationException, SAXException, TransformerException {

        String mode = json.getString("mode");
        String path = json.getString("path");

        File file = new File(path);

        String host = json.getString("host");
        String login = json.getString("login");
        String password = json.getString("password");

        RestApi api = new RestApi(host).setBase64BasicAuthorization(login, password);

        switch (mode) {
            case "setup":
                String projectKey = json.getString("projectKey");
                String versionName = json.getString("versionName");

                String cycleId = json.optString("cycleId");
                String clonedCycleId = json.optString("clonedCycleId");
                String cycleName = json.optString("cycleName");

                String criteriaField = json.optString("criteriaField");
                String criteriaValue = json.optString(criteriaField);
                String criteria = json.optString("criteria");
                /*
                 if (cycleId.isEmpty() && clonedCycleId.isEmpty() && criteriaValue.isEmpty()) {
                 throw new RuntimeException("One of parameters must be set: cycleId, clonedCycleId or " + criteriaField);
                 }
                 */
                if (!cycleId.isEmpty() && !clonedCycleId.isEmpty()) {
                    throw new RuntimeException("One of parameters must be empty! cycleId = " + cycleId + ", clonedCycleId = " + clonedCycleId);
                }

                JSONObject project = api.getRequest("/rest/api/2/project/" + projectKey).get().toJSONObject();
                String projectId = project.getString("id");
                JSONArray versions = project.getJSONArray("versions");

                String versionId = "-1";

                for (int i = 0; i < versions.length(); i++) {
                    JSONObject version = versions.getJSONObject(i);
                    if (version.getString("name").equals(versionName)) {
                        versionId = version.getString("id");
                        break;
                    }
                }

                JSONObject cycle = null;
                String startDate = "";
                String environment = "";
                String description = "";
                String build = "";

                boolean createEcecutions = false;

                if (!cycleId.isEmpty()) {
                    cycle = api.getRequest("/rest/zapi/latest/cycle/" + cycleId).get().toJSONObject();
                    cycleName = cycle.getString("name") + " " + cycleName;
                    startDate = cycle.getString("startDate");

                    if (startDate.isEmpty()) {
                        startDate = getCurrentDate();
                    }
                } else if (!clonedCycleId.isEmpty() || !criteriaField.isEmpty()) {

                    if (clonedCycleId.isEmpty()) {
                        clonedCycleId = getCycle(projectId, versionId, criteriaField, criteria, api);
                    }

                    cycle = api.getRequest("/rest/zapi/latest/cycle/" + clonedCycleId).get().toJSONObject();
                    cycleName = cycle.getString("name") + " " + cycleName;

                    if (!cycleName.startsWith("AUTO EXECUTED:")) {
                        cycleName = "AUTO EXECUTED:" + cycleName;
                    }

                    startDate = getCurrentDate();

                } else {
                    cycleName = "AUTO EXECUTED:" + cycleName;
                    environment = json.optString("environment");
                    description = json.optString("description");
                    build = json.optString("build");
                    createEcecutions = true;
                }

                if (cycle != null) {
                    environment = cycle.getString("environment");
                    description = cycle.getString("description");
                    build = cycle.getString("build");
                }

                JSONObject cycleJSON = new JSONObject();

                cycleJSON
                        .put("name", cycleName)
                        .put("projectId", projectId)
                        .put("versionId", versionId)
                        .put("environment", environment)
                        .put("description", description)
                        .put("build", build)
                        .put("startDate", startDate)
                        .put("endDate", "");

                if (!cycleId.isEmpty()) {
                    cycleJSON
                            .put("clonedCycleId", clonedCycleId)
                            .put("id", cycleId);

                    api.getRequest("/rest/zapi/latest/cycle").put(cycleJSON);

                    clonedCycleId = cycleId;

                } else {
                    cycleJSON.put("clonedCycleId", clonedCycleId);

                    cycleId = api.getRequest("/rest/zapi/latest/cycle").post(cycleJSON).toJSONObject().getString("id");
                    
                    if (clonedCycleId.isEmpty()) {
                        clonedCycleId = cycleId;
                    }
                }

                exportToPom(file, cycleId, clonedCycleId, projectKey, json.optString("metafilter"), createEcecutions, api);
                break;

            case "result":
                String charset = json.optString("charset");
                String pom = json.getString("pom");

                if (charset.isEmpty()) {
                    charset = "UTF-8";
                }

                exportResultToZephyr(file, new File(pom), charset, api);
                break;
            default:
                throw new RuntimeException("Cannot execute mode " + mode + "!");
        }

    }

    private String getCurrentDate() {
        String[] date = dateFormat.format(new Date()).split("/");
        return date[0] + "/" + months.get(date[1]) + "/" + date[2];

    }

    private void exportToPom(File file, String cycleId, String clonedCycleId, String projectKey, String metaFilter, Boolean createExecutions, RestApi api) throws RestApiException, IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException, JSONException {

        StringBuilder executionsExport = new StringBuilder();

        JSONArray executions = api.getRequest("/rest/zapi/latest/execution?cycleId=" + clonedCycleId).get().toJSONObject().getJSONArray("executions");

        for (int i = 0; i < executions.length(); i++) {
            JSONObject execution = executions.getJSONObject(i);
            executionsExport.append("+").append(execution.getString("issueKey").replace("-", "")).append(" ");
        }

        if (metaFilter != null && !metaFilter.isEmpty()) {
            executionsExport.append(" ").append(metaFilter);
        }

        Document doc = getXML(file);

        Element project = (Element) doc.getElementsByTagName("project").item(0);
        Element properties = (Element) project.getElementsByTagName("properties").item(0);

        Element metafilter = doc.createElement("metafilter");
        metafilter.setTextContent(executionsExport.toString());

        properties.appendChild(metafilter);

        Element cycleIdElement = doc.createElement("cycleId");
        cycleIdElement.setTextContent(cycleId);

        properties.appendChild(cycleIdElement);

        Element projectKeyElement = doc.createElement("projectKey");
        projectKeyElement.setTextContent(projectKey);

        properties.appendChild(projectKeyElement);

        Element createExecutionsElement = doc.createElement("createExecutions");
        createExecutionsElement.setTextContent(createExecutions.toString());

        properties.appendChild(createExecutionsElement);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(doc), new StreamResult(file));

    }

    private Document getXML(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
        f.setValidating(false);
        DocumentBuilder builder = f.newDocumentBuilder();
        return builder.parse(file);
    }

    private String getCycle(String projectId, String versionId, String criteriaField, String criteria, RestApi api) throws RestApiException, JSONException {

        try {
            return getCycles(projectId, versionId, criteriaField, criteria, api, true).get(0);
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }
    }

    private List<String> getCycles(String projectId, String versionId, String criteriaField, String criteria, RestApi api) throws RestApiException, JSONException {
        return getCycles(projectId, versionId, criteriaField, criteria, api, false);
    }

    private List<String> getCycles(String projectId, String versionId, String criteriaField, String criteria, RestApi api, boolean first) throws RestApiException, JSONException {

        if (criteriaField.equals("cycleName")) {
            criteriaField = "name";
        }

        ArrayList<String> cycleIdList = new ArrayList();

        JSONObject cycles = api.getRequest("/rest/zapi/latest/cycle?projectId=" + projectId + "&versionId=" + versionId).get().toJSONObject();
        JSONArray keys = cycles.names();
        for (int i = 0; i < keys.length(); i++) {
            try {
                String key = Integer.valueOf(keys.get(i).toString()).toString();
                JSONObject cycle = cycles.getJSONObject(key);

                String value = cycle.getString(criteriaField);

                if (checkCondition(criteria, value)) {

                    cycleIdList.add(key);

                    if (first) {
                        break;
                    }
                }
            } catch (NumberFormatException ex) {

            }
        }

        return cycleIdList;
    }

    private boolean checkCondition(String criteria, String value) {

        String[] conditions = criteria.split(":");

        if (conditions.length == 1) {
            return conditions[0].equals(value);
        }

        String condition = conditions[0];

        switch (condition) {
            case "starts":
                return value.startsWith(criteria);
            case "ends":
                return value.endsWith(criteria);
            case "contains":
                return value.contains(criteria);
            default:
                return value.equals(criteria);

        }
    }

    private void exportResultToZephyr(File file, File pom, String charset, RestApi api) throws IOException, JSONException, ParserConfigurationException, SAXException, RestApiException {

        Document doc = getXML(pom);

        Element project = (Element) doc.getElementsByTagName("project").item(0);
        Element properties = (Element) project.getElementsByTagName("properties").item(0);

        String cycleId = properties.getElementsByTagName("cycleId").item(0).getTextContent();
        String projectKey = properties.getElementsByTagName("projectKey").item(0).getTextContent();

        Result result = new Result(file, charset, projectKey);

        HashSet<String> issues = new HashSet();
        Map<String, Result.Test> testMap = result.getTests();

        Collection<Result.Test> tests = testMap.values();

        for (Result.Test test : tests) {
            if (!test.getStatus().equals("IGNORED")) {
                issues.add(test.getIssue());
            }
        }

        Element createEcecutions = (Element) properties.getElementsByTagName("createExecutions").item(0);

        JSONObject cycleJSON = api.getRequest("/rest/zapi/latest/cycle/" + cycleId).get().toJSONObject();

        String versionId = cycleJSON.getString("versionId");
        String projectId = cycleJSON.getString("projectId");

        if (createEcecutions != null && createEcecutions.getTextContent().equals("true")) {

            JSONObject json = new JSONObject();
            json
                    .put("versionId", versionId)
                    .put("cycleId", cycleId)
                    .put("projectId", projectId)
                    .put("method", "1");

            for (String issue : issues) {
                json.append("issues", issue);
            }

            api.getRequest("/rest/zapi/latest/execution/addTestsToCycle/").post(json);
        }

        JSONArray executions = api.getRequest("/rest/zapi/latest/execution?cycleId=" + cycleId).get().toJSONObject().getJSONArray("executions");
        
        Map<String, JSONObject> executionsMap = new HashMap();

        for (int i = 0; i < executions.length(); i++) {
            JSONObject execution = executions.getJSONObject(i);
            executionsMap.put(execution.getString("issueKey"), execution);
        }

        for (String issueKey : issues) {
            Result.Test test = testMap.get(issueKey);

            JSONObject execution = executionsMap.get(issueKey);

            if (execution != null) {
                api.getRequest("/rest/zapi/latest/execution/" + executionsMap.get(issueKey).getString("id") + "/quickExecute").post("{'status': '" + test.getZapiStatus() + "'}");
            }
        }

        cycleJSON.put("endDate", getCurrentDate());

        cycleJSON.remove("createdBy");
        cycleJSON.remove("modifiedBy");
        api.getRequest("/rest/zapi/latest/cycle").put(cycleJSON);

    }
}
