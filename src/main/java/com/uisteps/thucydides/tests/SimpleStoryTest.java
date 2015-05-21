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
package com.uisteps.thucydides.tests;

import net.thucydides.core.annotations.Steps;
import net.thucydides.jbehave.ThucydidesJUnitStory;
import com.uisteps.core.verifications.Verifications.That;
import com.uisteps.thucydides.browsers.Verifications;

/**
 *
 * @author ASolyankin
 */
public class SimpleStoryTest extends ThucydidesJUnitStory {

    @Steps
    Verifications verifications;

    public That verify() {
        return verifications.verify();
    }
}
