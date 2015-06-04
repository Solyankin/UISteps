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
package com.uisteps.core.browser;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author ASolyankin
 */
public abstract class BrowserFactory {

    public <T extends Browser> T instatiate(Class<T> browser) {
        return instatiate(browser, getDefaultDriver());
    }
    
    public <T extends Browser> T instatiate(Class<T> browser, WebDriver withDriver) {
        T browserInstance = getInstanceOf(browser);
        browserInstance.setDriver(withDriver);
        return browserInstance;
    }
    
    public abstract WebDriver getDefaultDriver();
    
    protected abstract <T extends Browser> T getInstanceOf(Class<T> browser);
    
}
