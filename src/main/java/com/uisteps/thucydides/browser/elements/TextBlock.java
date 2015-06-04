package com.uisteps.thucydides.browser.elements;

import com.uisteps.core.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class TextBlock extends com.uisteps.core.browser.elements.TextBlock {

    public TextBlock(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public TextBlock(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
