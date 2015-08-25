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

import com.uisteps.utils.api.rest.RestApiException;
import com.uisteps.utils.api.zapi.executions.ExecutionResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author A.Solyankin
 */
public class ExportFilter {

    private final Zapi zapi;
    private final File file;

    public ExportFilter(String path, String host, String login, String password) {
        zapi = new Zapi(host, login, password);
        file = new File(path);
    }

    public void execute(String cycleId, String filter) throws RestApiException, IOException, ParserConfigurationException, SAXException, TransformerConfigurationException, TransformerException {
        Set<ExecutionResponse> executions = zapi.getExecutionsByCycle(cycleId).getExecutions();

        StringBuilder executionsExport = new StringBuilder();

        for (ExecutionResponse execution : executions) {
            executionsExport.append("+").append(execution.getIssueKey().replace("-", "")).append(" ");
        }

        if (filter != null && !filter.isEmpty()) {
            executionsExport.append(" ").append(filter);
        }

        if (file.getName().equals("pom.xml")) {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(false);
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element project = (Element) doc.getElementsByTagName("project").item(0);
            Element properties = (Element) project.getElementsByTagName("properties").item(0);
            Element metafilter = (Element) properties.getElementsByTagName("metafilter").item(0);

            metafilter.setTextContent(executionsExport.toString());

            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.transform(new DOMSource(doc), new StreamResult(file));

        } else {
            Files.write(file.toPath(), executionsExport.toString().getBytes());
        }

    }
}
