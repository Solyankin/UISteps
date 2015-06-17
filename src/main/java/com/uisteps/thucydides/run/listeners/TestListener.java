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
package com.uisteps.thucydides.run.listeners;

import com.uisteps.thucydides.run.Story;
import com.uisteps.thucydides.run.Test;
import com.uisteps.thucydides.verify.ThucydidesVerify;

/**
 *
 * @author ASolyankin
 */
public class TestListener extends ThucydidesListener {

    private Test test;

    public void setStory(Test test) {
        this.test = test;
    }

    public Test getTest() {

        if (test == null) {
            throw new AssertionError("Test is not set in listener");
        }
        return test;
    }

    @Override
    protected ThucydidesVerify verify() {
        return getTest().verify();
    }

}
