package com.uisteps.core.verify;

import org.junit.Assert;

/**
 *
 * @author ASolyankin
 */
public class Verify {

    private Result result;

    public Verify() {
        this(new Result());
    }

    public Verify(Result result) {
        this.result = result;
    }

    public Verify and() {
        return then(LogicOperation.AND);
    }

    public Verify or() {
        return then(LogicOperation.OR);
    }

    private Verify then(LogicOperation logicOperation) {
        result.add(new ConditionPool().set(logicOperation));
        return this;
    }

    public ExpectedResult _that(boolean condition) {
        return new ExpectedResult(this, condition);
    }

    public Verify _that(Condition condition) {
        result.add(condition);
        return this;
    }

    public LastExpectedResult that(boolean condition) {
        result.hasLastCondition(true);
        return new LastExpectedResult(this, condition);
    }

    public void that(Condition condition) {
        result.hasLastCondition(true);
        _that(condition);
    }

    public Result verify(Result result) {
        try {
            Assert.assertTrue(result.isSuccessful());
            return result;
        } catch (AssertionError err) {
            throw err;
        } finally {
            result.reset();
        }
    }

    public Result result() {
        return verify(getResult());
    }

    public boolean isSuccessful() {
        return result.isSuccessful();
    }

    public Result getResult() {
        return result;
    }

}
