
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
package com.uisteps.thucydides.elements;

import com.uisteps.core.Browser;
import com.uisteps.core.Url;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.UrlFactory;
import com.uisteps.thucydides.NameConvertor;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author ASolyankin
 */
public class Page implements com.uisteps.core.Page {

    private final Browser browser;
    private final UrlFactory urlFactory;
    private Url url;

    public Page() {
        urlFactory = new UrlFactory();
        url = urlFactory.getDefaultUrlOfPage(this.getClass());
        browser = (Browser) ThucydidesUtils.getFromSession("#BROWSER#");
    }

    @Override
    public Url getDefaultUrl() {
        return urlFactory.getDefaultUrlOfPage(this.getClass());
    }

    @Override
    public Url getUrl() {
        return url;
    }

    @Override
    public void setUrl(Url url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return NameConvertor.humanize(getClass())
                .replace("dot", "\\.")
                + " by url <a href='" + getUrl() + "'>" + getUrl() + "</a>";
    }

    public <T extends Page> T onOpened(Class<T> pageClass) {
        return browser.onOpened(pageClass);
    }

    public <T extends UIBlock> T onDisplayed(Class<T> blockClass) {
        return browser.onDisplayed(blockClass);
    }
    
    public <T extends UIBlock> T onDisplayed(T block) {
        return browser.onDisplayed(block);
    }
    
    public WebDriver getDriver() {
        return browser.getDriver();
    }

    public Object executeScript(String script) {
        return browser.executeScript(script);
    }

    @Override
    public boolean isOnThisPage() {
        return true;
    }
}
