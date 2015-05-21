package com.uisteps.thucydides.elements;

import com.uisteps.core.browsers.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class Link extends com.uisteps.core.elements.Link {

    public Link(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public Link(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }

}
