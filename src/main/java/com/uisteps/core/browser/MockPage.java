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
package com.uisteps.core.browser;

/**
 *
 * @author ASolyankin
 */
public class MockPage {
    
    private final Page page;
    
    public MockPage(Class<? extends Page> page, Browser browser) {
        this.page = browser.getPageFactory().instatiate(page);
        open(this.page, browser);
    }
    
    public MockPage(Page page, Browser browser) {
        this.page = page;
        open(page, browser);
    }

    private void open(Page page, Browser browser) {
        browser.getDriver().get(page.getUrl().toString());
    }
    
    @Override
    public String toString() {
        return page.toString();
    }

    public Page getPage() {
        return page;
    }
}