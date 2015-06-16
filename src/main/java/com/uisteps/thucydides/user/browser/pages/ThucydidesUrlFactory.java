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

import com.uisteps.core.user.browser.pages.UrlFactory;
import com.uisteps.core.user.browser.pages.RootAnalizer;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.core.user.browser.pages.Url;
import com.uisteps.core.user.browser.pages.Page;
import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.thucydides.core.annotations.DefaultUrl;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesUrlFactory extends UrlFactory {
    
    

    public ThucydidesUrlFactory() {
        super(DefaultUrl.class);
    }
    
    public ThucydidesUrlFactory(String host, Class<? extends Annotation> urlAnnotation) {
        super(host, urlAnnotation);
    }

    @Override
    protected Class<?> getPageClass(Class<?> clazz) {
        
        if (clazz.getName().contains("$$")) {
            return getPageClass(clazz.getSuperclass());
        } else {
            return clazz;
        }
    }

    @Override
    protected String getBaseUrl() {
        return ThucydidesUtils.getBaseUrl();
    }

    @Override
    protected String getPageUrlFrom(Annotation urlAnnotation) {
        return ((DefaultUrl) urlAnnotation).value();
    }
}
