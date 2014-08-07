package com.livejournal.uisteps.thucydides.elements;

import com.livejournal.uisteps.core.UIContainer;
import com.livejournal.uisteps.thucydides.ThucydidesUtils;
import com.livejournal.uisteps.thucydides.UIActions;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

/**
 *
 * @author ASolyankin
 */
public class UIElement extends TypifiedElement {

    private final UIActions actions;

    public UIElement(WebElement wrappedElement) {
        super(wrappedElement);
        actions = (UIActions) ThucydidesUtils.getFromSession("#UI_ACTIONS");
    }

    protected <T extends UIContainer> T on(Class<T> UIContainerClass) {
        return actions.on(UIContainerClass);
    }

    protected <T extends UIContainer> T on(T uiContainer) {
        return actions.on(uiContainer);
    }

    public Object click() {
        actions.click(this);
        return null;
    }

    public Object moveMouseOver(){
        actions.moveMouseOver(this);
        return this;
    }

    protected UIActions getActions() {
        return actions;
    }
}
