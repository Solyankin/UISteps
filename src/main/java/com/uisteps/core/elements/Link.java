package com.uisteps.core.elements;

import com.uisteps.core.browsers.Browser;
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
