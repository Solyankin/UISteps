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
package com.uisteps.thucydides.elements;

import com.uisteps.core.browsers.Url;
import java.net.MalformedURLException;

/**
 *
 * @author ASolyankin
 */
public class MockPage extends Page {

    public MockPage(Url url) {
        super();
        this.setUrl(url);
    }

    public MockPage(String url) throws MalformedURLException {
        super();
        this.setUrl(new Url(url));
    }
    
    @Override
    public String toString() {
        return super.toString().replace(getName(), "page");
    }

}
