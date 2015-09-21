package com.uisteps.thucydides.user.browser.pages.elements;

import com.uisteps.core.user.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class TextField extends com.uisteps.core.user.browser.pages.elements.TextField {

    public TextField(WebElement wrappedElement) {
        super(wrappedElement);
    }

    @Override
    public Browser inOpenedBrowser() {
        return ThucydidesUtils.getCurrentBrowser();
    }
}
