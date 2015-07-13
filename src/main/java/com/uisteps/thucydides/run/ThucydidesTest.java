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
package com.uisteps.thucydides.run;

import com.uisteps.thucydides.run.containers.StepContainer;
import com.uisteps.core.name.Named;
import com.uisteps.thucydides.run.listeners.ThucydidesListener;
import com.uisteps.thucydides.verify.ThucydidesVerify;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author ASolyankin
 */
@RunWith(ThucydidesRunner.class)
public class ThucydidesTest implements ThucydidesVerifyWithStorage {

    private final StepContainer stepContainer;
    protected final ThucydidesVerify verify;

    public ThucydidesTest() {
        this(new ThucydidesListener());
    }

    public ThucydidesTest(ThucydidesListener listener) {
        stepContainer = new StepContainer(listener);
        verify = stepContainer.verify();
    }
    
    @Override
    public ThucydidesVerify verify() {
        return stepContainer.verify();
    }

    @Override
    public <T> T remember(String key, T value) {
        return stepContainer.remember(key, value);
    }

    @Override
    public <T extends Named> T remember(T value) {
        return stepContainer.remember(value);
    }

    @Override
    public <T> T remember(T value) {
        return stepContainer.remember(value);
    }

    @Override
    public <T> T remembered(String key) {
        return stepContainer.remembered(key);
    }

    @Override
    public <T> T remembered(Class<T> key) {
        return stepContainer.remembered(key);
    }
}
