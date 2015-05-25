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
package com.uisteps.core.browsers;

/**
 *
 * @author ASolyankin
 */
public class Browsers {

    private final BrowserList browserList = new BrowserList();
    private final BrowserFactory browserFactory;
    private final Class<? extends Browser> browserClass;

    public Browsers(BrowserFactory browserFactory, Class<? extends Browser> browserClass) {
        this.browserFactory = browserFactory;
        this.browserClass = browserClass;
    }

    public Browser openNewBrowser() {
        Browser browser = browserFactory.instatiate(browserClass);
        return browserList.add(browser);
    }

    public Browser switchToNextBrowser() {
        return browserList.switchToNextBrowser();
    }

    public Browser switchToPreviousBrowser() {
        return browserList.switchToPreviousBrowser();
    }

    public Browser switchToDefaultBrowser() {
        return browserList.switchToFirstBrowser();
    }

    public Browser switchToBrowserByIndex(int index) throws NoBrowserException {
        return browserList.switchToBrowserByIndex(index);
    }

    public Browser switchToLastBrowser() {
        return browserList.switchToLastBrowser();
    }

    public int count() {
        return this.browserList.size();
    }
}
