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

import com.uisteps.thucydides.ThucydidesStepLibraryFactory;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import com.uisteps.core.browser.Browser;
import com.uisteps.core.user.BrowserFactory;
import com.uisteps.core.user.User;
import com.uisteps.core.user.NoBrowserException;
import com.uisteps.thucydides.browser.ThucydidesBrowser;
import net.thucydides.core.annotations.Step;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesUser extends User {

    public ThucydidesUser() {
        this(new ThucydidesStepLibraryFactory(), ThucydidesBrowser.class);
    }

    public ThucydidesUser(BrowserFactory browserFactory, Class<? extends Browser> browser) {
        super(browserFactory, browser);
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
        return openNewBrowser(register(super.openNewBrowser()));
    }

    private Browser register(Browser browser) {
        ThucydidesUtils.putToSession(browser);
        ThucydidesUtils.getBaseStepListener().setDriver(browser.getDriver());
        return browser;
    }

    
    
}
