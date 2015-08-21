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
import org.json.JSONObject;

/**
 *
 * @author A.Solyankin
 */
public class UpdateCycleResponse extends ZapiResponse {

    private final String id = "id";
    private final String responseMessage = "responseMessage";

    public UpdateCycleResponse(JSONObject json) {
        super(json);
    }

    public String getId() {
        return getString(id);
    }

    public String getResponseMessage() {
        return getString(responseMessage);
    }
}
