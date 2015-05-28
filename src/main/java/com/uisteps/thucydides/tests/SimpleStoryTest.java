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
package com.uisteps.thucydides.tests;

import com.uisteps.core.browsers.NoBrowserException;
import com.uisteps.core.elements.Page;
import com.uisteps.core.elements.UIObject;
import net.thucydides.core.annotations.Steps;
import net.thucydides.jbehave.ThucydidesJUnitStory;
import com.uisteps.core.verifications.Verifications.That;
import com.uisteps.thucydides.browsers.ThucydidesBrowser;
import com.uisteps.thucydides.browsers.ThucydidesBrowsers;
import com.uisteps.thucydides.browsers.ThucydidesStepListener;
import com.uisteps.thucydides.browsers.Verifications;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
public class SimpleStoryTest extends ThucydidesJUnitStory {

    @Steps
    ThucydidesBrowsers browsers;
    @Steps
    Verifications verifications;

    public SimpleStoryTest() {
        ThucydidesUtils.registerListener(new ThucydidesStepListener());
    }

    public com.uisteps.core.verifications.Verifications.That verify() {
        return verifications.verify();
    }

    private ThucydidesBrowser getBrowser() {
        return ThucydidesUtils.getCurrentBrowser();
    }

    public <T extends Page> T open(Class<T> pageClass) {
        return getBrowser().open(pageClass);
    }

    public <T extends Page> T open(Class<T> rootClass, String pageName) {
        return getBrowser().open(rootClass, pageName);
    }

    public <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return getBrowser().onDisplayed(uiObject);
    }

    public void openUrl(String url) {
        getBrowser().openUrl(url);
    }

    public String getCurrentUrl() {
        return getBrowser().getCurrentUrl();
    }

    public String getCurrentTitle() {
        return getBrowser().getCurrentTitle();
    }

    public void switchToNextWindow() {
        getBrowser().switchToNextWindow();
    }

    public void switchToPreviousWindow() {
        getBrowser().switchToPreviousWindow();
    }

    public void switchToDefaultWindow() {
        getBrowser().switchToWindowByIndex(0);
    }

    public void switchToWindowByIndex(int index) {
        getBrowser().switchToWindowByIndex(index);
    }

    public void refreshCurrentPage() {
        getBrowser().refreshCurrentPage();
    }

    public void waitUntil(ExpectedCondition<Boolean> condition) {
        getBrowser().waitUntil(condition);
    }

    public Object executeScript(String script) {
        return getBrowser().executeScript(script);
    }

    public boolean isDisplayed(WrapsElement element) {
        return element.getWrappedElement().isDisplayed();
    }

    public boolean isEnabled(WrapsElement element) {
        return element.getWrappedElement().isEnabled();
    }

    public boolean isSelected(WrapsElement element) {
        return element.getWrappedElement().isSelected();
    }

    public void openNewBrowser() {
        browsers.openNewBrowser();
    }

    public void openNewWindow() {
        getBrowser().openNewWindow();
    }

    public void switchToNextBrowser() {
        browsers.switchToNextBrowser();
    }

    public void switchToPreviousBrowser() {
        browsers.switchToPreviousBrowser();
    }

    public void switchToDefaultBrowser() {
        browsers.switchToDefaultBrowser();
    }

    public void switchToBrowserByIndex(int index) throws NoBrowserException {
        browsers.switchToBrowserByIndex(index);
    }
}
