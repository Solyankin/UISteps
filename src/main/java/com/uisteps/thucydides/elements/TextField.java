package com.uisteps.thucydides.elements;

import com.uisteps.core.browsers.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class TextField extends com.uisteps.core.elements.TextField {

    public TextField(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public TextField(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
