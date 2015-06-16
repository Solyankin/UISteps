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

import com.uisteps.core.then.Then;
import com.uisteps.core.user.browser.Browser;
import com.uisteps.core.then.GetValueAction;
import org.openqa.selenium.support.ui.ExpectedCondition;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 *
 * @author ASolyankin
 */
public abstract class UIBlock extends HtmlElement implements UIObject {

    private final Browser browser;

    public UIBlock(Browser browser) {
        this.browser = browser;
    }

    @Override
    public void click() {
        browser.click(this);
    }

    public Object afterClick() {
        return browser.click(this);
    }
    
    public Object moveMouseOver() {
        return browser.moveMouseOver(this);
    }

    public Object clickOnPoint(int x, int y) {
        return browser.clickOnPoint(this, x, y);
    }

    protected <T> Then<T> then(Class<? extends UIObject> uiObject) {
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

    protected <T extends UIObject> T onDisplayed(T uiObject) {
        return browser.onDisplayed(uiObject);
    }
    
    protected Browser inOpenedBrowser() {
        return browser;
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

    protected void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        browser.waitUntil(condition, timeOutInSeconds);
    }

    protected void waitUntil(ExpectedCondition<Boolean> condition) {
        browser.waitUntil(condition);
    }

}
