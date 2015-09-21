
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
package com.uisteps.thucydides.user.browser.pages;

import com.uisteps.core.name.Name;
import com.uisteps.core.user.browser.Browser;
import com.uisteps.core.user.browser.pages.Url;
import com.uisteps.core.user.browser.pages.UrlFactory;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.name.NameConvertor;

/**
 *
 * @author ASolyankin
 */
public class Page extends com.uisteps.core.user.browser.pages.Page {

    public Page() {
        super(new ThucydidesUrlFactory());
    }

    public Page(UrlFactory urlFactory, Name name) {
        super(urlFactory, name);
    }

    public Page(UrlFactory urlFactory) {
        super(urlFactory);
    }

    public Page(Url url, Name name) {
        super(url, name);
    }

    public Page(Url url) {
        super(url);
    }

    @Override
    public Name getName() {

        Name name = super.getName();

        if (name.isDefault()) {
            name.setValue(NameConvertor.humanize(getClass()));
        }

        return name;
    }

    @Override
    public Browser inOpenedBrowser() {
        return ThucydidesUtils.getCurrentBrowser();
    }
}
