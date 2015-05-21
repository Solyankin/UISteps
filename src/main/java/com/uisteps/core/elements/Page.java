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
package com.uisteps.core.elements;

import com.uisteps.core.browsers.Browser;
import com.uisteps.core.browsers.Url;
import com.uisteps.core.browsers.UrlFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
public abstract class Page implements UIObject {

    private Url url;
    private final Url defaultUrl;
    private final Browser browser;

    public Page(Browser browser, UrlFactory urlFactory) {
        defaultUrl = urlFactory.getDefaultUrlOfPage(this.getClass());
        url = defaultUrl;
        this.browser = browser;
    }

    public Url getDefaultUrl() {
        return defaultUrl;
    }
    
    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
    
    public boolean isDisplayed() {
        return executeScript("return document.readyState").equals("complete");
    }

    public Browser getBrowser() {
        return browser;
    }

    protected <T extends UIObject> T onDisplayed(Class<T> uiObjectClass) {
        return browser.onDisplayed(uiObjectClass);
    }

    public Object executeScript(String script) {
        return browser.executeScript(script);
    }

    public String getTitle() {
        return browser.getCurrentTitle();
    }

    protected void switchToNextWindow() {
        browser.switchToNextWindow();
    }

    protected void switchToPreviousWindow() {
        browser.switchToPreviousWindow();
    }

    protected void switchToDefaultWindow() {
        browser.switchToDefaultWindow();
    }

    protected void switchToWindowByIndex(int index) {
        browser.switchToWindowByIndex(index);
    }

    public void refresh() {
        browser.refreshCurrentPage();
    }

    public void deleteCookies() {
        browser.deleteCookies();
    }

    protected void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        browser.waitUntil(condition, timeOutInSeconds);
    }

    protected void waitUntil(ExpectedCondition<Boolean> condition) {
        browser.waitUntil(condition);
    }
    
    
}
