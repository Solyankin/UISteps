package com.uisteps.core.elements;


import com.uisteps.core.browsers.Browser;
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
    
    public <T extends UIObject> T onDisplayed(Class<T> uiObject) {
        return browser.onDisplayed(uiObject);
    }
    
    public Browser getBrowser() {
        return browser;
    }
}
