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

import com.uisteps.core.browser.Browser;
import com.uisteps.core.browser.UIObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;

/**
 *
 * @author ASolyankin
 */
public class Initializer {

    private final WebDriver driver;
    private final long timeOutInSeconds;

    public Initializer(WebDriver driver, long timeOutInSeconds) {
        this.driver = driver;
        this.timeOutInSeconds = timeOutInSeconds;
    }

    public void initialize(UIObject uiObject) {
        HtmlElementLoader.populate(uiObject, driver);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        
        wait.until(new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return uiObject.isDisplayed();
            }
        });
    }
}
