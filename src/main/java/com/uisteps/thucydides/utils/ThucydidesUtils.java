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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import net.thucydides.core.Thucydides;
import net.thucydides.core.ThucydidesSystemProperties;
import net.thucydides.core.ThucydidesSystemProperty;
import net.thucydides.core.guice.Injectors;
import net.thucydides.core.steps.BaseStepListener;
import net.thucydides.core.steps.StepEventBus;
import net.thucydides.core.steps.StepFactory;
import net.thucydides.core.steps.StepListener;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.webdriver.Configuration;
import net.thucydides.core.webdriver.SupportedWebDriver;
import net.thucydides.core.webdriver.ThucydidesWebdriverManager;
import net.thucydides.core.webdriver.WebDriverFacade;
import net.thucydides.core.webdriver.WebDriverFactory;
import net.thucydides.core.webdriver.WebdriverInstances;
import net.thucydides.core.webdriver.WebdriverManager;
import net.thucydides.core.webdriver.WebdriverProxyFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Asolyankin
 */
public class ThucydidesUtils extends Thucydides {

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
        getCurrentSession().put(key, value);
    }

    public static Object getFromSession(String key) {
        return getCurrentSession().get(key);
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
        
        WebdriverManager webdriverManager = getWebdriverManager();

        String driverName = "#" + (++driverCounter);
        String driverType = getConfiguration().getDriverType().name().toLowerCase();
        
        WebDriver driver = webdriverManager.getWebdriver(driverType);
        
        getDrivers().registerDriverCalled(driverName).forDriver(driver);
        getBaseStepListener().setDriver(driver);
        getDriversMap().remove(driverType);
        return driver;
    }

    public static Map<String, WebDriver> getDriversMap() {
        String fieldName = "driverMap";

        try {
            Field configurationField = WebdriverInstances.class.getDeclaredField(fieldName);
            configurationField.setAccessible(true);
            return (Map<String, WebDriver>) configurationField.get(getDrivers());
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException ex) {
            throw new RuntimeException("Cannot get field by name " + fieldName + " in class " + WebdriverInstances.class + "!\nCause: " + ex);
        }
    }

    public static void removeMockDriver() {
        String fieldName = "mockDriver";

        try {
            Field field = WebdriverProxyFactory.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(WebdriverProxyFactory.getFactory(), null);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException ex) {
            throw new RuntimeException("Cannot get field by name " + fieldName + " in class " + WebdriverProxyFactory.class + "!\nCause: " + ex);
        }
    }
    
    
    public static WebdriverInstances getDrivers() {
        Field webdriverInstancesThreadLocalField = null;
        String fieldName = "webdriverInstancesThreadLocal";

        try {
            webdriverInstancesThreadLocalField = ThucydidesWebdriverManager.class.getDeclaredField(fieldName);
            webdriverInstancesThreadLocalField.setAccessible(true);
        } catch (NoSuchFieldException ex) {
            throw new RuntimeException("Cannot get field by name " + fieldName + " in class " + ThucydidesWebdriverManager.class + "!\nCause: " + ex);
        }

        try {
            return ((ThreadLocal<WebdriverInstances>) webdriverInstancesThreadLocalField.get(null)).get();
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException("Cannot get drivers!\nCause: " + ex);
        }
    }

    
    public static BaseStepListener getBaseStepListener() {
        String methodName = "getBaseStepListener";

        try {
            Method method = StepEventBus.class.getDeclaredMethod(methodName);
            method.setAccessible(true);
            return (BaseStepListener) method.invoke(StepEventBus.getEventBus());
        } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException ex) {
            throw new RuntimeException("Cannot invoke method by name " + methodName + " in class " + StepEventBus.class + "!\nCause: " + ex);
        }
    }
    
    
    public static WebdriverManager getWebdriverManager() {
        WebdriverManager webdriverManager = null;
        String methodName = "getWebdriverManager";

        try {
            Method getWebdriverManagerMethod = Thucydides.class.getDeclaredMethod(methodName);
            getWebdriverManagerMethod.setAccessible(true);
            webdriverManager = (WebdriverManager) getWebdriverManagerMethod.invoke(null);
        } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException ex) {
            throw new RuntimeException("Cannot invoke method by name " + methodName + " in class " + Thucydides.class + "!\nCause: " + ex);
        }

        if (webdriverManager == null) {
            webdriverManager = Injectors.getInjector().getInstance(ThucydidesWebdriverManager.class);
        }
        return webdriverManager;
    }
}