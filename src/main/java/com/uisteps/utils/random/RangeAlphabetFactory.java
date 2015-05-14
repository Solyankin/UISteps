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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASolyankin
 */
public class RangeAlphabetFactory extends AlphabetFactory {

    @Override
    public Alphabet getByMask(String mask) throws IllegalArgumentError {

        int minRepetitions = 1;
        int maxRepetitions;

        String regexp = "(\\(.*?\\)|\\[.*?\\])(?:(\\d+)(?:-(\\d+))?)?";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(mask);

        if (matcher.matches()) {

            String maskGroup = matcher.group(1);
            String minRepetitionsGroup = matcher.group(2);
            String maxRepetitionsGroup = matcher.group(3);

            if (minRepetitionsGroup != null) {
                minRepetitions = Integer.parseInt(minRepetitionsGroup);
            }

            if (maxRepetitionsGroup != null) {
                maxRepetitions = Integer.parseInt(maxRepetitionsGroup);

                if (minRepetitions >= maxRepetitions) {
                    throw new IllegalArgumentError("In mask " + mask + " max repetitions must be larger than min repetitions. min repetitions = " + minRepetitions + "; max repetitions = " + maxRepetitions);
                }
            } else {
                maxRepetitions = minRepetitions;
            }

            Alphabet alphabet = new Alphabet();
            AlphabetValues values = new AlphabetValues(super.getByMask(maskGroup.substring(1, maskGroup.length() - 1)));

            boolean hasNext = true;

            for (int repetition = minRepetitions; repetition <= maxRepetitions; repetition++) {
                StringBuilder value = new StringBuilder();

                for (int i = 0; i < repetition; i++) {
                    
                    if (!values.hasNext()) {
                        hasNext = false;
                        values.reset();
                    }
                    
                    value.append(values.getNextRandom());
                    
                    if (!values.hasNext()) {
                        hasNext = false;
                    }
                }

                alphabet.add(value.toString());

                    if (hasNext) {
                        if(repetition != 0) {
                            repetition--;
                        }
                    } else {
                        hasNext = true;
                        values.reset();
                    }
            }
            return alphabet;
        } else {
            throw new IllegalArgumentError("Mask " + mask + " must match regexp " + regexp);
        }

    }

}
