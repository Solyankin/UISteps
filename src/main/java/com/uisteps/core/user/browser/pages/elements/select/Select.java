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
package com.uisteps.core.user.browser.pages.elements.select;

import com.uisteps.core.user.browser.Browser;
import com.uisteps.core.user.browser.pages.UIElement;
import com.uisteps.core.user.browser.pages.elements.select.Option.By;
import java.util.List;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class Select extends UIElement {

    private final ru.yandex.qatools.htmlelements.element.Select wrappedSelect;

    public Select(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
        wrappedSelect = new ru.yandex.qatools.htmlelements.element.Select(wrappedElement);
    }

    public ru.yandex.qatools.htmlelements.element.Select getWrappedSelect() {
        return wrappedSelect;
    }

    public boolean isMultiple() {
        return wrappedSelect.isMultiple();
    }

    public List<WebElement> getOptions() {
        return wrappedSelect.getOptions();
    }

    public List<WebElement> getAllSelectedOptions() {
        return wrappedSelect.getAllSelectedOptions();
    }

    public WebElement getFirstSelectedOption() {
        return wrappedSelect.getFirstSelectedOption();
    }

    public boolean hasSelectedOption() {
        return wrappedSelect.hasSelectedOption();
    }

    public Object select(Option option) {
        browser.select(option);
        return null;
    }

    public Object select(String value, Option.By by) {
        return select(new Option(this, value, by));
    }

    public Object selectByIndex(int index) {
        return select(new Option(this, index));
    }

    public Object selectByValue(String value) {
        return select(value, By.VALUE);
    }

    public Object selectByVisibleText(String value) {
        return select(value, By.VISIBLE_VALUE);
    }

    public Object deselectAll() {
        browser.deselectAllValuesFrom(this);
        return null;
    }

    public Object deselect(Option option) {
        browser.deselect(option);
        return null;
    }

    public Object deselect(String value, Option.By by) {
        return deselect(new Option(this, value, by));
    }

    public Object deselectByIndex(int index) {
        return deselect(new Option(this, index));
    }

    public Object deselectByValue(String value) {
        return deselect(value, By.VALUE);
    }

    public Object deselectByVisibleText(String value) {
        return deselect(value, By.VISIBLE_VALUE);
    }

}
