
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

    private final WebDriver driver;
    private final PageFactory stepLibraryFactory;
    private final Initializer initializer;
    private final long timeOutInSeconds;
    private final WindowList windowList;

    public Browser(long timeOutInSeconds, WebDriver driver, PageFactory pageFactory) {
        this(timeOutInSeconds, driver, pageFactory, new Initializer(driver, timeOutInSeconds));
    }

    public Browser(long timeOutInSeconds, WebDriver driver, PageFactory pageFactory, Initializer initializer) {
        this.timeOutInSeconds = timeOutInSeconds;
        windowList = new WindowList(this, timeOutInSeconds);
        this.driver = driver;
        this.stepLibraryFactory = pageFactory;
        this.initializer = initializer;
    }

    public Page openUrl(String url) {

        try {
            return Browser.this.open(new Url(url));
        } catch (MalformedURLException ex) {
            throw new AssertionError("Cannot open url " + url + "\nCause:" + ex);
        }
    }

    public Page open(Url url) {
        return open(new Page(this, url));
    }

    public <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return displayed(uiObject);
    }

    public <T extends UIObject> T onDisplayed(T uiObject) {
        return displayed(uiObject);
    }

    protected <T extends UIObject> T displayed(Class<T> uiObject) {
        return displayed(stepLibraryFactory.instatiate(uiObject));
    }

    protected <T extends UIObject> T displayed(T uiObject) {
        initializer.initialize(uiObject);
        return uiObject;
    }

    public <T extends Page> T open(Class<T> page) {
        return open(stepLibraryFactory.instatiate(page));
    }

    public <T extends Page> T open(T page) {
        getDriver().get(page.getUrl().toString());
        initializer.initialize(page);
        return page;
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

    public void click(WrapsElement element) {

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
    }

    public void clickOnPoint(WrapsElement element, int x, int y) {

        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element.getWrappedElement(), x, y).click().build().perform();

        } catch (Exception ex) {
            throw new AssertionError("Cannot click " + element + "on point (" + x + "; " + y + ") !\n" + ex);
        }
    }

    public void moveMouseOver(WrapsElement element) {

        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element.getWrappedElement()).build().perform();
        } catch (Exception ex) {
            throw new AssertionError("Cannot move mouse over " + element + "!\n" + ex);
        }
    }

    public void typeInto(WrapsElement input, CharSequence... keys) {

        try {
            input.getWrappedElement().sendKeys(keys);
        } catch (Exception ex) {
            throw new AssertionError("Cannot type " + Arrays.toString(keys) + " into " + input + "!\n" + ex);
        }
    }

    public void clear(WrapsElement input) {

        try {
            input.getWrappedElement().clear();
        } catch (Exception ex) {
            throw new AssertionError("Cannot clear " + input + "!\n" + ex);
        }
    }

    public void enterInto(WrapsElement input, CharSequence... text) {

        try {
            input.getWrappedElement().clear();
            input.getWrappedElement().sendKeys(text);
        } catch (Exception ex) {
            throw new AssertionError("Cannot enter " + Arrays.toString(text) + " into " + input + "!\n" + ex);
        }
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

    public PageFactory getStepLibraryFactory() {
        return stepLibraryFactory;
    }

    public Initializer getInitializer() {
        return initializer;
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
