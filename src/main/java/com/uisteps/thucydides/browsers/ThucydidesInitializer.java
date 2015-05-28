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
package com.uisteps.thucydides.browsers;

import com.uisteps.core.browsers.Browser;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import net.thucydides.core.annotations.WhenPageOpens;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.loader.HtmlElementLoader;
import com.uisteps.core.browsers.Initializer;
import com.uisteps.core.elements.UIObject;

/**
 *
 * @author ASolyankin
 */
public class ThucydidesInitializer implements Initializer {

    @Override
    public void initialize(UIObject uiObject, Browser browser) {
        HtmlElementLoader.populate(uiObject, browser.getDriver());
        
        WebDriverWait wait = new WebDriverWait(browser.getDriver(), 10);

        try {
            wait.until(new ExpectedCondition<Boolean>() {

                @Override
                public Boolean apply(WebDriver driver) {
                    return uiObject.isDisplayed();
                }
            });
        } catch (Exception ex) {
            throw new AssertionError(uiObject + " is not displayed!\n" + ex);
        }

        callMethodsWhenOpens(uiObject);
    }

    private void callMethodsWhenOpens(Object uiObject) {
        Class<?> fieldClass = uiObject.getClass();

        if (fieldClass.getName().contains("$$")) {
            callWhenUIObjectOpensMethods(uiObject, fieldClass.getSuperclass());
        } else {
            callWhenUIObjectOpensMethods(uiObject, fieldClass);
        }
    }

    private void callWhenUIObjectOpensMethods(Object uiObject, Class<?> clazz) {

        if (!RootAnalizer.isRoot(clazz)) {
            callWhenUIObjectOpensMethods(uiObject, clazz.getSuperclass());
        }

        List<Method> methods = Arrays.asList(clazz.getDeclaredMethods());
        Collections.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method a, Method b) {
                return a.getName().compareTo(b.getName());
            }
        });

        for (Method method : methods) {

            if (method.isAnnotationPresent(WhenPageOpens.class)) {

                try {
                    method.setAccessible(true);
                    method.invoke(uiObject);
                } catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    throw new RuntimeException("Cannot invoke method \"" + method.getName() + "\""
                            + " in page \"" + uiObject + "\""
                            + " annotated by @WhenPageOpens!\n" + ex);
                }
            }
        }
    }
}
