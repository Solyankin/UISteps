/*
 * Copyright 2014 ASolyankin.
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
package com.uisteps.thucydides.user.browser.pages;

import com.uisteps.core.user.browser.pages.UIObjectFactory;
import com.uisteps.core.user.browser.pages.UIObject;
import com.uisteps.thucydides.ThucydidesUtils;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesUIObjectFactory implements UIObjectFactory {

    @Override
    public <T extends UIObject> T instatiate(Class<T> uiObject) {
        return ThucydidesUtils.getNewStepLibrary(uiObject);
    }

}
