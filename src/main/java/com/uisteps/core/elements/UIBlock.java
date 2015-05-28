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
        browser.click(this);
        return null;
    }
    
    public Object moveMouseOver() {
        browser.moveMouseOver(this);
        return null;
    }

    public Object clickOnPoint(int x, int y) {
        browser.clickOnPoint(this, x, y);
        return null;
    }

    protected <T extends UIObject> T onDisplayed(Class<T> uiObjectClass) {
        return browser.onDisplayed(uiObjectClass);
    }

    protected Object executeScript(String script) {
        return browser.executeScript(script);
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
