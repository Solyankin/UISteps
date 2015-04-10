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

import java.util.Random;

/**
 *
 * @author ASolyankin
 */
public class CharRandomizer {

    private String possibleValues;
    private String alphabit;
    private In in = In.LOWER_CASE;
    private boolean inUpperCase;
    private int simbolsCount;

    public CharRandomizer(String alphabit) {
        this(alphabit, 0);
    }

    public CharRandomizer(String alphabit, int simbolsCount) {
        if (alphabit != null) {
            this.alphabit = alphabit;
        }
        this.simbolsCount = simbolsCount;
    }
    
    public CharRandomizer(String alphabit, In in) {
        this(alphabit, in , 0);
    }
    
    public CharRandomizer(String alphabit, In in, int simbolsCount) {
        this(alphabit, simbolsCount);
        if (in != null) {
            this.in = in;
        }
        this.simbolsCount = simbolsCount;
    }

    
    public String getRandomCharWithoutRepeation() {
        return getRandomChar(true);
    }

    public String getRandomStringWithoutRepeation() {
        return getRandomString(true);
    }
    
    
    public String getRandomChar() {
        return getRandomChar(false);
    }

    public String getRandomString() {
        return getRandomString(false);
    }
    
    private String getRandomChar(boolean withoutRepeation) {
        String randomChar = getRandom();
        
        
        if(withoutRepeation) {
            possibleValues = possibleValues.replace(randomChar, "");

            if (possibleValues.length() == 0) {
                reset();
            }
        }
        
        inUpperCase = !inUpperCase && in.upperCase() && in.onlyThisCase();

        if (inUpperCase) {
            return randomChar.toUpperCase();
        } else {
            return randomChar;
        }
    }

    String getRandomString(boolean withoutRepeation) {
        String word = "";
        for(int i = 0; i < simbolsCount; i++) {
            word += getRandomChar(withoutRepeation);
        }
        return word;
    }
    
    public void reset() {
        possibleValues = alphabit;
    }

    private String getRandom() {
        Random rand = new Random();
        int count = possibleValues.length() - 1;
        if (count < 1) {
            count = 1;
        }
        char c = possibleValues.charAt(rand.nextInt(count));
        return String.valueOf(c);
    }

}
