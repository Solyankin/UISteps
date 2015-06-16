package com.uisteps.thucydides.run.listeners;

import com.uisteps.thucydides.verify.ThucydidesVerify;

/**
 *
 * @author m.prytkova
 */
public class StoryListener extends ThucydidesListener {
    
    private final com.uisteps.thucydides.run.Story story;

    public StoryListener(com.uisteps.thucydides.run.Story story) {
        this.story = story;
    }

    public com.uisteps.thucydides.run.Story getStory() {
        return story;
    }
    
    @Override
    protected ThucydidesVerify verify() {
        return getStory().verify();
    }

}
