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
        return browser.click(this);
    }

    public Object moveMouseOver() {
        return browser.moveMouseOver(this);
    }

    public Object clickOnPoint(int x, int y) {
        return browser.clickOnPoint(this, x, y);
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
