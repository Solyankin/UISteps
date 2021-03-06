package com.uisteps.thucydides.user.browser.pages.elements;

import com.uisteps.core.user.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class TextBlock extends com.uisteps.core.user.browser.pages.elements.TextBlock {

    public TextBlock(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public TextBlock(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
