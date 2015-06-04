package com.uisteps.thucydides.browser.elements;

import com.uisteps.core.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class TextField extends com.uisteps.core.browser.elements.TextField {

    public TextField(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public TextField(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
