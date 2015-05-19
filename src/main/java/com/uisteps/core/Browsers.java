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
package com.uisteps.core;

import org.openqa.selenium.WebDriver;

/**
 *
 * @author ASolyankin
 */
public abstract class Browsers {

    private final BrowserList browserList = new BrowserList();

    protected abstract StepLibraryFactory getBrowserFactory();

    protected abstract Class<? extends Browser> getBrowserClass();

    protected abstract WebDriver getDriver();

    protected abstract StepLibraryFactory getPageFactory();

    protected abstract Initializer getInitializer();

    private Browser instatiateBrowser() {
        Browser browser = getBrowserFactory().instatiate(getBrowserClass());
        browser.init(getDriver(), getPageFactory(), getInitializer());
        return browser;
    }

    public Browser openBrowser() {
        browserList.add(instatiateBrowser());
        return browserList.switchToNextBrowser();
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

    public Browser switchToCurrentBrowser() {
        return browserList.switchToCurrentBrowser();
    }
    
    public int count() {
        return this.browserList.size();
    }
}
