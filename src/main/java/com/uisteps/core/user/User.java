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
package com.uisteps.core.user;

import com.uisteps.core.user.browser.BrowserList;
import com.uisteps.core.user.browser.NoBrowserException;
import com.uisteps.core.user.browser.BrowserFactory;
import com.uisteps.core.user.browser.Browser;
import com.uisteps.core.user.browser.pages.Page;
import com.uisteps.core.user.browser.pages.UIObject;
import com.uisteps.core.user.browser.pages.Url;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
public class User {

    private final BrowserList browserList = new BrowserList();
    private final BrowserFactory browserFactory;
    private Storage storage;
    private String name = "user";

    public User(BrowserFactory browserFactory, Storage storage) {
        this.browserFactory = browserFactory;
        this.storage = storage;
    }

    public Browser inOpenedBrowser() {

        if (browserList.isEmpty()) {
            openNewBrowser();
        }
        return browserList.getCurrentBrowser();
    }

    public Browser openNewBrowser(WebDriver withDriver) {
        return in(browserFactory.getBrowser(withDriver));
    }

    public Browser openNewBrowser() {
        return in(browserFactory.getBrowser());
    }

    public Browser in(Browser browser) {
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
        return browserList.size();
    }

    public Page openUrl(String url) {
        return inOpenedBrowser().openUrl(url);
    }

    public Page open(Url url) {
        return inOpenedBrowser().open(url);
    }

    public <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return inOpenedBrowser().onDisplayed(uiObject);
    }

    public <T extends UIObject> T onDisplayed(T uiObject) {
        return inOpenedBrowser().onDisplayed(uiObject);
    }

    public <T extends Page> T open(Class<T> page) {
        return inOpenedBrowser().open(page);
    }

    public <T extends Page> T open(T page) {
        return inOpenedBrowser().open(page);
    }

    public void openNewWindow() {
        inOpenedBrowser().openNewWindow();
    }

    public void switchToNextWindow() {
        inOpenedBrowser().switchToNextWindow();
    }

    public void switchToPreviousWindow() {
        inOpenedBrowser().switchToPreviousWindow();
    }

    public void switchToDefaultWindow() {
        inOpenedBrowser().switchToDefaultWindow();
    }

    public void switchToWindowByIndex(int index) {
        inOpenedBrowser().switchToWindowByIndex(index);
    }

    public void refreshCurrentPage() {
        inOpenedBrowser().refreshCurrentPage();
    }

    public void deleteCookies() {
        inOpenedBrowser().deleteCookies();
    }

    public void click(WrapsElement element) {
        inOpenedBrowser().click(element);
    }

    public void clickOnPoint(WrapsElement element, int x, int y) {
        inOpenedBrowser().clickOnPoint(element, x, y);
    }

    public void moveMouseOver(WrapsElement element) {
        inOpenedBrowser().moveMouseOver(element);
    }

    public void typeInto(WrapsElement input, CharSequence... keys) {
        inOpenedBrowser().typeInto(input, keys);
    }

    public void clear(WrapsElement input) {
        inOpenedBrowser().clear(input);
    }

    public void enterInto(WrapsElement input, CharSequence... text) {
        inOpenedBrowser().enterInto(input, text);
    }

    public void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        inOpenedBrowser().waitUntil(condition, timeOutInSeconds);
    }

    public void waitUntil(ExpectedCondition<Boolean> condition) {
        inOpenedBrowser().waitUntil(condition);
    }

    public Object executeScript(String script) {
        return inOpenedBrowser().executeScript(script);
    }

    public boolean see(UIObject uiObject) {
        return uiObject.isDisplayed();
    }

    public boolean see(Class<? extends UIObject> uiObject) {
        return see(inOpenedBrowser().displayed(uiObject));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void remember(String key, Object value) {
        storage.remember(key, value);
    }

    public <T> T remembered(String key, Class<T> type) {
        return storage.remembered(key, type);
    }

    public Object remembered(String key) {
        return storage.remembered(key);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

}
