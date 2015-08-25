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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class CyclesResponse extends ZapiResponse {

    private final Set<CycleInProjectResponse> cycles = new HashSet();

    public CyclesResponse(JSONObject json, CyclesRequest cyclesRequest) {
        super(json);
        
        try {
            JSONObject root = getJSONArray(cyclesRequest.getVersionId()).getJSONObject(0);
            JSONArray keys = root.names();
            
            for(int i = 0; i < keys.length(); i++) {
                String key = keys.getString(i);
                
                try { 
                    Integer.parseInt(key);
                    JSONObject cycleInProjectJSON = root.getJSONObject(key);
                    cycleInProjectJSON.put("id", key);
                    cycles.add(new CycleInProjectResponse(cycleInProjectJSON));
                } catch(NumberFormatException | JSONException ex) {
                }
            }
        } catch (JSONException ex) {
        }
               
    }

    public Set<CycleInProjectResponse> getCycles() {
        return cycles;
    }

}
