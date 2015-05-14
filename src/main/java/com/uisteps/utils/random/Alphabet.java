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

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author ASolyankin
 */
public class Alphabet {

    private final String mask;
    private ArrayList<String> values = new ArrayList();

    public Alphabet(String mask, String values) {
        this.mask = mask;
        this.values.addAll(Arrays.asList(values.split("")));
    }

    public Alphabet(String mask, ArrayList<String> values) {
        this.mask = mask;
        this.values = values;
    }

    public Alphabet(String values) {
        this(null, values);
    }

    public Alphabet(ArrayList<String> values) {
        this(null, values);
    }

    public Alphabet() {
        this(null, new ArrayList());
    }

    public String getMask() {
        return mask;
    }

    public int size() {
        return values.size();
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public void add(String value) {
        if (!values.contains(value)) {
            values.add(value);
        }
    }

    public void addAll(ArrayList<String> values) {
        for (String value : values) {
            this.add(value);
        }
    }

    public void remove(String value) {
        values.remove(value);
    }

    public void clear() {
        values.clear();
    }

    public String getLast() {
        return values.get(values.size() - 1);
    }

    public boolean hasRange() {
        return getMask().contains("-");
    }

    public ArrayList<String> getValues() {
        return values;
    }
}
