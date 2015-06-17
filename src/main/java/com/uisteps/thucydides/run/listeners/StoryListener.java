package com.uisteps.thucydides.run.listeners;

import com.uisteps.thucydides.run.Story;
import com.uisteps.thucydides.verify.ThucydidesVerify;

/**
 *
 * @author m.prytkova
 */
public class StoryListener extends ThucydidesListener {
    
    private com.uisteps.thucydides.run.Story story;

    public void setStory(Story story) {
        this.story = story;
    }

    public com.uisteps.thucydides.run.Story getStory() {
        
        if(story == null) {
            throw new AssertionError("Story is not set in listener");
        }
        return story;
    }
    
    @Override
    protected ThucydidesVerify verify() {
        return getStory().verify();
    }

}
