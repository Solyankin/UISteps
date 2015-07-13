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
package com.uisteps.core.run.storage;

import com.uisteps.core.name.Name;
import com.uisteps.core.name.Named;

/**
 *
 * @author ASolyankin
 */
public class RememberedValue<T> implements Named {

    private final Name name;
    private final T value;

    public RememberedValue(String name, T value) {
        this.name = new Name(name);
        this.value = value;
    }
    
    public T get() {
        return value;
    }
    
    @Override
    public Named setName(String name) {
        this.name.setValue(name);
        return this;
    }
    
    @Override
    public Name getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName().toString();
    }
    
}