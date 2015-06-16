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

import com.uisteps.core.run.WithUser;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.run.listeners.UserTestListener;
import com.uisteps.thucydides.user.ThucydidesUser;
import net.thucydides.core.steps.StepListener;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author ASolyankin
 */
@RunWith(ThucydidesRunner.class)
public class UserTest extends Test implements WithUser {

    protected ThucydidesUser user = ThucydidesUtils.getNewStepLibrary(ThucydidesUser.class);

    public UserTest() {
        ThucydidesUtils.registerListener(new UserTestListener(this));
    }

    public UserTest(StepListener listener) {
        ThucydidesUtils.registerListener(listener);
    }
    
    @Override
    public ThucydidesUser user() {
        return user;
    }
}
