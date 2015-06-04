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
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ASolyankin
 */
public class Result extends ConditionContainer {

    private boolean hasLastCondition;

    public Result() {
        reset();
    }

    @Override
    public final void reset() {
        super.reset();
        add(new ConditionPool());
    }

    public boolean hasLastCondition() {
        return hasLastCondition;
    }

    public void hasLastCondition(boolean hasLastCondition) {
        this.hasLastCondition = hasLastCondition;
    }

    public void add(Condition condition) {
        ConditionPool lastConditionPool = (ConditionPool) getConditions().get(getConditions().size() - 1 );
        lastConditionPool.add(condition);
    }

    @Override
    public String toString() {

        StringBuilder resultBuilder = new StringBuilder();

        resultBuilder.append("<table border='1' cellpadding='3' bgcolor='white'>");

        resultBuilder.append("<tr bgcolor='#ededed'>");

        if (this.isSuccessful()) {
            resultBuilder.append("<th colspan='2'>");
        } else {
            resultBuilder.append("<th>");
        }

        resultBuilder.append("<b>Expected result</b></th>");

        if (!this.isSuccessful()) {
            resultBuilder.append("<th><b>Actual result</b></th>");
        }

        resultBuilder.append("<th><b>Status</b></th>");
        resultBuilder.append("</tr>");

        Iterator<WithLogicOperation> iterator = getConditions().iterator();

        while (iterator.hasNext()) {

            ConditionPool condition = (ConditionPool) iterator.next();

            resultBuilder.append("<tbody>");

            if (getConditions().indexOf(condition) != 0) {

                resultBuilder.append("<tr bgcolor='#ededed'><td colspan='3'><b>");
                resultBuilder.append(condition.getLogicOperation());
                resultBuilder.append("</b></td></tr>");
            }

            resultBuilder.append(condition);
            resultBuilder.append("</tbody>");
        }

        resultBuilder.append("</table>");
        return resultBuilder.toString();

    }
}