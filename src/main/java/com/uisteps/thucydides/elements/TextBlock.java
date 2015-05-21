package com.uisteps.thucydides.elements;

import com.uisteps.core.browsers.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class TextBlock extends com.uisteps.core.elements.TextBlock {

    public TextBlock(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public TextBlock(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
