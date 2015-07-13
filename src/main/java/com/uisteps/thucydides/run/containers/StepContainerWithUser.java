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

import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.run.ThucydidesVerifyWithUser;
import com.uisteps.thucydides.run.listeners.ThucydidesListener;
import com.uisteps.thucydides.run.listeners.ThucydidesUserListener;
import com.uisteps.thucydides.user.ThucydidesUser;

/**
 *
 * @author ASolyankin
 */
public class StepContainerWithUser extends StepContainer implements ThucydidesVerifyWithUser {

    private final ThucydidesUser user;
    
    @Override
    public ThucydidesUser user() {
        return user;
    }
    
    public StepContainerWithUser() {
        this(new ThucydidesUserListener());
    }

    public StepContainerWithUser(ThucydidesUserListener listener) {
        this(ThucydidesUtils.getNewStepLibrary(ThucydidesUser.class), listener);
    }
    
    public StepContainerWithUser(ThucydidesUser user, ThucydidesListener listener) {
        super(listener);
        this.user = user;
    }
    
}
