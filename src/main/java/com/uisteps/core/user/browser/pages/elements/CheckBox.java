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
package com.uisteps.core.user.browser.pages.elements;

import com.uisteps.core.user.browser.Browser;
import com.uisteps.core.user.browser.pages.UIElement;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class CheckBox extends UIElement {

    private final ru.yandex.qatools.htmlelements.element.CheckBox wrappedCheckBox;
    
    public CheckBox(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
        wrappedCheckBox = new ru.yandex.qatools.htmlelements.element.CheckBox(wrappedElement);
    }

    public String getLabelText() {
        return wrappedCheckBox.getLabelText();
    }

    public String getText() {
        return wrappedCheckBox.getText();
    }

    public Object select() {
        browser.select(this);
        return null;
    }

    public Object deselect() {
        browser.deselect(this);
        return null;
    }

    public ru.yandex.qatools.htmlelements.element.CheckBox getWrappedCheckBox() {
        return wrappedCheckBox;
    }

}
