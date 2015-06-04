/*
 * Copyright 2015 ASolyankin.
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
package com.uisteps.core.verify;

import java.util.ArrayList;

/**
 *
 * @author ASolyankin
 */
public class ConditionContainer extends WithLogicOperation {

    private ArrayList<WithLogicOperation> conditions = new ArrayList();
    private boolean modified;
    private boolean result = true;

    @Override
    public boolean isSuccessful() {

        if (modified) {

            for (WithLogicOperation condition : conditions) {

                result = condition.getLogicOperation().execute(result, condition.isSuccessful());

                if (conditions.indexOf(condition) != 0 && condition.getLogicOperation() == LogicOperation.AND && !result) {
                    break;
                }

                if (condition.getLogicOperation() == LogicOperation.OR && result) {
                    break;
                }
            }

            modified = false;
        }

        return result;
    }

    public void reset() {
        conditions = new ArrayList();
    }

    public void add(WithLogicOperation condition) {
        conditions.add(condition);
        modified = true;
    }

    public ArrayList<WithLogicOperation> getConditions() {
        return conditions;
    }
    
}
