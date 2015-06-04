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
package com.uisteps.thucydides.run;

import com.uisteps.thucydides.user.ThucydidesUser;
import com.uisteps.thucydides.ThucydidesStepListener;
import com.uisteps.thucydides.ThucydidesUtils;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.runner.RunWith;

/**
 *
 * @author ASolyankin
 */
@RunWith(ThucydidesRunner.class)
public class UserTest extends Test {

    @Steps
    protected ThucydidesUser user;

    public UserTest() {
        ThucydidesUtils.registerListener(new ThucydidesStepListener());
    }
}
