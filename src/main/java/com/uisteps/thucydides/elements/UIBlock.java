package com.uisteps.thucydides.elements;

import com.uisteps.core.browsers.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import com.uisteps.thucydides.utils.NameConvertor;
import com.uisteps.thucydides.browsers.ThucydidesBrowser;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author ASolyankin
 */
public class UIBlock extends com.uisteps.core.elements.UIBlock {

  
    public UIBlock() {
       super(ThucydidesUtils.getCurrentBrowser());
    }

    public UIBlock(Browser browser) {
        super(browser);
    }
    
    @Override
    public String toString() {
        return NameConvertor.humanize(getClass());
    }
}
