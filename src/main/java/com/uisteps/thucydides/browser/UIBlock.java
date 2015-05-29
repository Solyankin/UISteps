package com.uisteps.thucydides.browser;

import com.uisteps.core.browser.Browser;
import com.uisteps.thucydides.utils.ThucydidesUtils;
import com.uisteps.thucydides.utils.NameConvertor;

/**
 *
 * @author ASolyankin
 */
public class UIBlock extends com.uisteps.core.browser.UIBlock {

  
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
