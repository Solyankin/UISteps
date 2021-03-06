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
package com.uisteps.core.user.browser.pages;

import java.lang.annotation.Annotation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASolyankin
 */
public abstract class UrlFactory {

    protected final String HOST;
    protected final Class<? extends Annotation> urlAnnotation;

    public UrlFactory(String HOST, Class<? extends Annotation> urlAnnotation) {
        this.HOST = HOST;
        this.urlAnnotation = urlAnnotation;
    }


    public UrlFactory(Class<? extends Annotation> urlAnnotation) {
        this("#HOST", urlAnnotation);
    }
    

    public Url getUrlOf(Class<? extends Page> pageClass) {
        Url url = new Url();
        
        if (url.getHost().equals("")) {
            url.setHost(getBaseUrl());
        }
        
        getUrlOf(url, getPageClass(pageClass));
        return url;
    }

    private void getUrlOf(Url url, Class<?> clazz) {
        
   
        
        if (!RootAnalizer.isRoot(clazz)) {
            getUrlOf(url, clazz.getSuperclass());
        }
        
        if (clazz.isAnnotationPresent(urlAnnotation)) {
            String defaultUrl = getPageUrlFrom(clazz.getAnnotation(urlAnnotation));
            
            if (defaultUrl.contains(HOST)) {
                Pattern pattern = Pattern.compile("(.*)" + HOST + "(.*)");
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

    protected Class<?> getPageClass(Class<?> clazz) {
        
        if (clazz.getName().contains("$$")) {
            return getPageClass(clazz.getSuperclass());
        } else {
            return clazz;
        }
    }
    
    protected abstract String getBaseUrl();
    
    protected abstract String getPageUrlFrom(Annotation urlAnnotation);
}

