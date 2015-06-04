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
package com.uisteps.thucydides.browser;

import com.uisteps.core.browser.Browser;
import com.uisteps.core.browser.BrowserFactory;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesBrowserFactory extends BrowserFactory {

    @Override
    public WebDriver getDefaultDriver() {
        return ThucydidesUtils.getNewDriver();
    }

    @Override
    protected <T extends Browser> T getInstanceOf(Class<T> browser) {
        return ThucydidesUtils.getNewStepLibrary(browser);
    }

}
