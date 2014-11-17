
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
package com.uisteps.core;

import com.uisteps.thucydides.NameConvertor;
import com.uisteps.thucydides.elements.Link;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.uisteps.thucydides.UrlFactory;

/**
 *
 * @author ASolyankin
 */
public class Browser {

    private Cache cache;
    private WebDriver driver;
    private StepLibraryFactory stepLibraryFactory;
    private Initializer initializer;
    private WindowList windowList;
    private String name;

    public Browser() {
        cache = new Cache();
        windowList = new WindowList(this);
    }

    public void init(WebDriver driver, StepLibraryFactory pageFactory, Initializer initializer) {
        this.driver = driver;
        this.stepLibraryFactory = pageFactory;
        this.initializer = initializer;
    }

    public void init(WebDriver driver, StepLibraryFactory pageFactory, Initializer initializer, Cache cache, WindowList windowList) {
        init(driver, pageFactory, initializer);
        this.cache = cache;
        this.windowList = windowList;
    }

    public Browser(WebDriver driver, StepLibraryFactory pageFactory, Initializer initializer) {
        this();
        init(driver, pageFactory, initializer);
    }

    public Browser(WebDriver driver, StepLibraryFactory pageFactory, Initializer initializer, Cache cache, WindowList windowList) {
        init(driver, pageFactory, initializer, cache, windowList);
    }

    public void openUrl(String url) {
        getDriver().get(url);
    }

    public void open(Url url) {
        getDriver().get(url.toString());
    }

    public <T extends UIBlock> T onDisplayed(Class<T> blockClass) {
        if (cache.contains(blockClass)) {
            return (T) cache.getBlock();
        } else {
            T block = stepLibraryFactory.instatiate(blockClass);
            initializer.initialize(block, this);
            return onDisplayed(block);
        }
    }

    public <T extends UIBlock> T onDisplayed(T block) {
        cache.put(block);
        return block;
    }

    public <T extends Page> T open(Class<T> pageClass) {
        T page = stepLibraryFactory.instatiate(pageClass);
        open(page.getUrl());
        initializer.initialize(page, this);
        cache.put(page);
        return open(page);
    }

    public <T extends Page> T open(Class<T> rootClass, String pageName) {
        Class<?> klass = null;
        try {
            klass = Class.forName(pageName);
        } catch (ClassNotFoundException ex) {
            throw new AssertionError("Cannot find class with such name: " + pageName);
        }
        if (rootClass.isAssignableFrom(klass)) {
            return open((Class<T>) klass);
        }
        throw new AssertionError("" + klass.getName() + " is not assigned from " + rootClass + "!");
    }

    public <T extends Page> T open(Class<T> pageClass, Url url) {
        T page = stepLibraryFactory.instatiate(pageClass);
        if (url != null) {
            Url pageUrl = page.getUrl();
            pageUrl.prependPrefix(url.getPrefix())
                    .appendPostfix(url.getPostfix());
            String urlHost = url.getHost();
            if (!urlHost.equals("")) {
                pageUrl.setHost(urlHost);
            }
            Integer urlPort = url.getPort();
            if (urlPort != -1) {
                pageUrl.setPort(urlPort);
            }
        }
        open(page.getUrl());
        initializer.initialize(page, this);
        cache.put(page);
        return page;
    }

    public <T extends Page> T onOpened(Class<T> rootClass, String pageName) {
        Class<?> klass = null;
        try {
            klass = Class.forName(pageName);
        } catch (ClassNotFoundException ex) {
            throw new AssertionError("Cannot find class with such name: " + pageName);
        }
        if (rootClass.isAssignableFrom(klass)) {
            return onOpened((Class<T>) klass);
        }
        throw new AssertionError("" + klass.getName() + " is not assigned from " + rootClass + "!");
    }

    public <T extends Page> T onOpened(Class<T> pageClass) {
        if (cache.contains(pageClass)) {
            return (T) cache.getPage();
        } else {
            T page = stepLibraryFactory.instatiate(pageClass);
            initializer.initialize(page, this);
            cache.put(page);
            return page;
        }
    }

    public <T extends Page> T open(T page) {
        return page;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean isPage(Object obj) {
        return isPage(obj.getClass());
    }

    public boolean isBlock(Object obj) {
        return isBlock(obj.getClass());
    }

    public
            boolean isPage(Class<?> klass) {
        return Page.class
                .isAssignableFrom(klass);
    }

    public
            boolean isBlock(Class<?> klass) {
        return UIBlock.class
                .isAssignableFrom(klass);
    }

    public String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    public String getCurrentTitle() {
        return getDriver().getTitle();
    }

    public void switchToNextWindow() {
        windowList.switchToNextWindow();
    }

    public void switchToPreviousWindow() {
        windowList.switchToPreviousWindow();
    }

    public void switchToDefaultWindow() {
        windowList.switchToDefaultWindow();
    }

    public void switchToWindowByIndex(int index) {
        windowList.switchToWindowByIndex(index);
    }

    public void refreshCurrentPage() {
        getDriver().navigate().refresh();
    }

    public void setToDefaultState() {
        cache.clear();
        if (windowList.getCountOfWindows() > 1) {
            windowList.switchToDefaultWindow();
        }
        deleteCookies();
    }

    public void deleteCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void click(WrapsElement element) {
        boolean needToSwitch = false;
        try {
            WebElement webElement = element.getWrappedElement();
            if (element instanceof Link) {
                String attrTarget = webElement.getAttribute("target");
                needToSwitch = attrTarget != null && !attrTarget.equals("") && !attrTarget.equals("_self");
            }
            webElement.click();
            if (needToSwitch) {
                switchToNextWindow();
            }
        } catch (Exception ex) {
            throw new AssertionError("Cannot click " + element + "! " + ex);
        }
    }

    public void clickOnPoint(WrapsElement element, int x, int y) {
        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element.getWrappedElement(), x, y).click().build().perform();

        } catch (Exception ex) {
            throw new AssertionError("Cannot click " + element + "on point (" + x + "; " + y + ") !\n" + ex);
        }
    }

    public void moveMouseOver(WrapsElement element) {
        try {
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element.getWrappedElement()).build().perform();
        } catch (Exception ex) {
            throw new AssertionError("Cannot move mouse over " + element + "!\n" + ex);
        }
    }

    public void typeInto(WrapsElement input, CharSequence... keys) {
        try {
            input.getWrappedElement().sendKeys(keys);
        } catch (Exception ex) {
            throw new AssertionError("Cannot type " + Arrays.toString(keys) + " into " + input + "!\n" + ex);
        }
    }

    public void clear(WrapsElement input) {
        try {
            input.getWrappedElement().clear();

        } catch (Exception ex) {
            throw new AssertionError("Cannot clear " + input + "!\n" + ex);
        }
    }

    public void enterInto(WrapsElement input, CharSequence... text) {
        try {
            input.getWrappedElement().clear();
            input.getWrappedElement().sendKeys(text);
        } catch (Exception ex) {
            throw new AssertionError("Cannot enter " + Arrays.toString(text) + " into " + input + "!\n" + ex);
        }
    }

    public String getTextFrom(WrapsElement input) {
        try {
            return input.getWrappedElement().getText();
        } catch (Exception ex) {
            throw new AssertionError("Cannot clear " + input + "\n" + ex);
        }
    }

    public void waitUntil(ExpectedCondition<Boolean> condition) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return condition.apply(driver);
            }
        });
    }

    public StepLibraryFactory getStepLibraryFactory() {
        return stepLibraryFactory;
    }

    public Initializer getInitializer() {
        return initializer;
    }

    public boolean isOn(Class<? extends Page> klass) {
        if (cache.contains(klass)) {
            return true;
        }
        try {
            if (klass.newInstance().isOnThisPage()) {
                return true;
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new AssertionError("Cannot instantiate " + klass + "!\n" + ex);
        }
        UrlFactory urlFactory = new UrlFactory();
        Url pageUrl = urlFactory.getDefaultUrlOfPage((Class<? extends Page>) klass);
        String currentUrl = getCurrentUrl();
        Pattern pattern = Pattern.compile(pageUrl.getProtocol() + "(.*)" + pageUrl.getPrefix() + "(.*)" + pageUrl.getPostfix());
        Matcher matcher = pattern.matcher(currentUrl);
        return matcher.find();
    }

    public Cache getCache() {
        return cache;
    }

    public WindowList getWindowList() {
        return windowList;
    }

    public Object executeScript(String script) {
        return ((JavascriptExecutor) getDriver()).executeScript(script);

    }

    public class Cache {

        private Page page;
        private String pageClass;
        private UIBlock block;
        private String blockClass;

        void put(Page page) {
            clearBlock();
            this.page = page;
            pageClass = NameConvertor.humanize(page.getClass());
        }

        void put(UIBlock block) {
            this.block = block;
            blockClass = NameConvertor.humanize(block.getClass());
        }

        void clear() {
            clearBlock();
            clearPage();
        }

        void clearBlock() {
            block = null;
            blockClass = null;
        }

        void clearPage() {
            page = null;
            pageClass = null;
        }

        Page getPage() {
            return page;
        }

        UIBlock getBlock() {
            return block;
        }

        public boolean contains(Class<?> klass) {
            String klassName = NameConvertor.humanize(klass);
            return klassName.equals(pageClass) || klassName.equals(blockClass);
        }
    }

}
