package com.uisteps.thucydides.tests;

import com.uisteps.core.browsers.NoBrowserException;
import com.uisteps.core.verifications.Verifications.That;
import com.uisteps.core.elements.UIObject;
import com.uisteps.core.elements.Page;
import com.uisteps.thucydides.browsers.ThucydidesBrowser;
import com.uisteps.thucydides.browsers.ThucydidesBrowsers;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import net.thucydides.core.annotations.Steps;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 *
 * @author ASolyankin
 */
public class WebStoryTest extends SimpleStoryTest {

    @Steps
    ThucydidesBrowsers browserFactory;

    private ThucydidesBrowser getBrowser() {
        return ThucydidesUtils.getCurrentBrowser();
    }

    public <T extends Page> T open(Class<T> pageClass) {
        return getBrowser().open(pageClass);
    }

    public <T extends Page> T open(Class<T> rootClass, String pageName) {
        return getBrowser().open(rootClass, pageName);
    }

    public <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return getBrowser().onDisplayed(uiObject);
    }

    public void openUrl(String url) {
        getBrowser().openUrl(url);
    }

    public String getCurrentUrl() {
        return getBrowser().getCurrentUrl();
    }

    public String getCurrentTitle() {
        return getBrowser().getCurrentTitle();
    }

    public void switchToNextWindow() {
        getBrowser().switchToNextWindow();
    }

    public void switchToPreviousWindow() {
        getBrowser().switchToPreviousWindow();
    }

    public void switchToDefaultWindow() {
        getBrowser().switchToWindowByIndex(0);
    }

    public void switchToWindowByIndex(int index) {
        getBrowser().switchToWindowByIndex(index);
    }

    public void refreshCurrentPage() {
        getBrowser().refreshCurrentPage();
    }

    public void waitUntil(ExpectedCondition<Boolean> condition) {
        getBrowser().waitUntil(condition);
    }

    public Object executeScript(String script) {
        return getBrowser().executeScript(script);
    }

    public boolean isDisplayed(WrapsElement element) {
        return element.getWrappedElement().isDisplayed();
    }

    public boolean isEnabled(WrapsElement element) {
        return element.getWrappedElement().isEnabled();
    }

    public boolean isSelected(WrapsElement element) {
        return element.getWrappedElement().isSelected();
    }

    public void openNewBrowser() {
        browserFactory.openNewBrowser();
    }

    public void openNewWindow() {
        getBrowser().openNewWindow();
    }

    public void switchToNextBrowser() {
        browserFactory.switchToNextBrowser();
    }

    public void switchToPreviousBrowser() {
        browserFactory.switchToPreviousBrowser();
    }

    public void switchToDefaultBrowser() {
        browserFactory.switchToDefaultBrowser();
    }

    public void switchToBrowserByIndex(int index) throws NoBrowserException {
        browserFactory.switchToBrowserByIndex(index);
    }
}
