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
package com.uisteps.core.user.browser.pages;

import com.uisteps.core.name.Name;
import com.uisteps.core.name.Named;
import com.uisteps.core.then.Then;
import com.uisteps.core.user.browser.Browser;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
@Root
public class Page implements UIObject, Named {

    private Url url;
    private UrlFactory urlFactory;
    private Browser browser;
    private Name name;
    public static final String DEFAULT_NAME = "page";

    public Page(Browser browser, UrlFactory urlFactory, Name name) {
        this.urlFactory = urlFactory;
        this.browser = browser;
        this.name = name;
    }

    public Page(Browser browser, UrlFactory urlFactory) {
        this(browser, urlFactory, new Name(DEFAULT_NAME));
    }

    public Page(Browser browser, Url url, Name name) {
        this.url = url;
        this.browser = browser;
        this.name = name;
    }

    public Page(Browser browser, Url url) {
        this(browser, url, new Name(DEFAULT_NAME));
    }
    
    public Url getUrl() {

        if (url == null) {

            if (urlFactory == null) {
                throw new RuntimeException("Url and url factory are not set!");
            }

            url = urlFactory.getUrlOf(this.getClass());
        }

        return url;
    }

    @Override
    public boolean isDisplayed() {
        return executeScript("return document.readyState").equals("complete");
    }

    protected <T extends UIObject> Then<T> then(Class<T> uiObject) {
        return browser.then(uiObject);
    }

    protected <T> Then<T> then(T value) {
        return browser.then(value);
    }

    protected <T extends UIObject> T displayed(Class<T> uiObject) {
        return browser.displayed(uiObject);
    }

    protected <T extends UIObject> T displayed(T uiObject) {
        return browser.displayed(uiObject);
    }

    protected <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return browser.onDisplayed(uiObject);
    }
    
    public <T extends UIObject> T on(Class<T> uiObject) {
        return uiObject.cast(this);
    }

    protected <T extends UIObject> T onDisplayed(T uiObject) {
        return browser.onDisplayed(uiObject);
    }

    protected Browser inOpenedBrowser() {
        return browser;
    }

    protected Object executeScript(String script) {
        return browser.executeScript(script);
    }

    public String getTitle() {
        if(this.browser == null) {
            System.out.println("==================================================");
        } else {
            System.out.println("**************************************************");
        }
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

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Page setName(String name) {
        this.name.setValue(name);
        return this;
    }

    @Override
    public String toString() {
        return getName() + " by url <a href='" + this.getUrl() + "' target='blank'>" + this.getUrl() + "</a> with title " + this.getTitle();
    }

}
