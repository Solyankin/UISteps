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
package com.uisteps.thucydides.user;

import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.core.user.browser.Browser;
import com.uisteps.core.user.browser.BrowserFactory;
import com.uisteps.core.user.User;
import com.uisteps.core.user.browser.NoBrowserException;
import com.uisteps.core.user.Storage;
import com.uisteps.thucydides.user.browser.ThucydidesBrowserFactory;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.webdriver.SupportedWebDriver;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesUser extends User {

    public ThucydidesUser() {
        this(new ThucydidesBrowserFactory(), new ThucydidesStorage());
    }

    public ThucydidesUser(BrowserFactory browserFactory, Storage storage) {
        super(browserFactory, storage);
    }

    @Step
    @Override
    public Browser switchToLastBrowser() {
        return register(super.switchToLastBrowser());
    }

    @Step
    @Override
    public Browser switchToBrowserByIndex(int index) throws NoBrowserException {
        return register(super.switchToBrowserByIndex(index));
    }

    @Step
    @Override
    public Browser switchToDefaultBrowser() {
        return register(super.switchToDefaultBrowser());
    }

    @Step
    @Override
    public Browser switchToPreviousBrowser() {
        return register(super.switchToPreviousBrowser());
    }

    @Step
    @Override
    public Browser switchToNextBrowser() {
        return register(super.switchToNextBrowser());
    }

    @Step
    public Browser openNewBrowser(Browser browser) {
        return register(browser);
    }

    @Override
    public Browser openNewBrowser() {
        return openNewBrowser(super.openNewBrowser());
    }

    public Browser openNewBrowser(String withDriver) {
        return openNewBrowser(SupportedWebDriver.valueOf(withDriver.toUpperCase()));
    }

    public Browser openNewBrowser(SupportedWebDriver withDriver) {
        return openNewBrowser(super.openNewBrowser(ThucydidesUtils.getNewDriver(withDriver)));
    }

    private Browser register(Browser browser) {
        ThucydidesUtils.putToSession(browser);
        ThucydidesUtils.getBaseStepListener().setDriver(browser.getDriver());
        return browser;
    }

}
