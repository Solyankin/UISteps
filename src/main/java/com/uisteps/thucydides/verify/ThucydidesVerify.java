package com.uisteps.thucydides.verify;

import com.uisteps.core.verify.conditions.Condition;
import com.uisteps.core.verify.conditions.Result;
import com.uisteps.core.verify.Then;
import net.thucydides.core.annotations.Step;
import com.uisteps.core.verify.Verify;
import com.uisteps.core.verify.results.LastExpectedResult;
import com.uisteps.thucydides.verify.results.ThucydidesLastExpectedResult;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesVerify extends Verify {

private boolean complited = true;

    public boolean complited() {
        return complited;
    }

    public void complited(boolean complited) {
        this.complited = complited;
    }
    
    @Step
    @Override
    public Result result(Result result) {
        complited(true);
        return super.result(result);
    }

    @Override
    public Result result() {
        complited(true);
        return super.result();
    }

    
    
    @Override
    public Then that(Condition... conditions) {
        return _that(conditions);
    }

    @Override
    public LastExpectedResult that(boolean condition) {
        return _that(condition);
    }

    @Override
    public Then _that(Condition... conditions) {
        complited(false);
        return super._that(conditions);
    }

    @Override
    public LastExpectedResult _that(boolean condition) {
        complited(false);
        return new ThucydidesLastExpectedResult(this, condition);
    }
    
    

}
