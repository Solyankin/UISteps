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
package com.uisteps.thucydides.browser;

import com.uisteps.core.browser.RootAnalizer;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.core.browser.Url;
import com.uisteps.core.browser.Page;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.thucydides.core.annotations.DefaultUrl;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesUrlFactory implements com.uisteps.core.browser.UrlFactory {

    @Override
    public Url getUrlOf(Class<? extends Page> pageClass) {
        Url url = new Url();
        
        if (url.getHost().equals("")) {
            String baseUrl = ThucydidesUtils.getBaseUrl();
            url.setHost(baseUrl);
        }
        
        getUrlOf(url, getPageClass(pageClass));
        return url;
    }

    private void getUrlOf(Url url, Class<?> clazz) {
        
        if (!RootAnalizer.isRoot(clazz)) {
            getUrlOf(url, clazz.getSuperclass());
        }
        
        if (clazz.isAnnotationPresent(DefaultUrl.class)) {
            String defaultUrl = clazz.getAnnotation(DefaultUrl.class).value();
            
            if (defaultUrl.contains("#HOST")) {
                Pattern pattern = Pattern.compile("(.*)#HOST(.*)");
                Matcher matcher = pattern.matcher(defaultUrl);
                
                if (matcher.find()) {
                    String prefix = matcher.group(1);
                    String postfix = matcher.group(2);
                    
                    if (prefix != null) {
                        url.prependPrefix(prefix);
                    }
                    
                    if (postfix != null) {
                        url.appendPostfix(postfix);
                    }
                }
            } else {
                url.appendPostfix(defaultUrl);
            }
        }
    }

    private Class<?> getPageClass(Class<?> clazz) {
        
        if (clazz.getName().contains("$$")) {
            return getPageClass(clazz.getSuperclass());
        } else {
            return clazz;
        }
    }
}
