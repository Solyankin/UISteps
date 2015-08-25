/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uisteps.utils.api.zapi;

import com.uisteps.utils.api.rest.RestApiException;
import com.uisteps.utils.api.zapi.cycle.CycleRequest;
import com.uisteps.utils.api.zapi.executions.ExecutionResponse;
import com.uisteps.utils.thucydides.Result;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author A.Solyankin
 */
public class CycleUpdate {

    private final Result result;
    private final Zapi zapi;

    public CycleUpdate(Zapi zapi, Result result) {
        this.result = result;
        this.zapi = zapi;
    }

    public void execute(Map<String, String> cycleAttributes) throws RestApiException {

        CycleRequest cycle = new CycleRequest();
        cycle.putAll(cycleAttributes);
        
        Mode mode = getMode(cycle.getId(), cycle.getClonedCycleId());
        
        cycle.setId(zapi.update(cycle).getId());

        
        HashSet<String> issues = new HashSet();
        Map<String, Result.Test> testMap = result.getTests();

        Collection<Result.Test> tests = testMap.values();

        for (Result.Test test : tests) {
            if (!test.getStatus().equals("IGNORED")) {
                issues.add(test.getIssue());
            }
        }

        if(mode == Mode.CREATE) {
            zapi.addTestsToCycle(issues, cycle.getVersionId(), cycle.getId(), cycle.getProjectId());
        }
        
        Set<ExecutionResponse> executions = zapi.getExecutionsByCycle(cycle.getId()).getExecutions();

        Map<String, ExecutionResponse> executionsMap = new HashMap();
        
        for(ExecutionResponse execution: executions) {
            executionsMap.put(execution.getIssueKey(), execution);
        }
        
        for(String issueKey: issues) {
            Result.Test test = testMap.get(issueKey);
            zapi.quickExecute(executionsMap.get(issueKey).getId(), test.getZapiStatus());
        }
    }
    
    
    
    public Mode getMode(String cycleId, String cloneId) {
        
        if(cycleId != null && !cycleId.isEmpty()) {
            return Mode.EXIST;
        } else if(cloneId != null && !cloneId.isEmpty()) {
            return Mode.CLONE;
        } else {
            return Mode.CREATE;
        }
        
    }
}
