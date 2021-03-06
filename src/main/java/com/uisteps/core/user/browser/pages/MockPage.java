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
package com.uisteps.core.user.browser.pages;

import com.uisteps.core.user.browser.Browser;

/**
 *
 * @author ASolyankin
 * @param <T>
 */
public class MockPage<T extends Page> {
    
    private final T page;
    
    public MockPage(Class<T> page, Browser browser) {
        this.page = browser.getUIObjectFactory().instatiate(page);
        open(this.page, browser);
    }
    
    public MockPage(T page, Browser browser) {
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

    public T getPage() {
        return page;
    }
}
