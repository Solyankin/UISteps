package com.uisteps.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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
/**
 *
 * @author ASolyankin
 */
public class RandomAlphabit {

    public static final String EN_ALPHABIT = "qwertyuiopasdfghjklzxcvbnm";
    public static final String RU_ALPHABIT = "йцукенгшщзфывапролдхъячсмитьбю";
    public static final String SYMBOLS = "!№;%:?*()_+@#$^&-=,./|[]{} \"\\";
    public static final String FIGURES = "1234567890";

    public static String get(boolean en, boolean ru, boolean simbols, boolean figures, int simbolsCount) {
        return get(en, ru, simbols, figures, simbolsCount, 1)[0];
    }

    public static String[] get(boolean en, boolean ru, boolean simbols, boolean figures, int simbolsCount, int wordsCount) {
        return get(en, ru, simbols, figures, false, simbolsCount, wordsCount);
    }

    public static String get(boolean en, boolean ru, boolean simbols, boolean figures, boolean uppercase, int simbolsCount) {
        return get(en, ru, simbols, figures, uppercase, simbolsCount, 1)[0];
    }

    public static String[] get(boolean en, boolean ru, boolean simbols, boolean figures, boolean uppercase, int simbolsCount, int wordsCount) {
        return get(en, ru, simbols, figures, uppercase, false, simbolsCount, wordsCount);
    }

    public static String get(boolean en, boolean ru, boolean simbols, boolean figures, boolean uppercase, boolean uppercaseOnly, int simbolsCount) {
        return get(en, ru, simbols, figures, uppercase, uppercaseOnly, simbolsCount, 1)[0];
    }

    public static String[] get(boolean en, boolean ru, boolean simbols, boolean figures, boolean uppercase, boolean uppercaseOnly, int simbolsCount, int wordsCount) {
        ArrayList<Alphabit> alphabits = new ArrayList();
        alphabits.add(new Alphabit(EN_ALPHABIT, en, uppercase, uppercaseOnly));
        alphabits.add(new Alphabit(RU_ALPHABIT, ru, uppercase, uppercaseOnly));
        alphabits.add(new Alphabit(SYMBOLS, simbols));
        alphabits.add(new Alphabit(FIGURES, figures));

        String[] words = new String[wordsCount];
        Iterator<Alphabit> iterator = alphabits.iterator();

        for (int i = 0; i < wordsCount; i++) {
            String word = "";
            while (word.length() < simbolsCount) {
                if (iterator.hasNext()) {
                    word += iterator.next().getChar();
                } else {
                    iterator = alphabits.iterator();
                }
            }
            words[i] = word;
        }
        return words;
    }

    private static class Alphabit {

        private String value;
        private final String alphabit;
        private final boolean flag;
        private boolean uppercase;
        private boolean uppercaseOnly;
        private boolean uppercaseFlag;

        private Alphabit(String alphabit, boolean flag) {
            value = alphabit;
            this.alphabit = alphabit;
            this.flag = flag;
        }

        private Alphabit(String alphavit, boolean flag, boolean uppercase) {
            this(alphavit, flag);
            this.uppercase = uppercase;
        }

        private Alphabit(String alphavit, boolean flag, boolean uppercase, boolean uppercaseOnly) {
            this(alphavit, flag, uppercase);
            this.uppercaseOnly = uppercaseOnly;
        }

        private String getChar() {
            String c = "";
            if (flag) {
                c = getRandomChar(value);
                value = value.replace(c, "");
                if (value.length() < 1) {
                    value = alphabit;
                }

                if (uppercase) {
                    if (uppercaseOnly) {
                        return c.toUpperCase();
                    } else {
                        uppercaseFlag = !uppercaseFlag;
                        if (uppercaseFlag) {
                            return c.toUpperCase();
                        }
                    }
                }
            }
            return c;
        }

        private String getRandomChar(String alphabit) {
            Random rand = new Random();
            int count = alphabit.length() - 1;
            if (count < 1) {
                count = 1;
            }
            char c = alphabit.charAt(rand.nextInt(count));
            return String.valueOf(c);
        }

    }
}
