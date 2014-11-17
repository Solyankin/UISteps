package com.uisteps.thucydides.elements;

import com.uisteps.core.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.NameConvertor;
import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

/**
 *
 * @author ASolyankin
 */
public class UIBlock extends HtmlElement implements com.uisteps.core.UIBlock {

    protected final Browser browser;

    public UIBlock() {
        browser = (Browser) ThucydidesUtils.getFromSession("#BROWSER#");
    }

    @Override
    public void click() {
        browser.click(this);
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
    
    public <T extends UIBlock> T onDisplayed(T block) {
        return browser.onDisplayed(block);
    }
    
    
    public WebDriver getDriver() {
        return browser.getDriver();
    }

    @Override
    public String toString() {
        return NameConvertor.humanize(getClass());
    }

    public Object executeScript(String script) {
        return browser.executeScript(script);
    }
}
