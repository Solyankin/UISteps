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
package com.uisteps.thucydides.run.storage;

import com.uisteps.core.run.storage.RememberedValue;
import com.uisteps.core.run.storage.Storage;
import net.thucydides.core.Thucydides;
import net.thucydides.core.annotations.Step;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesStorage extends Storage {

    public ThucydidesStorage() {
        super(Thucydides.getCurrentSession());
    }

    @Step
    @Override
    public <T> T remembered(RememberedValue<T> value) {
        return super.remembered(value);
    }

    @Step
    @Override
    public <T> T remember(RememberedValue<T> value) {
        return super.remember(value);
    }

}
