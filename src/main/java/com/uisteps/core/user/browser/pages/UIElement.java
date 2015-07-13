package com.uisteps.core.user.browser.pages;

import com.uisteps.core.then.Then;
import com.uisteps.core.user.browser.Browser;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 *
 * @author ASolyankin
 */
public class UIElement extends TypifiedElement implements UIObject {

    protected final Browser browser;

    public UIElement(WebElement wrappedElement, Browser browser) {
        super(wrappedElement);
        this.browser = browser;
    }
    
    public Object click() {
        browser.click(this);
        return null;
    }

    public Object moveMouseOver() {
        browser.moveMouseOver(this);
        return null;
    }

    public Object clickOnPoint(int x, int y) {
        browser.clickOnPoint(this, x, y);
        return null;
    }
    
    protected <T extends UIObject> Then<T> then(Class<T> uiObject) {
        return browser.then(uiObject);
    }
    
    protected <T> Then<T> then(T value) {
        return browser.then(value);
    }
    
    protected <T extends UIObject> T displayed(Class<T> uiObject) {
        return browser.displayed(uiObject);
    }

    protected <T extends UIObject> T displayed(T uiObject) {
        return browser.displayed(uiObject);
    }
    
    protected <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return browser.onDisplayed(uiObject);
    }

    protected <T extends UIObject> T onDisplayed(T uiObject) {
        return browser.onDisplayed(uiObject);
    }
    
    protected Browser inOpenedBrowser() {
        return browser;
    }
}
