/*
 * Copyright 2015 ASolyankin.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uisteps.core.user.browser.pages.elements.select;

/**
 *
 * @author ASolyankin
 */
public class Option {
    
    private String name = "option";
    private final Select select;
    private final ru.yandex.qatools.htmlelements.element.Select wrappedSelect;
    private final String value;
    private final By by;

    public Option(Select select, String value, By by) {
        this.select = select;
        wrappedSelect = select.getWrappedSelect();
        this.value = value;
        this.by = by;
    }

    public Option(Select select, Integer index) {
        this(select, index.toString(), By.INDEX);
    }
    
    public Object select() {

        try {
            switch (by) {
                case VISIBLE_VALUE:
                    wrappedSelect.selectByVisibleText(value);
                    break;
                case VALUE:
                    wrappedSelect.selectByValue(value);
                    break;
                case INDEX:
                    wrappedSelect.selectByIndex(Integer.parseInt(value));
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception ex) {
            throw new AssertionError("Cannot select by " + by.name().toLowerCase().replace("_", " ") + " " + value + " in " + select + "\nCause:" + ex);
        }
        return null;
    }
    
    public Object deselect() {

        try {
            switch (by) {
                case VISIBLE_VALUE:
                    wrappedSelect.deselectByVisibleText(value);
                    break;
                case VALUE:
                    wrappedSelect.deselectByValue(value);
                    break;
                case INDEX:
                    wrappedSelect.deselectByIndex(Integer.parseInt(value));
                    break;
                default:
                    throw new Exception();
            }
        } catch (Exception ex) {
            throw new AssertionError("Cannot deselect by " + by.name().toLowerCase().replace("_", " ") + " " + value + " in " + select + "\nCause:" + ex);
        }
        return null;
    }
    

    public static enum By {

        VALUE, VISIBLE_VALUE, INDEX;
    }

    public By selectBy() {
        return by;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        
        builder
                .append(getName())
                .append(" with ")
                .append(by.name().toLowerCase().replace("_", " "))
                .append(" ")
                .append(value)
                .append(" from ")
                .append(select);

        return builder.toString();
    }

}
