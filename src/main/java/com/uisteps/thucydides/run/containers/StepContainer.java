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
package com.uisteps.thucydides.run.containers;

import com.uisteps.core.name.Named;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.run.ThucydidesVerifyWithStorage;
import com.uisteps.thucydides.run.listeners.ThucydidesListener;
import com.uisteps.thucydides.run.storage.ThucydidesStorage;
import com.uisteps.thucydides.verify.ThucydidesVerify;

/**
 *
 * @author ASolyankin
 */
public class StepContainer implements ThucydidesVerifyWithStorage {
 
    private final ThucydidesVerify verify = ThucydidesUtils.getNewStepLibrary(ThucydidesVerify.class);
    private final ThucydidesStorage storage = ThucydidesUtils.getNewStepLibrary(ThucydidesStorage.class);
  
    
    public StepContainer() {
        this(new ThucydidesListener());
    }

    public StepContainer(ThucydidesListener listener) {
        init(listener);
    }
    
    private void init(ThucydidesListener listener) {
        listener.setStepContainer(this);
        ThucydidesUtils.registerListener(listener);
    }

    @Override
    public <T> T remember(String key, T value) {
        return storage.remember(key, value);
    }

    @Override
    public <T extends Named> T remember(T value) {
        return storage.remember(value);
    }

    @Override
    public <T> T remember(T value) {
        return storage.remember(value);
    }

    @Override
    public <T> T remembered(String key) {
        return storage.remembered(key);
    }

    @Override
    public <T> T remembered(Class<T> key) {
        return storage.remembered(key);
    }

    @Override
    public ThucydidesVerify verify() {
        return verify;
    }
    
}
