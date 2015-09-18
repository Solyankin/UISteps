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
package com.uisteps.thucydides.user.browser;

import com.uisteps.thucydides.user.browser.pages.ThucydidesUIObjectFactory;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.core.user.browser.Browser;
import com.uisteps.core.user.browser.pages.UIObjectInitializer;
import com.uisteps.core.user.browser.pages.MockPage;
import com.uisteps.core.user.browser.pages.UIObjectFactory;
import com.uisteps.core.user.browser.pages.Page;
import com.uisteps.core.user.browser.pages.UIObject;
import com.uisteps.core.user.browser.pages.elements.CheckBox;
import com.uisteps.core.user.browser.pages.elements.FileInput;
import com.uisteps.core.user.browser.pages.elements.radio.RadioButton;
import com.uisteps.core.user.browser.pages.elements.select.Option;
import com.uisteps.core.user.browser.pages.elements.Select;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.internal.WrapsElement;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesBrowser extends Browser {

    public ThucydidesBrowser() {
        super(ThucydidesUtils.getImplementTimeout(), null, new ThucydidesUIObjectFactory());
    }

    public ThucydidesBrowser(long timeOutInSeconds, WebDriver driver, UIObjectFactory uiObjectFactory, UIObjectInitializer initializer) {
        super(timeOutInSeconds, driver, uiObjectFactory, initializer);
    }

    @Step
    @Override
    public void enterInto(WrapsElement input, String text) {
        super.enterInto(input, text);
    }

    @Step
    @Override
    public void clear(WrapsElement input) {
        super.clear(input);
    }

    @Step
    @Override
    public void typeInto(WrapsElement input, String text) {
        super.typeInto(input, text);
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

    @Step
    @Override
    protected Page open(MockPage mock) {
        return super.open(mock);
    }

    @Step
    @Override
    public <T extends UIObject> T onDisplayed(T uiObject) {
        return super.onDisplayed(uiObject);
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

    //Select
    @Step
    @Override
    public void select(Option option) {
        super.select(option);
    }

    @Step
    @Override
    public void deselectAllValuesFrom(Select select) {
        super.deselectAllValuesFrom(select);
    }

    @Step
    @Override
    public void deselect(Option option) {
        super.deselect(option);
    }

    //Radio button
    @Step
    @Override
    public void select(RadioButton button) {
        super.select(button);
    }

    //CheckBox
    
    @Step
    @Override
    public void select(CheckBox checkBox) {
        checkBox.getWrappedCheckBox().select();
    }

    @Step
    @Override
    public void deselect(CheckBox checkBox) {
        checkBox.getWrappedCheckBox().deselect();
    }
    
    //FileInput
    
    @Step
    @Override
    public void setTo(FileInput fileInput, String filePath) {
        fileInput.getWrappedFileInput().setFileToUpload(filePath);
    }
}
