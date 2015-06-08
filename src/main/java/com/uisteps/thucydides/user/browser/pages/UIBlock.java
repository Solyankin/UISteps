package com.uisteps.thucydides.user.browser.pages;

import com.uisteps.core.user.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.NameConvertor;

/**
 *
 * @author ASolyankin
 */
public class UIBlock extends com.uisteps.core.user.browser.pages.UIBlock {

  
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
