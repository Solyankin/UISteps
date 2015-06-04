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
package com.uisteps.core.browser;

import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
@Root
public class Page implements UIObject {

    private final Url url;
    private final Browser browser;
    private boolean isMock;

    public Page(Browser browser, UrlFactory urlFactory) {
        this.url = urlFactory.getUrlOf(this.getClass());
        this.browser = browser;
    }

    public Page(Browser browser, Url url) {
        this.url = url;
        this.browser = browser;
    }

    public Url getUrl() {
        return url;
    }

    protected boolean isMock() {
        return isMock;
    }

    protected void isMock(boolean isMock) {
        this.isMock = isMock;
    }

    @Override
    public boolean isDisplayed() {
        return executeScript("return document.readyState").equals("complete");
    }

    public Browser getBrowser() {
        return browser;
    }

    protected <T extends UIObject> T displayed(Class<T> uiObject) {
        return browser.displayed(uiObject);
    }

    protected <T extends UIObject> T displayed(T uiObject) {
        return browser.displayed(uiObject);
    }

    protected Object executeScript(String script) {
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

    protected void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        browser.waitUntil(condition, timeOutInSeconds);
    }

    protected void waitUntil(ExpectedCondition<Boolean> condition) {
        browser.waitUntil(condition);
    }

    public String getName() {
        return "page";
    }

    @Override
    public String toString() {
        return getName() + " by url <a href='" + this.getUrl() + "' target='blank'>" + this.getUrl() + "</a> with title " + this.getTitle();
    }

}