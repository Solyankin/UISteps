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

import com.uisteps.core.name.Named;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.run.listeners.ThucydidesStoryListener;
import com.uisteps.thucydides.run.storage.ThucydidesStorage;
import com.uisteps.thucydides.verify.ThucydidesVerify;
import net.thucydides.jbehave.ThucydidesJUnitStory;

/**
 *
 * @author ASolyankin
 */
public class Story extends ThucydidesJUnitStory {

    public final ThucydidesVerify verify;
    public final ThucydidesStorage storage;

    public Story() {
        this(new ThucydidesStoryListener());
    }

    public Story(ThucydidesStoryListener listener) {
        verify = ThucydidesUtils.getNewStepLibrary(ThucydidesVerify.class);
        storage = new ThucydidesStorage();
        listener.setStory(this);
        ThucydidesUtils.registerListener(listener);
    }

    public ThucydidesVerify verify() {
        return verify;
    }

    public <T> T remember(String key, T value) {
        return storage.remember(key, value);
    }

    public <T extends Named> T remember(T value) {
        return storage.remember(value);
    }

    public <T> T remember(T value) {
        return storage.remember(value);
    }

    public <T> T remembered(String key) {
        return storage.remembered(key);
    }

    public <T> T remembered(Class<T> key) {
        return storage.remembered(key);
    }

}
