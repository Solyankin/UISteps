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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASolyankin
 */
public class AlphabetFactory {

    private final static HashMap<String, Alphabet> map = new HashMap();

    public final static Alphabet EN = new Alphabet("a-z", "abcdefghijklmnopqrstuvwxyz");
    public final static Alphabet EN_UPPER_CASE = new Alphabet("A-Z", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public final static Alphabet RU = new Alphabet("а-я", "абвгдежзийклмнопрстуфхцчшщъыьэюя");
    public final static Alphabet RU_UPPER_CASE = new Alphabet("А-Я", "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");
    public final static Alphabet SPECIAL_SIMBOLS = new Alphabet("##", "!№;%:?*()_+@#$^&-=,./|[]{}'\"\\");
    public final static Alphabet INTEGERS = new Alphabet("0-9", "0123456789");
    public final static Alphabet ALL;

    static {

        ArrayList<String> allValues = new ArrayList();
        for (Alphabet alpabet : map.values()) {
            allValues.addAll(alpabet.getValues());
        }
        ALL = new Alphabet("**", allValues);
    }

    public AlphabetFactory() {
        put(EN);
        put(EN_UPPER_CASE);
        put(RU);
        put(RU_UPPER_CASE);
        put(SPECIAL_SIMBOLS);
        put(INTEGERS);
        put(ALL);

    }

    public final void put(Alphabet alpabet) {
        map.put(alpabet.getMask(), alpabet);
    }

    public Alphabet getByMask(String mask) throws IllegalArgumentError {

        ArrayList<String> values = new ArrayList();

        for (String subMask : mask.split("\\|\\|")) {

            if (subMask == null || subMask.isEmpty()) {
                throw new IllegalArgumentError("Mask must contain min one char");
            }

            if (map.containsKey(subMask)) {
                values.addAll(map.get(subMask).getValues());
                continue;
            }

            Pattern pattern = Pattern.compile("(.*?)-(.*?)");
            Matcher matcher = pattern.matcher(subMask);

            if (matcher.matches()) {

                String firstGroup = matcher.group(1);
                String secondGroup = matcher.group(2);

                if (!firstGroup.isEmpty() && !secondGroup.isEmpty()) {

                    try {
                        addDecimals(firstGroup, secondGroup, values);
                    } catch (NumberFormatException ex) {

                        boolean alphabetIsFound = false;

                        for (Alphabet alphabetCandidate : map.values()) {

                            List<String> alphabet = alphabetCandidate.getValues();

                            if (alphabetCandidate.hasRange() && alphabet.contains(firstGroup) && alphabet.contains(secondGroup)) {

                                int beginIndex = alphabet.indexOf(firstGroup);
                                int endIndex = alphabet.indexOf(secondGroup);

                                if (beginIndex < endIndex) {
                                    for (int i = beginIndex; i <= endIndex; i++) {
                                        values.add(alphabet.get(i));
                                    }
                                    alphabetIsFound = true;
                                    break;
                                } else if (beginIndex > endIndex) {
                                    for (int i = beginIndex; i >= endIndex; i--) {
                                        values.add(alphabet.get(i));
                                    }
                                    alphabetIsFound = true;
                                    break;
                                }
                            }
                        }

                        if (!alphabetIsFound) {
                            values.add(subMask);
                        }
                    }
                } else {
                    values.add(subMask);
                }
            } else {
                values.add(subMask);
            }
        }
        return new Alphabet(mask, values);
    }

    private void addDecimals(String firstGroup, String secondGroup, ArrayList<String> values) throws NumberFormatException {

        try {
            ValueFormat format = new ValueFormat(firstGroup, secondGroup);
            BigDecimal beginValue = format.getBeginValue();
            BigDecimal endValue = format.getEndValue();
            if (beginValue.compareTo(endValue) < 1) {
                while (beginValue.compareTo(endValue) < 1) {
                    beginValue = addDecimalValue(beginValue, format, values);
                }
            } else {
                while (beginValue.compareTo(endValue) > -1) {
                    beginValue = addDecimalValue(beginValue, format, values);
                }
            }
        } catch (NumberFormatException ex) {
            throw ex;
        }
    }

    private BigDecimal addDecimalValue(BigDecimal decimalValue, ValueFormat format, ArrayList<String> values) {
        decimalValue = decimalValue.setScale(format.getScale(), RoundingMode.UP);
        String value = String.valueOf(decimalValue);

        if (format.getScale() > 0) {
            int scaleDisparity = format.getScale() - format.getScaleOf(value);

            for (int i = 0; i < scaleDisparity; i++) {
                value += "0";
            }
        }
        values.add(value);
        return format.increase(decimalValue);
    }

    private class ValueFormat {

        double precision = 1;
        int scale;

        BigDecimal beginValue;
        BigDecimal endValue;

        ValueFormat(String beginValue, String endValue) throws NumberFormatException {

            try {
                this.beginValue = BigDecimal.valueOf(Integer.parseInt(beginValue));
                this.endValue = BigDecimal.valueOf(Integer.parseInt(endValue));
                getPrecision(this.beginValue, this.endValue);
            } catch (NumberFormatException ex) {

                try {
                    this.beginValue = BigDecimal.valueOf(Double.parseDouble(beginValue));
                    this.endValue = BigDecimal.valueOf(Double.parseDouble(endValue));
                    getPrecision(beginValue, endValue);
                } catch (NumberFormatException e) {
                    throw e;
                }
            }
        }

        BigDecimal increase(BigDecimal value) {
            return value.add(BigDecimal.valueOf(precision));
        }

        private void getPrecision(BigDecimal beginValue, BigDecimal endValue) {
            if (beginValue.compareTo(endValue) == 1) {
                precision *= -1;
            }
        }

        private void getPrecision(String beginValue, String endValue) {
            int beginValueScale = getScaleOf(beginValue);
            int endValueScale = getScaleOf(endValue);

            if (beginValueScale >= endValueScale) {
                precision = getPrecision(beginValueScale);
                scale = beginValueScale;

            } else {
                precision = getPrecision(endValueScale);
                scale = endValueScale;
            }

            getPrecision(this.beginValue, this.endValue);
        }

        int getScaleOf(String value) {
            return value.substring(value.indexOf(".") + 1).length();
        }

        private double getPrecision(int scale) {
            return 1.0 / Math.pow(10, scale);
        }

        public double getPrecision() {
            return precision;
        }

        public int getScale() {
            return scale;
        }

        public BigDecimal getBeginValue() {
            return beginValue;
        }

        public BigDecimal getEndValue() {
            return endValue;
        }

    }

}
