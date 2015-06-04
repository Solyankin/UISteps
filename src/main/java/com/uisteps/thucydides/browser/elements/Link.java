package com.uisteps.thucydides.browser.elements;

import com.uisteps.core.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class Link extends com.uisteps.core.browser.elements.Link {

    public Link(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public Link(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }

}
