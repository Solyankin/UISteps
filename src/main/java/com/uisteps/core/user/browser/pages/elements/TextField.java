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

import com.uisteps.core.user.browser.pages.UIElement;
import com.uisteps.core.user.browser.Browser;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class TextField extends UIElement {

    public TextField(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }

    public Object sendKeys(CharSequence... keys) {
        return type(keys);
    }

    public Object type(CharSequence... keys) {
        return browser.typeInto(this, keys);
    }

    public Object clear() {
        return browser.clear(this);
    }

    public Object enter(CharSequence... text) {
        return browser.enterInto(this, text);
    }

    public String getText() {
        return browser.getTextFrom(this);
    }
}
