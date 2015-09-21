package com.uisteps.thucydides.user.browser.pages;

import com.uisteps.core.user.browser.Browser;
import com.uisteps.thucydides.ThucydidesUtils;
import com.uisteps.thucydides.name.NameConvertor;

/**
 *
 * @author ASolyankin
 */
public class UIBlock extends com.uisteps.core.user.browser.pages.UIBlock {

    @Override
    public String toString() {
        return NameConvertor.humanize(getClass());
    }

    @Override
    public Browser inOpenedBrowser() {
        return ThucydidesUtils.getCurrentBrowser();
    }
}
