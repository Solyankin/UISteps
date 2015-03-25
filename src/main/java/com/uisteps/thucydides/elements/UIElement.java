package com.uisteps.thucydides.elements;


import com.uisteps.thucydides.ThucydidesBrowser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 *
 * @author ASolyankin
 */
public class UIElement extends TypifiedElement {

    protected final ThucydidesBrowser browser;

    public UIElement(WebElement wrappedElement) {
        super(wrappedElement);
        browser = (ThucydidesBrowser) ThucydidesUtils.getFromSession("#BROWSER#");
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

    public <T extends Page> T onOpened(Class<T> pageClass) {
        return browser.onOpened(pageClass);
    }
    
    public <T extends UIBlock> T onDisplayed(Class<T> blockClass) {
        return browser.onDisplayed(blockClass);
    }
    
    public WebDriver getDriver() {
        return browser.getDriver();
    }
}
