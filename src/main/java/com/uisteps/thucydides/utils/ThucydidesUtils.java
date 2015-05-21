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
package com.uisteps.thucydides.utils;

import com.uisteps.thucydides.browsers.ThucydidesBrowser;
import java.lang.reflect.Field;
import net.thucydides.core.Thucydides;
import net.thucydides.core.ThucydidesSystemProperties;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.steps.StepEventBus;
import net.thucydides.core.steps.StepFactory;
import net.thucydides.core.steps.StepListener;
import net.thucydides.core.webdriver.Configuration;
import net.thucydides.core.webdriver.SupportedWebDriver;
import net.thucydides.core.webdriver.ThucydidesWebdriverManager;
import net.thucydides.core.webdriver.WebdriverInstances;
import net.thucydides.core.webdriver.WebdriverProxyFactory;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Asolyankin
 */
public class ThucydidesUtils {

    public static String BROWSER_SESSION_KEY = "#BROWSER#";
    private static int driverCounter = 0;

    public static void putToSession(ThucydidesBrowser browser) {
        putToSession(BROWSER_SESSION_KEY, browser);
    }

    public static ThucydidesBrowser getCurrentBrowser() {
        return (ThucydidesBrowser) getFromSession(BROWSER_SESSION_KEY);
    }

    public static <T> T getNewStepLibrary(Class<T> stepLibraryClass) {
        return getStepFactory().instantiateNewStepLibraryFor(stepLibraryClass);
    }

    public static StepFactory getStepFactory() {
        return new StepFactory();
    }

    public static String getBaseUrl() {
        return getConfiguration().getBaseUrl();
    }

    public static void putToSession(String key, Object value) {
        Thucydides.getCurrentSession().put(key, value);
    }

    public static Object getFromSession(String key) {
        return Thucydides.getCurrentSession().get(key);
    }

    public static SupportedWebDriver getDriverType() {
        return getConfiguration().getDriverType();
    }

    public static void resetDriver(WebDriver driver) {
        WebdriverProxyFactory.resetDriver(driver);
    }

    public static void registerListener(StepListener stepsListener) {
        StepEventBus.getEventBus().registerListener(stepsListener);
    }

    public static Configuration getConfiguration() {
        return Injectors.getInjector().getInstance(Configuration.class);
    }

    public static Integer getImplementTimeout() {
        return getSystemProperties().getIntegerValue(ThucydidesSystemProperty.WEBDRIVER_TIMEOUTS_IMPLICITLYWAIT, 100000);
    }

    public static ThucydidesSystemProperties getSystemProperties() {
        return ThucydidesSystemProperties.getProperties();
    }

    public static Integer getImplementTimeoutInSec() {
        return getImplementTimeout() / 1000;
    }

    public static WebDriver getNewDriver() {
        WebDriver driver = WebdriverProxyFactory.getFactory().proxyDriver();
        getDrivers().registerDriverCalled("#" + (++driverCounter)).forDriver(driver);
        return driver;
    }

    public static WebdriverInstances getDrivers() {
        Field field = null;
        String fieldName = "webdriverInstancesThreadLocal";

        try {
            field = ThucydidesWebdriverManager.class.getDeclaredField(fieldName);
            field.setAccessible(true);
        } catch (NoSuchFieldException | SecurityException ex) {
            throw new RuntimeException("Cannot get field by name " + fieldName + " in class " + ThucydidesWebdriverManager.class + "!\nCause: " + ex);
        }

        try {
            ThreadLocal<WebdriverInstances> drivers = (ThreadLocal<WebdriverInstances>) field.get(null);
            return drivers.get();
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException("Cannot get drivers!\nCause: " + ex);
        }
    }

}
