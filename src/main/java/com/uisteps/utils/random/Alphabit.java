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
package com.uisteps.utils.random;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 *
 * @author ASolyankin
 */
public abstract class Alphabit {

    private HashMap<String, Alphabit> map = new HashMap();

    protected Alphabit() {
        init();
    }

    private void init() {
        map.put(getAlias(), this);
    }

    public final static Alphabit EN = new Alphabit() {

        @Override
        public String getAlias() {
            return "en";
        }

        @Override
        public String toString() {
            return "qwertyuiopasdfghjklzxcvbnm";
        }

    };
    public final static Alphabit RU = new Alphabit() {

        @Override
        public String getAlias() {
            return "ru";
        }

        @Override
        public String toString() {
            return "йцукенгшщзфывапролдхъячсмитьбю";
        }
    };
    public final static Alphabit SIMBOLS = new Alphabit() {

        @Override
        public String getAlias() {
            return "s";
        }

        @Override
        public String toString() {
            return "!№;%:?*()_+@#$^&-=,./|[]{} \"\\";
        }
    };

    public final static Alphabit FIGURES = new Alphabit() {

        @Override
        public String getAlias() {
            return "f";
        }

        @Override
        public String toString() {
            return "1234567890";
        }
    };

    public abstract String getAlias();

    public Alphabit getByAlias(String alias) throws NoSuchElementException {
        Alphabit alphabit = map.get(alias);
        if (alphabit != null) {
            return map.get(alias);
        } else {
            throw new NoSuchElementException("Cannot find alphabit by alias " + alias);
        }
    }
}
