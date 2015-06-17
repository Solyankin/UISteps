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
package com.uisteps.core.user.browser;

import com.uisteps.core.then.Then;
import com.uisteps.core.user.browser.pages.UIObjectInitializer;
import com.uisteps.core.user.browser.pages.MockPage;
import com.uisteps.core.user.browser.pages.Page;
import com.uisteps.core.user.browser.pages.UIObject;
import com.uisteps.core.user.browser.pages.UIObjectFactory;
import com.uisteps.core.user.browser.pages.Url;
import com.uisteps.core.then.GetValueAction;
import com.uisteps.core.then.OnDisplayedAction;
import java.net.MalformedURLException;
import java.util.Arrays;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author ASolyankin
 */
public class Browser {

    private WebDriver driver;
    private final UIObjectFactory uiObjectFactory;
    private UIObjectInitializer initializer;
    private final long timeOutInSeconds;
    private final WindowList windowList;

    public Browser(long timeOutInSeconds, WebDriver driver, UIObjectFactory uiObjectFactory, UIObjectInitializer initializer) {
        this.timeOutInSeconds = timeOutInSeconds;
        windowList = new WindowList(this, timeOutInSeconds);
        this.driver = driver;
        this.uiObjectFactory = uiObjectFactory;
        this.initializer = initializer;
    }

    public Browser(long timeOutInSeconds, WebDriver driver, UIObjectFactory pageFactory) {
        this(timeOutInSeconds, driver, pageFactory, null);
        initializer = new UIObjectInitializer(this);
    }

    protected void setInitializer(UIObjectInitializer initializer) {
        this.initializer = initializer;
    }

    public Page openUrl(String url) {
        try {
            return open(new Url(url));
        } catch (MalformedURLException ex) {
            throw new AssertionError("Cannot open url " + url + "\nCause:" + ex);
        }
    }

    public Page open(Url url) {
        return open(new Page(this, url));
    }

    public <T extends Page> T open(Class<T> page) {
        return displayed(open(new MockPage<T>(page, this)));
    }

    public <T extends Page> T open(T page) {
        return displayed(open(new MockPage<T>(page, this)));
    }

    protected <T extends Page> T open(MockPage<T> mock) {
        return mock.getPage();
    }

    public <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return displayed(uiObject);
    }

    public <T extends UIObject> T onDisplayed(T uiObject) {
        return displayed(uiObject);
    }
    
    public <T extends UIObject> T displayed(Class<T> uiObject) {
        return displayed(uiObjectFactory.instatiate(uiObject));
    }

    public <T extends UIObject> T displayed(T uiObject) {
        initializer.initialize(uiObject);
        return uiObject;
    }
    
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getCurrentTitle() {
        return getDriver().getTitle();
    }

    public void openNewWindow() {
        executeScript("window.open()");
        windowList.switchToNextWindow();
    }

    public void switchToNextWindow() {
        windowList.switchToNextWindow();
    }

    public void switchToPreviousWindow() {
        windowList.switchToPreviousWindow();
    }

    public void switchToDefaultWindow() {
        windowList.switchToDefaultWindow();
    }

    public void switchToWindowByIndex(int index) {
        windowList.switchToWindowByIndex(index);
    }

    public void refreshCurrentPage() {
        getDriver().navigate().refresh();
    }

    public void deleteCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public Object click(WrapsElement element) {

        try {
            WebElement webElement = element.getWrappedElement();
            String attrTarget = webElement.getAttribute("target");

            webElement.click();

            if (attrTarget != null && !attrTarget.equals("") && !attrTarget.equals("_self")) {
                switchToNextWindow();
            }
        } catch (Exception ex) {
            throw new AssertionError("Cannot click " + element + "! " + ex);
        }
        return null;
    }   
    
    public Object clickOnPoint(WrapsElement element, int x, int y) {

        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element.getWrappedElement(), x, y).click().build().perform();

        } catch (Exception ex) {
            throw new AssertionError("Cannot click " + element + "on point (" + x + "; " + y + ") !\n" + ex);
        }
        return null;
    }

    public Object moveMouseOver(WrapsElement element) {

        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element.getWrappedElement()).build().perform();
        } catch (Exception ex) {
            throw new AssertionError("Cannot move mouse over " + element + "!\n" + ex);
        }
        return null;
    }

    public Object typeInto(WrapsElement input, CharSequence... keys) {

        try {
            input.getWrappedElement().sendKeys(keys);
        } catch (Exception ex) {
            throw new AssertionError("Cannot type " + Arrays.toString(keys) + " into " + input + "!\n" + ex);
        }
        return null;
    }

    public Object clear(WrapsElement input) {

        try {
            input.getWrappedElement().clear();
        } catch (Exception ex) {
            throw new AssertionError("Cannot clear " + input + "!\n" + ex);
        }
        return null;
    }

    public Object enterInto(WrapsElement input, CharSequence... text) {

        try {
            input.getWrappedElement().clear();
            input.getWrappedElement().sendKeys(text);
        } catch (Exception ex) {
            throw new AssertionError("Cannot enter " + Arrays.toString(text) + " into " + input + "!\n" + ex);
        }
        return null;
    }

    public String getTextFrom(WrapsElement input) {

        try {
            return input.getWrappedElement().getText();
        } catch (Exception ex) {
            throw new AssertionError("Cannot clear " + input + "\n" + ex);
        }
    }

    public void waitUntil(ExpectedCondition<Boolean> condition, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeOutInSeconds);
        wait.until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return condition.apply(driver);
            }
        });
    }

    public void waitUntil(ExpectedCondition<Boolean> condition) {
        this.waitUntil(condition, timeOutInSeconds);
        
    }

    public <T> Then<T> then(Class<? extends UIObject> uiObject) {
        return new Then(new OnDisplayedAction<>(this, uiObject));
    }
    
    public <T> Then<T> then(T value) {
        return new Then(new GetValueAction<>(value));
    }
    
    public UIObjectInitializer getUIObjectInitializer() {
        return initializer;
    }

    public UIObjectFactory getUIObjectFactory() {
        return uiObjectFactory;
    }

    public long getTimeOutInSeconds() {
        return timeOutInSeconds;
    }

    public WindowList getWindowList() {
        return windowList;
    }

    public Object executeScript(String script) {
        return ((JavascriptExecutor) getDriver()).executeScript(script);
    }

    @Override
    public String toString() {
        return executeScript("return navigator.userAgent;").toString();
    }
}