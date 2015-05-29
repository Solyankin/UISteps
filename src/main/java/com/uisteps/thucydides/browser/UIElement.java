package com.uisteps.thucydides.browser;


import com.uisteps.core.browser.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class UIElement extends com.uisteps.core.browser.UIElement {


    public UIElement(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public UIElement(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
