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
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASolyankin
 */
public class SequenceGenerator {

    private final ArrayList<RangeValueGenerator> ranges = new ArrayList();

    public SequenceGenerator(String mask) throws IllegalArgumentError {

        String regexp = "(\\(.*?\\)|\\[.*?\\])(?:(\\d+)(?:-(\\d+))?)?";

        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(mask);
        while (matcher.find()) {
            ranges.add(new RangeValueGenerator(matcher.group()));
        }

    }

    public void reset() {
        for (RangeValueGenerator range : ranges) {
            range.reset();
        }
    }

    public String getNext(Values values) {
        return getNext(false, values);
    }

    public String getNextRandom(Values values) {
        return getNext(true, values);
    }

    private String getNext(boolean random, Values values) {

        StringBuilder value = new StringBuilder();

        for (RangeValueGenerator range : ranges) {
            AlphabetValues alphabetValues = range.get(values);

            if (!alphabetValues.hasNext()) {
                alphabetValues.reset();
            }
            
            if (random) {
                value.append(alphabetValues.getNextRandom());
            } else {
                value.append(alphabetValues.getNext());
            }
        }

        return value.toString();
    }

    public ArrayList<String> get(Values values) {
        return get(false, values);
    }

    public ArrayList<String> getRandom(Values values) {
        return get(true, values);
    }

    private ArrayList<String> get(boolean random, Values values) {

        ArrayList<String> stringValues = new ArrayList();

        while (!usedAll(values)) {
            stringValues.add(getNext(random, values));
        }

        return stringValues;
    }

    public boolean usedAll(Values values) {

        for (RangeValueGenerator range : ranges) {

            if (!range.usedAll(values)) {
                return false;
            }

        }
        return true;
    }
    
    public boolean check(String value) {
        return this.get(Values.All).contains(value);
    }
}
