package com.uisteps.thucydides.user.browser.pages.elements;

import com.uisteps.core.user.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class Button extends com.uisteps.core.user.browser.pages.elements.Button {

    public Button(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public Button(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }

}
