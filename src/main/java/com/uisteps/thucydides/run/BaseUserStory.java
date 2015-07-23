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

import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.run.listeners.ThucydidesStoryListener;
import com.uisteps.thucydides.user.ThucydidesUser;

/**
 *
 * @author ASolyankin
 * @param <T>
 */
public class BaseUserStory<T extends ThucydidesUser> extends Story {

    public final T user;

    public BaseUserStory(Class<T> user) {
        super();
        this.user = ThucydidesUtils.getNewStepLibrary(user);
    }

    public BaseUserStory(T user) {
        super();
        this.user = user;
    }
    
    public BaseUserStory(Class<T> user, ThucydidesStoryListener listener) {
        super(listener);
        this.user = ThucydidesUtils.getNewStepLibrary(user);
    }

    public BaseUserStory(T user, ThucydidesStoryListener listener) {
        super(listener);
        this.user = user;
    }

    public T user() {
        return user;
    }

}
