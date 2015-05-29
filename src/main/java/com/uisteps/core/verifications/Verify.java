package com.uisteps.core.verifications;

import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;

/**
 *
 * @author ASolyankin
 */
public class Verify {

    private final List<Condition> conditions;

    public Verify(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public Verify() {
        this(new ArrayList());
    }

    public ExpectedResult that(boolean condition) {
        return new ExpectedResult(conditions, condition);
    }

    public And that(Condition condition) {
        conditions.add(condition);
        return new And(conditions);
    }

    public class ExpectedResult {

        private final List<Condition> conditions;
        private final boolean condition;

        public ExpectedResult(List<Condition> conditions, boolean condition) {
            this.conditions = conditions;
            this.condition = condition;

        }

        public ActualResult ifResultIsExpected(String expectedDescription) {
            return new ActualResult(conditions, condition, expectedDescription);
        }
    }

    public class ActualResult {

        private final List<Condition> conditions;
        private final boolean condition;
        private final String expectedDescription;

        public ActualResult(List<Condition> conditions, boolean condition, String expectedDescription) {
            this.conditions = conditions;
            this.condition = condition;
            this.expectedDescription = expectedDescription;
        }

        public And ifElse(String actualDescription) {
            conditions.add(new Condition(condition, expectedDescription, actualDescription));
            return new And(conditions);
        }
    }

    public class And {

        private final List<Condition> conditions;

        public And(List<Condition> conditions) {
            this.conditions = conditions;
        }

        public Verify and() {
            return new Verify(conditions);
        }

        public void finish() {
            boolean resultCondition = true;
            String resultMessage = "<table border='1' cellpadding='2'>"
                    + "<tr>"
                    + "<th>Expected result</th>"
                    + "<th class='actual-result'>Actual result</th>"
                    + "<th>Status</th></tr>";
            for (Condition condition : conditions) {
                boolean expectedCondition = condition.isValue();
                resultCondition &= expectedCondition;
                resultMessage += "<tr><td>" + condition.getExpectedDescription() + "</td><td class='actual-result'>";
                if (expectedCondition) {
                    resultMessage += "</td><td>SUCCESS</td></tr>";
                } else {
                    resultMessage += condition.getActualDescription() + "</td><td>FAILURE</td></tr>";
                }
            }
            resultMessage += "</table>"
                    + "<script>"
                    + " var flag = true;"
                    + " var resultsTable = $('table').last();"
                    + " resultsTable.find('td.actual-result').each(function() {"
                    + "     if( $(this).text() !== '') {"
                    + "         flag = false;"
                    + "     }"
                    + "     return flag;"
                    + " });"
                    + "     if(flag) {"
                    + "     resultsTable.find('.actual-result').css('display','none');"
                    + "     }"
                    + "</script>";

            verifications(new ResultCondition(resultCondition, resultMessage));
        }
    }

    public class ResultCondition {

        private final boolean resultCondition;
        private final String resultMessage;

        public ResultCondition(boolean resultCondition, String resultMessage) {
            this.resultCondition = resultCondition;
            this.resultMessage = resultMessage;
        }

        @Override
        public String toString() {
            return resultMessage;
        }

    }

    public void verifications(ResultCondition resultCondition) {
        Assert.assertTrue(resultCondition.resultCondition);
    }
}
