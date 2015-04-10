package com.uisteps.utils.random;

import java.util.Iterator;
import java.util.List;

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
public class StringRandomizer {

    public static String get(int simbolsCount, List<CharRandomizer> randomizers) {
        return get(simbolsCount, 1, randomizers)[0];
    }

    public static String[] get(int simbolsCount, int wordsCount, List<CharRandomizer> randomizers) {

        String[] words = new String[wordsCount];
        Iterator<CharRandomizer> iterator = randomizers.iterator();

        for (int i = 0; i < wordsCount; i++) {
            String word = "";
            while (word.length() < simbolsCount) {
                if (iterator.hasNext()) {
                    word += iterator.next().getRandomString();
                } else {
                    iterator = randomizers.iterator();
                }
            }
            words[i] = word;
        }
        return words;
    }

}
