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
import com.uisteps.core.user.browser.pages.elements.CheckBox;
import com.uisteps.core.user.browser.pages.elements.FileInput;
import com.uisteps.core.user.browser.pages.elements.radio.RadioButton;
import com.uisteps.core.user.browser.pages.elements.select.Select;
import com.uisteps.core.user.browser.pages.elements.select.Option;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertTrue;
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
        return onDisplayed(displayed(uiObject));
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

    public void typeInto(WrapsElement input, String text) {

        try {
            input.getWrappedElement().sendKeys(text);
        } catch (Exception ex) {
            throw new AssertionError("Cannot type " + text + " into " + input + "!\n" + ex);
        }
    }

    public void clear(WrapsElement input) {

        try {
            input.getWrappedElement().clear();
        } catch (Exception ex) {
            throw new AssertionError("Cannot clear " + input + "!\n" + ex);
        }
    }

    public void enterInto(WrapsElement input, String text) {

        try {
            clear(input);
            typeInto(input, text);
        } catch (Exception ex) {
            throw new AssertionError("Cannot enter " + text + " into " + input + "!" + ex);
        }
    }

    public String getTextFrom(WrapsElement input) {
        try {
            if ("textarea".equals(input.getWrappedElement().getTagName())) {
                return input.getWrappedElement().getText();
            }

            String enteredText = input.getWrappedElement().getAttribute("value");
            if (enteredText == null) {
                return "";
            }
            return enteredText;
        } catch (Exception ex) {
            throw new AssertionError("Cannot get text from " + input + "\n" + ex);
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

    public <T extends UIObject> Then<T> then(Class<T> uiObject) {
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

    //Select
    
    public void select(Option option) {
        option.select();
    }

    public void deselectAllValuesFrom(Select select) {

        try {
            select.getWrappedSelect().deselectAll();
        } catch (Exception ex) {
            throw new AssertionError("Cannot deselect all values in " + select + "\nCause:" + ex);
        }
    }

    public void deselect(Option option) {
        option.deselect();
    }
    
    //Radio button
    
    public void select(RadioButton button) {
        try {
            if(!button.isSelected()) {
                button.click();
            }
        } catch (Exception ex) {
            throw new AssertionError("Cannot select radio button " + button + "\nCause:" + ex);
        }
    }
    
    //CheckBox
    
    public void select(CheckBox checkBox) {
        checkBox.getWrappedCheckBox().select();
    }

    public void deselect(CheckBox checkBox) {
        checkBox.getWrappedCheckBox().deselect();
    }
    
    //FileInput
    
    public void setTo(FileInput fileInput, String filePath) {
        fileInput.getWrappedFileInput().setFileToUpload(filePath);
    }
    
}
