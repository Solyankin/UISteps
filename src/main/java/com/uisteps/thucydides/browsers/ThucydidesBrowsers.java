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
package com.uisteps.thucydides.browsers;

import com.uisteps.thucydides.utils.ThucydidesUtils;
import com.uisteps.core.browsers.Browser;
import com.uisteps.core.browsers.BrowserFactory;
import com.uisteps.core.browsers.Browsers;
import com.uisteps.core.browsers.NoBrowserException;
import net.thucydides.core.annotations.Step;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesBrowsers extends Browsers {

    public ThucydidesBrowsers() {
        this(new ThucydidesStepLibraryFactory(), ThucydidesBrowser.class);
    }

    public ThucydidesBrowsers(BrowserFactory getBrowserFactory, Class<? extends Browser> browserClass) {
        super(getBrowserFactory, browserClass);
    }

    @Step
    @Override
    public ThucydidesBrowser switchToLastBrowser() {
        return register(super.switchToLastBrowser());
    }

    @Step
    @Override
    public ThucydidesBrowser switchToBrowserByIndex(int index) throws NoBrowserException {
        return register(super.switchToBrowserByIndex(index));
    }

    @Step
    @Override
    public ThucydidesBrowser switchToDefaultBrowser() {
        return register(super.switchToDefaultBrowser());
    }

    @Step
    @Override
    public ThucydidesBrowser switchToPreviousBrowser() {
        return register(super.switchToPreviousBrowser());
    }

    @Step
    @Override
    public ThucydidesBrowser switchToNextBrowser() {
        return register(super.switchToNextBrowser());
    }

    @Step
    public ThucydidesBrowser openNewBrowser(ThucydidesBrowser browser) {
        return register(browser);
    }

    @Override
    public ThucydidesBrowser openNewBrowser() {
        return openNewBrowser(register(super.openNewBrowser()));
    }

    private ThucydidesBrowser register(Browser browser) {
        ThucydidesBrowser thucydidesBrowser = (ThucydidesBrowser) (browser);
        ThucydidesUtils.putToSession(thucydidesBrowser);
        ThucydidesUtils.getBaseStepListener().setDriver(thucydidesBrowser.getDriver());
        return thucydidesBrowser;
    }

}
