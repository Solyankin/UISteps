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
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author ASolyankin
 */
public class AlphabetValues {

    private final Alphabet alphabet;
    private final ArrayList<String> values = new ArrayList();
    private final HashSet<AlphabetValues> alphabetValuesSet = new HashSet();
    private boolean used;

    public AlphabetValues(Alphabet alphabet) {
        this.alphabet = alphabet;
        alphabetValuesSet.add(this);
        reset();
    }

    private void used(boolean used) {
        this.used = used;
    }

    public boolean used() {
        return used;
    }

    public final void reset() {
        values.clear();
        values.addAll(alphabet.getValues());
        used(false);
    }

    public ArrayList<String> getAll() {
        return getAll(false);
    }

    public ArrayList<String> getAllRandom() {
        return getAll(true);
    }

    private ArrayList<String> getAll(boolean random) {
        ArrayList<String> valuesToReturn = new ArrayList();

        if (random) {
            while (this.hasNext()) {
                valuesToReturn.add(getNextRandom());
            }
        } else {
            while (this.hasNext()) {
                valuesToReturn.add(getNext());
            }
        }

        return valuesToReturn;
    }

    private String getNext(boolean random) {

        if (!this.hasNext()) {
            throw new NoValuesException();
        }

        String value;
        
        if (random) {
            Random rand = new Random();
            value = values.get(rand.nextInt(values.size()));
        } else {
            value = values.get(0);
        }
        
        remove(value);
        return value;
    }

    public String getNext() {
        return getNext(false);
    }

    public String getNextRandom() {
        return getNext(true);
    }

    public void remove(String value) {

        for (AlphabetValues alphabetValues : alphabetValuesSet) {

            if (alphabetValues.values.remove(value)) {
                alphabetValues.used(true);
            }
        }
    }

    public boolean hasNext() {
        return !values.isEmpty();
    }

    public void register(AlphabetValues alphabetValues) {
        if (!this.equals(alphabetValues)) {
            alphabetValuesSet.add(alphabetValues);
        }
    }

    public void register(ArrayList<AlphabetValues> values) {
        for (AlphabetValues alphabetValues : values) {
            register(alphabetValues);
        }
    }

    public void unregister(AlphabetValues alphabetValues) {
        if (!this.equals(alphabetValues)) {
            alphabetValuesSet.remove(alphabetValues);
        }
    }

    public void unregister(ArrayList<AlphabetValues> values) {
        for (AlphabetValues alphabetValues : values) {
            unregister(alphabetValues);
        }
    }
}
