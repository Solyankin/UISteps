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

import com.uisteps.utils.IllegalArgumentError;
import java.util.ArrayList;

/**
 *
 * @author ASolyankin
 */
public class ValueGenerator {

    private final AlphabetValues allValues;
    private final AlphabetValues minLengthValues;
    private final AlphabetValues maxLengthValues;
    private final AlphabetValues minValues;
    private final AlphabetValues maxValues;

    private final ArrayList<AlphabetValues> allAlphabetValues = new ArrayList();

    public ValueGenerator(String mask) throws IllegalArgumentError {
        this(new AlphabetFactory().getByMask(mask));
    }

    public ValueGenerator(AlphabetFactory alphabetFactory, String mask) throws IllegalArgumentError {
        this(alphabetFactory.getByMask(mask));

    }

    public ValueGenerator(Alphabet alphabet) {
        
        Alphabet minLengthValuesAlphabet = new Alphabet();
        Alphabet maxLengthValuesAlphabet = new Alphabet();
        Alphabet minValuesAlphabet = new Alphabet();
        Alphabet maxValuesAlphabet = new Alphabet();

        boolean flag = true;

        for (String value : alphabet.getValues()) {
            int valueLength = value.length();

            if (minLengthValuesAlphabet.isEmpty() || valueLength == minLengthValuesAlphabet.getLast().length()) {
                minLengthValuesAlphabet.add(value);
            } else if (value.length() < minLengthValuesAlphabet.getLast().length()) {
                minLengthValuesAlphabet.clear();
                minLengthValuesAlphabet.add(value);
            }

            if (maxLengthValuesAlphabet.isEmpty() || valueLength == maxLengthValuesAlphabet.getLast().length()) {
                maxLengthValuesAlphabet.add(value);
            } else if (valueLength > maxLengthValuesAlphabet.getLast().length()) {
                maxLengthValuesAlphabet.clear();
                maxLengthValuesAlphabet.add(value);
            }

            if (flag) {

                try {
                    double doubleValue = Double.parseDouble(value);

                    if (minValuesAlphabet.isEmpty()) {
                        minValuesAlphabet.add(value);
                    } else if (doubleValue < Double.parseDouble(minValuesAlphabet.getLast())) {
                        minValuesAlphabet.clear();
                        minValuesAlphabet.add(value);
                    }

                    if (maxValuesAlphabet.isEmpty()) {
                        maxValuesAlphabet.add(value);
                    } else if (doubleValue > Double.parseDouble(maxValuesAlphabet.getLast())) {
                        maxValuesAlphabet.clear();
                        maxValuesAlphabet.add(value);
                    }

                } catch (NumberFormatException ex) {
                    flag = false;
                }
            }
        }

        if (!flag) {
            minValuesAlphabet.clear();
            minValuesAlphabet.addAll(minLengthValuesAlphabet.getValues());
            maxValuesAlphabet.clear();
            maxValuesAlphabet.addAll(maxLengthValuesAlphabet.getValues());
        }

        
        allValues = new AlphabetValues(alphabet);
        minLengthValues = new AlphabetValues(minLengthValuesAlphabet);
        maxLengthValues = new AlphabetValues(maxLengthValuesAlphabet);
        minValues = new AlphabetValues(minValuesAlphabet);
        maxValues = new AlphabetValues(maxValuesAlphabet);

        allAlphabetValues.add(allValues);
        allAlphabetValues.add(minLengthValues);
        allAlphabetValues.add(maxLengthValues);
        allAlphabetValues.add(minValues);
        allAlphabetValues.add(maxValues);

        allValues.register(allAlphabetValues);
        minLengthValues.register(allAlphabetValues);
        maxLengthValues.register(allAlphabetValues);
        minValues.register(allAlphabetValues);
        maxValues.register(allAlphabetValues);

        reset();
    }

    public final void reset() {
        allValues.reset();
        minLengthValues.reset();
        maxLengthValues.reset();
        minValues.reset();
        maxValues.reset();
    }

    public AlphabetValues get(Values values) {
        switch (values) {
            case All:
                return allValues;
            case MIN:
                return minValues;
            case MAX:
                return maxValues;
            case MIN_LENGTH:
                return minLengthValues;
            case MAX_LENGTH:
                return maxLengthValues;
            default:
                return null;
        }
    }

}
