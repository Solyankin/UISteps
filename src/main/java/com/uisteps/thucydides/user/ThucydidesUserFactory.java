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
package com.uisteps.thucydides.user;

import com.uisteps.core.user.User;
import com.uisteps.core.user.UserFactory;
import com.uisteps.thucydides.ThucydidesUtils;
import net.thucydides.core.annotations.Step;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesUserFactory extends UserFactory {

    public ThucydidesUserFactory() {
        super(ThucydidesUser.class);
    }

    public ThucydidesUserFactory(Class<? extends User> user) {
        super(user);
    }

    @Step
    @Override
    public User by(String user) {
        return super.by(user);
    }

    @Override
    public <T extends User> T getInstanceOf(Class<T> user) {
        return ThucydidesUtils.getNewStepLibrary(user);
    }

}
