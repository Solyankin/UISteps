package com.uisteps.core.browser.elements;

import com.uisteps.core.browser.UIElement;
import com.uisteps.core.browser.Browser;
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
