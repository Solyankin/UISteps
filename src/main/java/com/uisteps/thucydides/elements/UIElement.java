package com.uisteps.thucydides.elements;


import com.uisteps.core.browsers.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 *
 * @author ASolyankin
 */
public class UIElement extends com.uisteps.core.elements.UIElement {


    public UIElement(WebElement wrappedElement) {
        super(wrappedElement, ThucydidesUtils.getCurrentBrowser());
    }

    public UIElement(WebElement wrappedElement, Browser browser) {
        super(wrappedElement, browser);
    }
}
