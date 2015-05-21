package com.uisteps.thucydides.elements;

import com.uisteps.core.browsers.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class Button extends com.uisteps.core.elements.Button {

    public Button(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public Button(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }

}
