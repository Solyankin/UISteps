package com.livejournal.uisteps.thucydides;

import com.livejournal.uisteps.core.Browser;
import com.livejournal.uisteps.core.Page;
import com.livejournal.uisteps.core.UIBlock;
import com.livejournal.uisteps.core.Url;
import com.livejournal.uisteps.thucydides.Verifications.That;
import com.livejournal.uisteps.utils.ClassEnumerator;
import net.thucydides.core.annotations.Steps;
import net.thucydides.jbehave.ThucydidesJUnitStory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
public class WebTest extends ThucydidesJUnitStory {

    @Steps
    ThucydidesBrowser browser;
    @Steps
    Verifications verifications;
    
    private final ClassEnumerator classEnumerator = new ClassEnumerator("com.livejournal.uitests.pages");


    public Browser getCurrentBrowser() {
        return browser;
    }

    public <T extends Page> T open(Class<T> pageClass) {
        return browser.open(pageClass);
    }

    public <T extends Page> T open(Class<T> rootClass, String pageName) {
        return browser.open(rootClass, pageName);
    }

    public <T extends Page> T open(Class<T> pageClass, Url url) {
        return browser.open(pageClass, url);
    }

    public <T extends Page> T onOpened(Class<T> pageClass) {
        return browser.onOpened(pageClass);
    }

    public <T extends Page> T onOpened(Class<T> rootClass, String pageName) {
        return browser.onOpened(rootClass, pageName);
    }

    public <T extends UIBlock> T onDisplayed(Class<T> block) {
        return browser.onDisplayed(block);
    }

    public void openUrl(String url) {
        browser.openUrl(url);
    }

    public String getCurrentUrl() {
        return browser.getCurrentUrl();
    }

    public String getCurrentTitle() {
        return browser.getCurrentTitle();
    }

    public void switchToNextWindow() {
        browser.switchToNextWindow();
    }

    public void switchToPreviousWindow() {
        browser.switchToPreviousWindow();
    }

    public void switchToDefaultWindow() {
        browser.switchToWindowByIndex(0);
    }

    public void switchToWindowByIndex(int index) {
        browser.switchToWindowByIndex(index);
    }

    public void refreshCurrentPage() {
        browser.refreshCurrentPage();
    }

    public void waitUntil(ExpectedCondition<Boolean> condition) {
        browser.waitUntil(condition);
    }

    public That verify() {
        return verifications.verify();
    }

    public Object startScript(String script) {
        return browser.startScript(script);
    }
    
    public Class<? extends Page> getPageClassByName(String pageClassName) {
        Class<?> klass = classEnumerator.getClassBySimpleName(pageClassName);
        if(!browser.isPage(klass)) {
            throw new AssertionError("Object by name " + pageClassName + " is not a page!");
        }
        return (Class<? extends Page>) klass;
    }
}
