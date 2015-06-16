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

import com.uisteps.core.run.WithStorage;
import com.uisteps.core.run.Verify;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.run.listeners.StoryListener;
import com.uisteps.thucydides.verify.ThucydidesVerify;
import net.thucydides.core.steps.StepListener;
import net.thucydides.jbehave.ThucydidesJUnitStory;

/**
 *
 * @author ASolyankin
 */
public class Story extends ThucydidesJUnitStory implements Verify, WithStorage {

    public final ThucydidesVerify verify = ThucydidesUtils.getNewStepLibrary(ThucydidesVerify.class);

    public Story() {
        ThucydidesUtils.registerListener(new StoryListener(this));
    }

    public Story(StepListener listener) {
        ThucydidesUtils.registerListener(listener);
    }

    @Override
    public ThucydidesVerify verify() {
        return verify;
    }

    @Override
    public <T> T remember(String key, T value) {
        ThucydidesUtils.putToSession(key, value);
        return value;
    }

    @Override
    public <T> T rememberedAs(String key) {
        return (T) ThucydidesUtils.getFromSession(key);
    }
}
