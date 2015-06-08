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

    public ExpectedResult _that(boolean condition) {
        return new ExpectedResult(this, condition);
    }

    public Then _that(Condition condition) {
        result.add(condition);
        return new Then(this);
    }

    public LastExpectedResult that(boolean condition) {
        return new LastExpectedResult(this, condition);
    }

    public Result that(Condition condition) {
        result.add(condition);
        return result();
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
        return verify(result);
    }

    public boolean isSuccessful() {
        return result.isSuccessful();
    }

    public Result getResult() {
        return result;
    }

}
