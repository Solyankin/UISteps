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
package com.uisteps.thucydides.browsers;

import com.uisteps.thucydides.utils.ThucydidesUtils;
import com.uisteps.core.browsers.Browser;
import com.uisteps.core.browsers.Initializer;
import com.uisteps.core.browsers.StepLibraryFactory;
import com.uisteps.core.elements.Page;
import com.uisteps.core.elements.UIObject;
import com.uisteps.core.browsers.Url;
import com.uisteps.thucydides.elements.MockPage;
import java.net.MalformedURLException;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesBrowser extends Browser {

    public ThucydidesBrowser() {
        super(ThucydidesUtils.getImplementTimeout(), ThucydidesUtils.getNewDriver(), new ThucydidesStepLibraryFactory(), new ThucydidesInitializer());
    }

    public ThucydidesBrowser(long timeOutInSeconds, WebDriver driver, StepLibraryFactory pageFactory, Initializer initializer) {
        super(timeOutInSeconds, driver, pageFactory, initializer);
    }

    @Step
    @Override
    public void enterInto(WrapsElement input, CharSequence... text) {
        super.enterInto(input, text);
    }

    @Step
    @Override
    public void clear(WrapsElement input) {
        super.clear(input);
    }

    @Step
    @Override
    public void typeInto(WrapsElement input, CharSequence... keys) {
        super.typeInto(input, keys);
    }

    @Step
    @Override
    public void clickOnPoint(WrapsElement element, int x, int y) {
        super.clickOnPoint(element, x, y);
    }

    @Step
    @Override
    public void click(WrapsElement element) {
        super.click(element);
    }

    @Step
    @Override
    public void deleteCookies() {
        super.deleteCookies();
    }

    @Step
    @Override
    public void refreshCurrentPage() {
        super.refreshCurrentPage();
    }

    @Override
    public <T extends Page> T open(Class<T> rootClass, String pageName) {
        return open(super.open(rootClass, pageName));
    }

    @Override
    public <T extends Page> T open(Class<T> pageClass) {
        return open(super.open(pageClass));
    }

    @Override
    public <T extends UIObject> T onDisplayed(Class<T> uiObjectClass) {
        return onDisplayed(element(uiObjectClass));
    }

    public <T extends UIObject> T element(Class<T> uiObjectClass) {
        return super.onDisplayed(uiObjectClass);
    }    
    
    @Step
    public <T extends Page> T open(T page) {
        return page;
    }

    @Step
    public <T extends UIObject> T onDisplayed(T uiObject) {
        return uiObject;
    }

    @Override
    public void openUrl(String url) {
        try {
            super.openUrl(url);
            open(new MockPage(url));
        } catch (MalformedURLException ex) {
            throw new AssertionError("Incorrect url " + url);
        }
    }

    @Override
    public void openUrl(Url url) {
        openUrl(url.toString());
    }

    @Step
    @Override
    public void openNewWindow() {
        super.openNewWindow();
    }

    @Step
    @Override
    public void switchToWindowByIndex(int index) {
        super.switchToWindowByIndex(index);
    }

    @Step
    @Override
    public void switchToDefaultWindow() {
        super.switchToDefaultWindow();
    }

    @Step
    @Override
    public void switchToPreviousWindow() {
        super.switchToPreviousWindow();
    }

    @Step
    @Override
    public void switchToNextWindow() {
        super.switchToNextWindow();
    }

    
    @Override
    public void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        super.waitUntil(condition, timeOutInSeconds);
    }

   
    @Override
    public void waitUntil(ExpectedCondition<Boolean> condition) {
        super.waitUntil(condition, ThucydidesUtils.getImplementTimeout());
    }

}
