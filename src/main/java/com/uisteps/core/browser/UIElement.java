package com.uisteps.core.browser;


import com.uisteps.core.browser.Browser;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 *
 * @author ASolyankin
 */
public class UIElement extends TypifiedElement {

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
    
    protected <T extends UIObject> T displayed(Class<T> uiObject) {
        return browser.displayed(uiObject);
    }

    protected <T extends UIObject> T displayed(T uiObject) {
        return browser.displayed(uiObject);
    }
    
    public Browser getBrowser() {
        return browser;
    }
}
