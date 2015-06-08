package com.uisteps.thucydides.user.browser.pages;


import com.uisteps.core.user.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class UIElement extends com.uisteps.core.user.browser.pages.UIElement {


    public UIElement(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public UIElement(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
