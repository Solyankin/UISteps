package com.uisteps.core.user.browser.pages.elements;

import com.uisteps.core.user.browser.pages.UIElement;
import com.uisteps.core.user.browser.Browser;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class Link extends UIElement {

    public Link(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }

    public String getText() {
        return browser.getTextFrom(this);
    }

}
