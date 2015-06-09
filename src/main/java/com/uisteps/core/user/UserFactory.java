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
package com.uisteps.core.user;

import java.util.HashMap;

/**
 *
 * @author ASolyankin
 */
public abstract class UserFactory {

    private final Class<? extends User> user;
    private final HashMap<String, User> users = new HashMap();

    public UserFactory() {
        this.user = User.class;
    }

    public UserFactory(Class<? extends User> user) {
        this.user = user;
    }

    public UserFactory add(String user) {
        return add(user, this.user);
    }
    
    public UserFactory add(String name, Class<? extends User> user) {
        users.put(name, getInstanceOf(user));
        return this;
    }
    
    public User by(String user) {

        if (!users.containsKey(user)) {
            add(user);
        }

        return users.get(user);
    }

    public <T extends User> T by(Class<T> user) {
        T userInstance = getInstanceOf(user);
        users.put(user.getName(), userInstance);
        return (T) by(userInstance.getName());
    }

    public <T extends User> T by(String name, Class<T> user) {

        if (!(users.containsKey(name) && users.get(name).getClass().equals(user))) {
            add(name, user);
        }

        return (T) by(name);
    }

    public abstract <T extends User> T getInstanceOf(Class<T> user);
}
